/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import models.Compte;
import models.Partenaire;
import services.Transaction;
import views.Utils.Utils;

/**
 *
 * @author Albéric
 */
public class AddCompteController implements Initializable {
    
    private Transaction service;
    
    @FXML
    private TextField txtNci;
    @FXML
    private TextField txtTel;
    @FXML
    private TextField txtAdresse;
    @FXML
    private TextField txtLogin;
    
    @FXML
    private TextField txtNomPrenom;
    @FXML
    private TextField txtSolde;
    
    private Partenaire part;
    
    @FXML
    private PasswordField txtPwd;
    @FXML
    private TableView<Compte> tblvCompte;
    @FXML
    //Observateur
    private TableColumn<Compte, String> tblcNumero;
    @FXML
    private TableColumn<Compte, String> tblcSolde;
    
    // Sujet d'observation
    private ObservableList<Compte> oblComptes;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        service = new Transaction();
    }    

    @FXML
    private void handleSearchPartenaire(ActionEvent event) {
        oblComptes = Utils.searchPartenaire(txtNci, txtNomPrenom, txtTel, txtAdresse, txtLogin, txtPwd, part, service, oblComptes, tblvCompte, tblcNumero, tblcSolde);
    }
    
    

    @FXML
    private void handleCreateCompte(ActionEvent event) {
        double solde = Double.parseDouble(txtSolde.getText());
        
        if(part == null){
            String nci = txtNci.getText();
            String nom = txtNomPrenom.getText();
            String adresse = txtAdresse.getText();
            String tel = txtTel.getText();
            String login = txtLogin.getText();
            String pwd = txtPwd.getText();
            
            part = new Partenaire(nci, nom, adresse,tel, login, pwd);
        }
        
        Compte compte = new Compte("xxx2", solde, part);
        service.addCompteTransaction(compte);
        //this.loadTableView();
        oblComptes.add(compte);
    }
    
    
    
    
}
