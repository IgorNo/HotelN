package com.nov.hotel.dao.impl;

import com.nov.hotel.entities.ServiceType;
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
public class TestServiceTypeDaoImpl {
    private static Logger LOG = Logger.getLogger(TestServiceTypeDaoImpl.class);
    @Autowired
    private ServiceTypeDaoImpl dao;

    private static List<ServiceType> testData = new LinkedList<>();
    private static List<ServiceType> result = new LinkedList<>();
    private static ServiceType ServiceType1 = new ServiceType();
    private static ServiceType ServiceType2 = new ServiceType();
    

    @BeforeClass
    public static void setUpBeforeClass(){
        ServiceType1.setName("не стандарт");
        ServiceType2.setName("відпочинок");
        testData.add(ServiceType1);
        testData.add(ServiceType2);
    }

    @Before
    public void setUp(){
        dao.deleteAll();
        assertEquals(0, dao.count());
        LOG.warn("\nTest Data:\n"+ testData.toString());
        for (ServiceType x: testData) {
            dao.insert(x);
        }
        assertEquals(testData.size(), dao.count());
    }


    @Test
    public void testGetByName(){
        for (ServiceType x: testData) {
            List<ServiceType> selected = dao.getSelected(x.getName());
            for (ServiceType y: selected) {
                assertEquals(x.getName(), y.getName());
            }
            LOG.warn("\ngetSelected Data:\n"+selected.toString());
        }
    }

    @Test
    public void testGetById(){
        for (ServiceType x:testData ) {
            ServiceType type = dao.getRow(x.getId());
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
        ServiceType elemUp = new ServiceType();
        elemUp.setId(testData.get(0).getId());
        elemUp.setName("стандарт");
        dao.update(elemUp);

        ServiceType elem = dao.getRow(elemUp.getId());
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
