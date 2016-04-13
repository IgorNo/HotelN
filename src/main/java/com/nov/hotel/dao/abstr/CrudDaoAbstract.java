package com.nov.hotel.dao.abstr;

import com.nov.hotel.dao.interfaces.CrudDao;
import com.nov.hotel.entities.interfaces.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

public abstract class CrudDaoAbstract<K, V> implements CrudDao<K,V>{

    protected String nameDataBase;
    protected String sqlInsert;
    protected String sqlUpdate;
    protected String sqlDelete;
    protected String sqlSelectSingle;
    protected String sqlSelectSome;
    protected String sqlSelectAll;

    protected NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    protected abstract MapSqlParameterSource getParams(V elem);

    protected abstract RowMapper<V> getRowMapper();

    protected abstract void checkId(V elem);

    @Override
    public void update(V elem) {
        jdbcTemplate.update(sqlUpdate, getParams(elem));
    }

    @Override
    public void delete(V elem) {
        jdbcTemplate.update(sqlDelete, getParams(elem));
    }

    @Override
    public V getRow(K id) {
        String sql = sqlSelectSingle + " = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbcTemplate.queryForObject(sql, params, getRowMapper());
    }

    @Override
    public <S extends Comparable> List<V> getSelected(S sample) {
        String sql = sqlSelectSome + " = :sample";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("sample", sample);

        return jdbcTemplate.query(sql, params, getRowMapper());
    }

    @Override
    public List<V> getAll() {
        return jdbcTemplate.query(sqlSelectAll, getRowMapper());
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM " + nameDataBase;
        jdbcTemplate.update(sql, new MapSqlParameterSource());
    }

    @Override
    public int count() {
        String sql = "select count(*) from " + nameDataBase;
        return jdbcTemplate.getJdbcOperations().queryForObject(sql, Integer.class);
    }

}
