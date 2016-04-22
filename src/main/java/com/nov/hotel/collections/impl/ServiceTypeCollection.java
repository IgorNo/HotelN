package com.nov.hotel.collections.impl;

import com.nov.hotel.collections.abstr.ObserCollecReposAbstract;
import com.nov.hotel.dao.impl.ServiceTypeDaoImpl;
import com.nov.hotel.dao.interfaces.CrudDao;
import com.nov.hotel.entities.ServiceType;
import com.nov.hotel.main.Start;

public class ServiceTypeCollection extends ObserCollecReposAbstract<Integer,ServiceType> {

    private ServiceTypeDaoImpl dao = (ServiceTypeDaoImpl) Start.APPLICATION_CONTEXT.getBean("serviceTypeDao");

    private static ServiceTypeCollection uniqueObsColl;

    private ServiceTypeCollection() {
    }

    public static ServiceTypeCollection getInstance() {
        if (uniqueObsColl == null){
            uniqueObsColl = new ServiceTypeCollection();
        }
        return uniqueObsColl;
    }

    @Override
    protected CrudDao getDao() {
        return dao;
    }
}
