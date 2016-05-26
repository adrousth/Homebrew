package entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.openjpa.persistence.jdbc.ElementColumn;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.*;

/**
 * Created by Alex on 4/4/2016.
 */
@Entity
@Table(name = "member_order")
public class Order implements Serializable {
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
    @Column
    private String type;

    @ManyToOne
    @JoinColumn(name = "member_id", insertable = false, updatable = false, referencedColumnName = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(3, 19).append(orderId).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Order that = (Order) obj;
        return new EqualsBuilder()
                .append(orderId, that.getOrderId())
                .isEquals();
    }
}
