package com.nov.hotel.services.impl;

import com.nov.hotel.dao.interfaces.CrudDao;
import com.nov.hotel.services.interfaces.GetService;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

abstract class GetServiceAbstract<E>  {

//    abstract CrudDao<E> getDao();
//
//
//    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
//    @Override
//    public E getById(int id) {
//        try {
//            return getDao().getRow(id);
//        } catch (DataAccessException e) {
//            return null;
//        }
//    }
//    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
//    @Override
//    public List<E> getByName(String name) {
//        try {
//            return getDao().getSelected(name);
//        } catch (DataAccessException e) {
//            return null;
//        }
//    }
//
//
//    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
//    @Override
//    public List<E> getAll() {
//        try {
//            return getDao().getAll();
//        } catch (DataAccessException e) {
//            return null;
//        }
//    }
//
//    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
//    @Override
//    public int count() {
//        return getDao().count();
//    }
}
