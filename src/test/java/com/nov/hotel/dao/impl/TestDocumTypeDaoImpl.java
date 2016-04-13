package com.nov.hotel.dao.impl;

import com.nov.hotel.entities.DocumType;
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
public class TestDocumTypeDaoImpl {

    private static Logger LOG = Logger.getLogger(TestDocumTypeDaoImpl.class);
    @Autowired
    private DocumTypeDaoImpl dao;

    private static DocumType elem1 = new DocumType();
    private static DocumType elem2 = new DocumType();
    private static DocumType elem3 = new DocumType();
    private static DocumType elem4 = new DocumType();
    private static DocumType elem5 = new DocumType();
    private static DocumType elem6 = new DocumType();
    private static List<DocumType> testData = new LinkedList<DocumType>();
    private static List<DocumType> result = new LinkedList<>();

    @BeforeClass
    public static void setUpBeforClass(){
        elem1.setName("1");
        elem2.setName("Закордонний паспорт");
        elem3.setName("Посвідчення особи");
        elem4.setName("Війсковий квиток");
        elem5.setName("Свідоцтво про народження");
        elem6.setName("Посвідчення водія");
        testData.add(elem1);
        testData.add(elem2);
        testData.add(elem3);
        testData.add(elem4);
        testData.add(elem5);
        testData.add(elem6);
    }

    @Before
    public void setUp(){
        dao.deleteAll();
        assertEquals(0, dao.count());
        LOG.warn("\nTest Data:\n"+ testData.toString());
        for (DocumType x: testData) {
            dao.insert(x);
        }
        assertEquals(testData.size(), dao.count());
    }


    @Test
    public void testGetByName(){
        result.clear();
        for (DocumType x: testData) {
            List<DocumType> type = dao.getSelected(x.getName());
            result.add(type.get(0));
            assertEquals(x.getName(),result.get(result.size()-1).getName());
        }
        LOG.warn("\ngetSelected Data:\n"+result.toString());
    }

    @Test
    public void testGetById(){
        testGetByName();
        for (DocumType x:result ) {
            DocumType type = dao.getRow(x.getId());
            assertEquals(x.getId(),type.getId());
            assertEquals(x.getName(),type.getName());
        }
        LOG.warn("\ngetRow Data:\n"+result.toString());
    }

    @Test
    public void testGetAll(){
        result.clear();
        result = dao.getAll();
        for (int i = 0; i < testData.size(); i++) {
            DocumType testType = testData.get(i);
            DocumType resultType = result.get(i);
            assertEquals(testType.getName(),resultType.getName());
        }
        LOG.warn("\ngetAll Data:\n"+result.toString());
    }

    @Test
    public void testUpdate(){
        result.clear();
        result = dao.getAll();
        DocumType apartType = new DocumType();
        apartType.setId(result.get(0).getId());
        apartType.setName("Паспорт");
        dao.update(apartType);
        DocumType type = dao.getRow(apartType.getId());
        assertEquals(apartType.getId(),type.getId());
        assertEquals(apartType.getName(),type.getName());
        LOG.warn("\nBefor Update:\n"+result.toString());
        LOG.warn("\nUpdate Data:\n"+ apartType.toString());
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
