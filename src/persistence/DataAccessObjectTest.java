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
    private static MemberDao dao;
    private static Member testMember1;
    private static Member testMember2;
    private static Member testMember3;
    private static Member testMember4;
    private static Member testMember;

    private static int count;

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

        dao = new MemberDao();
        count = 0;
        testMember = new Member();
        testMember1 = new Member();
        testMember2 = new Member();
        testMember3 = new Member();
        testMember4 = new Member();

        testMember1.setFirstName("Johnson");
        testMember1.setLastName("Doe");
        testMember1.setEmail("jdoe@domain.com");
        testMember1.setMemberId((Integer) dao.addRecord(testMember1));

        testMember2.setFirstName("Mary");
        testMember2.setLastName("Smith");
        testMember2.setEmail("msmith@hello.com");
        testMember2.setMemberId((Integer) dao.addRecord(testMember2));


        testMember3.setFirstName("Gen");
        testMember3.setLastName("Eric");
        testMember3.setEmail("generic@lol.net");
        testMember3.setMemberId((Integer) dao.addRecord(testMember3));


        testMember4.setFirstName("Darth");
        testMember4.setLastName("Vader");
        testMember4.setEmail("dv@deathstart.gax");
        testMember4.setMemberId((Integer) dao.addRecord(testMember4));

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
    public void addRecord() {
        System.out.println("Testing addRecord");
        testMember.setFirstName("Judith");
        testMember.setLastName("Drousth");
        testMember.setEmail("hello3@world.org");
        testMember.setPhone("608 777-7777");

        int i = (int) dao.addRecord(testMember);

        assert(i > 0);
    }

    @Test
    public void getRecordById() {
        System.out.println("Testing getRecordById");
        Member member = dao.getRecordById(testMember4.getMemberId());
        assertTrue(member.getFirstName().equals(testMember4.getFirstName()));
    }

    @Test
    public void searchNumberOfRecords() {
        System.out.println("Testing searchNumberOfRecords");
        List<Member> members = dao.searchNumberOfRecords(0, 5, "firstName", "ar");
        System.out.println("got " + members.size() + " records");
        assertTrue(members.size() >= 2);
    }

    @Test
    public void getNumberOfRecords() {
        System.out.println("Testing getNumberOfRecords");
        List<Member> members = dao.getNumberOfRecords(0, 100);
        System.out.println("got " + members.size() + " records");
        assertTrue(members.size() >= 4);
    }

    @Test
    public void updateRecord() {
        System.out.println("Testing updateRecord");
        testMember2.setEmail("mynewemail@hello.net");
        dao.updateRecord(testMember2);
        Member member = dao.getRecordById(testMember2.getMemberId());
        assertTrue(member.getFirstName().equals(testMember2.getFirstName()));
    }

    @Test
    public void deleteRecord() {
        System.out.println("Testing deleteRecord");

        Member member = dao.getMemberByEmail("generic@lol.net");
        dao.deleteRecord(member);
    }



}