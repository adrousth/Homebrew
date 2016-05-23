package entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Alex
 *         5/22/2016
 */
@Entity
@Table(name = "order_item")
public class OrderItem implements Serializable {
    @Id
    @Column(name = "order_id")
    private int orderId;
    @Id
    @Column(name = "asset_id")
    private int assetId;

    @Column(name = "quantity")
    private float quantity;

    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false )
    private Order order;

    @ManyToOne
    @JoinColumn(name = "asset_id", insertable = false, updatable = false )
    private Asset item;


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Asset getItem() {
        return item;
    }

    public void setItem(Asset item) {
        this.item = item;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
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
