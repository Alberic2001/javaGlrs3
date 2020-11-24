/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.bd;

import dao.DaoMysql;
import dao.IDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Partenaire;

/**
 *
 * @author Albéric
 */
public class PartenaireDao implements IDao<Partenaire>{
    
    private final String SQL_INSERT = "INSERT INTO `user`(`login`, `pwd`, `type`, `nci`, `nomComplet`, `adresse`, `tel`, `matricule`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final String SQL_SELECT_ALL = "select * from user u, compte c where u.id_user=c.partenaire_id;";
    private DaoMysql daoMysql;

    public PartenaireDao() {
        daoMysql = new DaoMysql();
    }
    
    
    @Override
    public Partenaire add(Partenaire part) {
        try {
            daoMysql.getConnection();
            daoMysql.initPS(SQL_INSERT);
            PreparedStatement ps =daoMysql.getPstm();
            ps.setString(1, part.getLogin());
            ps.setString(2, part.getPwd());
            ps.setString(3, part.getType());
            ps.setString(4, part.getNci());
            ps.setString(5, part.getNomComplet());
            ps.setString(6, part.getAdresse());
            ps.setString(7, part.getTel());
            ps.setString(8, null);
            
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                int id = rs.getInt(1);
                part.setId(id);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PartenaireDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            daoMysql.CloseConnection();
        }
        
        return part;
    }

    @Override
    public List<Partenaire> selectAll() {
        daoMysql.getConnection();
        daoMysql.initPS(SQL_SELECT_ALL);
        PreparedStatement ps = daoMysql.getPstm();
        List<Partenaire> partenaires = new ArrayList();
        try {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Partenaire part;
                part = new Partenaire(rs.getString("nci"),
                        rs.getString("nomComplet"),
                        rs.getString("adresse"),
                        rs.getString("tel"),
                        rs.getInt("id_user"),
                        rs.getString("login"),
                        rs.getString("pwd"));
                
                partenaires.add(part);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            daoMysql.CloseConnection();
        }
        
        return partenaires;
    }
    
}
