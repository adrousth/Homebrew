package entities;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex
 *         5/31/2016
 */
public class OrderResults extends Results {
    private Order order = new Order();
    private List<OrderItem> orderItems = new ArrayList<>();
    private String notes;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
