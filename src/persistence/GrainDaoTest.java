package persistence;

import entities.Grain;
import org.junit.Before;
import org.junit.Test;

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

        assert(i > 0);

    }

}