package persistence;

import com.sun.corba.se.spi.ior.ObjectKey;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.*;

/**
 * @author Alex
 *         5/21/2016
 */
public class DataAccessObject<T> {
    final Logger log = Logger.getLogger(this.getClass());

    private Class<T> type;
    private SessionFactory factory;
    private Session session;

    public void setType(Class<T> type) {
        this.type = type;
    }

    public DataAccessObject() {
        try{
            factory = SessionFactoryProvider.getSessionFactory();
        }catch (Throwable ex) {
            log.error(ex);
        }
    }

    public DataAccessObject(Class<T> type) {
        this();
        this.type = type;
    }

    public int addRecord(T newRecord) {
        beginSession();
        Transaction transaction = null;
        int objectId = 0;

        try {
            transaction = session.beginTransaction();

            session.persist(newRecord.getClass().getName(), newRecord);
            log.info(newRecord.getClass().toString() + " with id of " + objectId + " added to the database");

            transaction.commit();
            objectId = 1;
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

    public boolean persistRecord(T newRecord) {
        beginSession();
        Transaction transaction = null;
        boolean success = false;

        try {
            transaction = session.beginTransaction();

            session.persist(newRecord.getClass().getName(), newRecord);
            transaction.commit();
            success = true;
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

    return success;
    }

    public T getRecordById(int id) {
        T record;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        record = type.cast(session.get(type, id));
        session.close();
        return record;
    }

    public List getRecords(String searchType, String searchValue) {
        List records;
        beginSession();

        records = session.createCriteria(type).add(Restrictions.like(searchType, "%" + searchValue + "%")).list();

        return records;
    }

    public T getRecordByEmail(String email) {
        T record;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        record = type.cast(session.createCriteria(type).add(Restrictions.eq("email", email)).list().get(0));
        session.close();
        return record;
    }

    public List searchNumberOfRecords(int firstResult, int numberOfResults, String searchType, String searchValue) {
        List records;
        beginSession();

        records = session.createCriteria(type).add(Restrictions.like(searchType, "%" + searchValue + "%"))
                .setFirstResult(firstResult).setMaxResults(numberOfResults).list();

        return records;
    }

    public List getNumberOfRecords(int firstResult, int numberOfResults) {
        List records;
        beginSession();


        records = session.createCriteria(type).setFirstResult(firstResult).setMaxResults(numberOfResults).list();

        return records;
    }


    public void updateRecord(T record) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.update(type.getName(), record);
            transaction.commit();
            log.info(record.getClass().getName() + " updated");
        } catch (HibernateException ex) {
            if (transaction!=null) transaction.rollback();
            log.error(ex);
        } finally {
            session.close();
        }
    }

    public void deleteRecord(T record) {
        beginSession();
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

    public void beginSession() {
        session = factory.openSession();
    }

    public List getRecordsByParam(String param, Set<Object> ids) {
        List records;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();

        records = session.createCriteria(type).add(Restrictions.in(param, ids)).list();

        return records;

    }

    public boolean addOrUpdateRecord(T record) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        boolean success = false;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(type.getName(), record);
            transaction.commit();
            success = true;
            log.info(record.getClass().getName() + " updated");
        } catch (HibernateException ex) {
            if (transaction!=null) transaction.rollback();
            log.error(ex);
        } finally {
            session.close();
        }

        return success;
    }

    public List searchMultipleParams(Map searchParams) {
        List records;
        beginSession();

        records = session.createCriteria(type).add(Restrictions.allEq(searchParams)).list();

        session.close();
        return records;
    }

}
