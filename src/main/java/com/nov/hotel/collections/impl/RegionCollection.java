package com.nov.hotel.collections.impl;

import com.nov.hotel.collections.abstr.ObserCollecReposAbstract;
import com.nov.hotel.dao.impl.RegionDaoImpl;
import com.nov.hotel.dao.interfaces.CrudDao;
import com.nov.hotel.entities.Region;
import com.nov.hotel.main.Start;

public class RegionCollection extends ObserCollecReposAbstract<Integer, Region> {

    private RegionDaoImpl dao = (RegionDaoImpl) Start.APPLICATION_CONTEXT.getBean("regionDao");

    private static RegionCollection uniqueObsColl;

    private RegionCollection() {
    }

    public static RegionCollection getInstance() {
        if (uniqueObsColl == null){
            uniqueObsColl = new RegionCollection();
        }
        return uniqueObsColl;
    }

    @Override
    protected CrudDao getDao() {
        return dao;
    }
}
