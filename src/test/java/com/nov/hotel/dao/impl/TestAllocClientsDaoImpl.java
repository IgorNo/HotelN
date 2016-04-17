package com.nov.hotel.dao.impl;

import com.nov.hotel.collections.impl.AllocationCollection;
import com.nov.hotel.collections.impl.ClientCollection;
import com.nov.hotel.collections.interfaces.ObservableCollection;
import com.nov.hotel.entities.AllocClient;
import com.nov.hotel.entities.Allocation;
import com.nov.hotel.entities.Apartment;
import com.nov.hotel.entities.Client;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/app-context.xml"})
public class TestAllocClientsDaoImpl {

    private static Logger LOG = Logger.getLogger(TestAllocClientsDaoImpl.class);
    @Autowired
    private AllocClientsDaoImpl dao;

    private static int count = 0;

    private static List<AllocClient> testData = new LinkedList<>();
    private static List<AllocClient> result = new LinkedList<>();


    @Before
    public void setUp(){
        dao.deleteAll();
        assertEquals(0, dao.count());
        if (count == 0) {
            ObservableCollection<Allocation> allocations = AllocationCollection.getInstance().readAllData();
            ObservableCollection<Client> clients = ClientCollection.getInstance().readAllData();
            int nRoom = 2;
            List<Allocation> al = allocations.getViewList();
            List<Client> cl = clients.getViewList();

            int k = 0;
            for (int i = 0; i < al.size(); i++) {
                for (int j = 0; j < al.get(i).getRoom().getType().getSize(); j++) {
                    AllocClient elem = new AllocClient();
                    elem.setIdAlloc(al.get(i).getId());
                    elem.setClient(cl.get(k++ % cl.size()));
                    testData.add(elem);
                }
                for (int j = 0; j < al.get(i).getRoom().getType().getnSlots(); j++) {
                    AllocClient elem = new AllocClient();
                    elem.setIdAlloc(al.get(i).getId());
                    elem.setClient(cl.get(k++ % cl.size()));
                    testData.add(elem);
                }
            }
            count++;
        }
        LOG.warn("\nTest Data:\n"+ testData.toString());
        for (AllocClient x: testData) {
            dao.insert(x);
        }
        assertEquals(testData.size(), dao.count());
    }


    @Test
    public void testGetByName(){
        for (AllocClient x: testData) {
            List<AllocClient> selected = dao.getSelected(x.getIdAlloc());
            for (AllocClient y: selected) {
                assertEquals(x.getIdAlloc(), y.getIdAlloc());
            }
            LOG.warn("\ngetSelected Data:\n"+selected.toString());
        }
    }


    @Test
    public void testGetAll(){
        result.clear();
        result = dao.getAll();
        LOG.warn("\ngetAll Data:\n"+result.toString());
        assertEquals(testData.size(), result.size());
    }

    @Test
    public void testDelete(){
        result.clear();
        result = dao.getAll();
        dao.delete(result.get(0));
        assertEquals(testData.size()-1, dao.count());
    }

}
