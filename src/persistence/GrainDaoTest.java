package persistence;

import entities.Grain;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Alex on 4/8/2016.
 */
public class GrainDaoTest {
    private GrainDao dao;
    @Before
    public void setUp() throws Exception {
        dao = new GrainDao();
    }
    @Test
    public void addGrain() throws Exception {
        Grain newGrain = new Grain();
        newGrain.setName("second grain");
        newGrain.setCurrentStock((float) 10.5);
        newGrain.setDescription("the #2 grain to be added");

        int i = dao.addGrain(newGrain);
        if (i == -1) {
            System.out.println("grain was not added");
        }

        assertTrue(i > 0);

    }

    @Test
    public void getGrain() {
        Grain item = dao.getGrainById(1);
        System.out.println(item.getName());
        System.out.println(item.getCurrentStock());
        System.out.println(item.getGrainId());
        System.out.println();
        assertTrue(item != null);
    }

    @Test
    public void getAllGrains() {
        List<Grain> grains = dao.getAllGrains();
        for (Grain grain: grains) {
            System.out.println("grain id: " + grain.getGrainId());
            System.out.println("grain name: " + grain.getName());
            System.out.println("grain description: " + grain.getDescription());
            System.out.println("stock: " + grain.getCurrentStock());
            System.out.println();
        }
        assertTrue(grains.size() > 0);
    }


}