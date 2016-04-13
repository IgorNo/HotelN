package com.nov.hotel.collections.impl;

import com.nov.hotel.collections.abstr.ObserCollecReposAbstract;
import com.nov.hotel.dao.impl.UserDaoImpl;
import com.nov.hotel.dao.interfaces.CrudDao;
import com.nov.hotel.entities.User;
import com.nov.hotel.main.Start;

public class UserCollection extends ObserCollecReposAbstract<String,User> {

    private UserDaoImpl dao = (UserDaoImpl) Start.APPLICATION_CONTEXT.getBean("userDao");

    private static UserCollection uniqueObsColl;

    private UserCollection() {
    }

    public static UserCollection getInstance() {
        if (uniqueObsColl == null){
            uniqueObsColl = new UserCollection();
        }
        return uniqueObsColl;
    }

    @Override
    protected CrudDao getDao() {
        return dao;
    }
}
