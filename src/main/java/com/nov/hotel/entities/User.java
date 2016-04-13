package com.nov.hotel.entities;

import com.nov.hotel.entities.interfaces.Entity;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User implements Entity<String, User>, Comparable<User> {

    private StringProperty id = new SimpleStringProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();

    public User() {
    }

    public User(User elem) {
        this();
        assign(elem);
    }

    @Override
    public void assign(User elem) {
        setId(elem.getId());
        setName(elem.getName());
        setPassword(elem.getPassword());
    }

    @Override
    public boolean validate() {
        return getId() != null && !getId().trim().isEmpty() && getName() != null && !getName().trim().isEmpty()
            && getName() != null && !getPassword().trim().isEmpty();
    }

    @Override
    public int compareTo(User o) {
        return getId().compareTo(o.getId());
    }

    @Override
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

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }
}
