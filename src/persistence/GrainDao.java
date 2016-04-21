package persistence;

import entities.Grain;
import org.apache.cxf.service.invoker.SessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

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

    public Grain getGrainById(int grainId) {
        Grain grain;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        grain = (Grain) session.createCriteria(Grain.class).add(Restrictions.eq("grainId", grainId)).list().get(0);
        session.close();
        return grain;
    }

    public List<Grain> getAllGrains() {
        List<Grain> grains;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        grains = (List<Grain>) session.createCriteria(Grain.class).list();
        session.close();
        return grains;
    }

    /*
    public BookCopy getCopyById(int bookNumber, String isbn) {
        BookCopy copy;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();

        copy = (BookCopy) session.createCriteria(BookCopy.class)
                .add(Restrictions.eq("isbn", isbn))
                .add(Restrictions.eq("bookNumber", bookNumber))
                .list().get(0);
        return copy;
    }
     */

}
