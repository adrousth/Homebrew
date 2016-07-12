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
        TestCode x = new TestCode();
        x.run();
    }

    public void run() {
        Test<String> test = new Test<>();
        test.setType(String.class);
    }

    public class Test<T> {
        Class<T> type;
        public void setType(Class<T> type) {
            this.type = type;
        }

    }

}
