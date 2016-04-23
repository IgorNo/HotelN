package com.nov.hotel.entities;

import com.nov.hotel.entities.interfaces.Entity;
import javafx.beans.property.*;

import java.lang.Integer;

public class Service implements Entity<Integer, Service>, Comparable<Service>{

    private Integer id = new Integer(0);
    private StringProperty name = new SimpleStringProperty();
    private FloatProperty price = new SimpleFloatProperty();
    private ObjectProperty<ServiceType> serviceType = new SimpleObjectProperty<>();
    private ObjectProperty<ServiceUnit>  serviceUnit = new SimpleObjectProperty<>();


    @Override
    public String toString() {
         return getName();
    }

    public Service() {
    }

    public Service(ServiceType serviceType, ServiceUnit serviceUnit) {
        setServiceType(serviceType);
        setServiceUnit(serviceUnit);
    }

    public Service(Service elem) {
        this();
        assign(elem);
    }

    @Override
    public void assign(Service elem) {
        setId(elem.getId());
        setName(elem.getName());
        setPrice(elem.getPrice());
        setServiceType(elem.getServiceType());
        setServiceUnit(elem.getServiceUnit());
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public int compareTo(Service o) {
        if (id - o.getId() < 0) return -1;
        if (id - o.getId() > 0) return 1;
        return 0;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
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

    public ServiceType getServiceType() {
        return serviceType.get();
    }

    public ObjectProperty<ServiceType> serviceTypeProperty() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType.set(serviceType);
    }

    public ServiceUnit getServiceUnit() {
        return serviceUnit.get();
    }

    public ObjectProperty<ServiceUnit> serviceUnitProperty() {
        return serviceUnit;
    }

    public void setServiceUnit(ServiceUnit serviceUnit) {
        this.serviceUnit.set(serviceUnit);
    }
}
