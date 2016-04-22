package com.nov.hotel.collections.impl;

import com.nov.hotel.collections.abstr.ObserCollecAbstract;
import com.nov.hotel.dao.impl.ServiceInvoiceDaoImpl;
import com.nov.hotel.dao.interfaces.CrudDao;
import com.nov.hotel.entities.ServiceInvoice;

import static com.nov.hotel.main.Start.APPLICATION_CONTEXT;

public class ServiceInvoiceCollection extends ObserCollecAbstract<ServiceInvoice> {

    private static ServiceInvoiceDaoImpl dao = (ServiceInvoiceDaoImpl) APPLICATION_CONTEXT.getBean("serviceInvoiceDao");

    private static ServiceInvoiceCollection uniqueObsColl;

    private ServiceInvoiceCollection() {
    }

    public static ServiceInvoiceCollection createInstance() {
        uniqueObsColl = new ServiceInvoiceCollection();
        return uniqueObsColl;
    }

    @Override
    protected CrudDao getDao() {
        return dao;
    }
   
}
