package com.nov.hotel.services.transactions.interfaces;

import java.util.List;

public interface GetTransaction<E> extends Transaction {
    List<E> getResult();
}
