package persistence;

import entities.Hop;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by Alex on 4/8/2016.
 */
public class HopDao {
    public int addHop(Hop newHop) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        int hopId = 0;
        try {
            transaction = session.beginTransaction();
            hopId = (int) session.save("Hop", newHop);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e);
            hopId = -1;
        } finally {
            session.close();
        }

        return hopId;
    }
}
