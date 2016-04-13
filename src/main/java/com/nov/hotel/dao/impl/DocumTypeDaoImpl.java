package com.nov.hotel.dao.impl;

import com.nov.hotel.collections.impl.DocumTypeCollection;
import com.nov.hotel.dao.abstr.CrudDaoAbstractInt;
import com.nov.hotel.dao.interfaces.CrudDao;
import com.nov.hotel.entities.DocumType;
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

@Repository("documTypeDao")
public class DocumTypeDaoImpl extends CrudDaoAbstractInt<DocumType> {

    {
          nameDataBase = "doc_types";

          sqlInsert = "INSERT INTO doc_types (doc_name_s) VALUES (:name)";
          sqlUpdate = "UPDATE doc_types SET doc_name_s= :name WHERE doc_id_n = :id";
          sqlDelete = "DELETE FROM doc_types WHERE doc_id_n = :id";
          sqlSelectSingle = "SELECT * FROM doc_types WHERE doc_id_n";
          sqlSelectSome = "SELECT * FROM doc_types WHERE doc_name_s";
          sqlSelectAll = "SELECT * FROM doc_types";
    }

    private static final RowMapper<DocumType> rowMapper = new RowMapper<DocumType>() {
        @Override
        public DocumType mapRow(ResultSet rs, int rowNum) throws SQLException {
            DocumType elem = new DocumType();
            elem.setId(rs.getInt("doc_id_n"));
            elem.setName(rs.getString("doc_name_s"));
            return DocumTypeCollection.getInstance().putValue(elem);
        }
    };

    @Override
    protected MapSqlParameterSource getParams(DocumType elem) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id",elem.getId());
        params.addValue("name", elem.getName());
        return params;
    }

    @Override
    protected void checkId(DocumType elem) {

    }

    @Override
    public RowMapper<DocumType> getRowMapper() {
        return rowMapper;
    }
}
