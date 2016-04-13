package com.nov.hotel.dao.impl;

import com.nov.hotel.entities.Price;
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
public class TestPriceDaoImpl {
    private static Logger LOG = Logger.getLogger(TestPriceDaoImpl.class);
    @Autowired
    private PriceDaoImpl dao;

    private static List<Price> testData = new LinkedList<>();
    private static List<Price> result = new LinkedList<>();

    @BeforeClass
    public static void setUpBeforClass(){
        int N = 4;
        for (Integer i = 0; i < N; i++) {
            Price elem = new Price();
            elem.setName("Прайс"+i);
            elem.setPrice(i/(float) N + 1.0f);
            testData.add(elem);
        }
    }

    @Before
    public void setUp(){
        dao.deleteAll();
        assertEquals(0, dao.count());
        LOG.warn("\nTest Data:\n"+ testData.toString());
        for (Price x: testData) {
            dao.insert(x);
        }
        assertEquals(testData.size(), dao.count());
    }


    @Test
    public void testGetByName(){
        for (Price x: testData) {
            List<Price> selected = dao.getSelected(x.getName());
            for (Price y: selected) {
                assertEquals(x.getName(), y.getName());
            }
            LOG.warn("\ngetSelected Data:\n"+selected.toString());
        }
    }

    @Test
    public void testGetById(){
        for (Price x:testData ) {
            Price type = dao.getRow(x.getId());
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
        Price elemUp = new Price();
        elemUp.setId(testData.get(0).getId());
        elemUp.setName("Травневі свята");
        elemUp.setPrice(2.0f);
        dao.update(elemUp);
        Price elem = dao.getRow(elemUp.getId());
        assertEquals(elemUp.getId(),elem.getId());
        assertEquals(elemUp.getName(),elem.getName());
        assertEquals(elemUp.getPrice(),elem.getPrice(),0.00001);
        LOG.warn("\nBefor Update:\n"+result.toString());
        LOG.warn("\nUpdate Data:\n"+ elemUp.toString());
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
