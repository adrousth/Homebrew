package entities;

/**
 * Created by Alex on 4/4/2016.
 */
public class Hop {
    private int hopId;
    private String name;
    private float currentStock;
    private String description;
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
