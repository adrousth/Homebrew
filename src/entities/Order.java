package entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.ocpsoft.prettytime.PrettyTime;


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
public class Order implements Serializable, Comparable {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private int orderId;
    @Column(name = "order_status")
    private String orderStatus;
    @Column(name = "notes")
    private String notes;
    @Column(name = "type")
    private String type;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;


    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
        item.setOrder(this);
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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

    @Override
    public int compareTo(Object o) {
        if (orderId == ((Order)o).getOrderId()) {
            return 0;
        } else if (orderId > ((Order)o).getOrderId()) {
            return 1;
        } else {
            return -1;
        }
    }

    public String timeSinceLastUpdate() {
        PrettyTime formatter = new PrettyTime();
        String time = "";
        Calendar calendar = new GregorianCalendar();
        switch (orderStatus) {
            case "unfilled":
                calendar.setTime(createdAt);
                time += "Order placed ";
                break;
            case "filled":
                calendar.setTime(updatedAt);
                time += "Order filled ";
                break;
            default:
                calendar.setTime(updatedAt);
                time += "Order completed ";
                break;
        }


        time += formatter.formatUnrounded(calendar);
        return time;
    }

}
