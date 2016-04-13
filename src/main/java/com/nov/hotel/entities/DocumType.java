package com.nov.hotel.entities;

import com.nov.hotel.entities.interfaces.Entity;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DocumType implements Entity<Integer, DocumType>, Comparable<DocumType> {

    private Integer id;
    private StringProperty name = new SimpleStringProperty();

    public DocumType() {
    }

    public DocumType(DocumType elem) {
        this();
        assign(elem);
    }

    @Override
    public boolean validate() {
        return getName() != null && !getName().trim().isEmpty();
    }

    @Override
    public void assign(DocumType elem) {
        setId(elem.getId());
        setName(elem.getName());
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

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int compareTo(DocumType o) {
        if (id - o.getId() < 0) return -1;
        if (id - o.getId() > 0) return 1;
        return 0;

    }
}
