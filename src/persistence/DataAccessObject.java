package persistence;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.*;

/**
 * @author Alex
 *  5/21/2016
 *
 *  Generic Dao.
 */
public class DataAccessObject<T> {

    final Logger log = Logger.getLogger(this.getClass());
    private Class<T> type;

    public DataAccessObject() {
    }

    public DataAccessObject(Class<T> type) {
        this();
        this.type = type;
    }


    /**
     * Sets the class type.
     * @param type The type to be set.
     */
    protected void setType(Class<T> type) {
        this.type = type;
    }


    /**
     * Adds a new record to the database.
     * @param newRecord The record to be added.
     * @return The id of the newly added record.
     */
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

    /**
     * Persists a record.
     * @param newRecord The record to be persisted.
     * @return True or false based on if the database transaction was successfully committed.
     */
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

    /**
     * Get a record based on it's id.
     * @param id The id of the record.
     * @return The record that was found, if any.
     */
    public T getRecordById(int id) {
        T record;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();

        record = type.cast(session.get(type, id));

        session.close();
        return record;
    }

    /**
     * Searches for all records that are like the search value.
     * @param searchType The field being searched.
     * @param searchValue The value being used to searched.
     * @return A set of all records that match the search.
     */
    @SuppressWarnings("unchecked")
    public TreeSet<T> searchLikeRecords(String searchType, String searchValue) {
        TreeSet<T> records;
        Session session = createSession();

        records = new TreeSet<T>(session.createCriteria(type).add(Restrictions.like(searchType, "%" + searchValue + "%")).list());

        session.close();
        return records;
    }

    /**
     * Searches for all records that are equal to the search value.
     * @param searchType The field being searched.
     * @param searchValue The value being searched for.
     * @return A set of all records that match the search.
     */
    @SuppressWarnings("unchecked")
    public TreeSet<T> searchRecords(String searchType, Object searchValue) {
        TreeSet<T> records;
        Session session = createSession();

        records = new TreeSet<>(session.createCriteria(type)
                .setProjection(Projections.distinct(Projections.property(type.getSimpleName().toLowerCase() + "Id")))
                .add(Restrictions.eq(searchType, searchValue)).list());

        session.close();
        return records;
    }


    /**
     * Searches for a number of records that are equal to the search value.
     * @param firstResult The first result to add to the results.
     * @param numberOfResults The number of the results to return.
     * @param searchType the field being searched.
     * @param searchValue the value being searched for.
     * @return A set of records that match the search.
     */
    @SuppressWarnings("unchecked")
    public TreeSet<T> searchNumberOfRecords(int firstResult, int numberOfResults, String searchType, String searchValue) {
        TreeSet<T> records;
        Session session = createSession();

        records = new TreeSet<T>(session.createCriteria(type)
                .setProjection(Projections.distinct(Projections.property(type.getSimpleName().toLowerCase() + "Id")))
                .add(Restrictions.like(searchType, "%" + searchValue + "%"))
                .setFirstResult(firstResult).setMaxResults(numberOfResults).list());

        session.close();

        return records;
    }

    /**
     * Returns all records that are in the range specified.
     * @param firstResult The first record to add to the results.
     * @param numberOfResults The number of records to be returned, can be less if not enough records to return.
     * @return A set of all records that are in the range.
     */
    @SuppressWarnings("unchecked")
    public TreeSet<T> getNumberOfRecords(int firstResult, int numberOfResults) {
        TreeSet<T> records;
        Session session = createSession();

        records = new TreeSet<T>(session.createCriteria(type)
                .setProjection(Projections.distinct(Projections.property(type.getSimpleName().toLowerCase() + "Id")))
                .setFirstResult(firstResult).setMaxResults(numberOfResults).list());

        session.close();
        return records;
    }

    /**
     * Updates a record in the database.
     * @param record The record to be updated.
     * @return True or false based on if the record was successfully updated or not.
     */
    public boolean updateRecord(T record) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        boolean success = false;

        try {
            transaction = session.beginTransaction();
            session.update(type.getName(), record);
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

    /**
     * Deletes a record in the database.
     * @param record The record to be deleted.
     * @return True or false based on if the record was successfully deleted or not.
     */
    public boolean deleteRecord(T record) {
        Session session = createSession();
        Transaction transaction = null;
        boolean success = false;

        try {

            transaction = session.beginTransaction();
            session.delete(record);
            transaction.commit();
            success = true;
            log.info(record.getClass().getName() + " deleted");
        } catch (HibernateException ex) {
            if (transaction!=null) transaction.rollback();
            log.error(ex);
        } finally {
            session.close();
        }
        return success;
    }


    /**
     * Gets all records that has one of the values pass to the method.
     * @param property The field being searched.
     * @param values The values being searched for.
     * @return A set with all the records that have a value in the values passed to the method.
     */
    @SuppressWarnings("unchecked")
    public TreeSet<T> getRecordsByParam(String property, Set<Object> values) {
        TreeSet<T> records;
        Session session = createSession();

        records = new TreeSet<>(session.createCriteria(type).add(Restrictions.in(property, values)).list());

        session.close();

        return records;

    }

    /**
     * Adds a record or updates it if it all ready exists.
     * @param record The record to be added or updated.
     * @return True or false based on if the record was successfully added or updated.
     */
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
            if (transaction!=null) {
                transaction.rollback();
            }
            log.error(ex);
        } finally {
            session.close();
        }

        return success;
    }

    /**
     * Searches for all records that match the properties and values in the passed map.
     * @param searchParams Map of all the values being searched for and what field to search for them in.
     * @return A set with all the results found from the search.
     */
    @SuppressWarnings("unchecked")
    public TreeSet<T> searchMultipleParams(Map searchParams) {
        TreeSet<T> records;
        Session session = createSession();

        records = new TreeSet<T>(session.createCriteria(type).add(Restrictions.allEq(searchParams)).list());

        session.close();
        return records;
    }


    /**
     * Creates a session and returns it.
     * @return The newly created session.
     */
    protected Session createSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();
    }

}
