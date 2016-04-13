package com.nov.hotel.dao.impl;

import com.nov.hotel.entities.Region;
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
public class TestRegionDaoImpl {
    private static Logger LOG = Logger.getLogger(TestRegionDaoImpl.class);

    @Autowired
    private CountryDaoImpl countryDao;
    @Autowired
    private  RegionDaoImpl dao;

    Country country;

    private static Region region1 = new Region();
    private static Region region2 = new Region();
    private static Region region3 = new Region();
    private static Region region4 = new Region();
    private static Region region5 = new Region();
    private static Region region6 = new Region();
    private static Region region7 = new Region();
    private static Region region8 = new Region();
    private static List<Region> testData = new LinkedList<>();
    private static List<Region> results = new LinkedList<>();

    @BeforeClass
    public static void setUpBeforClass(){
        region1.setName("101");
        region2.setName("Київська обл.");
        region3.setName("Харківська обл.");
        region4.setName("м.Харків");
        region5.setName("м.Полтава");
        region6.setName("м.Дніпропетровськ");
        region7.setName("Полтавська обл.");
        region8.setName("London");

        testData.add(region1);
        testData.add(region2);
        testData.add(region3);
        testData.add(region4);
        testData.add(region5);
        testData.add(region6);
        testData.add(region7);
        testData.add(region8);
    }

    private void assertValues(Region x, Region elem) {
        assertEquals(x.getName(),elem.getName());
        assertEquals(x.getCountry().getId(),elem.getCountry().getId());
    }

    @Before
    public void setUp(){
        dao.deleteAll();
        assertEquals(0, dao.count());
        country = countryDao.getRow("UA");
        for (Region x:testData) {
            x.setCountry(country);
        }
        country = countryDao.getRow("GB");
        testData.get(testData.size()-1).setCountry(country);
        LOG.warn("\nTest Data:\n"+ testData.toString());
        for (Region x: testData) {
            dao.insert(x);
        }
        country = countryDao.getRow("UA");
        assertEquals(testData.size(), dao.count());
    }

    @Test
    public void testGetByName(){
        results.clear();
        for (Region x: testData) {
            List<Region> regions = dao.getSelected(x.getCountry().getId());
            for (Region y: regions) {
                assertEquals(x.getCountry().getId(), y.getCountry().getId());
            }
        }
        LOG.warn("\ngetSelected Data:\n"+ results.toString());
    }

    @Test
    public void testGetById(){
//        testGetByName();
        for (Region x: testData) {
            Region elem = dao.getRow(x.getId());
            assertEquals(x.getId(),elem.getId());
            assertValues(x, elem);
        }
        LOG.warn("\ngetRow Data:\n"+ results.toString());
    }

    @Test
    public void testGetAll(){
        results.clear();
        results = dao.getAll();
        assertEquals(testData.size(), results.size());
        LOG.warn("\ngetAll Data:\n"+ results.toString());
    }

    @Test
    public void testUpdate(){
        results.clear();
        results = dao.getAll();
        Region testData = new Region();
        testData.setId(results.get(0).getId());
        testData.setName("м.Київ");
        testData.setCountry(country);
        dao.update(testData);
        Region elem = dao.getRow(testData.getId());
        assertValues(testData, elem);
        LOG.warn("\nBefor Update:\n"+ results.toString());
        LOG.warn("\nUpdate Data:\n"+ testData.toString());
        results = dao.getAll();
        LOG.warn("\nAfter Update:\n"+ results.toString());
    }

    @Test
    public void testDelete(){
        results.clear();
        results = dao.getAll();
        dao.delete(results.get(0));
        assertEquals(testData.size()-1, dao.count());
    }
}
