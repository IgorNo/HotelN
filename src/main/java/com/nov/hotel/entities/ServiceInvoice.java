package com.nov.hotel.entities;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ServiceInvoice {
    private long idInvoice;
    private ObjectProperty<Service> service = new SimpleObjectProperty<>();
    private FloatProperty quantity = new SimpleFloatProperty();
    private FloatProperty price = new SimpleFloatProperty();

    @Override
    public String toString() {
         return service.getName();
    }

    public ServiceInvoice() {
    }


    public ServiceInvoice(ServiceInvoice elem) {
        this();
        assign(elem);
    }


    public void assign(ServiceInvoice elem) {
        setIdInvoice(elem.getIdInvoice());
        setService(elem.getService());
        setQuantity(elem.getQuantity());
        setPrice(elem.getPrice());
    }

    public boolean validate() {
        return true;
    }


    public long getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(long idInvoice) {
        this.idInvoice = idInvoice;
    }

    public float getPrice() {
        return price.get();
    }

    public FloatProperty priceProperty() {
        return price;
    }

    public void setPrice(float price) {
        this.price.set(price);
    }

    public float getQuantity() {
        return quantity.get();
    }

    public FloatProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity.set(quantity);
    }

    public Service getService() {
        return service.get();
    }

    public ObjectProperty<Service> serviceProperty() {
        return service;
    }

    public void setService(Service service) {
        this.service.set(service);
    }
}