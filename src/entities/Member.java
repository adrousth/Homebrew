package entities;




import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Alex
 * 4/4/2016
 */
@Entity
@Table(name = "member")
public class Member implements Serializable, Comparable {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private int memberId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
    private Set<MemberRole> roles;

    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
    private Set<Order> memberOrders;



    public Member() {
        memberOrders = new HashSet<>();
        roles = new HashSet<>();
    }

    public Member(String firstName, String lastName, String email, String phone) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Order> getMemberOrders() {
        return memberOrders;
    }

    public void setMemberOrders(Set<Order> memberOrders) {
        this.memberOrders = memberOrders;
    }

    public void addMemberOrder(Order order) {
        memberOrders.add(order);
        order.setMember(this);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<MemberRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<MemberRole> roles) {
        this.roles = roles;
    }

    public void addRole(MemberRole role) {
        roles.add(role);
        role.setMember(this);
    }

    public boolean containsRole(String roleType) {
        for (MemberRole role: roles) {
            if (role.getRole().equals(roleType)) {
                return true;
            }
        }
        return false;

    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int compareTo(Object o) {
        if (memberId == ((Member)o).getMemberId()) {
            return 0;
        } else if (memberId > ((Member)o).getMemberId()) {
            return 1;
        } else {
            return -1;
        }
    }
}
