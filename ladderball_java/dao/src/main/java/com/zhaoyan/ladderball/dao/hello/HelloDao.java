package com.zhaoyan.ladderball.dao.hello;

import com.zhaoyan.ladderball.domain.hello.db.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class HelloDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Greeting sayHello() {
        final Greeting greeting = new Greeting();

        String sql = getRamdomRecordSql("id", "greeting", 1);
        Object[] params = new Object[]{};
        jdbcTemplate.query(sql, params, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                greeting.message = resultSet.getString("message");
            }
        });

        if (greeting.message == null) {
            greeting.message = "Hello.";
        }

        return greeting;
    }

    public static String getRamdomRecordSql(String idColumn, String table, int count) {
        String sql = "SELECT * FROM " + table + " WHERE " + idColumn + " >= ((SELECT MAX(" + idColumn +
                ") FROM " + table + " )-(SELECT MIN(" + idColumn + ") FROM " + table +
                " )) * RAND() + (SELECT MIN(" + idColumn + ") FROM " + table + " )  LIMIT " + count;
        return sql;
    }

}
