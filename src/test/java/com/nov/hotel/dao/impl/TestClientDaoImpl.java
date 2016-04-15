package com.nov.hotel.dao.impl;

import com.nov.hotel.collections.impl.ClientTypeCollection;
import com.nov.hotel.collections.impl.DocumTypeCollection;
import com.nov.hotel.collections.impl.RegionCollection;
import com.nov.hotel.collections.interfaces.ObservableCollection;
import com.nov.hotel.entities.*;
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
public class TestClientDaoImpl {
    
    private static Logger LOG = Logger.getLogger(TestClientDaoImpl.class);
    @Autowired
    private  ClientTypeDaoImpl clienTypeDao;
    @Autowired
    private CountryDaoImpl countryDao;
    @Autowired
    private DocumTypeDaoImpl documTypeDao;
    @Autowired
    private RegionDaoImpl regionDao;
    @Autowired
    private  ClientDaoImpl dao;

    private int count = 0;

    private static Client client1 = new Client();
    private static Client client2 = new Client();
    private static Client client3 = new Client();
    private static Client client4 = new Client();
    private static Client client5 = new Client();
    private static Client client6 = new Client();
    private static Client client7 = new Client();
    private static List<Client> testData = new LinkedList<>();
    private static List<Client> result = new LinkedList<>();

    ObservableCollection<ClientType> clientTypes = ClientTypeCollection.getInstance().readAllData();
    ObservableCollection<DocumType> documTypes = DocumTypeCollection.getInstance().readAllData();
    ObservableCollection<Region> regions = RegionCollection.getInstance().readAllData();

    @BeforeClass
    public static void setUpBeforClass(){
        client1.setSurname("111");
        client1.setName("222");
        client1.setPatronymic("333");
        client1.setSex(true);
        client1.setBirthday(LocalDate.of(1963,11,1));
        client1.setDocSeries("ЕЕ");
        client1.setDocNumber("001001");
        client1.setDocDate(LocalDate.of(2001,7,7));
        client1.setDocIssue("Ізюмським МРВ УМВСУ в ХО");
        client1.setAddress("м.Ізюм, вул.Соборна 37, кв.1");
        client1.setDiscount(1.0f);

        client2.setSurname("Петренко");
        client2.setName("Марія");
        client2.setPatronymic("Володимірівна");
        client2.setSex(false);
        client2.setBirthday(LocalDate.of(1966,2,2));
        client2.setDocSeries("МК");
        client2.setDocNumber("001002");
        client2.setDocDate(LocalDate.of(2002,2,21));
        client2.setDocIssue("Ізюмським МРВ УМВСУ в ХО");
        client2.setAddress("м.Ізюм, вул.Соборна 37, кв.1");
        client2.setDiscount(1.0f);

        client3.setSurname("Петренко");
        client3.setName("Сергій");
        client3.setPatronymic("Петрович");
        client3.setSex(true);
        client3.setBirthday(LocalDate.of(1995,1,1));
        client3.setDocSeries("ММ");
        client3.setDocNumber("001111");
        client3.setDocDate(LocalDate.of(2011,3,3));
        client3.setDocIssue("Ізюмським МРВ УМВСУ в ХО");
        client3.setAddress("просп.Перемоги 50, кв.10");
        client3.setDiscount(1.0f);

        client4.setSurname("Cидоренко");
        client4.setName("Сидор");
        client4.setPatronymic("Сидорович");
        client4.setSex(true);
        client4.setBirthday(LocalDate.of(1970,4,14));
        client4.setDocSeries("МІ");
        client4.setDocNumber("201111");
        client4.setDocDate(LocalDate.of(1999,5,13));
        client4.setDocIssue("Бориспольским МРВ УМВСУ в КО");
        client4.setAddress("просп.Перемоги 55, кв.11");
        client4.setDiscount(0.99f);

        client5.setSurname("Cидоренко");
        client5.setName("Олена");
        client5.setPatronymic("Анатоліївна");
        client5.setSex(false);
        client5.setBirthday(LocalDate.of(1975,6,14));
        client5.setDocSeries("МЕ");
        client5.setDocNumber("201112");
        client5.setDocDate(LocalDate.of(1998,6,17));
        client5.setDocIssue("Бориспольским МРВ УМВСУ в КО");
        client5.setAddress("просп.Перемоги 55, кв.11");
        client5.setDiscount(0.99f);

        client6.setSurname("Cидоренко");
        client6.setName("Тетяна");
        client6.setPatronymic("Сидорівна");
        client6.setSex(false);
        client6.setBirthday(LocalDate.of(2001,8,18));
        client6.setDocSeries("ВВ");
        client6.setDocNumber("301112");
        client6.setDocDate(LocalDate.of(2001,8,28));
        client6.setDocIssue("Бориспольским МРВ УМВСУ в КО");
        client6.setAddress("просп.Перемоги 55, кв.11");
        client6.setDiscount(0.99f);

        client7.setSurname("McCartney");
        client7.setName("Paul");
        client7.setPatronymic("");
        client7.setSex(true);
        client7.setBirthday(LocalDate.of(1942,6,18));
        client7.setDocSeries("DSN");
        client7.setDocNumber("30112245");
        client7.setDocDate(LocalDate.of(1977,8,28));
        client7.setDocIssue("Liverpool");
        client7.setAddress("Linden street 77, fl.11");
        client7.setDiscount(0.99f);

        testData.add(client3);
        testData.add(client2);
        testData.add(client3);
        testData.add(client4);
        testData.add(client5);
        testData.add(client6);
        testData.add(client7);
    }

    private void assertValues(Client x, Client elem) {
//        assertEquals(x.getRegDate(),elem.getRegDate());

        assertEquals(x.getName(),elem.getName());
        assertEquals(x.getSurname(),elem.getSurname());
        assertEquals(x.getPatronymic(),elem.getPatronymic());

        assertEquals(x.getSex(),elem.getSex());
        assertEquals(x.getAddress(),elem.getAddress());
        assertEquals(x.getBirthday(),elem.getBirthday());

        assertEquals(x.getDocIssue(),elem.getDocIssue());
        assertEquals(x.getDocDate(),elem.getDocDate());
        assertEquals(x.getDocSeries(),elem.getDocSeries());
        assertEquals(x.getDocNumber(),elem.getDocNumber());

        assertEquals(x.getCitizenship().getId(),elem.getCitizenship().getId());
        assertEquals(x.getType().getId(),elem.getType().getId());
        assertEquals(x.getDocType().getId(),elem.getDocType().getId());
        assertEquals(x.getRegionAddress().getId(),elem.getRegionAddress().getId());
    }

    @Before
    public void setUp(){
        dao.deleteAll();
        assertEquals(0, dao.count());
        if (count == 0) {
            List<ClientType> ctl = clientTypes.getViewList();
            List<DocumType> dtl = documTypes.getViewList();
            Country citizenship = countryDao.getRow("UA");
            List<Region> rl = regions.getViewList().filtered((a) -> a.getCountry().getId().equals("UA"));
            for (int i = 0; i < testData.size(); i++) {
                testData.get(i).setType(ctl.get(i % ctl.size()));
                testData.get(i).setDocType(dtl.get(i % dtl.size()));
                if (i == testData.size()-1){
                    citizenship = countryDao.getRow("GB");
                    rl = regions.getViewList().filtered((a) -> a.getCountry().getId().equals("GB"));
                }
                testData.get(i).setRegionAddress(rl.get(i % rl.size()));
                testData.get(i).setCitizenship(citizenship);
            }
            citizenship = countryDao.getRow("UA");
            count++;
        }
        LOG.warn("\nTest Data:\n"+ testData.toString());
        for (Client x: testData) {
            dao.insert(x);
        }
        assertEquals(testData.size(), dao.count());
    }

    @Test
    public void testGetByName(){
        result.clear();
        for (Client x: testData) {
            List<Client> clients = dao.getSelected(x.getSurname());
            for (Client t: clients) {
                assertEquals(x.getSurname(), t.getSurname());
            }
        }
        LOG.warn("\ngetSelected Data:\n"+result.toString());
    }

    @Test
    public void testGetById(){
        for (Client x:testData ) {
            Client elem = dao.getRow(x.getId());
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
        Client td = testData.get(0);
        td.setId(result.get(0).getId());
        td.setSurname("Петренко");
        td.setName("Петро");
        td.setPatronymic("Петрович");
        td.setDocSeries("МН");
        td.setDocNumber("000000");
        dao.update(td);
        Client elem = dao.getRow(td.getId());
        assertValues(td, elem);
        LOG.warn("\nBefor Update:\n"+result.toString());
        LOG.warn("\nUpdate Data:\n"+ td.toString());
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
