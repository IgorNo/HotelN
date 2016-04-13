package com.nov.hotel.dao.impl;

import com.nov.hotel.entities.User;
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
public class TestUserDaoImpl {
    private static Logger LOG = Logger.getLogger(TestUserDaoImpl.class);
    @Autowired
    private UserDaoImpl dao;

    private static List<User> testData = new LinkedList<>();
    private static List<User> result = new LinkedList<>();

    @BeforeClass
    public static void setUpBeforClass(){
        int N = 4;
        for (Integer i = 0; i < N; i++) {
            User elem = new User();
            elem.setId("user"+i);
            elem.setName("Призвище"+i+" Ім'я"+i+" Побатькові"+i);
            elem.setPassword("user"+i);
            testData.add(elem);
        }
    }

    @Before
    public void setUp(){
        dao.deleteAll();
        assertEquals(0, dao.count());
        LOG.warn("\nTest Data:\n"+ testData.toString());
        for (User x: testData) {
            dao.insert(x);
        }
        assertEquals(testData.size(), dao.count());
    }


    @Test
    public void testGetByName(){
        for (User x: testData) {
            List<User> selected = dao.getSelected(x.getName());
            for (User y: selected) {
                assertEquals(x.getName(), y.getName());
            }
            LOG.warn("\ngetSelected Data:\n"+selected.toString());
        }
    }

    @Test
    public void testGetById(){
        for (User x:testData ) {
            User type = dao.getRow(x.getId());
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
        User elemUp = new User();
        elemUp.setId("user1");
        elemUp.setName("Петров Петро Петрович");
        elemUp.setPassword("user11");
        dao.update(elemUp);
        User elem = dao.getRow(elemUp.getId());
        assertEquals(elemUp.getId(),elem.getId());
        assertEquals(elemUp.getName(),elem.getName());
        assertEquals(elemUp.getPassword(),elem.getPassword());
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
