package persistence;

import entities.HopOrder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Alex on 4/11/2016.
 */
public class HopOrderDaoTest {
    private HopOrderDao dao;
    @Before
    public void setUp() throws Exception {
        dao = new HopOrderDao();
    }

    @Test
    public void addHopOrder() throws Exception {
        /*
        HopOrder order = new HopOrder();
        order.setMemberId(1);
        order.setOrderStatus("unfilled");

        order.addHop(1, (float) 2.2);
        order.addHop(2, (float) 1.9);

        dao.addHopOrder(order);
        */


    }

}