package com.nov.hotel.collections.impl;

import com.nov.hotel.collections.abstr.ObserCollecAbstract;
import com.nov.hotel.collections.abstr.ObserCollecReposAbstract;
import com.nov.hotel.dao.impl.AllocClientsDaoImpl;
import com.nov.hotel.dao.interfaces.CrudDao;
import com.nov.hotel.entities.AllocClient;

import static com.nov.hotel.main.Start.APPLICATION_CONTEXT;

public class AllocClientCollection extends ObserCollecAbstract<AllocClient> {

    private static AllocClientsDaoImpl dao = (AllocClientsDaoImpl) APPLICATION_CONTEXT.getBean("alloClientcDao");

    private static AllocClientCollection uniqueObsColl;

    private AllocClientCollection() {
    }

    public static AllocClientCollection createInstance() {
        uniqueObsColl = new AllocClientCollection();
        return uniqueObsColl;
    }

    @Override
    protected CrudDao getDao() {
        return dao;
    }

}
