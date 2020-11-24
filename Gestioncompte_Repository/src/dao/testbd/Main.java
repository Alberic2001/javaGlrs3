/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.testbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Partenaire;
import models.Caissier;
import models.User;

/**
 *
 * @author Albéric
 */
public class Main {
    
    private static Connection connexion;
    
    private static final String SELECT_ALL = "select * from user;";
    private static final String SELECT_BY_ONE = "select * from user where id=?;";
    private static final String SQL_INSERT = "INSERT INTO `user`(`login`, `pwd`, `type`, `nci`, `nomComplet`, `adresse`, `tel`, `matricule`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String SQL_UPDATE = "UPDATE `user` SET `login`=?,`pwd`=?,`type`=?,`nci`=?,`nomComplet`=?,`adresse`=?,`tel`=?,`matricule`=? WHERE `user`.`id` = ?;";
    
    
    
    public static List<User> findAll() throws SQLException{
        //3- Ecrire les requetes et executer les requetes
        //3.a Requetes Select
            PreparedStatement psmt = connexion.prepareStatement(SELECT_ALL);
            List<User> users;
        try (ResultSet rs = psmt.executeQuery()) {
            users = new ArrayList();
            //4- Recuperer le resultat
            while(rs.next()){
                System.out.println("ID "+rs.getInt("id")+" login "+rs.getString("login"));
                User user;
                if(rs.getString("type").compareTo("Caissier")==0){
                    user = new Caissier();
                    user.setId(rs.getInt("id"));
                    user.setLogin(rs.getString("login"));
                    ((Caissier)user).setMatricule(rs.getString("matricule"));
                } else {
                    user = new Partenaire(rs.getString("nci"),
                            rs.getString("nomComplet"),
                            rs.getString("adresse"),
                            rs.getString("tel"),
                            rs.getString("login"),
                            rs.getString("pwd"));
                }
                users.add(user);
            }   }
    return users;
    
    
    //NB: Entre deux requetes seuls les parties 
    //3 et 4 peuvent changer
    }
    
    
    
    
    public static User findOneBy(int id) throws SQLException{
        //3- Ecrire les requetes et executer les requetes
        //3.a Requetes Select
            PreparedStatement psmt = connexion.prepareStatement(SELECT_BY_ONE);
            psmt.setInt(1, id);
            User user;
        try (ResultSet rs = psmt.executeQuery()) {
            user = null;
            //4- Recuperer le resultat
            if(rs.next()){
                
                if(rs.getString("type").compareTo("Caissier")==0){
                    user = new Caissier();
                    user.setId(rs.getInt("id"));
                    user.setLogin(rs.getString("login"));
                    ((Caissier)user).setMatricule(rs.getString("matricule"));
                } else {
                    user = new Partenaire(rs.getString("nci"),
                            rs.getString("nomComplet"),
                            rs.getString("adresse"),
                            rs.getString("tel"),
                            rs.getString("login"),
                            rs.getString("pwd"));
                }
            }   }
    return user;
    
    
    //NB: Entre deux requetes seuls les parties 
    //3 et 4 peuvent changer
    }
    
    
    
    public static void persist(User user) throws SQLException{
        PreparedStatement psmt = connexion.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
        // `login`, `pwd`, `type`, `nci`, `nomComplet`, `adresse`, `tel`, `matricule`
        psmt.setString(1, user.getLogin());
        psmt.setString(2, user.getPwd());
        psmt.setString(3, user.getType());
        
       if(user.getType().compareTo("Caissier")==0){
           psmt.setString(4, null);
           psmt.setString(5, null);
           psmt.setString(6, null);
           psmt.setString(7, null);
           psmt.setString(8, ((Caissier)user).getMatricule());
       } else {
           psmt.setString(4, ((Partenaire)user).getNci());
           psmt.setString(5, ((Partenaire)user).getNomComplet());
           psmt.setString(6, ((Partenaire)user).getAdresse());
           psmt.setString(7, ((Partenaire)user).getTel());
           psmt.setString(8, null);
       }
       int nbreUserInsert = psmt.executeUpdate();
       ResultSet rs = psmt.getGeneratedKeys();
       if(rs.next()){
           int id = rs.getInt(1);
           System.out.println("Id Généré "+id);
       }
       
    }

    
    public static void main(String[] args) throws SQLException{
        
        try{
            //1- Ajouter et charger le Driver
            Class.forName("com.mysql.jdbc.Driver");
            //2- Connexion à la BD
            connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_compte", "root", "");
            // Traitement
            User user = findOneBy(2); // Selection User id 2
            user.setId(0); // id à 0
            user.setPwd("passer");
            persist(user); // Insertion de user dans la base
            findAll().forEach(System.out::println); // Selection de tous les users de la table user

            System.out.println("User Id: 1 "+ findOneBy(1));
        } catch (ClassNotFoundException ex) {
            System.err.println("Erreur de chargement du Driver");
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
        
    }
    
    
    
}
