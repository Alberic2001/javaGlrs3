/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.list;

import dao.IDao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import models.User;

/**
 *
 * @author Albéric
 */
public class UserDao implements IDao<User> {
    private List<User> tableUser;
    private static int nbreUser = 2;
    
    public UserDao() {
        this.tableUser = new ArrayList<>(
        Arrays.asList(
                new User(1, "admin", "admin"),
               new User(2, "alberic", "alberic")
        )
        );
    }
    
    @Override
    public User add(User user) {
        //Generer ID
        user.setId(++nbreUser);
        
        this.tableUser.add(user);
        return user;
    }

    @Override
    public List<User> selectAll() {
        return this.tableUser;
    }
    
    
    

}
