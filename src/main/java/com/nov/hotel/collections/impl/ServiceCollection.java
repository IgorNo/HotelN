package com.nov.hotel.collections.impl;

import com.nov.hotel.collections.abstr.ObserCollecReposAbstract;
import com.nov.hotel.dao.impl.ServiceDaoImpl;
import com.nov.hotel.dao.interfaces.CrudDao;
import com.nov.hotel.entities.Service;
import com.nov.hotel.main.Start;

public class ServiceCollection extends ObserCollecReposAbstract<Integer,Service> {

    private ServiceDaoImpl dao = (ServiceDaoImpl) Start.APPLICATION_CONTEXT.getBean("serviceDao");

    private static ServiceCollection uniqueObsColl;

    private ServiceCollection() {
    }

    public static ServiceCollection getInstance() {
        if (uniqueObsColl == null){
            uniqueObsColl = new ServiceCollection();
        }
        return uniqueObsColl;
    }

    @Override
    protected CrudDao getDao() {
        return dao;
    }
}
