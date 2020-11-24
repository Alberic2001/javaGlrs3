/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.IDao;
import dao.bd.CompteDao;
import dao.list.PartenaireDao;
import dao.list.UserDao;
import java.util.List;
import java.util.stream.Collectors;
import models.Compte;
import models.Depot;
import models.Partenaire;
import models.User;

/**
 *
 * @author Albéric
 */
public class Transaction {
    PartenaireDao daoPartenaire;
    IDao<Compte> daoCompte;
    UserDao userDao;
    
    //Depot depot;

    public Transaction() {
        this.daoPartenaire = new PartenaireDao();
        this.daoCompte = new CompteDao();
        this.userDao = new UserDao();
    }

    
    public Compte addCompteTransaction(Compte compte){
        // Partenaire n'existe pas
        if(compte.getPartenaire().getId()==0){
            this.addComptePartenaire(compte.getPartenaire());
        }
        compte = daoCompte.add(compte);
        this.setDepot(compte, compte.getSolde());
        return compte;
    }
    
    public Partenaire addComptePartenaire(Partenaire partenaire){
        return this.daoPartenaire.add(partenaire);
    }
    
    
    public User addCompteConnexion(User user){
        return null;
    }
    
    public Compte setDepot(Compte compte, double mnt){
        Depot depot = new Depot(mnt);
        compte.getDepots().add(depot);
        return compte;
    }
    
    public Partenaire getPartenaireByNci(String nci){
        List<Partenaire> partenaires = daoPartenaire.selectAll();
        for(Partenaire part : partenaires){
            if(part.getNci().compareTo(nci)==0){
                return part;
            }
        }
        return null; 
    }
    
    public List<Compte> getCompteByPartenaire(Partenaire part){
        List<Compte> comptes = daoCompte.selectAll();
        /*for(Compte cpt : comptes){
            if(cpt.getPartenaire().getId()!=part.getId()){
                comptes.remove(cpt);
            }
        }
        */
        return comptes.stream().filter((cpt)->cpt.getPartenaire().getId()==part.getId()).collect(Collectors.toList());
    }
    
    public UserDao getUserDao() {
        return userDao;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // TODO code application logic here
    }
}
