package persistence;

import entities.Member;
import entities.Order;
import entities.OrderItem;
import org.junit.*;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author Alex
 *         7/5/2016
 */
public class OrderDaoTest {
    private static OrderDao dao;
    private static Member testMember;
    private static Order testOrder1;
    private static Order testOrder2;
    private static Order testOrder3;

    private static Order testOrder;

    private static int count;

    @Before
    public void startTest() {
        count++;
        System.out.println("Starting Testing #" + count);

    }

    @After
    public void endTest() {
        System.out.println("Ending Testing #" + count);
        System.out.println("--------------------------------------------");
        System.out.println();
    }

    @BeforeClass
    public static void setup() {

        dao = new OrderDao();
        count = 0;
        testOrder = new Order();
        testOrder1 = new Order();
        testOrder2 = new Order();
        testOrder3 = new Order();


        MemberDao memberDao = new MemberDao();
        testMember = new Member();
        testMember.setFirstName("Johnson");
        testMember.setLastName("Doe");
        testMember.setEmail("jdoe@domain.com");
        testMember.setMemberId((Integer) memberDao.addRecord(testMember));



        testOrder1.setOrderStatus("unfilled");
        testOrder1.setType("HOP");
        testMember.addMemberOrder(testOrder1);
        testOrder1.setOrderId((Integer) dao.addRecord(testOrder1));


        testMember.addMemberOrder(testOrder2);
        testOrder2.setOrderStatus("filled");
        testOrder2.setType("GRAIN");
        testOrder2.setOrderId((Integer) dao.addRecord(testOrder2));


        testMember.addMemberOrder(testOrder3);
        testOrder3.setOrderStatus("COMPLETE");
        testOrder3.setType("HOP");
        testOrder3.setOrderId((Integer) dao.addRecord(testOrder3));


    }

    @AfterClass
    public static void tearDown() {
        dao.deleteRecord(testOrder);
        dao.deleteRecord(testOrder1);
        dao.deleteRecord(testOrder2);
        dao.deleteRecord(testOrder3);
        MemberDao memberDao = new MemberDao();
        memberDao.deleteRecord(testMember);
    }



    @Test
    public void addRecord() {
        System.out.println("Testing addRecord");

        testOrder = new Order();
        testMember.addMemberOrder(testOrder);
        testOrder.setOrderStatus("unfilled");
        testOrder.setType("HOP");

        int i = (int) dao.addRecord(testOrder);

        assert(i > 0);
    }


    @Test
    public void getRecordById() {
        System.out.println("Testing getRecordById");
        Order Order = dao.getRecordById(testOrder3.getOrderId());

        assertTrue(Order.getOrderId() == testOrder3.getOrderId());
    }

    @Test
    public void getRecordById2() {
        System.out.println("Testing getRecordById2");
        Order Order = dao.getRecordById(testOrder2.getOrderId() + 5);

        assertTrue(Order == null);
    }

    @Test
    public void searchNumberOfRecords() {
        System.out.println("Testing searchNumberOfRecords");
        Set<Order> Orders = dao.searchNumberOfRecords(0, 5, "orderStatus", "filled");
        System.out.println("got " + Orders.size() + " records");
        assertTrue(Orders.size() >= 2);
    }

    @Test
    public void searchNumberOfRecords2() {
        System.out.println("Testing searchNumberOfRecords2");
        Set<Order> Orders = dao.searchNumberOfRecords(0, 1, "orderStatus", "filled");
        System.out.println("got " + Orders.size() + " records");
        assertTrue(Orders.size() == 1);
    }

    @Test
    public void getNumberOfRecords() {
        System.out.println("Testing getNumberOfRecords");
        Set<Order> Orders = dao.getNumberOfRecords(0, 100);
        System.out.println("got " + Orders.size() + " records");
        assertTrue(Orders.size() >= 3);
    }

    @Test
    public void getNumberOfRecords2() {
        System.out.println("Testing getNumberOfRecords2");
        Set<Order> Orders = dao.getNumberOfRecords(18700, 1);
        System.out.println("got " + Orders.size() + " records");
        assertTrue(Orders.size() == 0);
    }

    @Test
    public void updateRecord() {
        System.out.println("Testing updateRecord");
        testOrder2.setOrderStatus("COMPLETE");
        assertTrue(dao.updateRecord(testOrder2));
    }

    @Test
    public void updateRecord2() {
        System.out.println("Testing updateRecord2");
        Order Order = new Order();
        assertFalse(dao.updateRecord(Order));
    }

    @Test
    public void deleteRecord() {
        System.out.println("Testing deleteRecord");
        Order Order = dao.getRecordById(testOrder1.getOrderId());
        assertTrue(dao.deleteRecord(Order));
    }

    @Test
    public void deleteRecord2() {
        System.out.println("Testing deleteRecord2");
        Order Order = new Order();
        Order.setOrderId(-100);
        assertFalse(dao.deleteRecord(Order));

    }
}