package ca.sheridancollege.hoangjam.database;

import ca.sheridancollege.hoangjam.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabaseAccess {

    @Autowired
    NamedParameterJdbcTemplate jdbc;

    public void insertStudent(String name){

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "INSERT into STUDENT(name) VALUES(:name)";
        namedParameters.addValue("name", name);
        int rowsAffected = jdbc.update(query, namedParameters);
        if(rowsAffected > 0){
            System.out.println("The record was inserted successfully");
        }
    }

    public List<Student> getStudents(){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM STUDENT";

        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Student>(Student.class));
    }

}
