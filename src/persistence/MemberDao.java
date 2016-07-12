package persistence;


import entities.Member;
import entities.MemberResults;
import entities.MemberRole;
import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.opensaml.xml.encryption.P;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;


/**
 * @author Alex
 * 4/7/2016
 */
public class MemberDao extends DataAccessObject<Member> {

    private DataAccessObject<MemberRole> dao;

    public MemberDao() {
        setType(Member.class);
         dao = new DataAccessObject<>();
    }

    public Member getMemberByEmail(String email) {
        Member member = null;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        List members;
        members = session.createCriteria(Member.class).add(Restrictions.eq("email", email)).list();
        if (members.size() > 0) {
            member = (Member) members.get(0);
        }
        session.close();
        return member;
    }

    public Set<Member> searchMemberByName(String firstName, String lastName) {
        Set<Member> members;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Member.class);

        if (firstName != null) {
            criteria.add(Restrictions.ilike("firstName", "%" + firstName + "%"));
        }
        if (lastName != null) {
            criteria.add(Restrictions.ilike("lastName", "%" + lastName + "%"));
        }

        members = new TreeSet<Member>(criteria.list());

        session.close();
        return members;
    }

    @Override
    public boolean deleteRecord(Member record) {
        Session session = createSession();
        Transaction transaction = null;
        boolean success = false;
        try {
            transaction = session.beginTransaction();
            for (MemberRole role: record.getRoles()) {
                System.out.println(role.getRole());
                System.out.println(role.getMember().getEmail());
                dao.deleteRecord(role);
            }
            session.delete(record);
            transaction.commit();
            success = true;
            log.info(record.getClass().getName() + " deleted");
        } catch (HibernateException ex) {
            if (transaction!=null) transaction.rollback();
            log.error(ex);
        } finally {
            session.close();
        }
        return success;

    }

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

    public MemberResults createNewMemberFromForm(String firstName, String lastName, String email, String phone) {

        MemberResults results = new MemberResults();

        Member newMember = new Member(firstName, lastName, email, phone);

        validateFirstName(firstName, results);
        validateLastName(lastName, results);
        validateEmail(email, results);
        validatePhone(phone, results);


        if (results.getMessages().size() > 0) {

            results.setType("Error");
        } else {

            newMember.setPassword(RandomStringUtils.randomAlphanumeric(6));
            int i = (int) addRecord(newMember);

            MemberRole role = new MemberRole("MEMBER");
            newMember.addRole(role);

            dao.addRecord(role);



            if (i > 0) {
                newMember.setMemberId(i);
                results.setSuccess(true);
                results.setType("Success");
                results.addMessage("New member password: " + newMember.getPassword());
            } else {
                results.setType("Error");
            }
        }
        results.setMember(newMember);

        return results;
    }

    public void validateFirstName(String firstName, MemberResults results) {
        if (firstName == null) {
            results.addMessage("Please enter a first name.");
        } else if (firstName.length() == 0) {
            results.addMessage("Please enter a first name.");
        } else if (firstName.length() < 2) {
            results.addMessage("First name must be at least 2 letters long.");
        } else if (firstName.length() >= 30) {
            results.addMessage("First name must be at less than 30 letters long.");
        }
    }

    public void validateLastName(String lastName, MemberResults results) {
        if (lastName == null) {
            results.addMessage("Please enter a last name.");
        } else if (lastName.length() == 0) {
            results.addMessage("Please enter a last name.");
        } else if (lastName.length() < 2) {
            results.addMessage("Last name must be at least 2 letters long.");
        } else if (lastName.length() >= 35) {
            results.addMessage("Last name must be at less than 35 letters long.");
        }
    }

    public void validateEmail(String email, MemberResults results) {
        if (email == null) {
            results.addMessage("Please enter an email address.");
        } else if (email.length() == 0) {
            results.addMessage("Please enter an email address.");
        } else if (!Pattern.matches(EMAIL_REGEX, email)) {
            results.addMessage("Invalid email.");
        } else if (getMemberByEmail(email) != null) {
            results.addMessage("Email is already being used.");
        }
    }

    public void validatePhone(String phone, MemberResults results) {
        if (phone != null) {
            if (phone.length() >= 15) {
                results.addMessage("Phone number must be less than 15 characters long.");
            }
        }
    }
}
