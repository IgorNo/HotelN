package com.nov.hotel.services.transactions.interfaces;

public interface ChangeTransaction<E> extends Transaction{
    E getElem();
}
