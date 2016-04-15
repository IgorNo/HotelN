package com.nov.hotel.collections.impl;

import com.nov.hotel.collections.abstr.ObserCollecReposAbstract;
import com.nov.hotel.dao.impl.ApartStatusDaoImpl;
import com.nov.hotel.dao.interfaces.CrudDao;
import com.nov.hotel.entities.ApartStatus;
import com.nov.hotel.main.Start;

public class ApartStatusCollection extends ObserCollecReposAbstract<String, ApartStatus> {

    private ApartStatusDaoImpl dao = (ApartStatusDaoImpl) Start.APPLICATION_CONTEXT.getBean("apartStatusDao");

    private static ApartStatusCollection uniqueObsColl;

    private ApartStatusCollection() {

    }

    public static ApartStatusCollection getInstance() {
        if (uniqueObsColl == null){
            uniqueObsColl = new ApartStatusCollection();
        }
        return uniqueObsColl;
    }

    @Override
    protected CrudDao getDao() {
        return dao;
    }

}
