package com.nov.hotel.collections.impl;

import com.nov.hotel.collections.abstr.ObserCollecReposAbstract;
import com.nov.hotel.dao.impl.DocumTypeDaoImpl;
import com.nov.hotel.dao.interfaces.CrudDao;
import com.nov.hotel.entities.DocumType;
import com.nov.hotel.main.Start;

public class DocumTypeCollection extends ObserCollecReposAbstract<Integer, DocumType> {


    private DocumTypeDaoImpl dao = (DocumTypeDaoImpl) Start.APPLICATION_CONTEXT.getBean("documTypeDao");

    private static DocumTypeCollection uniqueObsColl;

    private DocumTypeCollection() {
    }

    public static DocumTypeCollection getInstance() {
        if (uniqueObsColl == null){
            uniqueObsColl = new DocumTypeCollection();
        }
        return uniqueObsColl;
    }

    @Override
    protected CrudDao getDao() {
        return dao;
    }
}
