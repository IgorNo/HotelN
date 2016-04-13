package com.nov.hotel.dao.impl;

import com.nov.hotel.entities.Block;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/app-context.xml"})
public class TestBlockDaoImpl {

    private static Logger LOG = Logger.getLogger(TestBlockDaoImpl.class);
    @Autowired
    private BlockDaoImpl dao;

    private static Block elem1 = new Block();
    private static Block elem2 = new Block();
    private static Block elem3 = new Block();
    private static Block elem4 = new Block();
    private static List<Block> testData = new LinkedList<Block>();
    private static List<Block> result = new LinkedList<>();

    @BeforeClass
    public static void setUpBeforClass(){
        elem1.setName("Корпус 1");
        elem2.setName("Корпус 2");
        elem3.setName("Корпус 3");
        elem4.setName("Корпус 4");
        testData.add(elem1);
        testData.add(elem2);
        testData.add(elem3);
        testData.add(elem4);
    }

    @Before
    public void setUp(){
        dao.deleteAll();
        assertEquals(0, dao.count());
        LOG.warn("\nTest Data:\n"+ testData.toString());
        for (Block x: testData) {
            dao.insert(x);
        }
        assertEquals(testData.size(), dao.count());
    }


    @Test
    public void testGetByName(){
        result.clear();
        for (Block x: testData) {
            List<Block> type = dao.getSelected(x.getName());
            result.add(type.get(0));
            assertEquals(x.getName(),result.get(result.size()-1).getName());
        }
        LOG.warn("\ngetSelected Data:\n"+result.toString());
    }

    @Test
    public void testGetById(){
        testGetByName();
        for (Block x:result ) {
            Block type = dao.getRow(x.getId());
            assertEquals(x.getId(),type.getId());
            assertEquals(x.getName(),type.getName());
        }
        LOG.warn("\ngetRow Data:\n"+result.toString());
    }

    @Test
    public void testGetAll(){
        result.clear();
        result = dao.getAll();
        for (int i = 0; i < testData.size(); i++) {
            Block testType = testData.get(i);
            Block resultType = result.get(i);
            assertEquals(testType.getName(),resultType.getName());
        }
        LOG.warn("\ngetAll Data:\n"+result.toString());
    }

    @Test
    public void testUpdate(){
        result.clear();
        result = dao.getAll();
        Block apartType = new Block();
        apartType.setId(result.get(0).getId());
        apartType.setName("Корпус люкс");
        dao.update(apartType);
        Block type = dao.getRow(apartType.getId());
        assertEquals(apartType.getId(),type.getId());
        assertEquals(apartType.getName(),type.getName());
        LOG.warn("\nBefor Update:\n"+result.toString());
        LOG.warn("\nUpdate Data:\n"+ apartType.toString());
        result = dao.getAll();
        LOG.warn("\nAfter Update:\n"+result.toString());
    }

    @Test
    public void testDelete(){
        result.clear();
        result = dao.getAll();
        dao.delete(result.get(0));
        assertEquals(testData.size()-1, dao.count());
    }

}
