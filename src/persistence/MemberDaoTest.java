package persistence;

import entities.Member;
import entities.MemberResults;
import org.junit.*;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author Alex
 * 7/5/2016
 */
public class MemberDaoTest {
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
        System.out.println();
        System.out.println("Ending Testing #" + count);
        System.out.println("-----------------------------");

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
        testMember4.setEmail("dv@deathstar.gax");
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
        testMember = new Member();
        testMember.setFirstName("Judith");
        testMember.setLastName("Drousth");
        testMember.setEmail("hello3@world.org");
        testMember.setPhone("608 777-7777");

        int i = (int) dao.addRecord(testMember);

        assert(i > 0);
    }

    @Test
    public void addRecord2() {
        System.out.println("Testing addRecord2");

        int i = (int) dao.addRecord(testMember1);

        assert(i <= 0);
    }


    @Test
    public void getRecordById() {
        System.out.println("Testing getRecordById");
        Member member = dao.getRecordById(testMember4.getMemberId());

        assertTrue(member.getFirstName().equals(testMember4.getFirstName()));
    }

    @Test
    public void getRecordById2() {
        System.out.println("Testing getRecordById2");
        Member member = dao.getRecordById(testMember4.getMemberId() + 5);

        assertTrue(member == null);
    }

    @Test
    public void searchNumberOfRecords() {
        System.out.println("Testing searchNumberOfRecords");
        Set<Member> members = dao.searchNumberOfRecords(0, 5, "firstName", "ar");
        System.out.println("got " + members.size() + " records");
        assertTrue(members.size() >= 2);
    }

    @Test
    public void searchNumberOfRecords2() {
        System.out.println("Testing searchNumberOfRecords2");
        Set<Member> members = dao.searchNumberOfRecords(0, 1, "firstName", "ar");
        System.out.println("got " + members.size() + " records");
        assertTrue(members.size() == 1);
    }

    @Test
    public void getNumberOfRecords() {
        System.out.println("Testing getNumberOfRecords");
        Set<Member> members = dao.getNumberOfRecords(0, 100);
        System.out.println("got " + members.size() + " records");
        assertTrue(members.size() >= 4);
    }

    @Test
    public void getNumberOfRecords2() {
        System.out.println("Testing getNumberOfRecords2");
        Set<Member> members = dao.getNumberOfRecords(187, 1);
        System.out.println("got " + members.size() + " records");
        assertTrue(members.size() == 0);
    }

    @Test
    public void updateRecord() {
        System.out.println("Testing updateRecord");
        testMember2.setEmail("mynewemail@hello.net");
        assertTrue(dao.updateRecord(testMember2));


    }

    @Test
    public void updateRecord2() {
        System.out.println("Testing updateRecord2");
        Member member = new Member();
        member.setEmail("hello@hello.orc");
        assertFalse(dao.updateRecord(member));


    }

    @Test
    public void deleteRecord() {
        System.out.println("Testing deleteRecord");
        Member member = dao.getMemberByEmail("generic@lol.net");
        assertTrue(dao.deleteRecord(member));
    }

    @Test
    public void deleteRecord2() {
        System.out.println("Testing deleteRecord2");
        Member member = new Member();
        member.setEmail("mynameis@hi.lol");
        member.setMemberId(-100);
        assertFalse(dao.deleteRecord(member));

    }

    @Test
    public void getMemberByEmail() {
        System.out.println("Testing getMemberByEmail");
        Member member = dao.getMemberByEmail("jdoe@domain.com");
        System.out.println(testMember1.getMemberId());
        System.out.println(member.getMemberId());
        assertTrue(testMember1.getMemberId() == member.getMemberId());
    }

    @Test
    public void getMemberByEmail2() {
        System.out.println("Testing getMemberByEmail2");
        Member member = dao.getMemberByEmail("email@email.email");
        assertTrue(member == null);
    }

    @Test
    public void createNewMemberFromForm() {
        System.out.println("Testing createNewMemberFromForm");
        MemberResults results = dao.createNewMemberFromForm("first", "last", "my@email.net", "");
        for (String message :
                results.getMessages()) {
            System.out.println(message);
        }
        assertTrue(results.isSuccess());
        System.out.println("true");
        Member member = dao.getMemberByEmail("my@email.net");
        assertTrue(dao.deleteRecord(member));

    }

    @Test
    public void createNewMemberFromForm2() {
        System.out.println("Testing createNewMemberFromForm2");
        MemberResults results = dao.createNewMemberFromForm("f", "hereismyreallyreallylonglastnamethatshouldbetolongforthis", "my@email", "");
        assertTrue(!results.isSuccess());
        assertTrue(results.getMessages().size() == 3);
        assertTrue(results.getType().equals("Error"));
    }

    @Test
    public void searchMemberByName() {
        System.out.println("Testing searchMemberByName");
        Set<Member> members = dao.searchMemberByName("", "Vader");
        assertTrue(members.size() >= 1);
        members = dao.searchMemberByName("Darth", "");
        assertTrue(members.size() >= 1);
    }

    @Test
    public void searchMemberByName2() {
        System.out.println("Testing searchMemberByName2");
        Set<Member> members = dao.searchMemberByName("", "qqqqqqqq");
        assertTrue(members.size() == 0);
        members = dao.searchMemberByName("qqqqqqqqqq", "");
        assertTrue(members.size() == 0);
    }

    @Test
    public void validateFirstName() {
        System.out.println("Testing validateFirstName");
        MemberResults results = new MemberResults();
        dao.validateFirstName("Jo", results);
        assertTrue(results.getMessages().size() == 0);
    }


    @Test
    public void validateFirstName2() {
        System.out.println("Testing validateFirstName2");
        MemberResults results = new MemberResults();
        dao.validateFirstName("J", results);
        assertTrue(results.getMessages().size() == 1
                && results.getMessages().get(0).equals("First name must be at least 2 letters long."));
    }

    @Test
    public void validateFirstName3() {
        System.out.println("Testing validateFirstName3");
        MemberResults results = new MemberResults();
        dao.validateFirstName("123456789012345678901234567890", results);
        System.out.println(results.getMessages().size());
        assertTrue(results.getMessages().size() == 1
                && results.getMessages().get(0).equals("First name must be at less than 30 letters long."));
    }

    @Test
    public void validateFirstName4() {
        System.out.println("Testing validateFirstName4");
        MemberResults results = new MemberResults();
        dao.validateFirstName("", results);
        assertTrue(results.getMessages().size() == 1
                && results.getMessages().get(0).equals("Please enter a first name."));
    }

    @Test
    public void validateFirstName5() {
        System.out.println("Testing validateFirstName5");
        MemberResults results = new MemberResults();
        dao.validateFirstName(null, results);
        assertTrue(results.getMessages().size() == 1
                && results.getMessages().get(0).equals("Please enter a first name."));
    }

    @Test
    public void validateFirstName6() {
        System.out.println("Testing validateFirstName3");
        MemberResults results = new MemberResults();
        dao.validateFirstName("12345678901234567890123456789", results);
        assertTrue(results.getMessages().size() == 0);
    }

    @Test
    public void validateLastName() {
        System.out.println("Testing validateLastName");
        MemberResults results = new MemberResults();
        dao.validateLastName("De", results);
        assertTrue(results.getMessages().size() == 0);
    }

    @Test
    public void validateLastName2() {
        System.out.println("Testing validateLastName1");
        MemberResults results = new MemberResults();
        dao.validateLastName("D", results);
        assertTrue(results.getMessages().size() == 1);
    }

    @Test
    public void validateLastName3() {
        System.out.println("Testing validateLastName3");
        MemberResults results = new MemberResults();
        dao.validateLastName("", results);
        assertTrue(results.getMessages().size() == 1);
    }

    @Test
    public void validateLastName4() {
        System.out.println("Testing validateLastName4");
        MemberResults results = new MemberResults();
        dao.validateLastName(null, results);
        assertTrue(results.getMessages().size() == 1);
    }

    @Test
    public void validateLastName5() {
        System.out.println("Testing validateLastName5");
        MemberResults results = new MemberResults();
        dao.validateLastName("12345678901234567890123456789012345", results);
        assertTrue(results.getMessages().size() == 1);
    }

    @Test
    public void validateLastName6() {
        System.out.println("Testing validateLastName6");
        MemberResults results = new MemberResults();
        dao.validateLastName("1234567890123456789012345678901234", results);
        assertTrue(results.getMessages().size() == 0);
    }

    @Test
    public void validateEmail() {
        System.out.println("Testing validateEmail");
        MemberResults results = new MemberResults();
        dao.validateEmail("", results);
        assertTrue(results.getMessages().size() == 1);
    }

    @Test
    public void validateEmail2() {
        System.out.println("Testing validateEmail2");
        MemberResults results = new MemberResults();
        dao.validateEmail("dv@deathstar.gax", results);
        assertTrue(results.getMessages().size() == 1);
    }

    @Test
    public void validateEmail3() {
        System.out.println("Testing validateEmail3");
        MemberResults results = new MemberResults();
        dao.validateEmail("hello@world", results);
        assertTrue(results.getMessages().size() == 1);
    }

    @Test
    public void validateEmail4() {
        System.out.println("Testing validateEmail4");
        MemberResults results = new MemberResults();
        dao.validateEmail(null, results);
        assertTrue(results.getMessages().size() == 1);
    }

    @Test
    public void validateEmail5() {
        System.out.println("Testing validateEmail5");
        MemberResults results = new MemberResults();
        dao.validateEmail("x@y.net", results);
        assertTrue(results.getMessages().size() == 0);
    }

    @Test
    public void validatePhone() {
        System.out.println("Testing validatePhone");
        MemberResults results = new MemberResults();
        dao.validatePhone("12345678901234", results);
        assertTrue(results.getMessages().size() == 0);
    }

    @Test
    public void validatePhone2() {
        System.out.println("Testing validatePhone2");
        MemberResults results = new MemberResults();
        dao.validatePhone("123456789012345", results);
        assertTrue(results.getMessages().size() == 1);
    }

}