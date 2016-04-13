package com.nov.hotel.dao.impl;

import com.nov.hotel.dao.abstr.CrudDaoAbstractLong;
import com.nov.hotel.entities.Allocation;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

public class AllocationDaoImpl extends CrudDaoAbstractLong<Allocation>{
    {
        nameDataBase = "inv_rooms";

        sqlInsert = "INSERT INTO inv_rooms (invd_invoice_fk, invd_apart_id_fk, invd_start_date_d, invd_end_date_d, " +
                "invd_dt_arriv_dt, invd_price_fk, invd_master_beds_n, invd_extra_beds_n, invd_apart_status_fk " +
                "VALUES (:regDate, :surname, :name, :patronimic, :sex, :birthday, :citizenship, " +
                ":docType, :docSeries, :docNumber, :docDate, :docIssue, :regionAddress, :address, :type, :discount)";

        sqlUpdate = "UPDATE inv_rooms SET invd_invoice_fk= :regDate, invd_apart_id_fk= :surname, invd_start_date_d= :name, invd_end_date_d= :patronimic, " +
                "invd_dt_arriv_dt= :sex, invd_price_fk= :birthday, invd_master_beds_n=  :citizenship, invd_extra_beds_n= :docType, " +
                "invd_apart_status_fk= :docSeries " +
                "WHERE client_id_n = :id";

        sqlDelete = "DELETE FROM inv_rooms WHERE client_id_n = :id";
        sqlSelectSingle = "SELECT * FROM inv_rooms_view WHERE client_id_n";
        sqlSelectSome = "SELECT * FROM inv_rooms_view WHERE client_surname_s";
        sqlSelectAll = "SELECT * FROM inv_rooms_view";
    }

    @Override
    protected MapSqlParameterSource getParams(Allocation elem) {
        return null;
    }

    @Override
    protected RowMapper<Allocation> getRowMapper() {
        return null;
    }

    @Override
    protected void checkId(Allocation elem) {

    }
}
