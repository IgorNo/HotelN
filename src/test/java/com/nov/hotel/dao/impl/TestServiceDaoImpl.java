package com.nov.hotel.dao.impl;

import com.nov.hotel.entities.Service;
import com.nov.hotel.entities.Service;
import com.nov.hotel.entities.ServiceType;
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
public class TestServiceDaoImpl {
    private static Logger LOG = Logger.getLogger(TestServiceDaoImpl.class);
    @Autowired
    private ServiceTypeDaoImpl ServiceTypeDao;
    @Autowired
    private ServiceUnitDaoImpl ServiceUnitDao;
    @Autowired
    private  ServiceDaoImpl dao;

    private static int count = 0;

    private static ServiceType ServiceType1 = new ServiceType();
    private static ServiceType ServiceType2 = new ServiceType();
    private static ServiceType ServiceType3 = new ServiceType();

    private static ServiceUnit ServiceUnit1 = new ServiceUnit();
    private static ServiceUnit ServiceUnit2 = new ServiceUnit();
    private static ServiceUnit ServiceUnit3 = new ServiceUnit();
    private static ServiceUnit ServiceUnit4 = new ServiceUnit();
    private static ServiceUnit ServiceUnit5 = new ServiceUnit();
    private static ServiceUnit ServiceUnit6 = new ServiceUnit();

    private static Service service1 = new Service();
    private static Service service2 = new Service();
    private static Service service3 = new Service();
    private static Service service4 = new Service();
    private static Service service5 = new Service();
    private static Service service6 = new Service();
    private static Service service7 = new Service();
    private static List<Service> testData = new LinkedList<>();
    private static List<Service> result = new LinkedList<>();


    @BeforeClass
    public static void setUpBeforClass(){
        ServiceType1.setName("стандарт");
        ServiceType2.setName("відпочинок");
        ServiceType3.setName("other");


        ServiceUnit1.setName("день");
        ServiceUnit2.setName("доба");
        ServiceUnit3.setName("одиниця");
        ServiceUnit4.setName("година");
        ServiceUnit5.setName("хвилина");
        ServiceUnit6.setName("unit");

        service1.setName("додаткове прибирання номеру");
        service1.setPrice(100.00f);
        service1.setServiceType(ServiceType1);
        service1.setServiceUnit(ServiceUnit3);

        service2.setName("більярд");
        service2.setPrice(300.00f);
        service2.setServiceType(ServiceType2);
        service2.setServiceUnit(ServiceUnit4);

        service3.setName("спа");
        service3.setPrice(200.00f);
        service3.setServiceType(ServiceType2);
        service3.setServiceUnit(ServiceUnit3);

        service4.setName("прокат велосипеду");
        service4.setPrice(100.00f);
        service4.setServiceType(ServiceType2);
        service4.setServiceUnit(ServiceUnit1);

        service5.setName("міні-бар напої");
        service5.setPrice(50.00f);
        service5.setServiceType(ServiceType1);
        service5.setServiceUnit(ServiceUnit3);

        service6.setName("телефон");
        service6.setPrice(20.00f);
        service6.setServiceType(ServiceType1);
        service6.setServiceUnit(ServiceUnit5);

        service7.setName("парковка");
        service7.setPrice(100.00f);
        service7.setServiceType(ServiceType3);
        service7.setServiceUnit(ServiceUnit6);


        testData.add(service1);
        testData.add(service2);
        testData.add(service3);
        testData.add(service4);
        testData.add(service5);
        testData.add(service6);
        testData.add(service7);
    }

    private void assertValues(Service x, Service elem) {
        assertEquals(x.getName(),elem.getName());
        assertEquals(x.getPrice(),elem.getPrice(), 0.01);
    }

    @Before
    public void setUp(){
        dao.deleteAll();
        assertEquals(0, dao.count());
        if (count == 0) {
            ServiceTypeDao.deleteAll();
            assertEquals(0, ServiceTypeDao.count());
            ServiceUnitDao.deleteAll();
            assertEquals(0, ServiceUnitDao.count());
            count++;
        }
        LOG.warn("\nTest Data:\n"+ testData.toString());
        for (Service x: testData) {
            dao.insert(x);
        }
        assertEquals(testData.size(), dao.count());
    }

    @Test
    public void testGetByName(){
        result.clear();
        for (Service x: testData) {
            List<Service> Services = dao.getSelected(x.getName());
            result.add(Services.get(0));
            Service elem = result.get(result.size()-1);
            assertValues(x, elem);
        }
        LOG.warn("\ngetSelected Data:\n"+result.toString());
    }

    @Test
    public void testGetById(){
        testGetByName();
        for (Service x:result ) {
            Service elem = dao.getRow(x.getId());
            assertEquals(x.getId(),elem.getId());
            assertValues(x, elem);
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
        result.clear();
        result = dao.getAll();
        Service testData = new Service();
        testData.setId(result.get(0).getId());
        testData.setName("cleaning");
        testData.setPrice(150.00f);
        testData.setServiceType(ServiceType3);
        testData.setServiceUnit(ServiceUnit6);
        dao.update(testData);
        Service elem = dao.getRow(testData.getId());
        assertValues(testData, elem);
        LOG.warn("\nBefor Update:\n"+result.toString());
        LOG.warn("\nUpdate Data:\n"+ testData.toString());
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
