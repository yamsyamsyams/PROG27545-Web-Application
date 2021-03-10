package ca.sheridancollege.hoangjam.service;


import ca.sheridancollege.hoangjam.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private DatabaseAccess da;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Find the user based on the username (read email)
        ca.sheridancollege.hoangjam.beans.User user = da.findUserAccount(username);

        // If the user doesn't exist throw an exception
        if (user == null) {
            System.out.println("User not found:" + username);
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }

        //Get a list of roles for that user
        List<String> roleNames = da.getRolesById(user.getUserId());

        // Change the list of the user's roles into a list of GrantedAuthority
        List<GrantedAuthority> grantList= new ArrayList<GrantedAuthority>();
        if (roleNames!= null) {
            for (String role : roleNames) {
                grantList.add(new SimpleGrantedAuthority(role));
            }
        }

        // Create a user based on the information above.
        // make sure User import is from the springframework one, not our User class
        UserDetails userDetails = (UserDetails) new User(user.getEmail(), user.getEncryptedPassword(), grantList);
        return userDetails;
    }
}
