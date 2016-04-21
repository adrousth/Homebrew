package persistence;

import entities.Grain;
import entities.GrainOrder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import static org.junit.Assert.*;

/**
 * Created by Alex on 4/8/2016.
 */
public class GrainOrderDaoTest {
    private GrainOrderDao orderDao;
    private GrainDao grainDao;
    @Before
    public void setUp() throws Exception {
        orderDao = new GrainOrderDao();
        grainDao = new GrainDao();
    }

    @Test
    public void addGrainOrder() throws Exception {
        GrainOrder order = new GrainOrder();
        order.setMemberId(7);
        order.setOrderStatus("unfilled");


        order.addGrain(2, (float) 7.2);


        order.setNotes("first order");

        int i = orderDao.addGrainOrder(order);

        assertTrue(i > 0);

    }

    @Test
    public void getAllGrainOrders() {
        List<GrainOrder> orders = orderDao.getAllGrainOrders();


        assertTrue(orders.size() > 0);
    }

}