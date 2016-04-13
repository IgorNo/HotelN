package com.nov.hotel.dao.impl;

import com.nov.hotel.entities.Country;
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
public class TestCountryDaoImpl {
    private static Logger LOG = Logger.getLogger(TestCountryDaoImpl.class);
    @Autowired
    private CountryDaoImpl dao;

    private static Country elem1 = new Country();
    private static Country elem2 = new Country();
    private static Country elem3 = new Country();
    private static Country elem4 = new Country();
    private static Country elem5 = new Country();
    private static Country elem6 = new Country();
    private static Country elem7 = new Country();
    private static List<Country> testData = new LinkedList<Country>();
    private static List<Country> result = new LinkedList<>();

    @BeforeClass
    public static void setUpBeforClass(){
        elem1.setId("US");
        elem1.setName("1");
        elem2.setId("BG");
        elem2.setName("Болгарія");
        elem3.setId("FN");
        elem3.setName("Фінляндія");
        elem4.setId("DE");
        elem4.setName("Германія");
        elem5.setId("UA");
        elem5.setName("Україна");
        elem6.setId("RU");
        elem6.setName("Російська Федерація");
        elem7.setId("GB");
        elem7.setName("Великобританія");
        testData.add(elem1);
        testData.add(elem2);
        testData.add(elem3);
        testData.add(elem4);
        testData.add(elem5);
        testData.add(elem6);
        testData.add(elem7);
    }

    @Before
    public void setUp(){
        dao.deleteAll();
        assertEquals(0, dao.count());
        LOG.warn("\nTest Data:\n"+ testData.toString());
        for (Country x: testData) {
            dao.insert(x);
        }
        assertEquals(testData.size(), dao.count());
    }


    @Test
    public void testGetByName(){
        result.clear();
        for (Country x: testData) {
            List<Country> type = dao.getSelected(x.getName());
            result.add(type.get(0));
            assertEquals(x.getName(),result.get(result.size()-1).getName());
        }
        LOG.warn("\ngetSelected Data:\n"+result.toString());
    }

    @Test
    public void testGetById(){
        testGetByName();
        for (Country x:result ) {
            Country type = dao.getRow(x.getId());
            assertEquals(x.getId(),type.getId());
            assertEquals(x.getName(),type.getName());
        }
        LOG.warn("\ngetRow Data:\n"+result.toString());
    }

    @Test
    public void testGetAll(){
        result.clear();
        result = dao.getAll();
        LOG.warn("\ngetAll Data:\n"+result.toString());
        assertEquals(testData.size(), result.size());
    }

    @Test
    public void testUpdate(){
        result.clear();
        result = dao.getAll();
        Country apartType = new Country();
        apartType.setId("US");
        apartType.setName("США");
        dao.update(apartType);
        Country type = dao.getRow(apartType.getId());
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
