package com.nov.hotel.dao.impl;

import com.nov.hotel.collections.impl.ApartStatusCollection;
import com.nov.hotel.dao.abstr.CrudDaoAbstrObject;
import com.nov.hotel.dao.abstr.CrudDaoAbstractInt;
import com.nov.hotel.dao.interfaces.CrudDao;
import com.nov.hotel.entities.ApartStatus;
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

@Repository("apartStatusDao")
public class ApartStatusDaoImpl extends CrudDaoAbstrObject<String, ApartStatus> {

    {
        nameDataBase = "apart_status";
        sqlInsert = "INSERT INTO apart_status (app_stat_id_s, app_stat_name_s, app_stat_color_s) " +
                "VALUES (:id, :name, :color)";
        sqlUpdate = "UPDATE apart_status SET app_stat_name_s= :name, app_stat_color_s= :color " +
                "WHERE app_stat_id_s = :id";
        sqlDelete = "DELETE FROM apart_status WHERE app_stat_id_s = :id";
        sqlSelectSingle = "SELECT * FROM apart_status WHERE app_stat_id_s";
        sqlSelectSome = "SELECT * FROM apart_status WHERE app_stat_name_s";
        sqlSelectAll = "SELECT * FROM apart_status";
    }

    @Override
    protected MapSqlParameterSource getParams(ApartStatus elem) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", elem.getId());
        params.addValue("name", elem.getName());
        params.addValue("color", elem.getColor());
        return params;
    }

    private static final RowMapper<ApartStatus> rowMapper = new RowMapper<ApartStatus>() {
        @Override
        public ApartStatus mapRow(ResultSet rs, int rowNum) throws SQLException {
            ApartStatus apartStatus = new ApartStatus();
            apartStatus.setId(rs.getString("app_stat_id_s"));
            apartStatus.setName(rs.getString("app_stat_name_s"));
            apartStatus.setColor(rs.getString("app_stat_color_s"));
            return ApartStatusCollection.getInstance().putValue(apartStatus);
        }
    };

    @Override
    protected RowMapper<ApartStatus> getRowMapper() {
        return rowMapper;
    }

    @Override
    protected void checkId(ApartStatus elem) {

    }
}
