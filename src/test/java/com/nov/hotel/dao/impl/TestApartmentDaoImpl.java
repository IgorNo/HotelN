package com.nov.hotel.dao.impl;

import com.nov.hotel.entities.ApartType;
import com.nov.hotel.entities.Apartment;
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
public class TestApartmentDaoImpl {

    private static Logger LOG = Logger.getLogger(TestApartmentDaoImpl.class);
    @Autowired
    private ApartTypeDaoImpl apartTypeDao;
    @Autowired
    private BlockDaoImpl blockDao;
    @Autowired
    private  ApartmentDaoImpl dao;

    private static int count = 0;

    private static ApartType apartType1 = new ApartType();
    private static ApartType apartType2 = new ApartType();
    private static ApartType apartType3 = new ApartType();

    private static Block block1 = new Block();
    private static Block block2 = new Block();
    private static Block block3 = new Block();
    private static Block block4 = new Block();

    private static Apartment apart1 = new Apartment();
    private static Apartment apart2 = new Apartment();
    private static Apartment apart3 = new Apartment();
    private static Apartment apart4 = new Apartment();
    private static Apartment apart5 = new Apartment();
    private static Apartment apart6 = new Apartment();
    private static Apartment apart7 = new Apartment();
    private static List<Apartment> testData = new LinkedList<>();
    private static List<Apartment> result = new LinkedList<>();

    @BeforeClass
    public static void setUpBeforClass(){
        block1.setName("Корпус 1");
        block2.setName("Корпус 2");
        block3.setName("Корпус 3");
        block4.setName("Корпус 4");

        apartType1.setName("Одномісний номер");
        apartType1.setSize(1);
        apartType1.setPriceDay(100.00f);
        apartType1.setPriceHour(5.00f);
        apartType1.setPriceSlot(50.00f);
        apartType1.setnSlots(1);
        apartType1.setDescription("Description1");
        apartType2.setName("Двомісний номер");
        apartType2.setSize(2);
        apartType2.setPriceDay(200.00f);
        apartType2.setPriceHour(10.00f);
        apartType2.setPriceSlot(100.00f);
        apartType2.setnSlots(1);
        apartType2.setDescription("Description2");
        apartType3.setName("Тримісний номер");
        apartType3.setSize(3);
        apartType3.setPriceDay(300.00f);
        apartType3.setPriceHour(15.00f);
        apartType3.setPriceSlot(150.00f);
        apartType3.setnSlots(2);
        apartType3.setDescription("Description3");

        apart1.setRoomNumber("101");
        apart1.setLevelNumber(1);
        apart1.setStatus(false);
        apart1.setBlock(block1);
        apart1.setType(apartType1);

        apart2.setRoomNumber("201");
        apart2.setLevelNumber(2);
        apart2.setStatus(false);
        apart2.setBlock(block2);
        apart2.setType(apartType2);

        apart3.setRoomNumber("301");
        apart3.setLevelNumber(3);
        apart3.setStatus(true);
        apart3.setBlock(block3);
        apart3.setType(apartType3);

        apart4.setRoomNumber("102");
        apart4.setLevelNumber(1);
        apart4.setStatus(false);
        apart4.setBlock(block4);
        apart4.setType(apartType1);

        apart5.setRoomNumber("202");
        apart5.setLevelNumber(2);
        apart5.setStatus(false);
        apart5.setBlock(block1);
        apart5.setType(apartType2);

        apart6.setRoomNumber("302");
        apart6.setLevelNumber(3);
        apart6.setStatus(false);
        apart6.setBlock(block2);
        apart6.setType(apartType3);

        apart7.setRoomNumber("402");
        apart7.setLevelNumber(4);
        apart7.setStatus(true);
        apart7.setBlock(block3);
        apart7.setType(apartType1);

        testData.add(apart1);
        testData.add(apart2);
        testData.add(apart3);
        testData.add(apart4);
        testData.add(apart5);
        testData.add(apart6);
        testData.add(apart7);
    }

    private void assertValues(Apartment x, Apartment elem) {
        assertEquals(x.getRoomNumber(),elem.getRoomNumber());
        assertEquals(x.getLevelNumber(),elem.getLevelNumber());
        assertEquals(x.getStatus(),elem.getStatus());
        assertEquals(x.getBlock().getId(),elem.getBlock().getId());
        assertEquals(x.getType().getId(),elem.getType().getId());
    }

    @Before
    public void setUp(){
        dao.deleteAll();
        assertEquals(0, dao.count());
        if (count == 0) {
            apartTypeDao.deleteAll();
            assertEquals(0, apartTypeDao.count());
            blockDao.deleteAll();
            assertEquals(0, apartTypeDao.count());
            count++;
        }
        LOG.warn("\nTest Data:\n"+ testData.toString());
        for (Apartment x: testData) {
            dao.insert(x);
        }
        assertEquals(testData.size(), dao.count());
    }

    @Test
    public void testGetByName(){
        result.clear();
        for (Apartment x: testData) {
            List<Apartment> apartments = dao.getSelected(x.getRoomNumber());
            result.add(apartments.get(0));
            Apartment elem = result.get(result.size()-1);
            assertValues(x, elem);
        }
        LOG.warn("\ngetSelected Data:\n"+result.toString());
    }

    @Test
    public void testGetById(){
        testGetByName();
        for (Apartment x:result ) {
            Apartment elem = dao.getRow(x.getId());
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
        Apartment testData = new Apartment();
        testData.setId(result.get(0).getId());
        testData.setRoomNumber("111");
        testData.setLevelNumber(2);
        testData.setStatus(false);
        testData.setBlock(block2);
        testData.setType(apartType2);
        dao.update(testData);
        Apartment elem = dao.getRow(testData.getId());
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
