package com.nov.hotel.services.transactions.impl;

import com.nov.hotel.dao.interfaces.CrudDao;
import com.nov.hotel.services.transactions.interfaces.GetTransaction;
import com.nov.hotel.services.transactions.interfaces.Transaction;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class GetAllTransaction<K,E> implements GetTransaction<E> {

    List<E> result;
    CrudDao<K,E> dao;
    String exceptionMessage;

    public GetAllTransaction(CrudDao<K,E> dao) {
        this.dao = dao;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public GetAllTransaction execute() {
        exceptionMessage = null;
        try {
            result = dao.getAll();
        } catch (DataAccessException e) {
            exceptionMessage = e.getLocalizedMessage();
        }
        return this;
    }

    @Override
    public List<E> getResult() {
        return result;
    }

    @Override
    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
