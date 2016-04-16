package com.nov.hotel.entities;

import com.nov.hotel.entities.interfaces.Entity;
import javafx.beans.property.*;

public class ApartType implements Entity<Integer, ApartType>, Comparable<ApartType> {

    private Integer id = new Integer(0);
    private StringProperty name = new SimpleStringProperty();
    private IntegerProperty size = new SimpleIntegerProperty();
    private FloatProperty priceDay = new SimpleFloatProperty();
    private FloatProperty priceHour = new SimpleFloatProperty();
    private IntegerProperty nSlots = new SimpleIntegerProperty();
    private FloatProperty priceSlot = new SimpleFloatProperty();
    private StringProperty description = new SimpleStringProperty();


    public ApartType() { }

    public ApartType(ApartType apartType){
        this();
        assign(apartType);
    }

    public void assign(ApartType elem){
        setId(elem.getId());
        setName(elem.getName());
        setSize(elem.getSize());
        setPriceDay(elem.getPriceDay());
        setPriceHour(elem.getPriceHour());
        setPriceSlot(elem.getPriceSlot());
        setnSlots(elem.getnSlots());
        setDescription(elem.getDescription());
    }

    @Override
    public boolean validate() {
        return getName() != null && !getName().trim().isEmpty() && getSize() > 0 && getDescription() != null;
    }

    @Override
    public String toString() {
        return getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public int getSize() {
        return size.get();
    }

    public IntegerProperty sizeProperty() {
        return size;
    }

    public void setSize(int size) {
        this.size.set(size);
    }

    public float getPriceDay() {
        return priceDay.get();
    }

    public FloatProperty priceDayProperty() {
        return priceDay;
    }

    public void setPriceDay(float priceDay) {
        this.priceDay.set(priceDay);
    }

    public float getPriceHour() {
        return priceHour.get();
    }

    public FloatProperty priceHourProperty() {
        return priceHour;
    }

    public void setPriceHour(float priceHour) {
        this.priceHour.set(priceHour);
    }

    public int getnSlots() {
        return nSlots.get();
    }

    public IntegerProperty nSlotsProperty() {
        return nSlots;
    }

    public void setnSlots(int nSlots) {
        this.nSlots.set(nSlots);
    }

    public float getPriceSlot() {
        return priceSlot.get();
    }

    public FloatProperty priceSlotProperty() {
        return priceSlot;
    }

    public void setPriceSlot(float priceSlot) {
        this.priceSlot.set(priceSlot);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    @Override
    public int compareTo(ApartType o) {
        if (id - o.getId() < 0) return -1;
        if (id - o.getId() > 0) return 1;
        return 0;

    }
}
