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
    protected final Logger log = Logger.getLogger(this.getClass());

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
            try {
                objectId = (int) session.save(newRecord.getClass().getName(), newRecord);
            } catch (ClassCastException e) {
                session.save(newRecord.getClass().getName(), newRecord);
                objectId = 1;
            }
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

    public T getRecordById(int id) {
        T record;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        record = type.cast(session.get(type, id));
        session.close();
        return record;
    }

    public List<T> getRecords(String searchType, String searchValue) {
        ArrayList<T> records;
        beginSession();

        records = (ArrayList<T>) session.createCriteria(type).add(Restrictions.like(searchType, "%" + searchValue + "%")).list();

        return records;
    }

    public T getRecordByEmail(String email) {
        T record;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        record = type.cast(session.createCriteria(type).add(Restrictions.eq("email", email)).list().get(0));
        session.close();
        return record;
    }

    public List<T> searchNumberOfRecords(int firstResult, int numberOfResults, String searchType, String searchValue) {
        ArrayList<T> records;
        beginSession();

        records = (ArrayList<T>) session.createCriteria(type).add(Restrictions.like(searchType, "%" + searchValue + "%"))
                .setFirstResult(firstResult).setMaxResults(numberOfResults).list();

        return records;
    }

    public List<T> getNumberOfRecords(int firstResult, int numberOfResults) {
        ArrayList<T> records;
        beginSession();

        records = (ArrayList<T>) session.createCriteria(type).setFirstResult(firstResult).setMaxResults(numberOfResults).list();

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
        /*
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        int i = 0;
        try {
            tx = session.beginTransaction();
            session.update("Book", book);
            tx.commit();
            log.info("Book with: " + book.getIsbn() + " updated");
            i = 1;
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            log.error(e);
            i = -1;
        } finally {
            session.close();
        }
        return i;
         */
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


}
