package com.nov.hotel.entities;

import com.nov.hotel.enums.ComparisonOperations;
import com.nov.hotel.enums.CompositionType;

import java.time.LocalDateTime;

public class RoomQuery {
    Block block;
    Integer level;
    ComparisonOperations compOper;
    ApartType type;
    int mBedsN;
    int eBedN;
    LocalDateTime dtStart;
    LocalDateTime dtEnd;
    CompositionType compType;

    public ComparisonOperations getCompOper() {
        return compOper;
    }

    public void setCompOper(ComparisonOperations compOper) {
        this.compOper = compOper;
    }

    public CompositionType getCompType() {
        return compType;
    }

    public void setCompType(CompositionType compType) {
        this.compType = compType;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public ApartType getType() {
        return type;
    }

    public void setType(ApartType type) {
        this.type = type;
    }

    public int getmBedsN() {
        return mBedsN;
    }

    public void setmBedsN(int mBedsN) {
        this.mBedsN = mBedsN;
    }

    public int geteBedN() {
        return eBedN;
    }

    public void seteBedN(int eBedN) {
        this.eBedN = eBedN;
    }

    public LocalDateTime getDtStart() {
        return dtStart;
    }

    public void setDtStart(LocalDateTime dtStart) {
        this.dtStart = dtStart;
    }

    public LocalDateTime getDtEnd() {
        return dtEnd;
    }

    public void setDtEnd(LocalDateTime dtEnd) {
        this.dtEnd = dtEnd;
    }
}
