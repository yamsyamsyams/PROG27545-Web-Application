package ca.sheridancollege.hoangjam.database;

import ca.sheridancollege.hoangjam.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabaseAccess {

    @Autowired
    protected NamedParameterJdbcTemplate jdbc;

    public List<Team> getTeams(){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM teams";
        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Team>(Team.class));
    }

    public void addTeam(String teamName, String continent, int played, int won, int drawn, int lost){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "INSERT INTO teams (TeamName, Continent, Played, Won, Drawn, Lost) VALUES (:teamName, :continent, :played, :won, :drawn, :lost)";
        namedParameters.addValue("TeamName", teamName);
        namedParameters.addValue("Continent", continent);
        namedParameters.addValue("Played", played);
        namedParameters.addValue("Won", won);
        namedParameters.addValue("Drawn", drawn);
        namedParameters.addValue("Lost", lost);
        int rowsAffected = jdbc.update(query, namedParameters);
        if(rowsAffected > 0){
            System.out.println("Team was inserted successfully");
        }
    }

}
