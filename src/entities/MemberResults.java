package entities;

/**
 * @author Alex
 *         6/22/2016
 */
public class MemberResults extends Results {
    private Member member = new Member();

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
