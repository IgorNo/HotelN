package com.nov.hotel.collections.impl;

import com.nov.hotel.collections.abstr.ObserCollecReposAbstract;
import com.nov.hotel.dao.impl.PriceDaoImpl;
import com.nov.hotel.dao.interfaces.CrudDao;
import com.nov.hotel.entities.Price;
import com.nov.hotel.main.Start;

public class PriceCollection extends ObserCollecReposAbstract<Integer,Price> {


    private PriceDaoImpl dao = (PriceDaoImpl) Start.APPLICATION_CONTEXT.getBean("priceDao");

    private static PriceCollection uniqueObsColl;

    private PriceCollection() {
    }

    public static PriceCollection getInstance() {
        if (uniqueObsColl == null){
            uniqueObsColl = new PriceCollection();
        }
        return uniqueObsColl;
    }

    @Override
    protected CrudDao getDao() {
        return dao;
    }

}
