package com.nov.hotel.dao.impl;

import com.nov.hotel.collections.impl.PriceCollection;
import com.nov.hotel.dao.abstr.CrudDaoAbstractInt;
import com.nov.hotel.entities.Price;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("priceDao")
public class PriceDaoImpl extends CrudDaoAbstractInt<Price>{
    {
        nameDataBase = "prices";

        sqlInsert = "INSERT INTO prices (price_name_s, price_value_n) VALUES (:name, :price)";

        sqlUpdate = "UPDATE prices SET price_name_s= :name, price_value_n= :price WHERE price_id_n = :id";

        sqlDelete = "DELETE FROM prices WHERE price_id_n = :id";

        sqlSelectSingle = "SELECT * FROM prices WHERE price_id_n";

        sqlSelectSome = "SELECT * FROM prices WHERE price_name_s";

        sqlSelectAll = "SELECT * FROM prices";
    }

    protected MapSqlParameterSource getParams(Price elem) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", elem.getId());
        params.addValue("name", elem.getName());
        params.addValue("price", elem.getPrice());
        return params;
    }

    private static final RowMapper<Price> rowMapper = new RowMapper<Price>() {

        @Override
        public Price mapRow(ResultSet rs, int rowNum) throws SQLException {
            Price price = new Price();
            price.setId(rs.getInt("price_id_n"));
            price.setName(rs.getString("price_name_s"));
            price.setPrice(rs.getFloat("price_value_n"));
            return PriceCollection.getInstance().putValue(price);
        }
    };

    @Override
    public RowMapper<Price> getRowMapper() {
        return rowMapper;
    }

    @Override
    protected void checkId(Price elem) {

    }

}
