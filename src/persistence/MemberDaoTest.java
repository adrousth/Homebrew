package persistence;

import entities.Member;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Alex on 4/7/2016.
 */
public class MemberDaoTest {
    private MemberDao dao;
    @Before
    public void setUp() throws Exception {
        dao = new MemberDao();
    }

    @Test
    public void addNewMember() throws Exception {
        Member member = new Member();
        member.setFirstName("Judith");
        member.setLastName("");
        member.setEmail("hello2@world.org");
        member.setPhone("608 777-7777");

        int i = dao.addNewMember(member);
        if (i == -1) {
            System.out.println("member not added");
        }

        assert(i > 0);

    }

    @Test
    public void testGetMember() {
        Member member = dao.getMember("hello2@world.org");
        assertTrue(member != null);
    }

}