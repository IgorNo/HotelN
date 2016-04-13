package com.nov.hotel.dao.impl;

import com.nov.hotel.collections.impl.CountryCollection;
import com.nov.hotel.dao.abstr.CrudDaoAbstrObject;
import com.nov.hotel.entities.Country;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("countryDao")
public class CountryDaoImpl extends CrudDaoAbstrObject<String, Country> {
    {
          nameDataBase = "countries";

          sqlInsert = "INSERT INTO countries (country_id_iso_s, country_name_s) VALUES (:id, :name)";
          sqlUpdate = "UPDATE countries SET country_name_s= :name WHERE country_id_iso_s = :id";
          sqlDelete = "DELETE FROM countries WHERE country_id_iso_s = :id";
          sqlSelectSingle = "SELECT * FROM countries WHERE country_id_iso_s";
          sqlSelectSome = "SELECT * FROM countries WHERE country_name_s";
          sqlSelectAll = "SELECT * FROM countries";
    }
    @Override
    protected MapSqlParameterSource getParams(Country elem) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id",elem.getId());
        params.addValue("name", elem.getName());
        return params;
    }

    private static final RowMapper<Country> rowMapper = new RowMapper<Country>() {
        @Override
        public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
            Country country = new Country();
            country.setId(rs.getString("country_id_iso_s"));
            country.setName(rs.getString("country_name_s"));
            return CountryCollection.getInstance().putValue(country);
        }
    };

    @Override
    public  RowMapper<Country> getRowMapper() {
        return rowMapper;
    }

    @Override
    protected void checkId(Country elem) {

    }
}
