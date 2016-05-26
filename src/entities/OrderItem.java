package entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.ForeignKey;

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
    @Column(name = "asset_id")
    private int assetId;

    @Id
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "quantity")
    private float quantity;

    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false, referencedColumnName = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "asset_id", insertable = false, updatable = false, referencedColumnName = "asset_id")
    private Asset item;


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
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

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(orderId).append(assetId).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }
        OrderItem that = (OrderItem) obj;
        return new EqualsBuilder()
                .append(assetId, that.getAssetId())
                .append(orderId, that.getOrderId())
                .isEquals();
    }
}
