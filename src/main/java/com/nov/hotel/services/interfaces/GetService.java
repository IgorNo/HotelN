package com.nov.hotel.services.interfaces;

import java.util.List;

public interface GetService<E,O,P> {

    //Read
    E getById(O id);

    //Read
    List<E> getByName(P name);

    //Read All
    List<E> getAll();

    //Count
    int count();
}
