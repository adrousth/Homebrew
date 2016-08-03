package persistence;


import entities.Member;
import entities.MemberResults;
import entities.MemberRole;
import entities.Order;
import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.*;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;


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

    /**
     * Finds a member based on email.
     * @param email The email to be searched for.
     * @return The member that was found, if any.
     */
    public Member getMemberByEmail(String email) {
        Member member = null;
        Session session = createSession();
        Transaction tx = session.beginTransaction();

        List members = session.createCriteria(Member.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .add(Restrictions.eq("email", email)).list();

        if (members.size() > 0) {
            member = (Member) members.get(0);
        }
        tx.commit();
        return member;
    }

    /**
     * Searches for members based on first and last name.
     * @param firstName The first name being searched for.
     * @param lastName The last name being searched for.
     * @return A set of all members found that meet the criteria.
     */
    public Set<Member> searchMemberByName(String firstName, String lastName) {
        Set<Member> members;
        Session session = createSession();
        Transaction tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(Member.class);

        if (firstName != null) {
            criteria.add(Restrictions.ilike("firstName", "%" + firstName + "%"));
        }
        if (lastName != null) {
            criteria.add(Restrictions.ilike("lastName", "%" + lastName + "%"));
        }

        members = new TreeSet<Member>(criteria.list());

        tx.commit();
        return members;
    }

    /**
     * Member version of deleteRecord.
     * @param record The record to be deleted.
     * @return True or false based on if the record was deleted or not.
     */
    @Override
    public boolean deleteRecord(Member record) {
        for (MemberRole role: record.getRoles()) {
            dao.deleteRecord(role);
        }
        return super.deleteRecord(record);

    }

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

    /**
     * Creates a new member and add it to the database from a form.
     * @param firstName The new member's first name.
     * @param lastName The new member's last name.
     * @param email The new member's email.
     * @param phone The new member's phone.
     * @return The results from adding the member to the database.
     */
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

    /**
     * Validates firstName.
     * @param firstName The name to validate.
     * @param results The results for the validation.
     */
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

    /**
     * Validates lastName.
     * @param lastName The name to validate.
     * @param results The results for the validation.
     */
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

    /**
     * Validates email.
     * @param email The email to validate.
     * @param results The results for the validation.
     */
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

    /**
     * Validates phone number.
     * @param phone The phone number to validate.
     * @param results The results for the validation.
     */
    public void validatePhone(String phone, MemberResults results) {
        if (phone != null) {
            if (phone.length() >= 15) {
                results.addMessage("Phone number must be less than 15 characters long.");
            }
        }
    }
    public Member getRecordById(int id) {
        Member record;
        Session session = createSession();
        Transaction tx = session.beginTransaction();

        record = Member.class.cast(session.get(Member.class, id));
        for (Order order : record.getMemberOrders()) {
            Hibernate.initialize(order.getOrderItems());
        }
        tx.commit();
        return record;
    }
}
