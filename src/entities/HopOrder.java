package entities;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Alex on 4/4/2016.
 */
@Entity
@Table(name = "hop_order")
public class HopOrder {

    @ElementCollection
    @CollectionTable(name="hop_order_item",
            joinColumns = @JoinColumn(name="orderId", referencedColumnName="order_id")
    )
    @MapKeyJoinColumn(name="order_id")
    @Column(name = "quantity")
    private Map<Hop, Float> hops;

    public HopOrder() {
        hops = new HashMap<>();
    }

    public Map<Hop, Float> gethops() {
        return hops;
    }

    public void sethops(Map<Hop, Float> hops) {
        this.hops = hops;
    }

    public void addhop(Hop hop, float quantity) {
        hops.put(hop, quantity);
    }
}
