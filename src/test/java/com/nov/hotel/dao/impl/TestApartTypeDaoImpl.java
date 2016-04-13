package com.nov.hotel.dao.impl;

import com.nov.hotel.entities.ApartType;
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
public class TestApartTypeDaoImpl {

    private static Logger LOG = Logger.getLogger(TestApartTypeDaoImpl.class);
    @Autowired
    private ApartTypeDaoImpl apartTypeDao;

    private static ApartType apartType1 = new ApartType();
    private static ApartType apartType2 = new ApartType();
    private static ApartType apartType3 = new ApartType();
    private static List<ApartType> testData = new LinkedList<ApartType>();
    private static List<ApartType> result = new LinkedList<>();

    @BeforeClass
    public static void setUpBeforClass(){
        apartType1.setName("Одномісний номер");
        apartType1.setSize(1);
        apartType1.setPriceDay(100.00f);
        apartType1.setPriceHour(110.00f);
        apartType1.setPriceSlot(111.00f);
        apartType1.setPriceSlot(0);
        apartType1.setDescription("Description1");
        apartType2.setName("Двомісний номер");
        apartType2.setSize(2);
        apartType2.setPriceDay(200.00f);
        apartType2.setPriceHour(220.00f);
        apartType2.setPriceSlot(222.00f);
        apartType2.setPriceSlot(1);
        apartType2.setDescription("Description2");
        apartType3.setName("Тримісний номер");
        apartType3.setSize(3);
        apartType3.setPriceDay(300.00f);
        apartType3.setPriceHour(330.00f);
        apartType3.setPriceSlot(333.00f);
        apartType3.setPriceSlot(3);
        apartType3.setDescription("Description3");
        testData.add(apartType1);
        testData.add(apartType2);
        testData.add(apartType3);
     }

    @Before
    public void setUp(){
        apartTypeDao.deleteAll();
        assertEquals(0, apartTypeDao.count());
        LOG.warn("\nTest Data:\n"+ testData.toString());
        for (ApartType x: testData) {
            apartTypeDao.insert(x);
        }
        assertEquals(testData.size(), apartTypeDao.count());
    }


    @Test
    public void testGetByName(){
        result.clear();
        for (ApartType x: testData) {
            List<ApartType> type = apartTypeDao.getSelected(x.getName());
            result.add(type.get(0));
            assertEquals(x.getName(),result.get(result.size()-1).getName());
            assertEquals(x.getSize(),result.get(result.size()-1).getSize());
            assertEquals(x.getPriceDay(),result.get(result.size()-1).getPriceDay(),0.001f);
            assertEquals(x.getPriceHour(),result.get(result.size()-1).getPriceHour(),0.001f);
            assertEquals(x.getPriceSlot(),result.get(result.size()-1).getPriceSlot(),0.001f);
            assertEquals(x.getnSlots(),result.get(result.size()-1).getnSlots());
            assertEquals(x.getDescription(),result.get(result.size()-1).getDescription());
        }
        LOG.warn("\ngetSelected Data:\n"+result.toString());
    }

    @Test
    public void testGetById(){
        testGetByName();
        for (ApartType x:result ) {
            ApartType type = apartTypeDao.getRow(x.getId());
            assertEquals(x.getId(),type.getId());
            assertEquals(x.getName(),type.getName());
            assertEquals(x.getSize(),type.getSize());
            assertEquals(x.getPriceDay(),type.getPriceDay(),0.001f);
            assertEquals(x.getPriceHour(),type.getPriceHour(),0.001f);
            assertEquals(x.getPriceSlot(),type.getPriceSlot(),0.001f);
            assertEquals(x.getnSlots(),type.getnSlots());
            assertEquals(x.getDescription(),type.getDescription());
        }
        LOG.warn("\ngetRow Data:\n"+result.toString());
    }

    @Test
    public void testGetAll(){
        result.clear();
        result = apartTypeDao.getAll();
        for (int i = 0; i < testData.size(); i++) {
            ApartType testType = testData.get(i);
            ApartType type = result.get(i);
            assertEquals(testType.getName(),type.getName());
            assertEquals(testType.getSize(),type.getSize());
            assertEquals(testType.getPriceDay(),type.getPriceDay(),0.001f);
            assertEquals(testType.getPriceHour(),type.getPriceHour(),0.001f);
            assertEquals(testType.getPriceSlot(),type.getPriceSlot(),0.001f);
            assertEquals(testType.getnSlots(),type.getnSlots());
            assertEquals(testType.getDescription(),type.getDescription());

        }
        LOG.warn("\ngetAll Data:\n"+result.toString());
    }

    @Test
    public void testUpdate(){
        result.clear();
        result = apartTypeDao.getAll();
        ApartType testType = new ApartType();
        testType.setId(result.get(0).getId());
        testType.setName("Одномісний номер люкс");
        testType.setSize(1);
        testType.setPriceDay(500.00f);
        testType.setPriceHour(550.00f);
        testType.setPriceSlot(555.00f);
        testType.setnSlots(1);
        testType.setDescription("Description luxury");
        apartTypeDao.update(testType);
        ApartType type = apartTypeDao.getRow(testType.getId());
        assertEquals(testType.getId(),type.getId());
        assertEquals(testType.getName(),type.getName());
        assertEquals(testType.getSize(),type.getSize());
        assertEquals(testType.getPriceDay(),type.getPriceDay(),0.001f);
        assertEquals(testType.getPriceHour(),type.getPriceHour(),0.001f);
        assertEquals(testType.getPriceSlot(),type.getPriceSlot(),0.001f);
        assertEquals(testType.getnSlots(),type.getnSlots());
        assertEquals(testType.getDescription(),type.getDescription());
        LOG.warn("\nBefor Update:\n"+result.toString());
        LOG.warn("\nUpdate Data:\n"+ testType.toString());
        result = apartTypeDao.getAll();
        LOG.warn("\nAfter Update:\n"+result.toString());

    }

    @Test
    public void testDelete(){
        result.clear();
        result = apartTypeDao.getAll();
        apartTypeDao.delete(result.get(0));
        assertEquals(testData.size()-1, apartTypeDao.count());
    }
}
