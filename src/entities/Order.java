package entities;

import org.apache.openjpa.persistence.jdbc.ElementColumn;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Alex on 4/4/2016.
 */
@Entity
@Table(name = "order")
public class Order  {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private int orderId;
    @Column(name = "member_id")
    private int memberId;
    @Column(name = "order_status")
    private String orderStatus;
    @Column(name = "notes")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "member_id", insertable = false, updatable = false )
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems;

    public Order() {
        orderItems = new HashSet<>();
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void addOrderItem(OrderItem item) {
        orderItems.add(item);
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
