package com.nov.hotel.entities.interfaces;

import com.nov.hotel.entities.ClientType;
import javafx.beans.property.StringProperty;

public interface Customer {

    public StringProperty fullNameProperty();
    public ClientType getType();

}
