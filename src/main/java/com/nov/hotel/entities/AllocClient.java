package com.nov.hotel.entities;

import com.nov.hotel.entities.interfaces.Entity;
import javafx.beans.property.*;

import java.time.LocalDate;

public class
AllocClient{

    private long idAlloc;
    private Client client;

    public AllocClient() {
    }

    public AllocClient(long idAlloc, Client client) {
        this.idAlloc = idAlloc;
        this.client = client;
    }

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

    public StringProperty fullNameProperty(){
        return client.fullNameProperty();
    }
    public BooleanProperty sexProperty() {
        return client.sexProperty();
    }
    public ObjectProperty<LocalDate> birthdayProperty() {
        return client.birthdayProperty();
    }

    public StringProperty passportProperty(){
        return client.passportProperty();
    }

    public ObjectProperty<Country> citizenshipProperty() {
        return client.citizenshipProperty();
    }




}
