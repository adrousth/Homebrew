package persistence;

import entities.Grain;
import entities.GrainOrder;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 4/8/2016.
 */
public class GrainOrderDao {

    public int addGrainOrder(GrainOrder newOrder) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        int orderId = 0;
        try {
            transaction = session.beginTransaction();
            orderId = (int) session.save("GrainOrder", newOrder);
            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            orderId = -1;
            System.out.println(e);
        } finally {
            session.close();
        }
        return orderId;
    }

    public List<GrainOrder> getAllGrainOrders() {
        List<GrainOrder> orders = new ArrayList<>();
        Session session = SessionFactoryProvider.getSessionFactory().openSession();

        orders = session.createCriteria(GrainOrder.class).list();


        return orders;
    }

}
