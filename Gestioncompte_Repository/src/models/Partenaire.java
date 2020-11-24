/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Albéric
 */
public class Partenaire extends User {
    private String nci;
    private String nomComplet;
    private String adresse;
    private String tel;

    public Partenaire() {
        type = "Partenaire";
    }

    public Partenaire(String nci, String nomComplet, String adresse, String tel, String login, String pwd) {
        super(login, pwd);
        this.nci = nci;
        this.nomComplet = nomComplet;
        this.adresse = adresse;
        this.tel = tel;
    }

    public Partenaire(String nci, String nomComplet, String adresse, String tel, int id, String login, String pwd) {
        super(id, login, pwd);
        this.nci = nci;
        this.nomComplet = nomComplet;
        this.adresse = adresse;
        this.tel = tel;
    }

    public String getNci() {
        return nci;
    }

    public void setNci(String nci) {
        this.nci = nci;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // TODO code application logic here
    }
}
