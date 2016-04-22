package com.nov.hotel.dao.impl;

import com.nov.hotel.entities.ServiceUnit;
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
public class TestServiceUnitDaoImpl {
    private static Logger LOG = Logger.getLogger(TestServiceUnitDaoImpl.class);
    @Autowired
    private ServiceUnitDaoImpl dao;

    private static List<ServiceUnit> testData = new LinkedList<>();
    private static List<ServiceUnit> result = new LinkedList<>();
    private static ServiceUnit ServiceUnit1 = new ServiceUnit();
    private static ServiceUnit ServiceUnit2 = new ServiceUnit();
    private static ServiceUnit ServiceUnit3 = new ServiceUnit();



    @BeforeClass
    public static void setUpBeforeClass(){
        ServiceUnit1.setName("день");
        ServiceUnit2.setName("доба");
        ServiceUnit3.setName("одиниця");
        testData.add(ServiceUnit1);
        testData.add(ServiceUnit2);
        testData.add(ServiceUnit3);
    }

    @Before
    public void setUp(){
        dao.deleteAll();
        assertEquals(0, dao.count());
        LOG.warn("\nTest Data:\n"+ testData.toString());
        for (ServiceUnit x: testData) {
            dao.insert(x);
        }
        assertEquals(testData.size(), dao.count());
    }


    @Test
    public void testGetByName(){
        for (ServiceUnit x: testData) {
            List<ServiceUnit> selected = dao.getSelected(x.getName());
            for (ServiceUnit y: selected) {
                assertEquals(x.getName(), y.getName());
            }
            LOG.warn("\ngetSelected Data:\n"+selected.toString());
        }
    }

    @Test
    public void testGetById(){
        for (ServiceUnit x:testData ) {
            ServiceUnit type = dao.getRow(x.getId());
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
        ServiceUnit elemUp = new ServiceUnit();
        elemUp.setId(testData.get(0).getId());
        elemUp.setName("година");
        dao.update(elemUp);

        ServiceUnit elem = dao.getRow(elemUp.getId());
        assertEquals(elemUp.getId(),elem.getId());
        assertEquals(elemUp.getName(),elem.getName());

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
