package com.nov.hotel.entities;

import com.nov.hotel.collections.impl.AllocClientCollection;
import com.nov.hotel.collections.interfaces.ObservableCollection;
import com.nov.hotel.entities.interfaces.Entity;
import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;

public class Allocation implements Entity<Long, Allocation>, Comparable<Allocation> {

    private Long id = new Long(0);
    private Long invoiceId;
    private ObjectProperty<Apartment> room = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDateTime> startDate = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDateTime> endDate = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDateTime> arrivalDate = new SimpleObjectProperty<>();
    private ObjectProperty<Price> priceType = new SimpleObjectProperty<>();
    private IntegerProperty masterBedsN = new SimpleIntegerProperty();
    private IntegerProperty extraBedsN = new SimpleIntegerProperty();
    private ObjectProperty<ApartStatus> allocType = new SimpleObjectProperty<>();
    private ObservableList<AllocClient> clients = new SimpleListProperty<>();

    public Allocation() {
    }
    public Allocation(Allocation elem) {
        this();
        assign(elem);
    }

    @Override
    public void assign(Allocation elem) {
        setId(elem.getId());

        setStartDate(elem.getStartDate());
        setEndDate(elem.getEndDate());
        setArrivalDate(elem.getArrivalDate());
        setPriceType(elem.getPriceType());
        setMasterBedsN(elem.getMasterBedsN());
        setExtraBedsN(elem.getExtraBedsN());
        setAllocType(elem.getAllocType());
        setClients(elem.getClients());
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public int compareTo(Allocation o) {
        if (id - o.getId() < 0) return -1;
        if (id - o.getId() > 0) return 1;
        return 0;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Apartment getRoom() {
        return room.get();
    }

    public ObjectProperty<Apartment> roomProperty() {
        return room;
    }

    public void setRoom(Apartment room) {
        this.room.set(room);
    }

    public LocalDateTime getStartDate() {
        return startDate.get();
    }

    public ObjectProperty<LocalDateTime> startDateProperty() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate.set(startDate);
    }

    public LocalDateTime getEndDate() {
        return endDate.get();
    }

    public ObjectProperty<LocalDateTime> endDateProperty() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate.set(endDate);
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate.get();
    }

    public ObjectProperty<LocalDateTime> arrivalDateProperty() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate.set(arrivalDate);
    }

    public Price getPriceType() {
        return priceType.get();
    }

    public ObjectProperty<Price> priceTypeProperty() {
        return priceType;
    }

    public void setPriceType(Price priceType) {
        this.priceType.set(priceType);
    }

    public int getMasterBedsN() {
        return masterBedsN.get();
    }

    public IntegerProperty masterBedsNProperty() {
        return masterBedsN;
    }

    public void setMasterBedsN(int masterBedsN) {
        this.masterBedsN.set(masterBedsN);
    }

    public int getExtraBedsN() {
        return extraBedsN.get();
    }

    public IntegerProperty extraBedsNProperty() {
        return extraBedsN;
    }

    public void setExtraBedsN(int extraBedsN) {
        this.extraBedsN.set(extraBedsN);
    }

    public ApartStatus getAllocType() {
        return allocType.get();
    }

    public ObjectProperty<ApartStatus> allocTypeProperty() {
        return allocType;
    }

    public void setAllocType(ApartStatus allocType) {
        this.allocType.set(allocType);
    }

    public ObservableList<AllocClient> getClients() {
        return clients;
    }

    public void setClients(ObservableList<AllocClient> clients) {
        this.clients = clients;
    }
}
