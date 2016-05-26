package entities;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by Alex on 4/5/2016.
 */
public class TestCode {
    public static void main(String[] args) {
        HashCodeBuilder x = new HashCodeBuilder(17, 37);
        System.out.println(x.append(7).append(13).toHashCode());
        HashCodeBuilder y = new HashCodeBuilder(17, 37);
        System.out.println(y.append(13).append(7).toHashCode());


    }
}
