package persistence;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.ordering.antlr.Factory;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex
 *         5/21/2016
 */
public class DataAccessObject<T> {
    private final Logger log = Logger.getLogger(this.getClass());

    private Class<T> type;
    private SessionFactory factory = SessionFactoryProvider.getSessionFactory();
    private Session session;

    public void setType(Class<T> type) {
        this.type = type;
    }

    public DataAccessObject() {
    }

    public DataAccessObject(Class<T> type) {
        this.type = type;
    }

    public int addRecord(T newRecord) {
        
        session = factory.openSession();
        Transaction transaction = null;
        int objectId = 0;
        
        try {
            transaction = session.beginTransaction();
            objectId = (int) session.save(newRecord.getClass().getName(), newRecord);
            transaction.commit();
            log.info(newRecord.getClass().toString() + " with id of " + objectId + " added to the database");
        } catch (HibernateException ex) {
            if (transaction!=null) transaction.rollback();
            objectId = -1;
            log.error(ex);
        } finally {
            try {
                session.close();
            } catch (HibernateException ex) {
                log.error(ex);
            }
        }

        return objectId;
    }

    public T getRecordById(Object id) {
        T record;
        session = factory.openSession();
        record = (T) session.get(type, (Serializable) id);
        return record;
    }

    public T getRecordByEmail(String email) {
        T record;
        session = factory.openSession();
        record = (T) session.createCriteria(type).add(Restrictions.eq("email", email)).list().get(0);
        return record;
    }

    public List<T> searchNumberOfRecords(int firstResult, int numberOfResults, String searchType, String searchValue) {
        ArrayList<T> records;
        session = factory.openSession();

        records = (ArrayList<T>) session.createCriteria(type).add(Restrictions.like(searchType, "%" + searchValue + "%"))
                .setFirstResult(firstResult).setMaxResults(numberOfResults).list();

        return records;
    }

    public List<T> getNumberOfRecords(int firstResult, int numberOfResults) {
        ArrayList<T> records;
        session = factory.openSession();

        records = (ArrayList<T>) session.createCriteria(type).setFirstResult(firstResult).setMaxResults(numberOfResults).list();

        return records;
    }

    public void updateRecord(T record) {
        session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.update(record);
            transaction.commit();
            log.info(record.getClass().getName() + " updated");
        } catch (HibernateException ex) {
            if (transaction!=null) transaction.rollback();

            log.error(ex);
        } finally {
            try {
                session.close();
            } catch (HibernateException ex) {
                log.error(ex);
            }
        }
    }

    public void deleteRecord(T record) {
        session = factory.openSession();
        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();
            session.delete(record);
            transaction.commit();
            log.info(record.getClass().getName() + " deleted");
        } catch (HibernateException ex) {
            if (transaction!=null) transaction.rollback();
            log.error(ex);
        } finally {
            try {
                session.close();
            } catch (HibernateException ex) {
                log.error(ex);
            }
        }
    }


}
