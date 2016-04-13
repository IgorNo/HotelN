package com.nov.hotel.collections.impl;

import com.nov.hotel.collections.abstr.ObserCollecReposAbstract;
import com.nov.hotel.dao.impl.AllocationDaoImpl;
import com.nov.hotel.dao.interfaces.CrudDao;

import static com.nov.hotel.main.Start.APPLICATION_CONTEXT;

public class AllocationCollection extends ObserCollecReposAbstract {

    private AllocationDaoImpl dao = (AllocationDaoImpl) APPLICATION_CONTEXT.getBean("apartmentDao");

    private static AllocationCollection uniqueObsColl;

    private AllocationCollection() {
    }

    public static AllocationCollection getInstance() {
        if (uniqueObsColl == null){
            uniqueObsColl = new AllocationCollection();
        }
        return uniqueObsColl;
    }

    @Override
    protected CrudDao getDao() {
        return dao;
    }

}
