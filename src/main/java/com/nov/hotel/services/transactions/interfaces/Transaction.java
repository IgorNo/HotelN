package com.nov.hotel.services.transactions.interfaces;

// Pattern Transaction
public interface Transaction {

    Transaction execute();

    String getExceptionMessage();
}
