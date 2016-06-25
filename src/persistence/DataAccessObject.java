package persistence;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.*;

/**
 * @author Alex
 *  5/21/2016
 */
public class DataAccessObject<T> {
    final Logger log = Logger.getLogger(this.getClass());

    private Class<T> type;



    public void setType(Class<T> type) {
        this.type = type;
    }

    public DataAccessObject() {

    }

    public DataAccessObject(Class<T> type) {
        this();
        this.type = type;
    }

    public Object addRecord(T newRecord) {
        Session session = createSession();
        Transaction transaction = null;
        Object objectId = 0;

        try {
            transaction = session.beginTransaction();

            objectId = session.save(newRecord);
            log.info(newRecord.getClass().toString() + " with id of " + objectId + " added to the database");

            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction!=null) transaction.rollback();
            objectId = -1;
            log.error(ex);
        } finally {
            session.close();
        }

        return objectId;
    }

    public boolean persistRecord(T newRecord) {
        Session session = createSession();

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
            session.close();
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

    @SuppressWarnings("unchecked")
    public List<T> getRecords(String searchType, String searchValue) {
        List<T> records;
        Session session = createSession();

        records = session.createCriteria(type).add(Restrictions.like(searchType, "%" + searchValue + "%")).list();

        session.close();
        return records;
    }

    @SuppressWarnings("unchecked")
    public List<T> searchNumberOfRecords(int firstResult, int numberOfResults, String searchType, String searchValue) {
        List<T> records;
        Session session = createSession();

        records = session.createCriteria(type).add(Restrictions.like(searchType, "%" + searchValue + "%"))
                .setFirstResult(firstResult).setMaxResults(numberOfResults).list();

        session.close();

        return records;
    }

    @SuppressWarnings("unchecked")
    public List<T> getNumberOfRecords(int firstResult, int numberOfResults) {
        List<T> records;
        Session session = createSession();

        records = session.createCriteria(type).setFirstResult(firstResult).setMaxResults(numberOfResults).list();

        session.close();
        return records;
    }


    void updateRecord(T record) {
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
        Session session = createSession();
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
            session.close();
        }
    }


    @SuppressWarnings("unchecked")
    public List<T> getRecordsByParam(String param, Set<Object> ids) {
        List<T> records;
        Session session = createSession();

        records = session.createCriteria(type).add(Restrictions.in(param, ids)).list();

        session.close();

        return records;

    }

    public boolean addOrUpdateRecord(T record) {
        Session session = createSession();
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

    @SuppressWarnings("unchecked")
    public List<T> searchMultipleParams(Map searchParams) {
        List<T> records;
        Session session = createSession();

        records = session.createCriteria(type).add(Restrictions.allEq(searchParams)).list();

        session.close();
        return records;
    }

    public Object getRecordById(int id, Class c) {
        Object o;
        Session session = createSession();

        o = session.get(c, id);

        session.close();
        return o;
    }

    private Session createSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();
    }

}
