package persistence;

import entities.Grain;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by Alex on 4/8/2016.
 */
public class GrainDao {


    public int addGrain(Grain newGrain) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        int grainId = 0;
        try {
            transaction = session.beginTransaction();
            grainId = (int) session.save("Grain", newGrain);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            grainId = -1;
            System.out.println(e);
        } finally {
            session.close();
        }

        return grainId;
    }

}
