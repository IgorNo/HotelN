package com.nov.hotel.collections.abstr;

import com.nov.hotel.collections.interfaces.ObservableCollection;
import com.nov.hotel.dao.interfaces.CrudDao;
import com.nov.hotel.entities.interfaces.Entity;
import com.nov.hotel.services.transactions.impl.*;
import com.nov.hotel.services.transactions.interfaces.ChangeTransaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

abstract public class ObserCollecReposAbstract<K, V extends Entity<K,V>> implements ObservableCollection<V>{

    private Map<K, V> repository = new TreeMap<>();

    private ObservableList<V> viewList = FXCollections.observableArrayList();

    private LinkedList<ChangeTransaction<V>> transactions = new LinkedList<>();

    private List<String> exceptMess = new LinkedList<>();

    // Factory Method
    protected abstract CrudDao getDao();

    @Override
    public ObservableCollection add(V element) {
        if (!element.validate()){
            return null;
        }
        addTransaction(new AddTransaction<>(getDao(),element));
        viewList.add(element);
        return this;
    }


    @Override
    public ObservableCollection update(V newElem, V oldElem) {
        if (!newElem.validate()){
            return null;
        }
        viewList.remove(oldElem);
        viewList.add(newElem);
        addTransaction(new UpdateTransaction<>(getDao(), newElem, oldElem));
        return this;
    }

    @Override
    public ObservableCollection delete(V element) {
        if (!element.validate()){
            return null;
        }
        addTransaction(new DeleteTransaction<>(getDao(),element));
        viewList.remove(element);
        return this;
    }

    @Override
    public boolean isChanged() {
        return transactions.size() > 0;
    }

    public ObservableList<V> getViewList() {
        return FXCollections.unmodifiableObservableList(viewList);
    }

//    public Map<K, V> getRepository() {
//        return repository;
//    }


    @Override
    public ObservableCollection readAllData() {
        GetAllTransaction<K,V> t = new GetAllTransaction(getDao());
        viewList.clear();
        viewList.addAll(t.execute().getResult());
        repository.clear();
        for (V v :viewList) repository.put(v.getId(), v);
        return this;
    }

    @Override
    public <S> ObservableCollection readSelectedData(S sample) {
        GetSeveralTransaction<K,V,S> t = new GetSeveralTransaction<>(getDao(), sample);
//        viewList.clear();
        viewList.addAll(t.execute().getResult());
        return this;
    }

    @Override
    public void cancelChanges() {
        while (!transactions.isEmpty()) {
            ChangeTransaction<V> t = transactions.getLast();
            if (t instanceof AddTransaction) {
                viewList.remove(t.getElem());
            } else {
                if(t instanceof UpdateTransaction ){
                    viewList.remove(t.getElem());
                    viewList.add( (V) ((UpdateTransaction) t).getOldElem() );
                } else {
                    if (t instanceof DeleteTransaction)
                        viewList.add(t.getElem());
                }
            }
            transactions.removeLast();
        }
    }

    @Override
    public String saveChanges() {
        executeTransactions();

        String errMsg = "";
        for (int i = 0; i < exceptMess.size(); i++) {
            errMsg += exceptMess.get(i);
        }
        exceptMess.clear();

        return errMsg;
    }



    private void addTransaction(ChangeTransaction<V> transaction){
        transactions.add(transaction);
    }

    private void executeTransactions(){
        while (!transactions.isEmpty()) {
            ChangeTransaction<V> t = transactions.getFirst();
            t.execute();
            if (t.getExceptionMessage() != null)
                exceptMess.add(t.getExceptionMessage());
            else {
                if (t instanceof AddTransaction) {
                    repository.put(t.getElem().getId(), t.getElem());
                } else {
                    if(t instanceof UpdateTransaction ){
                        repository.put(t.getElem().getId(), t.getElem());
                    } else {
                        if (t instanceof DeleteTransaction)
                            repository.remove(t.getElem().getId());
                    }
                }
                transactions.removeFirst();
            }
        }
    }

//    @Override
    public V putValue(V value) {
            K key = value.getId();
            if (repository.containsKey(key)) {
                repository.get(key).assign(value);
            } else
                repository.put(key, value);
            return repository.get(key);
    }

        @Override
    public ObservableCollection clear() {
        viewList.clear();
        return this;
    }
}
