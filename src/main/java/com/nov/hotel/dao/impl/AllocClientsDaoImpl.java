package com.nov.hotel.dao.impl;

import com.nov.hotel.collections.impl.*;
import com.nov.hotel.dao.abstr.CrudDaoAbstrObject;
import com.nov.hotel.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("allocClientsDao")
public class AllocClientsDaoImpl extends CrudDaoAbstrObject<Object, AllocClient> {

    @Autowired
    ClientDaoImpl clientDao;

    {
        nameDataBase = "alloc_clients";

        sqlInsert = "INSERT INTO alloc_clients (alloc_inv_room_fk, alloc_client_fk) VALUES (:idAlloc, :idClient)";
 //       sqlUpdate = "UPDATE alloc_clients SET alloc_inv_room_fk = :idAlloc  WHERE country_id_iso_s";
        sqlDelete = "DELETE FROM alloc_clients WHERE alloc_inv_room_fk = :idAlloc AND alloc_client_fk= :idClient";
//        sqlSelectSingle = "SELECT * FROM alloc_clients WHERE alloc_inv_room_fk = idAlloc AND alloc_client_fk = idClient";
        sqlSelectSome = "SELECT * FROM alloc_clients_view WHERE alloc_inv_room_fk";
        sqlSelectAll = "SELECT * FROM alloc_clients_view";
    }
    
    @Override
    protected MapSqlParameterSource getParams(AllocClient elem) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idAlloc",elem.getIdAlloc());
        params.addValue("idClient",elem.getClient().getId());
        return params;
    }
    @Override
    public void update(AllocClient elem) {
        throw new UnsupportedOperationException("update");
    }


    @Override
    public AllocClient getRow(Object id) {
        throw new UnsupportedOperationException("getRow");
    }

    private static final RowMapper<AllocClient> rowMapper = new RowMapper<AllocClient>() {
        @Override
        public AllocClient mapRow(ResultSet rs, int rowNum) throws SQLException {
            ClientDaoImpl clientDao = new ClientDaoImpl();
            Client client = clientDao.getRowMapper().mapRow(rs, rowNum);
            client = ClientCollection.getInstance().putValue(client);

            AllocClient allocClient = new AllocClient();
            allocClient.setIdAlloc(rs.getLong("alloc_inv_room_fk"));
            allocClient.setClient(client);
            return allocClient;
        }
    };

    @Override
    public  RowMapper<AllocClient> getRowMapper() {
        return rowMapper;
    }

    @Override
    protected void checkId(AllocClient elem) {

    }

}
