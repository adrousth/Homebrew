package persistence;

import entities.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

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
    public void test4() {
        dao.setType(Order.class);
        Order order2 = (Order) dao.getRecordById(11);

        assertTrue(order2.getOrderItems().size() > 0);
        for (OrderItem item: order2.getOrderItems()) {
            System.out.println(item.getOrder().getOrderId());
            System.out.println(item.getAssetId());
            System.out.println(item.getQuantity());
            System.out.println(item.getAsset().getName());
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
                System.out.println(item.getOrder().getOrderId() + " " + item.getAssetId());
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
        dao = new DataAccessObject(Asset.class);
        OrderItem item = new OrderItem();
        OrderItem item2 = new OrderItem();
        OrderItem item3 = new OrderItem();
        item.setAsset((Asset) dao.getRecordById(367));
        item2.setAsset((Asset) dao.getRecordById(370));
        item3.setAsset((Asset) dao.getRecordById(377));

        item.setQuantity((float) 7.0);
        item.setOrder(order);
        item2.setQuantity((float) 1.0);
        item2.setOrder(order);
        item3.setQuantity((float) 0.5);
        item3.setOrder(order);

        order.addOrderItem(item);
        order.addOrderItem(item2);
        order.addOrderItem(item3);
        order.setMemberId(187);

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

    @Test
    public void test9() {
        dao = new DataAccessObject(Asset.class);
        Set<Integer> stringSet = new TreeSet<>();

        stringSet.add(165);
        stringSet.add(166);
        stringSet.add(174);
        stringSet.add(180);

        List<Asset> assets = dao.getRecordsByParam("assetId", stringSet);
        System.out.println(assets.size());
    }

    @Test
    public void test10() {
        OrderDao orderDao = new OrderDao();
        Map<String, String> webOrder = new TreeMap<>();
        webOrder.put("", "");

        webOrder.put("366", "2.0");
        webOrder.put("367", "1.0");

        OrderResults results = orderDao.orderFromWebForm(webOrder, "hello@world.net", "HOP", "notes");
        System.out.println(results.isSuccess());
        System.out.println(results.getType());
        for (String message: results.getMessages()) {
            System.out.println(message);
        }
        System.out.println();
        for (OrderItem item: results.getOrder().getOrderItems()) {
            System.out.println();
            System.out.println(item.getAssetId() + " " + item.getOrder().getOrderId() + " " + item.getQuantity());
        }



    }

    @Test
    public void test11() {
        Order order = new Order();
        dao = new DataAccessObject(Asset.class);

        OrderItem item = new OrderItem();
        OrderItem item2 = new OrderItem();
        OrderItem item3 = new OrderItem();

        item.setAssetId(366);
        item2.setAssetId(370);
        item3.setAssetId(377);

        item.setQuantity((float) 7.0);
        item2.setQuantity((float) 1.0);
        item3.setQuantity((float) 0.5);

        order.addOrderItem(item);
        order.addOrderItem(item2);
        order.addOrderItem(item3);
        order.setMemberId(187);

        dao.setType(Order.class);
        int i = dao.addRecord(order);

        System.out.println(i);
    }

    @Test
    public void test12() {
        Map<String, String> map = new TreeMap<>();
        map.put("type", "HOP");

        DataAccessObject<Order> dao = new DataAccessObject<>();

        dao.setType(Order.class);
        Set<Order> orders = new TreeSet<>(dao.searchMultipleParams(map));

        for (Order order: orders) {
            System.out.println(order.getOrderId() + " " + order.getOrderStatus() + " " + order.getType());
            System.out.println();
        }


    }
}