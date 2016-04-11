package persistence;

import entities.GrainOrder;
import entities.GrainOrderItem;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Alex on 4/8/2016.
 */
public class GrainOrderDaoTest {
    private GrainOrderDao dao;
    @Before
    public void setUp() throws Exception {
        dao = new GrainOrderDao();
    }

    @Test
    public void addGrainOrder() throws Exception {
        GrainOrder order = new GrainOrder();
        order.setMemberId(1);
        order.setOrderStatus("unfilled");

        order.addGrain(1, (float) 9.5);
        order.addGrain(2, (float) 2.1);
        /*
        GrainOrderItem orderItem = new GrainOrderItem();
        orderItem.setGrainId(1);
        orderItem.setOrderId(7);
        orderItem.setQuantity((float) 2.5);

        order.addOrder(orderItem);
        */
        order.setNotes("first order");

        int i = dao.addGrainOrder(order);

        assert (i > 0);

    }

}