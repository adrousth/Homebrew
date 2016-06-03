package persistence;


import entities.Member;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;


/**
 * Created by Alex on 4/7/2016.
 */
public class MemberDao extends DataAccessObject{


    public Member getMemberByEmail(String email) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        ArrayList<Member> member;
        member = (ArrayList<Member>) session.createCriteria(Member.class).add(Restrictions.eq("email", email)).list();
        if (member.size() > 0) {
            return member.get(0);
        }
        return null;
    }
}
