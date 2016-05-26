package persistence;

import entities.Member;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Alex
 *         5/21/2016
 */
public class DataAccessObjectTest {
    static DataAccessObject dao;
    static Member testMember1;
    static Member testMember2;
    static Member testMember3;
    static Member testMember4;
    static Member testMember;

    static int count;

    @Before
    public void startTest() {
        count++;
        System.out.println("Starting Testing #" + count);
    }

    @After
    public void endTest() {
        System.out.println("Ending Testing #" + count);
        System.out.println();
    }

    @BeforeClass
    public static void setup() {
        dao = new DataAccessObject(Member.class);
        count = 0;
        testMember = new Member();
        testMember1 = new Member();
        testMember2 = new Member();
        testMember3 = new Member();
        testMember4 = new Member();

        testMember1.setFirstName("Johnson");
        testMember1.setLastName("Doe");
        testMember1.setEmail("jdoe@domain.com");
        testMember1.setMemberId(dao.addRecord(testMember1));

        testMember2.setFirstName("Mary");
        testMember2.setLastName("Smith");
        testMember2.setEmail("msmith@hello.com");
        testMember2.setMemberId(dao.addRecord(testMember2));


        testMember3.setFirstName("Gen");
        testMember3.setLastName("Eric");
        testMember3.setEmail("generic@lol.net");
        testMember3.setMemberId(dao.addRecord(testMember3));


        testMember4.setFirstName("Darth");
        testMember4.setLastName("Vader");
        testMember4.setEmail("dv@deathstart.gax");
        testMember4.setMemberId(dao.addRecord(testMember4));

    }

    @AfterClass
    public static void tearDown() {
        dao.deleteRecord(testMember);
        dao.deleteRecord(testMember1);
        dao.deleteRecord(testMember2);
        dao.deleteRecord(testMember3);
        dao.deleteRecord(testMember4);
    }

    @Test
    public void addRecord() throws Exception {
        System.out.println("Testing addRecord");
        testMember.setFirstName("Judith");
        testMember.setLastName("Drousth");
        testMember.setEmail("hello3@world.org");
        testMember.setPhone("608 777-7777");

        int i = dao.addRecord(testMember);

        assert(i > 0);
    }

    @Test
    public void getRecordById() throws Exception {
        System.out.println("Testing getRecordById");
        Member member = (Member) dao.getRecordById(testMember4.getMemberId());
        assertTrue(member.getFirstName().equals(testMember4.getFirstName()));
    }

    @Test
    public void searchNumberOfRecords() throws Exception {
        System.out.println("Testing searchNumberOfRecords");
        List<Member> members = dao.searchNumberOfRecords(0, 5, "firstName", "Jo");
        System.out.println("got " + members.size() + " records");
        assertTrue(members.size() >= 2);
    }

    @Test
    public void getNumberOfRecords() throws Exception {
        System.out.println("Testing getNuberOfRecords");
        List<Member> members = dao.getNumberOfRecords(0, 100);
        System.out.println("got " + members.size() + " records");
        assertTrue(members.size() >= 4);
    }

    @Test
    public void updateRecord() throws Exception {
        System.out.println("Testing updateRecord");
        testMember2.setEmail("mynewemail@hello.net");
        dao.updateRecord(testMember2);
        Member member = (Member) dao.getRecordById(testMember2.getMemberId());
        assertTrue(member.getFirstName().equals(testMember2.getFirstName()));
    }

    @Test
    public void deleteRecord() throws Exception {
        System.out.println("Testing deleteRecord");
        dao.setType(Member.class);
        Member member = (Member) dao.getRecordByEmail("generic@lol.net");
        dao.deleteRecord(member);
    }

}