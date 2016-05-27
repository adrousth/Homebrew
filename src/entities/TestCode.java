package entities;


import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Alex on 4/5/2016.
 */
public class TestCode {
    public static void main(String[] args) {




        Random r = new Random();
        List<Float> quantities = new ArrayList<>();
        Faker faker = new Faker();
        int numberPicks = faker.number().numberBetween(1, 5);
        float totalQuantity = (float) Math.round(2 * (Math.random() * (10 - 5) + 5)) / 2;
        //random numbers
        float sum = 0;
        for (int i = 0; i < numberPicks; i++) {
            float next = (float) Math.random();
            System.out.println(next);
            quantities.add(next);
            sum += next;
        }


        //scale to the desired target sum
        double scale = 1d * totalQuantity / sum;
        sum = 0;
        for (int i = 0; i < numberPicks; i++) {
            quantities.set(i, (float) Math.round((quantities.get(i) * scale) * 2) / 2);
            sum += quantities.get(i);
        }

        //take rounding issues into account
        while(sum++ < totalQuantity) {
            int i = faker.number().numberBetween(0, numberPicks);
            quantities.set(i, (quantities.get(i) + 1));
        }

        System.out.println("Random arraylist " + quantities);
        System.out.println("Sum is "+ (sum - 1));
        float total = 0;
        for (Float num: quantities) {
            total += num;
        }
        System.out.println(total);


    }
}
