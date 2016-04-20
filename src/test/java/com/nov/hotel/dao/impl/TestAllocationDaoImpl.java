package com.nov.hotel.dao.impl;

import com.nov.hotel.collections.impl.ApartStatusCollection;
import com.nov.hotel.collections.impl.ApartmentCollection;
import com.nov.hotel.collections.impl.InvoiceCollection;
import com.nov.hotel.collections.impl.PriceCollection;
import com.nov.hotel.collections.interfaces.ObservableCollection;
import com.nov.hotel.entities.*;
import com.nov.hotel.entities.interfaces.Customer;
import com.nov.hotel.main.Start;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/app-context.xml"})
public class TestAllocationDaoImpl {
    
    private static Logger LOG = Logger.getLogger(TestAllocationDaoImpl.class);
    @Autowired
    private AllocationDaoImpl dao;

    private int count = 0;

    private static List<Allocation> testData = new LinkedList<>();
    private static List<Allocation> result = new LinkedList<>();

//    private ObservableCollection<Invoice> invoices = InvoiceCollection.getInstance().readAllData();
//    private ObservableCollection<Apartment> rooms = ApartmentCollection.getInstance().readAllData();
//    private ObservableCollection<Price> prices = PriceCollection.getInstance().readAllData();
//    private ApartStatusCollection statuses = ApartStatusCollection.getInstance();

    @BeforeClass
    public static void setUpBeforClass(){
        int N = 4;

        for (Integer i = 0; i < N; i++) {
            Allocation elem = new Allocation();
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.of(12,0);
            elem.setStartDate(date);
            elem.setStartTime(time);
            elem.setEndDate(date.plusDays(i+1));
            elem.setEndTime(time);
            LocalDateTime dateTime = LocalDateTime.now();
            dateTime = dateTime.withNano(0).withSecond(0).withMinute(0);
            elem.setArrivalDate(dateTime.plusHours(1));
            testData.add(elem);
        }
    }

    private void assertValues(Allocation x, Allocation y) {
        assertEquals(x.getId(),y.getId());
        assertEquals(x.getStartDate(),y.getStartDate());
        assertEquals(x.getEndDate(),y.getEndDate());
        assertEquals(x.getArrivalDate(),y.getArrivalDate());
        assertEquals(x.getMasterBedsN(),y.getMasterBedsN());
        assertEquals(x.getExtraBedsN(),y.getExtraBedsN());
        assertEquals(x.getInvoiceId(),y.getInvoiceId());
        assertEquals(x.getRoom().getId(),y.getRoom().getId());
        assertEquals(x.getPriceType().getId(),y.getPriceType().getId());
        assertEquals(x.getAllocType().getId(),y.getAllocType().getId());
    }

    @Before
    public void setUp(){
        dao.deleteAll();
        assertEquals(0, dao.count());
        if (count == 0) {
            ObservableCollection<Invoice> invoices = InvoiceCollection.getInstance().readAllData();
            ObservableCollection<Apartment> rooms = ApartmentCollection.getInstance().readAllData();
            ObservableCollection<Price> prices = PriceCollection.getInstance().readAllData();
            ApartStatusCollection statuses = ApartStatusCollection.getInstance();
            statuses.readAllData();
            int nRoom = 2;
            List<Invoice> il = invoices.getViewList();
            List<Apartment> al = rooms.getViewList();
            List<Price> pl = prices.getViewList();

            ApartStatus status = new ApartStatus();
            for (ApartStatus x:statuses.getViewList()) {
                if (x.getId().equals("B"))
                    status = x;
            }
            for (int i = 0; i < testData.size(); i++) {
                testData.get(i).setInvoiceId(il.get( (i/nRoom % il.size()) ).getId());
                Apartment room = al.get(i % al.size());
                testData.get(i).setRoom(room);
                testData.get(i).setPriceType(pl.get(i % pl.size()));
                testData.get(i).setMasterBedsN(room.getType().getSize());
                testData.get(i).setExtraBedsN(room.getType().getnSlots());
                testData.get(i).setAllocType(status);
            }
            count++;
        }
        LOG.warn("\nTest Data:\n"+ testData.toString());
        for (Allocation x: testData) {
            dao.insert(x);
        }
        assertEquals(testData.size(), dao.count());
    }


    @Test
    public void testGetByName(){
        for (Allocation x: testData) {
            List<Allocation> selected = dao.getSelected(x.getInvoiceId());
            for (Allocation y: selected) {
                assertEquals(x.getInvoiceId(), y.getInvoiceId());
            }
            LOG.warn("\ngetSelected Data:\n"+selected.toString());
        }
    }

    @Test
    public void testGetById(){
        for (Allocation x:testData ) {
            Allocation y = dao.getRow(x.getId());
            assertValues(x, y);
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
        Allocation elem = testData.get(0);
        LocalDateTime dateTime = LocalDateTime.now();
        dateTime = dateTime.withNano(0).withSecond(0).withMinute(0).withHour(11);
        elem.setStartDate(dateTime.toLocalDate());
        elem.setStartTime(dateTime.toLocalTime());
        elem.setEndDate(dateTime.toLocalDate().plusDays(1));
        elem.setEndTime(dateTime.toLocalTime());
        elem.setArrivalDate(dateTime.plusHours(1));
        dao.update(elem);
        Allocation elemUp = dao.getRow(elem.getId());
        assertValues(elem, elemUp);
    }

    @Test
    public void testDelete(){
        result.clear();
        result = dao.getAll();
        dao.delete(result.get(0));
        assertEquals(testData.size()-1, dao.count());
    }

}
