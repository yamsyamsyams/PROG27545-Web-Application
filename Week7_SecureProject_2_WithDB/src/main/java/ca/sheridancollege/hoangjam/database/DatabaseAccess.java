package ca.sheridancollege.hoangjam.database;

import ca.sheridancollege.hoangjam.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class DatabaseAccess {

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void addRole(Long userId, Long roleId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "INSERT INTO user_role(userId, roleId)"
                + " VALUES (:userId, :roleId)";
        parameters.addValue("userId", userId); parameters.addValue("roleId", roleId); jdbc.update(query,
                parameters);
    }

    public void addUser(String email, String password){
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "INSERT INTO sec_user"
                + "(email, encryptedPassword, enabled)"
                + " VALUES (:email, :encryptedPassword, 1)";
        parameters.addValue("email", email);
        parameters.addValue("encryptedPassword",
                passwordEncoder.encode(password));
        jdbc.update(query, parameters);
    }

    public User findUserAccount(String email) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT * FROM sec_user where email=:email";
        parameters.addValue("email", email);
        ArrayList<User> users = (ArrayList<User>) jdbc.query(query, parameters, new BeanPropertyRowMapper<User>(User.class));
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

    public List<String> getRolesById(Long userId) {
        ArrayList<String> roles = new ArrayList<String>();
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "select user_role.userId, sec_role.roleName "
                + "FROM user_role, sec_role "
                + "WHERE user_role.roleId=sec_role.roleId "
                + "AND userId=:userId";
        parameters.addValue("userId", userId);
        List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
        for (Map<String, Object> row : rows) {
            roles.add((String) row.get("roleName"));
        }
        return roles;
    }
}
