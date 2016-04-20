package com.nov.hotel.dao.impl;

import com.nov.hotel.collections.impl.AllocationCollection;
import com.nov.hotel.dao.abstr.CrudDaoAbstractLong;
import com.nov.hotel.entities.*;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository("allocationDao")
public class AllocationDaoImpl extends CrudDaoAbstractLong<Allocation>{

    {
        nameDataBase = "inv_rooms";

        sqlInsert = "INSERT INTO inv_rooms (invd_invoice_fk, invd_apart_id_fk, invd_start_date_dt, invd_end_date_dt, " +
                "invd_dt_arriv_dt, invd_price_fk, invd_master_beds_n, invd_extra_beds_n, invd_apart_status_fk) " +
                "VALUES (:invoice, :room, :sDate, :eDate, :aDate, :price, :mBeds, :eBeds, :status)";

        sqlUpdate = "UPDATE inv_rooms SET invd_invoice_fk= :invoice, invd_apart_id_fk= :room, invd_start_date_dt= :sDate, invd_end_date_dt= :eDate, " +
                "invd_dt_arriv_dt= :aDate, invd_price_fk= :price, invd_master_beds_n=  :mBeds, invd_extra_beds_n= :eBeds, invd_apart_status_fk= :status " +
                "WHERE invd_id_n = :id";

        sqlDelete = "DELETE FROM inv_rooms WHERE invd_id_n = :id";
        sqlSelectSingle = "SELECT * FROM allocation_view WHERE invd_id_n";
        sqlSelectSome = "SELECT * FROM allocation_view WHERE invd_invoice_fk";
        sqlSelectAll = "SELECT * FROM allocation_view";

    }

    @Override
    protected MapSqlParameterSource getParams(Allocation elem) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", elem.getId());
        params.addValue("invoice", elem.getInvoiceId());
        params.addValue("room", elem.getRoom().getId());
        params.addValue("sDate", Timestamp.valueOf(LocalDateTime.of(elem.getStartDate(), elem.getStartTime())));
        params.addValue("eDate", Timestamp.valueOf(LocalDateTime.of(elem.getEndDate(), elem.getEndTime())));
        params.addValue("aDate", Timestamp.valueOf(elem.getArrivalDate()));
        params.addValue("price", elem.getPriceType().getId());
        params.addValue("mBeds", elem.getMasterBedsN());
        params.addValue("eBeds", elem.getExtraBedsN());
        params.addValue("status", elem.getAllocType().getId());
        return params;
    }
    private static final RowMapper<Allocation> rowMapper = new RowMapper<Allocation>() {
        @Override
        public Allocation mapRow(ResultSet rs, int rowNum) throws SQLException {

            ApartmentDaoImpl allocationDao = new ApartmentDaoImpl();
            ApartStatusDaoImpl apartStatusDao = new ApartStatusDaoImpl();
            PriceDaoImpl priceDao = new PriceDaoImpl();

            Apartment room = allocationDao.getRowMapper().mapRow(rs,rowNum);
            ApartStatus apartStatus = apartStatusDao.getRowMapper().mapRow(rs,rowNum);
            Price price = priceDao.getRowMapper().mapRow(rs,rowNum);

            Allocation allocation = new Allocation();
            allocation.setId(rs.getLong("invd_id_n"));
            allocation.setInvoiceId(rs.getLong("invd_invoice_fk"));
            allocation.setRoom(room);
            allocation.setStartDate(rs.getTimestamp("invd_start_date_dt").toLocalDateTime().toLocalDate());
            allocation.setStartTime(rs.getTimestamp("invd_start_date_dt").toLocalDateTime().toLocalTime());
            allocation.setEndDate(rs.getTimestamp("invd_end_date_dt").toLocalDateTime().toLocalDate());
            allocation.setEndTime(rs.getTimestamp("invd_end_date_dt").toLocalDateTime().toLocalTime());
            allocation.setArrivalDate(rs.getTimestamp("invd_dt_arriv_dt").toLocalDateTime());
            allocation.setPriceType(price);
            allocation.setMasterBedsN(rs.getInt("invd_master_beds_n"));
            allocation.setExtraBedsN(rs.getInt("invd_extra_beds_n"));
            allocation.setAllocType(apartStatus);

            return AllocationCollection.getInstance().putValue(allocation);
        }
    };
    
    @Override
    protected RowMapper<Allocation> getRowMapper() {
        return rowMapper;
    }

    @Override
    protected void checkId(Allocation elem) {

    }
}
