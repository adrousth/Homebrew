package entities;

import javax.persistence.*;

/**
 * Created by Alex on 4/4/2016.
 */
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Order  {
    @Id
    @Column
    private int orderId;
    @Column(name = "member_id")
    private int memberId;
    @Column(name = "order_status")
    private String orderStatus;
    @Column(name = "notes")
    private String notes;


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
}
