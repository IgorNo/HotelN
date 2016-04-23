package com.nov.hotel.entities;

import com.nov.hotel.entities.interfaces.Entity;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.lang.Integer;

public class ServiceUnit implements Entity<Integer, ServiceUnit>, Comparable<ServiceUnit> {

    private Integer id = new Integer(0);
    private StringProperty name = new SimpleStringProperty();

    @Override
    public String toString() {
        return  getName();
    }

    public ServiceUnit() {
    }

    public ServiceUnit(StringProperty name) {
        this.name = name;
    }

    public ServiceUnit(ServiceUnit elem) {
        this();
        assign(elem);
    }

    @Override
    public void assign(ServiceUnit elem) {
        setId(elem.getId());
        setName(elem.getName());
    }

    @Override
    public boolean validate() {
        return getName() != null && !getName().isEmpty();
    }

    @Override
    public int compareTo(ServiceUnit o) {
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
}