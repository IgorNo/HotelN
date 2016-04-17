package com.nov.hotel.entities;

import com.nov.hotel.entities.interfaces.Entity;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class AllocClient{

    private long idAlloc;
    private Client client;

    public long getIdAlloc() {
        return idAlloc;
    }

    public void setIdAlloc(long idAlloc) {
        this.idAlloc = idAlloc;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
