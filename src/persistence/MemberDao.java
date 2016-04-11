package persistence;


import entities.Member;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 * Created by Alex on 4/7/2016.
 */
public class MemberDao {
    private final Logger log = Logger.getLogger(this.getClass());
    public int addNewMember(Member newMember) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        int memberId = 0;
        try {
            transaction = session.beginTransaction();
            memberId = (int) session.save("Member", newMember);
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction!=null) {
                transaction.rollback();
            }
            memberId = -1;
            System.out.println(ex);
        } finally {
            session.close();
        }

        return memberId;
    }
    /*
    public int addBook(Book book) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        Integer bookId = 0;
        log.info("Book being added");
        try {
            tx = session.beginTransaction();
            bookId = (Integer) session.save("Book", book);
            tx.commit();

            for (int i = 0; i < book.getCopies(); i++) {
                BookCopy newBook = new BookCopy();
                newBook.setIsbn(book.getIsbn());
                newBook.setBookCondition("New");
                newBook.setCheckoutStatus('I');
                newBook.setBookNumber(i + 1);
                addBookCopy(newBook);
            }


            //log.info("Added user: " + employee + " with id of: " + employeeId);
        } catch (HibernateException e) {
            if (tx!=null) {
                tx.rollback();
            }
            bookId = -1;
            log.error("error did not add book");
        } finally {
            session.close();
        }
        return bookId;
    }
    */
}
