package com.nov.hotel.services.impl;

import com.nov.hotel.dao.impl.ApartmentDaoImpl;
import com.nov.hotel.entities.Apartment;
import com.nov.hotel.entities.RoomQuery;

import java.util.List;

import static com.nov.hotel.main.Start.APPLICATION_CONTEXT;

public class GetSelectedRoom {

    private ApartmentDaoImpl dao = (ApartmentDaoImpl) APPLICATION_CONTEXT.getBean("apartmentDao");

    private static GetSelectedRoom uniqueObsColl;

    private GetSelectedRoom() {
    }

    public static GetSelectedRoom getInstance() {
        if (uniqueObsColl == null){
            uniqueObsColl = new GetSelectedRoom();
        }
        return uniqueObsColl;
    }
    public List<Apartment> get(RoomQuery query){
        return dao.getSelectedRoom(query);
    }
}
