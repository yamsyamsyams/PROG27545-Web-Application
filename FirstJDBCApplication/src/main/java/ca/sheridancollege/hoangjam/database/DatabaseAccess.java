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
    protected  NamedParameterJdbcTemplate jdbc;


    public void insertStudent(String name){

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query ="INSERT INTO student(name) VALUES(:name)";
        namedParameters.addValue("name", name);
        int rowsAffected = jdbc.update(query,namedParameters);
        if (rowsAffected > 0)
            System.out.println("The record was inserted successfully!");
    }


    public void deleteStudentByID(Long id){

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query ="DELETE FROM student WHERE id = :id";
        namedParameters.addValue("id", id);
        int rowsAffected = jdbc.update(query,namedParameters);
        if (rowsAffected > 0)
            System.out.println("The record was deleted successfully!");
    }

    public void editStudentByID(Student student){

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query ="UPDATE student set name= :name WHERE id = :id";
        namedParameters.addValue("name", student.getName());
        namedParameters.addValue("id", student.getId());
        int rowsAffected = jdbc.update(query,namedParameters);
        if (rowsAffected > 0)
            System.out.println("The record was updated successfully!");
    }

    public List<Student> getStudents(){

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query ="SELECT * FROM student";

        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Student>(Student.class));
    }


    public List<Student> getStudent(Long id){

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query ="SELECT * FROM student WHERE id = :id";
        namedParameters.addValue("id", id);

        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Student>(Student.class));
    }

}
