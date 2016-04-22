package com.nov.hotel.dao.impl;
import com.nov.hotel.collections.impl.ServiceTypeCollection;
import com.nov.hotel.dao.abstr.CrudDaoAbstractInt;
import com.nov.hotel.entities.ServiceType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;


@Repository("serviceTypeDao")
public class ServiceTypeDaoImpl extends CrudDaoAbstractInt<ServiceType> {
    {
        nameDataBase = "serv_types";

        sqlInsert = "INSERT INTO serv_types (stype_name_s) VALUES (:name)";

        sqlUpdate = "UPDATE serv_types SET stype_name_s= :name WHERE stype_id_n = :id";

        sqlDelete = "DELETE FROM serv_types WHERE stype_id_n = :id";

        sqlSelectSingle = "SELECT * FROM serv_types WHERE stype_id_n";

        sqlSelectSome = "SELECT * FROM serv_types WHERE stype_name_s";

        sqlSelectAll = "SELECT * FROM serv_types";
    }

    @Override
    protected MapSqlParameterSource getParams(ServiceType elem) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id",elem.getId());
        params.addValue("name", elem.getName());
        return params;
    }

    private static final RowMapper<ServiceType> rowMapper = new RowMapper<ServiceType>() {
        @Override
        public ServiceType mapRow(ResultSet rs, int rowNum) throws SQLException {
            ServiceType type = new ServiceType();
            type.setId(rs.getInt("stype_id_n"));
            type.setName(rs.getString("stype_name_s"));
            return ServiceTypeCollection.getInstance().putValue(type);
        }
    };

    @Override
    public  RowMapper<ServiceType> getRowMapper() {
        return rowMapper;
    }

    @Override
    protected void checkId(ServiceType elem) { }

}
