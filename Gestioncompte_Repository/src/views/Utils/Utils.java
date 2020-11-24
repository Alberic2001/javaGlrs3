/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Utils;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Compte;
import models.Depot;
import models.Partenaire;
import models.User;
import services.Transaction;




/**
 *
 * @author Albéric
 */
public class Utils {
    public static ObservableList<Compte> searchPartenaire(TextField txtNci,
            TextField txtNomPrenom,
            TextField txtTel,
            TextField txtAdresse,
            TextField txtLogin,
            PasswordField txtPwd,
            Partenaire part,
            Transaction service,
            ObservableList oblComptes,
            TableView<Compte> tblvCompte,
            TableColumn<Compte, String> tblcNumero,
            TableColumn<Compte, String> tblcSolde) {
        String nci = txtNci.getText();
        part = service.getPartenaireByNci(nci);
        if(oblComptes!=null)
            oblComptes.clear();
        if(part!=null){
            txtNomPrenom.setText(part.getNomComplet());
            txtAdresse.setText(part.getAdresse());
            txtTel.setText(part.getTel());
            txtLogin.setText(part.getLogin());
            txtPwd.setText(part.getPwd());
            
            enableOrDisableFields(true, txtNci, txtNomPrenom, txtTel, txtAdresse, txtLogin, txtPwd);
            
            List<Compte> comptes = service.getCompteByPartenaire(part);
            oblComptes = FXCollections.observableArrayList(comptes);
            loadTableView(tblvCompte, tblcNumero, tblcSolde, oblComptes);
            
        } else {
            enableOrDisableFields(false, txtNci, txtNomPrenom, txtTel, txtAdresse, txtLogin, txtPwd);
            clearFields(txtNci, txtNomPrenom, txtTel, txtAdresse, txtLogin, txtPwd);
        }
        
        return oblComptes;
    
    }
    
    
    public static void enableOrDisableFields(boolean etat, TextField txtNci, TextField txtNomPrenom, TextField txtTel, TextField txtAdresse, TextField txtLogin, PasswordField txtPwd){
            txtNomPrenom.setDisable(etat);
            txtAdresse.setDisable(etat);
            txtTel.setDisable(etat);
            txtLogin.setDisable(etat);
            txtPwd.setDisable(etat);
    }
    
    
    
    public static void clearFields(TextField txtNci, TextField txtNomPrenom, TextField txtTel, TextField txtAdresse, TextField txtLogin, PasswordField txtPwd){
            txtNomPrenom.clear();
            txtAdresse.clear();
            txtTel.clear();
            txtLogin.clear();
            txtPwd.clear();
    }
    
    
    
    public static void loadTableView(TableView<Compte> tblvCompte, TableColumn<Compte, String> tblcNumero, TableColumn<Compte, String> tblcSolde, ObservableList oblComptes){
        tblcNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tblcSolde.setCellValueFactory(new PropertyValueFactory<>("solde"));
        // Souscrire
        tblvCompte.setItems(oblComptes);
    }
    
    
    
    // Methodes surchargées
    
    
    public static ObservableList<Compte> searchPartenaire(TextField txtNci,
            TextField txtNomPrenom,
            TextField txtTel,
            TextField txtAdresse,
            Partenaire part,
            Transaction service,
            ObservableList oblComptes,
            TableView<Compte> tblvCompte,
            TableColumn<Compte, String> tblcNumero,
            TableColumn<Compte, String> tblcSolde) {
        String nci = txtNci.getText();
        part = service.getPartenaireByNci(nci);
        if(oblComptes!=null)
            oblComptes.clear();
        if(part!=null){
            txtNomPrenom.setText(part.getNomComplet());
            txtAdresse.setText(part.getAdresse());
            txtTel.setText(part.getTel());
            enableOrDisableFields(true, txtNci, txtNomPrenom, txtTel, txtAdresse);
            
            List<Compte> comptes = service.getCompteByPartenaire(part);
            oblComptes = FXCollections.observableArrayList(comptes);
            loadTableView(tblvCompte, tblcNumero, tblcSolde, oblComptes);
            
        } else {
            enableOrDisableFields(false, txtNci, txtNomPrenom, txtTel, txtAdresse);
            clearFields(txtNci, txtNomPrenom, txtTel, txtAdresse);
        }
        
        return oblComptes;
    
    }
    
    public static void enableOrDisableFields(boolean etat, TextField txtNci, TextField txtNomPrenom, TextField txtTel, TextField txtAdresse){
            txtNomPrenom.setDisable(etat);
            txtAdresse.setDisable(etat);
            txtTel.setDisable(etat);
            
    }
    
    public static void clearFields(TextField txtNci, TextField txtNomPrenom, TextField txtTel, TextField txtAdresse){
            txtNomPrenom.clear();
            txtAdresse.clear();
            txtTel.clear();
    }
    
    
    // Pour les champs de transaction.fxml
    public static void enableOrDisableFields(boolean etat, TextField txtNciEnvoi, TextField txtNomEnvoi, TextField txtTelEnvoi, TextField txtNciBenefice, TextField txtNomBenefice, TextField txtTelBenefice){
        txtNciEnvoi.setDisable(etat);
        txtNomEnvoi.setDisable(etat);
        txtTelEnvoi.setDisable(etat);
        txtNciBenefice.setDisable(etat);
        txtNomBenefice.setDisable(etat);
        txtTelBenefice.setDisable(etat);
    }
    
    public static void enableOrDisableFields(boolean etat, TextField txtCode, Button btnValidateCode){
        txtCode.setDisable(etat);
        btnValidateCode.setDisable(etat);
    }
    
    
    
    
    
    public static void loadTableViewDeposit(TableView<Depot> tblvTransaction, TableColumn<Depot, String> tblcDate, TableColumn<Depot, String> tblcMontant, ObservableList oblDepots){
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("createAt"));
        tblcMontant.setCellValueFactory(new PropertyValueFactory<>("mnt"));
        // Souscrire
        tblvTransaction.setItems(oblDepots);
    }
    
    
    public static ObservableList<Depot> validateDeposit(TextField txtNciEnvoi,
            TextField txtNomEnvoi,
            TextField txtTelEnvoi,
            TextField txtNciBenefice,
            TextField txtNomBenefice,
            TextField txtTelBenefice,
            TextField txtMontant,
            Transaction service,
            TableView<Depot> tblvTransaction,
            TableColumn<Depot, String> tblcDate,
            TableColumn<Depot, String> tblcMontant,
            ObservableList oblDepots) {
        
        String nciEnvoi = txtNciEnvoi.getText();
        String nomEnvoi = txtNomEnvoi.getText();
        String telEnvoi = txtTelEnvoi.getText();
        String nciBenefice = txtNciBenefice.getText();
        String nomBenefice = txtNomBenefice.getText();
        String telBenefice = txtTelBenefice.getText();
        String montant = txtMontant.getText();
        Double mnt = Double.parseDouble(montant);
        
        Partenaire partEnvoi = service.getPartenaireByNci(nciEnvoi);
        Partenaire partBenefice = service.getPartenaireByNci(nciBenefice);
        
        Compte cpt = service.getCompteByPartenaire(partBenefice).get(0);
        cpt = service.addCompteTransaction(cpt);
        service.setDepot(cpt, mnt);
        
        List<Depot> depots = cpt.getDepots();
        
        oblDepots = FXCollections.observableArrayList(depots);
        
        loadTableViewDeposit(tblvTransaction, tblcDate, tblcMontant, oblDepots);
        
        return null;
    }
    
    
    
}
