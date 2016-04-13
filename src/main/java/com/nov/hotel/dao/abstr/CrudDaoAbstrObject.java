package com.nov.hotel.dao.abstr;

import com.nov.hotel.entities.interfaces.Entity;

public abstract class CrudDaoAbstrObject<K,V> extends CrudDaoAbstract<K,V> {

    @Override
    public void insert(V elem) {
        checkId(elem);
        jdbcTemplate.update(sqlInsert, getParams(elem));
    }

}
