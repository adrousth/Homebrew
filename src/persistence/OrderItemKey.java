package persistence;

import entities.OrderItem;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.io.Serializable;

/**
 * @author Alex
 *         7/17/2016
 */
public class OrderItemKey implements Serializable {
    protected int orderId;
    protected int assetId;

    public OrderItemKey() {}

    public OrderItemKey(int assetId, int orderId) {
        this.assetId = assetId;
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }
        OrderItemKey that = (OrderItemKey) obj;
        return new EqualsBuilder()
                .append(orderId, that.orderId)
                .append(assetId, that.assetId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
