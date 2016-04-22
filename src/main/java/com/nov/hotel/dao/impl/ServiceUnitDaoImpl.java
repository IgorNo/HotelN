package com.nov.hotel.dao.impl;
import com.nov.hotel.collections.impl.ServiceUnitCollection;
import com.nov.hotel.dao.abstr.CrudDaoAbstractInt;
import com.nov.hotel.entities.ServiceUnit;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;


@Repository("serviceUnitDao")
public class ServiceUnitDaoImpl extends CrudDaoAbstractInt<ServiceUnit> {
    {
        nameDataBase = "units";

        sqlInsert = "INSERT INTO units (unit_name) VALUES (:name)";

        sqlUpdate = "UPDATE units SET unit_name= :name WHERE unit_id = :id";

        sqlDelete = "DELETE FROM units WHERE unit_id = :id";

        sqlSelectSingle = "SELECT * FROM units WHERE unit_id";

        sqlSelectSome = "SELECT * FROM units WHERE unit_name";

        sqlSelectAll = "SELECT * FROM units";

    }

    @Override
    protected MapSqlParameterSource getParams(ServiceUnit elem) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id",elem.getId());
        params.addValue("name", elem.getName());
        return params;
    }

    private static final RowMapper<ServiceUnit> rowMapper = new RowMapper<ServiceUnit>() {
        @Override
        public ServiceUnit mapRow(ResultSet rs, int rowNum) throws SQLException {
            ServiceUnit unit = new ServiceUnit();
            unit.setId(rs.getInt("unit_id"));
            unit.setName(rs.getString("unit_name"));
            return ServiceUnitCollection.getInstance().putValue(unit);
        }
    };

    @Override
    public  RowMapper<ServiceUnit> getRowMapper() {
        return rowMapper;
    }

    @Override
    protected void checkId(ServiceUnit elem) { }

}
