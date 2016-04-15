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

//    protected String nameDataBase;
//    protected String sqlInsert;
//    protected String sqlUpdate;
//    protected String sqlDelete;
//    protected String sqlSelectSingle;
//    protected String sqlSelectSome;
//    protected String sqlSelectAll;

    {


        sqlInsert = "INSERT INTO alloc_clients (alloc_inv_room_fk, alloc_client_fk) VALUES (:idAlloc, :idClient)";
 //       sqlUpdate = "UPDATE alloc_clients SET alloc_inv_room_fk = :idAlloc  WHERE country_id_iso_s";
        sqlDelete = "DELETE FROM alloc_clients WHERE alloc_inv_room_fk = ";
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
            ClientType clientType = new ClientType();
            clientType.setId(rs.getInt("cltyp_id_n"));
            clientType.setName(rs.getString("cltyp_name_s"));
            clientType.setDiscount(rs.getFloat("cltyp_discount_n"));
            clientType.setColor(rs.getString("cltyp_color_s"));
            clientType = ClientTypeCollection.getInstance().putValue(clientType);

            DocumType documType = new DocumType();
            documType.setId(rs.getInt("doc_id_n"));
            documType.setName(rs.getString("doc_name_s"));
            documType = DocumTypeCollection.getInstance().putValue(documType);

            Country sitizenCountry = new Country();
            sitizenCountry.setId(rs.getString("citiz_country_id_iso_s"));
            sitizenCountry.setName(rs.getString("citiz_country_name_s"));
            sitizenCountry = CountryCollection.getInstance().putValue(sitizenCountry);

            Country addressCountry = new Country();
            addressCountry.setId(rs.getString("addr_country_id_iso_s"));
            addressCountry.setName(rs.getString("addr_country_name_s"));
            addressCountry = CountryCollection.getInstance().putValue(addressCountry);

            Region addressRegion = new Region();
            addressRegion.setId(rs.getInt("region_id_n"));
            addressRegion.setName(rs.getString("region_name_s"));
            addressRegion.setCountry(addressCountry);
            addressRegion = RegionCollection.getInstance().putValue(addressRegion);

            Client client = new Client();
            client.setId(rs.getLong("client_id_n"));
            client.setRegDate(rs.getTimestamp("client_regdate_d").toLocalDateTime());

            client.setSurname(rs.getString("client_surname_s"));
            client.setName(rs.getString("client_name_s"));
            client.setPatronymic(rs.getString("client_patronymic_s"));
            client.setSex(rs.getBoolean("client_sex_b"));
            client.setBirthday(rs.getDate("client_birthday_d").toLocalDate());
            client.setCitizenship(sitizenCountry);

            client.setDocType(documType);
            client.setDocSeries(rs.getString("client_docser_s"));
            client.setDocNumber(rs.getString("client_docnum_s"));
            client.setDocDate(rs.getDate("client_docdate_d").toLocalDate());
            client.setDocIssue(rs.getString("client_docissue_s"));

            client.setRegionAddress(addressRegion);
            client.setAddress(rs.getString("client_address_s"));

            client.setType(clientType);
            client.setDiscount(rs.getFloat("cltyp_discount_n"));
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
