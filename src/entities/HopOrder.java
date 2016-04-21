package entities;

import java.util.*;

/**
 * Created by Alex on 4/4/2016.
 */
public class HopOrder extends Order {
    private final String UNITS = "ounce";
    private Map<Integer, Float> hops;

    public HopOrder() {
        hops = new HashMap<>();
    }

    public Map<Integer, Float> getHops() {
        return hops;
    }

    public void setHops(Map<Integer, Float> hops) {
        this.hops = hops;
    }

    public void addHop(int hopId, float quantity) {
        hops.put(hopId, quantity);
    }
}
