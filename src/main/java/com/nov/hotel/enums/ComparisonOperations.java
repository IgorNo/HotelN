package com.nov.hotel.enums;

public enum ComparisonOperations {
    Less("<"), LE("<="), Equal("="), GE(">="), Greater(">");

    ComparisonOperations(String co) {
        strOper = co;
    }

    public String getStrOper() {
        return strOper;
    }

    private String strOper;
}
