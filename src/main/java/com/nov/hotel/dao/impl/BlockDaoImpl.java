package com.nov.hotel.dao.impl;

import com.nov.hotel.collections.impl.BlockCollection;
import com.nov.hotel.dao.abstr.CrudDaoAbstractInt;
import com.nov.hotel.entities.Block;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Repository("blockDao")
public class BlockDaoImpl extends CrudDaoAbstractInt<Block> {

    {
        nameDataBase = "blocks";
        sqlInsert = "INSERT INTO blocks (block_name_s) VALUES (:name)";
        sqlUpdate = "UPDATE blocks SET block_name_s= :name WHERE block_id_n = :id";
        sqlDelete = "DELETE FROM blocks WHERE block_id_n = :id";
        sqlSelectSingle = "SELECT * FROM blocks WHERE block_id_n";
        sqlSelectSome = "SELECT * FROM blocks WHERE block_name_s";
        sqlSelectAll = "SELECT * FROM blocks";
    }

    @Override
    protected MapSqlParameterSource getParams(Block elem) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id",elem.getId());
        params.addValue("name", elem.getName());
        return params;
    }

    private static final RowMapper<Block> rowMapper = new RowMapper<Block>() {
        @Override
        public Block mapRow(ResultSet rs, int rowNum) throws SQLException {

            Block block = new Block();
            block.setId(rs.getInt("block_id_n"));
            block.setName(rs.getString("block_name_s"));
            return BlockCollection.getInstance().putValue(block);
        }
    };

    @Override
    public RowMapper<Block> getRowMapper() {
        return rowMapper;
    }

    @Override
    protected void checkId(Block elem) {

    }
}
