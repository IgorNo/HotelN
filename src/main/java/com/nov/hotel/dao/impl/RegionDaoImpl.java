package com.nov.hotel.dao.impl;

import com.nov.hotel.collections.impl.CountryCollection;
import com.nov.hotel.collections.impl.RegionCollection;
import com.nov.hotel.dao.abstr.CrudDaoAbstractInt;
import com.nov.hotel.dao.interfaces.CrudDao;
import com.nov.hotel.entities.Country;
import com.nov.hotel.entities.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("regionDao")
public class RegionDaoImpl extends CrudDaoAbstractInt<Region> {
    {
          nameDataBase = "regions";

          sqlInsert = "INSERT INTO regions (region_name_s, region_country_fk) VALUES (:name, :countryId)";
          sqlUpdate = "UPDATE regions SET region_name_s= :name, region_country_fk= :countryId WHERE region_id_n = :id";
          sqlDelete = "DELETE FROM regions WHERE region_id_n = :id";
          sqlSelectSingle = "SELECT * FROM regions_view WHERE region_id_n";
          sqlSelectSome = "SELECT * FROM regions_view WHERE region_country_fk";
          sqlSelectAll = "SELECT * FROM regions_view";
    }
    protected MapSqlParameterSource getParams(Region elem) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id",elem.getId());
        params.addValue("name", elem.getName());
        params.addValue("countryId", elem.getCountry().getId());
        return params;
    }
    
    private static final RowMapper<Region> rowMapper = new RowMapper<Region>() {
        @Override
        public Region mapRow(ResultSet rs, int rowNum) throws SQLException {
            Country country = new Country();
            country.setId(rs.getString("country_id_iso_s"));
            country.setName(rs.getString("country_name_s"));
            country = CountryCollection.getInstance().putValue(country);

            Region region = new Region();
            region.setId(rs.getInt("region_id_n"));
            region.setName(rs.getString("region_name_s"));
            region.setCountry(country);
            
            return RegionCollection.getInstance().putValue(region);
        }
    };

    @Override
    public RowMapper<Region> getRowMapper() {
        return rowMapper;
    }

    @Override
    protected void checkId(Region elem) {

    }
}
