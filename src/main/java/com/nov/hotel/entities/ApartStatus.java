package com.nov.hotel.entities;

import com.nov.hotel.entities.interfaces.Entity;
import javafx.beans.property.SimpleStringProperty;

public class ApartStatus implements Entity<Integer, ApartStatus>, Comparable<ApartStatus>{
    private int id;
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty color = new SimpleStringProperty();

    public ApartStatus() {    }

    public ApartStatus(ApartStatus apartType){
        this();
        assign(apartType);
    }

    public void assign(ApartStatus apartType){
        setId(apartType.getId());
        setName(apartType.getName());
        setColor(apartType.getColor());
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

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getColor() {
        return color.get();
    }

    public SimpleStringProperty colorProperty() {
        return color;
    }

    public void setColor(String color) {
        this.color.set(color);
    }

    @Override
    public boolean validate() {
        return getName() != null && !getName().trim().isEmpty() && getColor() != null && !getColor().trim().isEmpty() && getColor().length() == 10;
    }

    @Override
    public int compareTo(ApartStatus o) {
        return 0;
    }

}
