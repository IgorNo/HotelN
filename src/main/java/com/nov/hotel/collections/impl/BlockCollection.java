package com.nov.hotel.collections.impl;

import com.nov.hotel.collections.abstr.ObserCollecReposAbstract;
import com.nov.hotel.dao.impl.BlockDaoImpl;
import com.nov.hotel.dao.interfaces.CrudDao;
import com.nov.hotel.entities.Block;
import com.nov.hotel.main.Start;

public class BlockCollection extends ObserCollecReposAbstract<Integer, Block> {

    private BlockDaoImpl dao = (BlockDaoImpl) Start.APPLICATION_CONTEXT.getBean("blockDao");

    private static BlockCollection uniqueObsColl;

    private BlockCollection() {
    }

    public static BlockCollection getInstance() {
        if (uniqueObsColl == null){
            uniqueObsColl = new BlockCollection();
        }
        return uniqueObsColl;
    }

    @Override
    protected CrudDao getDao() {
        return dao;
    }

}
