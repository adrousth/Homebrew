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
@Entity
@Table(name = "grain_order")
public class GrainOrder {


    @ElementCollection
    @CollectionTable(name="grain_order_item",
            joinColumns = @JoinColumn(name="orderId", referencedColumnName="order_id")
    )
    @MapKeyJoinColumn(name="order_id")
    @Column(name = "quantity")
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

}
