package entities;

import java.io.Serializable;

/**
 * Created by Alex on 4/9/2016.
 */
public class GrainOrderItem implements Serializable {
    private int orderId;
    private int grainId;
    private float quantity;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public int getGrainId() {
        return grainId;
    }

    public void setGrainId(int grainId) {
        this.grainId = grainId;
    }
}
