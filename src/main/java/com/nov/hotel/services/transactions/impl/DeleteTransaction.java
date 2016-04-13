package com.nov.hotel.services.transactions.impl;

import com.nov.hotel.dao.interfaces.CrudDao;
import com.nov.hotel.services.transactions.interfaces.ChangeTransaction;
import com.nov.hotel.services.transactions.interfaces.Transaction;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class DeleteTransaction<K,E> implements ChangeTransaction<E>{

    E elem;
    CrudDao<K,E> dao;
    String exceptionMessage;

    public DeleteTransaction(CrudDao<K,E> dao, E elem) {
        this.elem = elem;
        this.dao = dao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public DeleteTransaction execute() {
        exceptionMessage = null;
        try {
            dao.delete(elem);
        } catch (DataAccessException e) {
            exceptionMessage = e.getLocalizedMessage();
        }
        return this;
    }

    @Override
    public String getExceptionMessage() {
        return exceptionMessage;
    }

    @Override
    public E getElem() {
        return elem;
    }
}
