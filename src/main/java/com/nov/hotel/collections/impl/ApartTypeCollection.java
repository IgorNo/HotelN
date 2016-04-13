package com.nov.hotel.collections.impl;

import com.nov.hotel.collections.abstr.ObserCollecReposAbstract;
import com.nov.hotel.dao.impl.ApartTypeDaoImpl;
import com.nov.hotel.dao.interfaces.CrudDao;
import com.nov.hotel.entities.ApartType;
import com.nov.hotel.main.Start;
import org.springframework.beans.factory.annotation.Autowired;


public class ApartTypeCollection extends ObserCollecReposAbstract<Integer, ApartType> {

    @Autowired
    private ApartTypeDaoImpl dao = (ApartTypeDaoImpl) Start.APPLICATION_CONTEXT.getBean("apartTypeDao");

    private static ApartTypeCollection uniqueObsColl;

    private ApartTypeCollection() {
    }

    public static ApartTypeCollection getInstance() {
        if (uniqueObsColl == null){
            uniqueObsColl = new ApartTypeCollection();
        }
        return uniqueObsColl;
    }

    @Override
    protected CrudDao getDao() {
        return dao;
    }
}
