package com.nov.hotel.dao.abstr;

import com.nov.hotel.entities.interfaces.Entity;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public abstract class CrudDaoAbstractInt<V extends Entity<Integer,V>> extends CrudDaoAbstract<Integer, V>{

    @Override
    public void insert(V elem) {
        checkId(elem);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sqlInsert, getParams(elem), keyHolder);
        elem.setId(keyHolder.getKey().intValue());
    }
}
