package persistence;

import entities.Hop;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Alex on 4/8/2016.
 */

public class HopDaoTest {

    private HopDao dao;
    private Session session;
    private Transaction transaction;
    @Before
    public void setUp() throws Exception {
        dao = new HopDao();
    }

    @Test
    public void addHop() throws Exception {
        Hop newHop = new Hop();

        newHop.setName("Hop Teh Second");
        newHop.setCurrentStock(0);
        newHop.setDescription("second hop to be added");

        int i = dao.addHop(newHop);

        if (i == -1) {
            System.out.println("hop was not added");
        }

        assertTrue(i > 0);
    }


}