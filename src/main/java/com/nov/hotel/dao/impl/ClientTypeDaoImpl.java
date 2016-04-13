package com.nov.hotel.dao.impl;

import com.nov.hotel.collections.impl.ClientTypeCollection;
import com.nov.hotel.dao.abstr.CrudDaoAbstractInt;
import com.nov.hotel.dao.interfaces.CrudDao;
import com.nov.hotel.entities.ClientType;
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

@Repository("clientTypeDao")
public class ClientTypeDaoImpl extends CrudDaoAbstractInt<ClientType> {

    {
          nameDataBase = "client_types";

          sqlInsert = "INSERT INTO client_types (cltyp_name_s, cltyp_color_s, cltyp_discount_n) VALUES (:name, :color, :discount)";
          sqlUpdate = "UPDATE client_types SET cltyp_name_s= :name, cltyp_discount_n= :discount, cltyp_color_s= :color  WHERE cltyp_id_n = :id";
          sqlDelete = "DELETE FROM client_types WHERE cltyp_id_n = :id";
          sqlSelectSingle = "SELECT * FROM client_types WHERE cltyp_id_n";
          sqlSelectSome = "SELECT * FROM client_types WHERE cltyp_name_s";
          sqlSelectAll = "SELECT * FROM client_types";
    }
    @Override
    protected MapSqlParameterSource getParams(ClientType elem) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id",elem.getId());
        params.addValue("name", elem.getName());
        params.addValue("color", elem.getColor());
        params.addValue("discount", elem.getDiscount());
        return params;
    }

    private static final RowMapper<ClientType> rowMapper = new RowMapper<ClientType>() {
        @Override
        public ClientType mapRow(ResultSet rs, int rowNum) throws SQLException {
            ClientType elem = new ClientType();
            elem.setId(rs.getInt("cltyp_id_n"));
            elem.setDiscount(rs.getFloat("cltyp_discount_n"));
            elem.setName(rs.getString("cltyp_name_s"));
            elem.setColor(rs.getString("cltyp_color_s"));
            return ClientTypeCollection.getInstance().putValue(elem);
        }
    };

    @Override
    public  RowMapper<ClientType> getRowMapper() {
        return rowMapper;
    }

    @Override
    protected void checkId(ClientType elem) {

    }
}
