package com.nov.hotel.entities;

import com.nov.hotel.entities.interfaces.Entity;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Country implements Entity<String, Country>, Comparable<Country> {

    private StringProperty id = new SimpleStringProperty();
    private StringProperty name = new SimpleStringProperty();

    public Country() {
    }

    public Country(Country elem) {
        this();
        assign(elem);
    }
    @Override
    public void assign(Country elem) {
        setId(elem.getId());
        setName(elem.getName());
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public String toString() {
        return getName();
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
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

    @Override
    public int compareTo(Country o) {
        return getId().compareTo(o.getId());

    }
}
