package com.nov.hotel.dao.impl;

import com.nov.hotel.collections.impl.ApartTypeCollection;
import com.nov.hotel.dao.abstr.CrudDaoAbstractInt;
import com.nov.hotel.entities.ApartType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("apartTypeDao")
public class ApartTypeDaoImpl extends CrudDaoAbstractInt<ApartType> {
    {
          nameDataBase = "apart_types";

          sqlInsert = "INSERT INTO apart_types (app_typ_name_s, app_typ_sizing_n, app_typ_price1_n, app_typ_price2_n, app_typ_price3_n, app_typ_slot_n, app_typ_description_s) " +
                "VALUES (:name, :sizing, :price1, :price2, :price3, :nSlots, :description)";

          sqlUpdate = "UPDATE apart_types SET app_typ_name_s= :name, app_typ_sizing_n= :sizing, " +
                "app_typ_price1_n=  :price1, app_typ_price2_n= :price2, app_typ_price3_n= :price3, " +
                "app_typ_slot_n= :nSlots, app_typ_description_s= :description " +
                "WHERE app_typ_id_n = :id";

          sqlDelete = "DELETE FROM apart_types WHERE app_typ_id_n = :id";

          sqlSelectSingle = "SELECT * FROM apart_types WHERE app_typ_id_n";

          sqlSelectSome = "SELECT * FROM apart_types WHERE app_typ_name_s";

          sqlSelectAll = "SELECT * FROM apart_types";
    }

    protected MapSqlParameterSource getParams(ApartType apartType) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", apartType.getId());
        params.addValue("name", apartType.getName());
        params.addValue("sizing", apartType.getSize());
        params.addValue("price1", apartType.getPriceDay());
        params.addValue("price2", apartType.getPriceHour());
        params.addValue("price3", apartType.getPriceSlot());
        params.addValue("nSlots", apartType.getnSlots());
        params.addValue("description", apartType.getDescription());
        return params;
    }
    
    private static final RowMapper<ApartType> rowMapper = new RowMapper<ApartType>() {

        @Override
        public ApartType mapRow(ResultSet rs, int rowNum) throws SQLException {
            ApartType apartType = new ApartType();
            apartType.setId(rs.getInt("app_typ_id_n"));
            apartType.setName(rs.getString("app_typ_name_s"));
            apartType.setSize(rs.getInt("app_typ_sizing_n"));
            apartType.setPriceDay(rs.getFloat("app_typ_price1_n"));
            apartType.setPriceHour(rs.getFloat("app_typ_price2_n"));
            apartType.setPriceSlot(rs.getFloat("app_typ_price3_n"));
            apartType.setnSlots(rs.getInt("app_typ_slot_n"));
            apartType.setDescription(rs.getString("app_typ_description_s"));
            return ApartTypeCollection.getInstance().putValue(apartType);
        }
    };

    @Override
    public RowMapper<ApartType> getRowMapper() {
        return rowMapper;
    }

    @Override
    protected void checkId(ApartType elem) {

    }
}
