package persistence;

import entities.Order;
import entities.OrderItem;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Alex
 *         5/25/2016
 */
public class OrderDao extends DataAccessObject {
    public int createNewOrder(Order order) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        int id = 0;
        int id2 = 0;
        try {
            transaction = session.beginTransaction();
            id = (int) session.save(order);
            transaction.commit();
            order.setOrderId(id);
            setType(OrderItem.class);
            for (OrderItem orderItem : order.getOrderItems()) {
                orderItem.setOrderId(id);
                id2 = addRecord(orderItem);
                System.out.println(id2);
            }
            log.info(order);
        } catch (HibernateException ex) {
            if (transaction!=null) transaction.rollback();
            log.error(ex);
            id = -1;
        } finally {
            session.close();
        }


        return id;
    }
}
