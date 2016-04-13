package com.nov.hotel.services.transactions.impl;

import com.nov.hotel.dao.interfaces.CrudDao;
import com.nov.hotel.services.transactions.interfaces.GetTransaction;
import com.nov.hotel.services.transactions.interfaces.Transaction;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class GetSeveralTransaction<K,E,S> implements GetTransaction<E>{

    List<E> result;
    CrudDao<K,E> dao;
    String exceptionMessage;
    S sample;

    public GetSeveralTransaction(CrudDao<K,E> dao, S sample) {
        this.dao = dao;
        this.sample = sample;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public GetSeveralTransaction execute() {
        try {
            result = dao.getSelected(sample);
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
        return null;
    }

}
