package com.nov.hotel.collections.impl;

import com.nov.hotel.collections.abstr.ObserCollecReposAbstract;
import com.nov.hotel.dao.impl.ClientDaoImpl;
import com.nov.hotel.dao.interfaces.CrudDao;
import com.nov.hotel.entities.Client;
import com.nov.hotel.main.Start;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientCollection extends ObserCollecReposAbstract<Long, Client> {

    @Autowired
    private ClientDaoImpl dao = (ClientDaoImpl) Start.APPLICATION_CONTEXT.getBean("clientDao");

    private static ClientCollection uniqueObsColl;

    private ClientCollection() {
    }

    public static ClientCollection getInstance() {
        if (uniqueObsColl == null){
            uniqueObsColl = new ClientCollection();
        }
        return uniqueObsColl;
    }

    @Override
    protected CrudDao getDao() {
        return dao;
    }

}
