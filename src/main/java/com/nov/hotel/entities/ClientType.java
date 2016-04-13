package com.nov.hotel.entities;

import com.nov.hotel.entities.interfaces.Entity;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ClientType implements Entity<Integer, ClientType>, Comparable<ClientType> {

    private Integer id;
    private StringProperty name = new SimpleStringProperty();
    private FloatProperty discount = new SimpleFloatProperty();
    private StringProperty color = new SimpleStringProperty();

    public ClientType() {
    }

    public ClientType(ClientType elem) {
        this();
        assign(elem);
    }

    @Override
    public void assign(ClientType elem) {
        setId(elem.getId());
        setName(elem.getName());
        setColor(elem.getColor());
    }

    @Override
    public boolean validate() {
        return getName() != null && !getName().trim().isEmpty() && getColor() != null && !getColor().trim().isEmpty() &&
                getColor().length() == 10 && getDiscount() > 0 && getDiscount() <= 1;
    }

    @Override
    public String toString() {
        return getName();
    }

    public Integer getId() {
        return id;
    }

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

    public float getDiscount() {
        return discount.get();
    }

    public FloatProperty discountProperty() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount.set(discount);
    }

    public String getColor() {
        return color.get();
    }

    public StringProperty colorProperty() {
        return color;
    }

    public void setColor(String color) {
        this.color.set(color);
    }

    @Override
    public int compareTo(ClientType o) {
        if (id - o.getId() < 0) return -1;
        if (id - o.getId() > 0) return 1;
        return 0;

    }
}
