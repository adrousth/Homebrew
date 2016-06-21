package persistence;


import entities.Member;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/**
 * @author Alex
 * 4/7/2016
 */
public class MemberDao extends DataAccessObject {


    public Member getMemberByEmail(String email) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        List member;
        member = session.createCriteria(Member.class).add(Restrictions.eq("email", email)).list();
        if (member.size() > 0) {
            return (Member) member.get(0);
        }
        return null;
    }

    public Set searchMemberByName(String firstName, String lastName) {
        Set members;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Member.class);
        if (firstName != null) {
            criteria.add(Restrictions.ilike("firstName", "%" + firstName + "%"));
        }
        if (lastName != null) {
            criteria.add(Restrictions.ilike("lastName", "%" + lastName + "%"));
        }

        members = new TreeSet<>(criteria.list());

        session.close();
        return members;
    }

    public int createNewMemberFromForm(String firstName, String lastName, String email, String phone) {
        Member newMember = new Member(firstName, lastName, email, phone);
        newMember.setPassword("foobar");




        return 0;
    }
}
