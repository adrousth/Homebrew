package persistence;

import entities.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.*;

/**
 * @author Alex
 *         5/25/2016
 */
public class OrderDao extends DataAccessObject {

    public int createNewOrder(Order order) {
        setType(Order.class);
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        int id = 0;
        try {
            transaction = session.beginTransaction();
            if (persistRecord(order)) {
                id = order.getOrderId();
                setType(Asset.class);
                for (OrderItem orderItem : order.getOrderItems()) {
                    Asset asset = orderItem.getAsset();
                    asset.setCurrentStock(asset.getCurrentStock() - orderItem.getQuantity());
                    updateRecord(asset);
                }
            }

            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction!=null) transaction.rollback();
            log.error(ex);
            id = -1;
        } finally {
            session.close();
        }
        return id;
    }

    public void updateOrder(Order order) {
        order.setUpdatedAt(new Date());
        updateRecord(order);
    }

    public void method() {

    }

    public OrderResults orderFromWebForm(Map<String, String> webOrder, String memberEmail, String type) {
        setType(Asset.class);
        List<OrderItem> orderItems = new ArrayList<>();
        MemberDao memberDao = new MemberDao();
        OrderResults results = new OrderResults();
        results.setType("");
        Order order = new Order();

        if (memberEmail == null) {
            results.addMessage("Must be logged in to place an order");
            results.setType("Error");
            return results;
        }
        int memberId = memberDao.getMemberByEmail(memberEmail).getMemberId();
        float total = 0;

        for (Map.Entry<String, String> item : webOrder.entrySet()) {

            try {
                if (!item.getKey().equals("")) {
                    int id = Integer.parseInt(item.getKey());
                    float quantity = (float) (Math.round(Float.parseFloat(item.getValue()) * 2) / 2.0);
                    total += quantity;

                    Asset asset = (Asset) getRecordById(id);
                    if (asset == null || !asset.getType().equals(type)) {
                        results.setType("Error");
                        results.addMessage("Invalid item in order");
                        return results;
                    } else if (asset.getCurrentStock() < quantity) {
                        if (asset.getCurrentStock() == 0) {
                            results.addMessage(asset.getName() + " is out of stock, please try again. ");
                        } else {
                            results.addMessage(asset.getName() + " only has "
                                    + asset.getCurrentStock() + "oz in stock, please try again");
                        }
                        results.setType("Error");

                    }
                    if (quantity > 0) {
                        OrderItem orderItem = new OrderItem(asset, quantity, type);
                        orderItems.add(orderItem);
                        results.addOrderItem(new OrderItem(asset, quantity, type));
                        order.addOrderItem(orderItem);
                    }
                }
            } catch (NumberFormatException ex) {
                results.setMessages(new ArrayList<>());
                results.setType("Error");
                results.addMessage("Quantities must be a number");
                return results;
            }
        }
        if (total > 10) {
            results.setType("Error");
            results.addMessage("Total can not be greater than 10 ounces");
            return results;
        }
        if (orderItems.size() <= 0) {
            results.setType("Error");
            results.addMessage("No items selected");
        }
        if (results.getType().equals("Error")) {
            return results;
        }





        order.setOrderStatus("unfilled");
        order.setType(type);
        order.setMemberId(memberId);

        if (createNewOrder(order) > 0) {
            results.setType("Success");
            results.addMessage("Order has been successfully placed");
            results.setSuccess(true);
            results.setOrder(order);
            results.setOrderItems(orderItems);
        } else {
            results.setType("Database Error");
            results.addMessage("Order was not placed");
        }

        return results;
    }
}
