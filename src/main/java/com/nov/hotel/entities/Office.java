package com.nov.hotel.entities;

import com.nov.hotel.entities.interfaces.Entity;

public class Office implements Entity<Long, Office>, Comparable<Office> {
    @Override
    public void assign(Office elem) {

    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public void setId(Long id) {

    }

    @Override
    public int compareTo(Office o) {
        return 0;
    }
}
