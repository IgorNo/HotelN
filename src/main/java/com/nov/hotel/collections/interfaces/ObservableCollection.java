package com.nov.hotel.collections.interfaces;

import javafx.collections.ObservableList;

import java.util.Map;

public interface ObservableCollection<V> {
    // add records
    ObservableCollection add(V element);

    // update records
    ObservableCollection update(V newElem, V oldElem);

    // delete records
    ObservableCollection delete(V element);

    ObservableCollection clear();

    ObservableList<V> getViewList();

//    <K> Map<K, V> getRepository();

    ObservableCollection readAllData();

    <S> ObservableCollection readSelectedData(S sample);

    String saveChanges();

    void cancelChanges();

    boolean isChanged();

    // return value from repository with same Id
//    V putValue(V value);

}
