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
        member.setFirstName("joe");
        member.setLastName("smith");
        member.setEmail("joe3@smith.org");
        member.setPhone("608 244-2442");

        int i = dao.addNewMember(member);
        if (i == -1) {
            System.out.println("member not added");
        }

        assert(i > 0);

    }

}