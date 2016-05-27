package persistence;

import entities.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;


/**
 * @author Alex
 *         5/22/2016
 */
public class Testing {

    DataAccessObject dao;
    Asset asset1;
    Asset asset2;
    Order order;


    @Before
    public void setup() {
        dao = new DataAccessObject(Order.class);
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
        order = (Order) dao.getRecordById(11);
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
        dao.setType(OrderItem.class);
        Order order = new Order();
        order.setMemberId(7);

        order.setOrderStatus("unfilled");

        order.setNotes("first order");
        dao = new DataAccessObject(Order.class);
        int i = dao.addRecord(order);

        assertTrue(i > 0);

    }

    @Test
    public void test3() {
        dao = new DataAccessObject(Order.class);
        Order order2 = new Order();
        order2.setMemberId(5);
        order2.setOrderStatus("unfilled");

        OrderItem item = new OrderItem();
        item.setAssetId(asset1.getAssetId());
        item.setOrderId(order2.getOrderId());
        item.setQuantity((float) 7.2);
        order2.addOrderItem(item);


        OrderItem item2 = new OrderItem();
        item2.setAssetId(asset2.getAssetId());
        item2.setOrderId(order2.getOrderId());
        item2.setQuantity((float) 0.9);
        order2.addOrderItem(item2);
        dao.updateRecord(order2);
    }

    @Test
    public void test4() {
        dao.setType(Order.class);
        Order order2 = (Order) dao.getRecordById(11);

        assertTrue(order2.getOrderItems().size() > 0);
        for (OrderItem item: order2.getOrderItems()) {
            System.out.println(item.getOrderId());
            System.out.println(item.getAssetId());
            System.out.println(item.getQuantity());
            System.out.println(item.getItem().getName());
            System.out.println(item.getOrder().getOrderStatus());
            System.out.println();
        }
    }

    @Test
    public void test5() {
        dao.setType(Member.class);
        Member member = (Member) dao.getRecordById(7);
        System.out.println(member.getFirstName());
        System.out.println(member.getMemberOrders().size());
        for (Order order1 : member.getMemberOrders()) {
            System.out.println();
            for (OrderItem item :
                    order1.getOrderItems()) {
                System.out.println(item.getOrderId() + " " + item.getAssetId());
            }

        }
        /*
        Order order = new Order();

        OrderItem item = new OrderItem();
        item.setAssetId(asset1.getAssetId());
        item.setQuantity((float) 7.2);
        order.addOrderItem(item);


        OrderItem item2 = new OrderItem();
        item2.setAssetId(asset2.getAssetId());
        item2.setQuantity((float) 0.9);
        order.addOrderItem(item2);

        member.addOrder(order);
        dao.updateRecord(member);
        */


    }

    @Test
    public void test6() {
        OrderDao orderDao = new OrderDao();
        Order order = new Order();

        OrderItem item = new OrderItem();
        OrderItem item2 = new OrderItem();

        item.setAssetId(61);
        item.setQuantity((float) 7.2);

        item2.setAssetId(62);
        item2.setQuantity((float) 0.9);

        order.addOrderItem(item);
        order.addOrderItem(item2);
        order.setMemberId(147);

        int i = orderDao.createNewOrder(order);
        System.out.println(i);
    }

    @Test
    public void test7() {
        dao = new DataAccessObject(Member.class);
        Member member = (Member) dao.getRecordById(104);
        MemberRole role = new MemberRole();
        role.setEmail(member.getEmail());
        role.setRole("MEMBER");
        member.addRole(role);
        dao.updateRecord(member);
    }

    @Test
    public void test8() {
        dao = new DataAccessObject(Asset.class);
        List<Asset> hops = dao.searchNumberOfRecords(0, 1000, "type", "HOP");
        List<Asset> grains = dao.searchNumberOfRecords(0, 1000, "type", "GRAIN");
        System.out.println(hops.size());
        System.out.println(grains.size());
    }
}