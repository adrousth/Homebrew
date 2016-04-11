package entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Alex on 4/4/2016.
 */
public class HopOrder extends Order {
    private final String UNITS = "ounce";
    private Set<Hop> hops;

    public HopOrder() {
        hops = new HashSet<>();
    }

    public Set<Hop> getHops() {
        return hops;
    }

    public void setHops(Set<Hop> hops) {
        this.hops = hops;
    }

    public void addHop(Hop hop) {
        hops.add(hop);
    }
}
