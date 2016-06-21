package entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Alex
 *         5/22/2016
 */
@Entity
@Table(name = "asset")
public class Asset implements Comparable {
    @Id
    @GeneratedValue
    @Column(name = "asset_id")
    private int assetId;
    @Column(name = "name")
    private String name;
    @Column(name = "current_stock")
    private float currentStock;
    @Column(name = "description")
    private String description;
    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "asset")
    private Set<OrderItem> orderItems;

    public Asset() {
        orderItems = new HashSet<>();
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(float currentStock) {
        this.currentStock = currentStock;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public int compareTo(Object o) {
        if (assetId == ((Asset)o).getAssetId()) {
            return 0;
        } else if (assetId > ((Asset)o).getAssetId()) {
            return 1;
        } else {
            return -1;
        }
    }
}
