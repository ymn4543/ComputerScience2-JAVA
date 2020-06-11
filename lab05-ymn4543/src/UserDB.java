/**
 * Description: UserDB implementation for lab 5.
 * Course: CS2
 * Language: java
 * @author Youssef Naguib </ymn4543@rit.edu>
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class UserDB implements DB<String, User>{

    /** Hashmap that stores a collection of Users */
    private HashMap<String,User> users;


    /**
     * UserDB constructor, creates a user database.
     */
    public UserDB(){
        this.users = new HashMap<>();
    }


    /**
     * Adds a value entry to the database.
     * The database will determine the key based on the username.
     * @param user user being added to database
     * @return previous user in same storage spot, null if no prev.
     */
    public User	addValue(User user){
        if(users.containsKey(user.getUsername())){
            User prev = users.get(user.getUsername());
            users.put(user.getUsername(),user);
            return prev;
        }
        users.put(user.getUsername(),user);
        return null;
    }


    /**
     * Get all the values in the database, where the elements are naturally ordered.
     * @return collection of users
     */
   public Collection<User> getAllValues(){
       ArrayList<User> userVals = new ArrayList<>(users.values());
       Collections.sort(userVals);
       return userVals;
   }

    /**
     * Get the value for an associated key
     * @param username key
     * @return value of key in UserDB
     */
    public User	getValue(String username){
        return users.get(username);
    }

    /**
     * Indicates whether a key is in the database or not.
     * @param username key
     * @return boolean
     */
    public boolean	hasKey(String username){
        return users.containsKey(username);
    }


}
