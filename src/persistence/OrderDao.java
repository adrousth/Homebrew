package persistence;

import entities.*;
import org.hibernate.*;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.*;

/**
 * @author Alex
 *         5/25/2016
 */
public class OrderDao extends DataAccessObject<Order> {

    public OrderDao() {
        setType(Order.class);
    }

    /**
     * Adds a new order to the database.
     * @param order The order to be added.
     * @return The new order's id.
     */
    public int createNewOrder(Order order) {
        int id = 0;

        if (persistRecord(order)) {
            id = order.getOrderId();
            updateAssets(order);
        }

        return id;
    }

    /**
     * Updates the currentStock of an order's assets when an order is added.
     * @param order The order that was added to the database.
     */
    public void updateAssets(Order order) {
        AssetDao assetDao = new AssetDao();
        for (OrderItem orderItem : order.getOrderItems()) {
            Asset asset = assetDao.getRecordById(orderItem.getAsset().getAssetId());
            asset.setCurrentStock(asset.getCurrentStock() - orderItem.getQuantity());
            assetDao.updateRecord(asset);
        }
    }

    /**
     * Updates an order.
     * @param order The order to be updated.
     * @return True or false based on if the update was successful.
     */
    public boolean updateOrder(Order order) {
        order.setUpdatedAt(new Date());
        return updateRecord(order);
    }

    public boolean deleteOrder(Order order) {
        Session session = createSession();
        Transaction transaction = null;
        boolean success = false;

        try {
            transaction = session.beginTransaction();
            session.delete("Order", order);
            transaction.commit();
            success = true;
            log.info(order.getClass().getName() + " deleted");
        } catch (HibernateException ex) {
            if (transaction!=null) transaction.rollback();
            log.error(ex);
        }
        return success;
    }

    /**
     * Adds a order to the database from a web form.
     * @param webOrder The items and quantities for the order.
     * @param memberEmail The member that is making the order email.
     * @param type The type of order that is being made.
     * @param notes Any notes for the order.
     * @return The results from add the order to the database.
     */
    public OrderResults orderFromWebForm(Map<String, String> webOrder, String memberEmail, String type, String notes) {
        List<OrderItem> orderItems = new ArrayList<>();
        MemberDao memberDao = new MemberDao();
        OrderResults results = new OrderResults();
        results.setType("");
        Order order = new Order();
        order.setNotes(notes);
        Member member = memberDao.getMemberByEmail(memberEmail);
        if (member == null) {
            results.addMessage("Must be logged in to place an order");
            results.setType("Error");
            return results;
        }


        webOrderLoop(webOrder, type, results, order, orderItems);

        if (orderItems.size() <= 0) {
            results.setType("Error");
            results.addMessage("No items selected");
        }

        if (results.getType().equals("Error")) {
            return results;
        }


        order.setOrderStatus("unfilled");
        order.setType(type);
        member.addMemberOrder(order);

        int id = createNewOrder(order);
        if (id > 0) {
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

    /**
     * Loops through all order items and validates them.
     * @param webOrder The order items to be validated.
     * @param type The type of order.
     * @param results The results from the validation.
     * @param order The order that is being made.
     * @param orderItems A list of the order items after validation.
     */
    private void webOrderLoop(Map<String, String> webOrder, String type, OrderResults results, Order order, List<OrderItem> orderItems) {
        float total = 0;
        for (Map.Entry<String, String> item : webOrder.entrySet()) {

            try {
                if (!item.getKey().equals("")) {
                    int id = Integer.parseInt(item.getKey());
                    float quantity = (float) (Math.round(Float.parseFloat(item.getValue()) * 2) / 2.0);
                    total += quantity;

                    AssetDao assetDao = new AssetDao();
                    Asset asset = assetDao.getRecordById(id);

                    if (asset == null || !asset.getType().equals(type)) {
                        results.setType("Error");
                        results.addMessage("Invalid item in order");
                        return;
                    } else if (asset.getCurrentStock() < quantity) {
                        if (asset.getCurrentStock() == 0) {
                            results.addMessage(asset.getName() + " is out of stock, please try again.");
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

                results.setType("Error");
                results.addMessage("Quantities must be a number");
                return;
            }
        }
        if (total > 10) {
            results.setType("Error");
            results.addMessage("Total can not be greater than 10 ounces");
        }

    }


    /**
     * Searches orders based on their order status and type.
     * @param orderStatus The order status being search for.
     * @param type The type being searched for.
     * @return A set of orders that meet the criteria.
     */
    public Set<Order> searchOrdersByStatus(String orderStatus, String type) {

        Set<Order> orders;
        Session session = createSession();
        Transaction tx = session.beginTransaction();

        Criteria criteria = session.createCriteria(Order.class);

        if (type != null) {
            if (!type.toLowerCase().equals("both")) {
                criteria.add(Restrictions.eq("type", type).ignoreCase());
            }
        }

        if (orderStatus != null) {
            if (!orderStatus.toLowerCase().equals("all")) {
                criteria.add(Restrictions.eq("orderStatus", orderStatus).ignoreCase());
            }
        }

        orders = new TreeSet<Order>(criteria.list());

        for (Order order : orders) {
            Hibernate.initialize(order.getOrderItems());
        }

        tx.commit();
        return orders;
    }

    /**
     * Searches for all records that match the properties and values in the passed map.
     * @param searchParams Map of all the values being searched for and what field to search for them in.
     * @return A set with all the results found from the search.
     */
    @SuppressWarnings("unchecked")
    public TreeSet<Order> searchMultipleParams(Map searchParams) {
        TreeSet<Order> records;
        Session session = createSession();
        Transaction tx = session.beginTransaction();

        records = new TreeSet<Order>(session.createCriteria(Order.class).add(Restrictions.allEq(searchParams)).list());
        for (Order order: records) {
            Hibernate.initialize(order.getOrderItems());
        }

        tx.commit();
        return records;
    }

    @Override
    public Order getRecordById(int id) {
        Order record;
        Session session = createSession();
        Transaction tx = session.beginTransaction();

        record = Order.class.cast(session.get(Order.class, id));
        Hibernate.initialize(record.getOrderItems());

        tx.commit();
        return record;
    }
}
