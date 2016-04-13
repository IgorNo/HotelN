package com.nov.hotel.entities;

import com.nov.hotel.entities.interfaces.Entity;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Block implements Entity<Integer, Block>, Comparable<Block> {

    private Integer id = new Integer(0);;
    private StringProperty name = new SimpleStringProperty();

    public Block() { }

    public Block(Block block) {
        this();
        assign(block);
    }

    @Override
    public boolean validate() {
        return getName() != null && !getName().trim().isEmpty();
    }

    @Override
    public void assign(Block block) {
        setId(block.getId());
        setName(block.getName());
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
    public int compareTo(Block o) {
        if (id - o.getId() < 0) return -1;
        if (id - o.getId() > 0) return 1;
        return 0;

    }
}
