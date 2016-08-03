package entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.openjpa.persistence.ReadOnly;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Alex
 *         5/22/2016
 */
@Entity
@Table(name = "order_item")
public class OrderItem implements Serializable, Comparable  {


    @Transient
    @Column(name = "order_id")
    private int orderId;

    @Id
    @Column(name = "asset_id")
    private int assetId;

    @Column(name = "quantity")
    private float quantity;

    @Column(name = "type")
    private String type;


    @Id
    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false, referencedColumnName = "order_id")
    private Order order;


    @ManyToOne
    @JoinColumn(name = "asset_id", insertable = false, updatable = false, referencedColumnName = "asset_id")
    private Asset asset;

    public OrderItem() {

    }

    public OrderItem(Asset asset, float quantity, String type) {
        this.asset = asset;
        assetId = asset.getAssetId();
        this.quantity = quantity;
        this.type = type;
    }

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

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
        assetId = asset.getAssetId();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
        orderId = order.getOrderId();
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(assetId).toHashCode();
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
                .append(order.getOrderId(), that.getOrder().getOrderId())
                .append(assetId, that.getAssetId())
                .isEquals();
    }


    @Override
    public int compareTo(Object o) {

        if (assetId == ((OrderItem)o).getAssetId() && orderId == ((OrderItem)o).getOrderId()) {
            return 0;
        } else if (assetId > ((OrderItem)o).getAssetId()) {
            return 1;
        } else {
            return -1;
        }

    }
}
