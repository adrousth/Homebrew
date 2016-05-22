package entities;


import org.apache.commons.collections.map.HashedMap;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Alex on 4/4/2016.
 */
@MappedSuperclass
@Entity
@Table(name = "grain_order")
public class GrainOrder extends Order {
    private final String UNIT = "pound";
    //private List<Grain> grains;

    @JoinTable(name="grain_order_item", joinColumns=@JoinColumn(name="order_id"))
    @MapKey(name = "quantity")
    private Map<Grain, Float> grains;

    public GrainOrder() {
        grains = new HashMap<>();
    }

    public Map<Grain, Float> getGrains() {
        return grains;
    }

    public void setGrains(Map<Grain, Float> grains) {
        this.grains = grains;
    }

    public void addGrain(Grain grain, float quantity) {
        grains.put(grain, quantity);
    }

    /*
    private Set<GrainOrderItem> grains;
    public GrainOrder() {
        grains = new HashSet<>();
    }

    public void setGrains(Set<GrainOrderItem> grains) {
        this.grains = grains;
    }

    public Set<GrainOrderItem> getGrains() {
        return grains;
    }

    public void addOrder(GrainOrderItem order) {
        order.setOrderId(getOrderId());
        grains.add(order);
    }
*/
    /*
    public List<Grain> getGrains() {
        return grains;
    }

    public void setGrains(List<Grain> grains) {
        this.grains = grains;
    }


    public void addGrain(Grain grain) {
        grains.add(grain);
    }
    */
}
