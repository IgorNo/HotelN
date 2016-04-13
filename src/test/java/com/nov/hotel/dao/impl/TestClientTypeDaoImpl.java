package com.nov.hotel.dao.impl;

import com.nov.hotel.entities.ClientType;
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
public class TestClientTypeDaoImpl {

    private static Logger LOG = Logger.getLogger(TestClientTypeDaoImpl.class);
    @Autowired
    private ClientTypeDaoImpl dao;

    private static ClientType elem1 = new ClientType();
    private static ClientType elem2 = new ClientType();
    private static ClientType elem3 = new ClientType();
    private static ClientType elem4 = new ClientType();
    private static List<ClientType> testData = new LinkedList<ClientType>();
    private static List<ClientType> result = new LinkedList<>();

    @BeforeClass
    public static void setUpBeforClass(){
        elem1.setName("1");
        elem1.setColor("1");
        elem1.setDiscount(0.0f);
        elem2.setName("VIP");
        elem2.setColor("0xffff4dff");
        elem2.setDiscount(0.9f);
        elem3.setName("Важливий");
        elem3.setColor("0x4d66ccff");
        elem3.setDiscount(0.99f);
        elem4.setName("Дуже важливий");
        elem4.setColor("0xff6666ff");
        elem4.setDiscount(0.97f);
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
        for (ClientType x: testData) {
            dao.insert(x);
        }
        assertEquals(testData.size(), dao.count());
    }


    @Test
    public void testGetByName(){
        result.clear();
        for (ClientType x: testData) {
            List<ClientType> type = dao.getSelected(x.getName());
            result.add(type.get(0));
            assertEquals(x.getName(),result.get(result.size()-1).getName());
            assertEquals(x.getColor(),result.get(result.size()-1).getColor());
            assertEquals(x.getDiscount(),result.get(result.size()-1).getDiscount(),0.001f);
        }
        LOG.warn("\ngetSelected Data:\n"+result.toString());
    }

    @Test
    public void testGetById(){
        testGetByName();
        for (ClientType x:result ) {
            ClientType type = dao.getRow(x.getId());
            assertEquals(x.getId(),type.getId());
            assertEquals(x.getName(),type.getName());
            assertEquals(x.getColor(),type.getColor());
            assertEquals(x.getDiscount(),type.getDiscount(),0.001f);
        }
        LOG.warn("\ngetRow Data:\n"+result.toString());
    }

    @Test
    public void testGetAll(){
        result.clear();
        result = dao.getAll();
        for (int i = 0; i < testData.size(); i++) {
            ClientType testType = testData.get(i);
            ClientType resultType = result.get(i);
            assertEquals(testType.getName(),resultType.getName());
            assertEquals(testType.getColor(),resultType.getColor());
            assertEquals(testType.getDiscount(),resultType.getDiscount(),0.001f);
        }
        LOG.warn("\ngetAll Data:\n"+result.toString());
    }

    @Test
    public void testUpdate(){
        result.clear();
        result = dao.getAll();
        ClientType elem = new ClientType();
        elem.setId(result.get(0).getId());
        elem.setName("Звичайний");
        elem.setColor("0xccffffff");
        elem.setDiscount(1.0f);
        dao.update(elem);
        ClientType type = dao.getRow(elem.getId());
        assertEquals(elem.getId(),type.getId());
        assertEquals(elem.getName(),type.getName());
        assertEquals(elem.getColor(),type.getColor());
        LOG.warn("\nBefor Update:\n"+result.toString());
        LOG.warn("\nUpdate Data:\n"+ elem.toString());
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
