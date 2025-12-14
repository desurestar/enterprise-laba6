package ru.zagrebin.laba11.security;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<UserRecord> {
    @Override
    public UserRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserRecord rec = new UserRecord();
        rec.setUsername(rs.getString("username"));
        rec.setPassword(rs.getString("password"));
        rec.setRole(rs.getString("role")); // 'ROLE_USER' или 'ROLE_ADMIN'
        return rec;
    }
}
