package persistence;


import entities.Member;
import org.apache.cxf.service.invoker.SessionFactory;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/**
 * Created by Alex on 4/7/2016.
 */
public class MemberDao extends DataAccessObject {


    public Member getMemberByEmail(String email) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        ArrayList<Member> member;
        member = (ArrayList<Member>) session.createCriteria(Member.class).add(Restrictions.eq("email", email)).list();
        if (member.size() > 0) {
            return member.get(0);
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
}
