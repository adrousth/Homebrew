package entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Alex
 *         5/26/2016
 */
@Entity
@Table(name = "member_roles")
public class MemberRole implements Serializable {
    @Id
    @Column
    private String email;
    @Id
    @Column(name = "member_role")
    private String role;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "email", insertable = false, updatable = false, referencedColumnName = "email")
    private Member member;

    public MemberRole() {

    }

    public MemberRole(String email, String role) {
        this.email = email;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
