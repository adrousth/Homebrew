package entities;

/**
 * Created by Alex on 4/5/2016.
 */
public class TestCode {
    public static void main(String[] args) {
        Order order = new HopOrder();
        order.setMemberId(1234);
        System.out.println(order.getMemberId());


    }
}
