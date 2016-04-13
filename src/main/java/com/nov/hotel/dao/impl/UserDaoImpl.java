package com.nov.hotel.dao.impl;

import com.nov.hotel.dao.abstr.CrudDaoAbstrObject;
import com.nov.hotel.entities.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("userDao")
public class UserDaoImpl extends CrudDaoAbstrObject<String, User> {

    {
        nameDataBase = "users";

        sqlInsert = "INSERT INTO users (user_name_s, user_full_name_s, user_password_s) VALUES (:id, :name, :password)";
        sqlUpdate = "UPDATE users SET user_full_name_s = :name, user_password_s = :password  WHERE user_name_s = :id";
        sqlDelete = "DELETE FROM users WHERE user_name_s = :id";
        sqlSelectSingle = "SELECT * FROM users WHERE user_name_s";
        sqlSelectSome = "SELECT * FROM users WHERE user_full_name_s";
        sqlSelectAll = "SELECT * FROM users";
    }

    @Override
    protected MapSqlParameterSource getParams(User elem) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id",elem.getId());
        params.addValue("name", elem.getName());
        params.addValue("password", elem.getPassword());
        return params;
    }

    private static final RowMapper<User> rowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User country = new User();
            country.setId(rs.getString("user_name_s"));
            country.setName(rs.getString("user_full_name_s"));
            country.setPassword(rs.getString("user_password_s"));
            return country;
        }
    };

    @Override
    public  RowMapper<User> getRowMapper() {
        return rowMapper;
    }

    @Override
    protected void checkId(User elem) { }

}
