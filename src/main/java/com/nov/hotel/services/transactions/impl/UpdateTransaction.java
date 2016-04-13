package com.nov.hotel.services.transactions.impl;

import com.nov.hotel.dao.interfaces.CrudDao;
import com.nov.hotel.services.transactions.interfaces.ChangeTransaction;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class UpdateTransaction<K,E> implements ChangeTransaction<E>{
    E newElem;
    E oldElem;
    CrudDao<K,E> dao;
    String exceptionMessage;

    public UpdateTransaction(CrudDao<K,E> dao, E newElem, E oldElem) {
        this.newElem = newElem;
        this.oldElem = oldElem;
        this.dao = dao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public UpdateTransaction execute() {
        exceptionMessage = null;
        try {
            dao.update(newElem);
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
        return newElem;
    }

    public E getOldElem() {
        return oldElem;
    }
}
