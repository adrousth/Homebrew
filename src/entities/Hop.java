package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Alex on 4/4/2016.
 */
@Entity
@Table(name = "item_grain")
public class Hop {
    @Id
    @Column(name = "hop_id")
    private int hopId;
    @Column(name = "name")
    private String name;
    @Column(name = "current_stock")
    private float currentStock;
    @Column(name = "description")
    private String description;
    @Column(name = "url")
    private String url;


    public int getHopId() {
        return hopId;
    }

    public void setHopId(int hopId) {
        this.hopId = hopId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
