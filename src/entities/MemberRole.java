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
    @Column(name = "member_role")
    private String role;

    @Id
    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "email", insertable = false, updatable = false, referencedColumnName = "email")
    private Member member;

    public MemberRole() {
    }

    public MemberRole(String role) {
        this.role = role;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
        email = member.getEmail();
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
