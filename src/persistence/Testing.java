package persistence;

import entities.Asset;
import entities.Order;
import entities.OrderItem;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * @author Alex
 *         5/22/2016
 */
public class Testing {

    DataAccessObject dao;
    Asset asset1;
    Asset asset2;


    @Before
    public void setup() {
        asset1 = new Asset();
        asset1.setAssetId(1);
        asset1.setName("Hopalisucus");
        asset1.setType("Hop");
        asset1.setDescription("very hoppy");

        asset2 = new Asset();
        asset2.setAssetId(5);
        asset2.setName("Mr. Hop");
        asset2.setType("Hop");
        asset2.setDescription("Married to Mrs. Hop");
    }

    @Test
    public void test2() {
        dao = new DataAccessObject(Asset.class);
        Asset asset = new Asset();

        asset.setName("Hopalisucus");
        asset.setType("Hop");
        asset.setDescription("very hoppy");

        asset.setName("Mr. Hop");
        asset.setType("Hop");
        asset.setDescription("Married to Mrs. Hop");

        int i = 0;
        i = dao.addRecord(asset);

        assertTrue(i > 0);



    }

    @Test
    public void test1() {
        dao = new DataAccessObject(Order.class);
        Order order = new Order();
        order.setMemberId(7);
        order.setOrderStatus("unfilled");

        OrderItem item = new OrderItem();
        item.setItem(asset1);
        item.setQuantity((float) 7.2);

        order.addOrderItem(item);

        item.setItem(asset2);
        item.setQuantity((float) 0.9);
        order.addOrderItem(item);

        order.setNotes("first order");

        int i = dao.addRecord(order);

        assertTrue(i > 0);

    }

}