package ca.sheridancollege.midterm.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DatabaseAccess {

    @Autowired
    protected NamedParameterJdbcTemplate jdbc;

    public void insertFruit(String fType, int weight, double subTotal){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "INSERT INTO fruit(fType) VALUES (:fType, :weight, :subTotal)";
        int rowsAffected = jdbc.update(query, namedParameters);
        if (rowsAffected > 0){
            System.out.println("The fruit was inserted successfully");
        }
    }
}
