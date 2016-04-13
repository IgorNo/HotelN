package com.nov.hotel.entities.interfaces;

public interface Entity<K,V> {
    void assign(V elem);
    boolean validate();
    K getId();
    void setId(K id);
}
