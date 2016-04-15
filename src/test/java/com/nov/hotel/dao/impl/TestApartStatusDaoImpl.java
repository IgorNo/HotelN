package com.nov.hotel.dao.impl;

import com.nov.hotel.entities.ApartStatus;
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
public class TestApartStatusDaoImpl {

    private static Logger LOG = Logger.getLogger(TestApartStatusDaoImpl.class);
    @Autowired
    private ApartStatusDaoImpl dao;

    private static ApartStatus elem1 = new ApartStatus();
    private static ApartStatus elem2 = new ApartStatus();
    private static ApartStatus elem3 = new ApartStatus();
    private static ApartStatus elem4 = new ApartStatus();
    private static List<ApartStatus> testData = new LinkedList<ApartStatus>();
    private static List<ApartStatus> result = new LinkedList<>();

    @BeforeClass
    public static void setUpBeforClass(){
        elem1.setId("F");
        elem1.setName("номер");
        elem1.setColor("1");
        elem2.setId("R");
        elem2.setName("Бронь");
        elem2.setColor("0xffff4dff");
        elem3.setId("B");
        elem3.setName("Зайнятий");
        elem3.setColor("0x4d66ccff");
        elem4.setId("O");
        elem4.setName("Ремонт");
        elem4.setColor("0xff6666ff");
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
        for (ApartStatus x: testData) {
            dao.insert(x);
        }
        assertEquals(testData.size(), dao.count());
    }


    @Test
    public void testGetByName(){
        result.clear();
        for (ApartStatus x: testData) {
            List<ApartStatus> type = dao.getSelected(x.getName());
            result.add(type.get(0));
            assertEquals(x.getName(),result.get(result.size()-1).getName());
            assertEquals(x.getColor(),result.get(result.size()-1).getColor());
        }
        LOG.warn("\ngetSelected Data:\n"+result.toString());
    }

    @Test
    public void testGetById(){
        testGetByName();
        for (ApartStatus x:result ) {
            ApartStatus type = dao.getRow(x.getId());
            assertEquals(x.getId(),type.getId());
            assertEquals(x.getName(),type.getName());
            assertEquals(x.getColor(),type.getColor());
        }
        LOG.warn("\ngetRow Data:\n"+result.toString());
    }

    @Test
    public void testGetAll(){
        result.clear();
        result = dao.getAll();
        assertEquals(testData.size(), result.size());
        LOG.warn("\ngetAll Data:\n"+result.toString());
    }

    @Test
    public void testUpdate(){
        ApartStatus apartType = testData.get(0);
        apartType.setId(testData.get(0).getId());
        apartType.setName("Вільний номер");
        apartType.setColor("0xccffffff");
        dao.update(apartType);
        ApartStatus type = dao.getRow(apartType.getId());
        assertEquals(apartType.getId(),type.getId());
        assertEquals(apartType.getName(),type.getName());
        assertEquals(apartType.getColor(),type.getColor());
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
