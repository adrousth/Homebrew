package entities;


import com.github.javafaker.Faker;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Created by Alex on 4/5/2016.
 */
public class TestCode {
    public static void main(String[] args) {


        Faker faker = new Faker();
        for (int i = 0; i < 20; i++) {
            Date date = faker.date().past(360, TimeUnit.DAYS);
            System.out.println(date);
            Date date2 = faker.date().past(15, TimeUnit.DAYS, date);
            System.out.println(date2);
        }
        /*
        System.out.println(faker.date().between(date, date2));
        System.out.println(faker.date().between(date, date2));
        System.out.println(faker.date().between(date, date2));
        System.out.println(faker.date().between(date, date2));
        System.out.println(faker.date().between(date, date2));
        System.out.println(faker.date().between(date, date2));
        Date date3 = faker.date().between(date, date2);
        System.out.println(date3);
        */



    }

}
