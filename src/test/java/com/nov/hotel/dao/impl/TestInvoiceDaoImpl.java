package com.nov.hotel.dao.impl;

import com.nov.hotel.collections.impl.ClientCollection;
import com.nov.hotel.collections.impl.UserCollection;
import com.nov.hotel.collections.interfaces.ObservableCollection;
import com.nov.hotel.entities.Invoice;
import com.nov.hotel.entities.User;
import com.nov.hotel.entities.interfaces.Customer;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/app-context.xml"})
public class TestInvoiceDaoImpl {
    private static Logger LOG = Logger.getLogger(TestInvoiceDaoImpl.class);
    @Autowired
    private InvoiceDaoImpl dao;

    private int count = 0;

    private static List<Invoice> testData = new LinkedList<>();
    private static List<Invoice> result = new LinkedList<>();

    ObservableCollection<Customer> customers = ClientCollection.getInstance().readAllData();
    ObservableCollection<User> users = UserCollection.getInstance().readAllData();
    @BeforeClass
    public static void setUpBeforClass(){
        int N = 2;
        for (Integer i = 0; i < N; i++) {
            Invoice elem = new Invoice();
            elem.setInvoiceN(i.toString());
            elem.setInvoiceDate(LocalDate.now());
            elem.setAmount(i);
            testData.add(elem);
        }
    }

    @Before
    public void setUp(){
        dao.deleteAll();
        assertEquals(0, dao.count());
        LOG.warn("\nTest Data:\n"+ testData.toString());
        if (count == 0) {
            List<Customer> ctl = customers.getViewList();
            List<User> dtl = users.getViewList();
            for (int i = 0; i < testData.size(); i++) {
                testData.get(i).setCustomer(ctl.get(i % ctl.size()));
                testData.get(i).setUser(dtl.get(i % dtl.size()));
            }
            count++;
        }
        for (Invoice x: testData) {
            dao.insert(x);
        }
        assertEquals(testData.size(), dao.count());
    }


    @Test
    public void testGetByName(){
        for (Invoice x: testData) {
            List<Invoice> selected = dao.getSelected(x.getInvoiceDate());
            for (Invoice y: selected) {
                assertEquals(x.getInvoiceDate(), y.getInvoiceDate());
            }
            LOG.warn("\ngetSelected Data:\n"+selected.toString());
        }
    }

    @Test
    public void testGetById(){
        for (Invoice x:testData ) {
            Invoice y = dao.getRow(x.getId());
            assertValues(x, y);
        }
        LOG.warn("\ngetRow Data:\n"+result.toString());
    }

    private void assertValues(Invoice x, Invoice y) {
        assertEquals(x.getId(),y.getId());
//        assertEquals(x.getCreateTime(),y.getCreateTime());
        assertEquals(x.getInvoiceN(),y.getInvoiceN());
        assertEquals(x.getInvoiceDate(),y.getInvoiceDate());
        assertEquals(x.getAmount(),y.getAmount(),0.001f);
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
        Invoice elemUp = testData.get(0);
        elemUp.setInvoiceN("11");
        elemUp.setAmount(11);
        elemUp.setInvoiceDate(LocalDate.now().plusDays(1));
        dao.update(elemUp);
        Invoice elem = dao.getRow(elemUp.getId());
        assertValues(elemUp, elem);
    }

    @Test
    public void testDelete(){
        result.clear();
        result = dao.getAll();
        dao.delete(result.get(0));
        assertEquals(testData.size()-1, dao.count());
    }

}
