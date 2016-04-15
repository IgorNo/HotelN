package com.nov.hotel.collections.impl;

import com.nov.hotel.collections.abstr.ObserCollecReposAbstract;
import com.nov.hotel.dao.impl.ApartmentDaoImpl;
import com.nov.hotel.dao.impl.InvoiceDaoImpl;
import com.nov.hotel.dao.interfaces.CrudDao;
import com.nov.hotel.entities.Invoice;

import static com.nov.hotel.main.Start.APPLICATION_CONTEXT;

public class InvoiceCollection extends ObserCollecReposAbstract<Long, Invoice> {

    private InvoiceDaoImpl dao = (InvoiceDaoImpl) APPLICATION_CONTEXT.getBean("invoiceDao");

    private static InvoiceCollection uniqueObsColl;

    private InvoiceCollection() {
    }

    public static InvoiceCollection getInstance() {
        if (uniqueObsColl == null){
            uniqueObsColl = new InvoiceCollection();
        }
        return uniqueObsColl;
    }

    @Override
    protected CrudDao getDao() {
        return dao;
    }

}
