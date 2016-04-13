package com.nov.hotel.entities;

import com.nov.hotel.entities.interfaces.Entity;
import javafx.beans.property.*;

public class Apartment implements Entity<Long, Apartment>, Comparable<Apartment>{

    private Long id = new Long(0);;
    private StringProperty roomNumber = new SimpleStringProperty();
    private IntegerProperty levelNumber = new SimpleIntegerProperty();
    private ObjectProperty<Block> block = new SimpleObjectProperty<>();
    private BooleanProperty status = new SimpleBooleanProperty();
    private ObjectProperty<ApartType> type = new SimpleObjectProperty<>();

    public Apartment() {
    }

    public Apartment(ApartType type, Block block) {
        setType(type);
        setBlock(block);
    }

    public Apartment(Apartment apartment) {
        this();
        assign(apartment);
    }

    @Override
    public void assign(Apartment apartment) {
        setId(apartment.getId());
        setRoomNumber(apartment.getRoomNumber());
        setLevelNumber(apartment.getLevelNumber());
        setBlock(apartment.getBlock());
        setStatus(apartment.getStatus());
        setType(apartment.getType());
    }

    @Override
    public boolean validate() {
        return getRoomNumber() != null && !getRoomNumber().trim().isEmpty() && getBlock() != null && getType() != null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber.get();
    }

    public StringProperty roomNumberProperty() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber.set(roomNumber);
    }

    public int getLevelNumber() {
        return levelNumber.get();
    }

    public IntegerProperty levelNumberProperty() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber.set(levelNumber);
    }

    public boolean getStatus() {
        return status.get();
    }

    public BooleanProperty statusProperty() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status.set(status);
    }

    public Block getBlock() {
        return block.get();
    }

    public ObjectProperty<Block> blockProperty() {
        return block;
    }

    public void setBlock(Block block) {
        this.block.set(block);
    }

    public ApartType getType() {
        return type.get();
    }

    public ObjectProperty<ApartType> typeProperty() {
        return type;
    }

    public void setType(ApartType type) {
        this.type.set(type);
    }

    @Override
    public String toString() {
        return getRoomNumber();
    }

    @Override
    public int compareTo(Apartment o) {
        if (id - o.getId() < 0) return -1;
        if (id - o.getId() > 0) return 1;
        return 0;
    }
}
