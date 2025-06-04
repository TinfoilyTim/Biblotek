/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblotek;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author 05timerl
 */
public class User {
    String username;
    String password;
    boolean loggedIn;
    List<String> borrowed;
    boolean admin;
    
    public User(String username, String password, Boolean loggedIn, ArrayList<String> borrowed, Boolean admin){
        this.username = username;
        this.password = password;
        this.loggedIn = false;
        this.borrowed = null;
        this.admin = false;
    }
    
}
    
