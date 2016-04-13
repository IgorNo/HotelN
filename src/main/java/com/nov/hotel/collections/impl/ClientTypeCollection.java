package com.nov.hotel.collections.impl;

import com.nov.hotel.collections.abstr.ObserCollecReposAbstract;
import com.nov.hotel.dao.impl.ClientTypeDaoImpl;
import com.nov.hotel.dao.interfaces.CrudDao;
import com.nov.hotel.entities.ClientType;
import com.nov.hotel.main.Start;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientTypeCollection extends ObserCollecReposAbstract<Integer, ClientType> {

    @Autowired
    private ClientTypeDaoImpl dao = (ClientTypeDaoImpl) Start.APPLICATION_CONTEXT.getBean("clientTypeDao");

    private static ClientTypeCollection uniqueObsColl;

    private ClientTypeCollection() {
    }

    public static ClientTypeCollection getInstance() {
        if (uniqueObsColl == null){
            uniqueObsColl = new ClientTypeCollection();
        }
        return uniqueObsColl;
    }

    @Override
    protected CrudDao getDao() {
        return dao;
    }
}
