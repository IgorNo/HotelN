package com.nov.hotel.entities;

import com.nov.hotel.entities.interfaces.Entity;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Region implements Entity<Integer, Region>, Comparable<Region> {

    private int id;
    private StringProperty name = new SimpleStringProperty();
    private ObjectProperty<Country> country = new SimpleObjectProperty<>();

    public Region() {
    }

    public Region(Country country) {
        this();
        setCountry(country);
    }

    public Region(Region elem) {
        this();
        assign(elem);
    }

    @Override
    public void assign(Region elem) {
        setId(elem.getId());
        setName(elem.getName());
        setCountry(elem.getCountry());
    }

    @Override
    public boolean validate() {
        return getName() != null && !getName().trim().isEmpty() && getCountry() != null;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Country getCountry() {
        return country.get();
    }

    public ObjectProperty<Country> countryProperty() {
        return country;
    }

    public void setCountry(Country country) {
        this.country.set(country);
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int compareTo(Region o) {
        if (id - o.getId() < 0) return -1;
        if (id - o.getId() > 0) return 1;
        return 0;

    }
}
