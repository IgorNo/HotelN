package com.nov.hotel.collections.impl;

import com.nov.hotel.collections.abstr.ObserCollecReposAbstract;
import com.nov.hotel.dao.impl.ServiceUnitDaoImpl;
import com.nov.hotel.dao.interfaces.CrudDao;
import com.nov.hotel.entities.ServiceUnit;
import com.nov.hotel.main.Start;

public class ServiceUnitCollection extends ObserCollecReposAbstract<Integer,ServiceUnit> {

    private ServiceUnitDaoImpl dao = (ServiceUnitDaoImpl) Start.APPLICATION_CONTEXT.getBean("serviceUnitDao");

    private static ServiceUnitCollection uniqueObsColl;

    private ServiceUnitCollection() {
    }

    public static ServiceUnitCollection getInstance() {
        if (uniqueObsColl == null){
            uniqueObsColl = new ServiceUnitCollection();
        }
        return uniqueObsColl;
    }

    @Override
    protected CrudDao getDao() {
        return dao;
    }
}
