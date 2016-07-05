package entities;


import com.github.javafaker.Faker;
import org.ocpsoft.prettytime.PrettyTime;
import persistence.DataAccessObject;

import java.util.*;

/**
 * Created by Alex on 4/5/2016.
 */
public class TestCode {
    public static void main(String[] args) {


        PrettyTime p = new PrettyTime();
        /*
        Calendar calendar1 = new GregorianCalendar();
        Calendar calendar2 = new GregorianCalendar();

        calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH), 10, 10);
        calendar2.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH), 10, 12);


        p.setReference(calendar1.getTime());
        System.out.println(p.format(calendar2));
        */

        DataAccessObject<Order> dao = new DataAccessObject<>();



        Map<String, Object> map = new TreeMap<>();
        map.put("memberId", 187);
        Set<Order> orders = new TreeSet<>(dao.searchMultipleParams(map));
        Faker faker = new Faker();

        Date date = new Date();
        for (Order order : orders) {
            System.out.println();
            System.out.println(order.timeSinceLastUpdate());
            /*
            Calendar calendar = new GregorianCalendar();
            calendar.setTimeInMillis(order.getUpdatedAt().getTime() + 72*60*60*1000);

            System.out.println(calendar.toInstant());


            p.setReference(order.getUpdatedAt());
            System.out.println(p.formatDuration(calendar));
            */
        }



        PrettyTime prettyTime = new PrettyTime();
        System.out.println(prettyTime.format(date));

    }

}
