/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Compte;
import models.Depot;
import models.Partenaire;
import services.Transaction;
import views.Utils.Utils;

/**
 * FXML Controller class
 *
 * @author Albéric
 */
public class DepotCompteController implements Initializable {

    private Transaction service;
    
    private Partenaire part;
    
    @FXML
    private TextField txtNci;
    @FXML
    private TextField txtNomPrenom;
    @FXML
    private TextField txtTel;
    @FXML
    private TextField txtAdresse;
    @FXML
    private TextField txtMontant;
    @FXML
    private TableView<Compte> tblvCompte;
    @FXML
    private TableColumn<Compte, String> tblcNumero;
    @FXML
    private TableColumn<Compte, String> tblcSolde;
    @FXML
    private TableView<Depot> tblvDepots;
    @FXML
    private TableColumn<Depot, String> tblcDate;
    @FXML
    private TableColumn<Depot, String> tblcMontant;

    // Sujet d'observation
    private ObservableList<Depot> oblDepots;
    private ObservableList<Compte> oblComptes;
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        service = new Transaction();
    }    

    @FXML
    private void handleSearchPartenaire(ActionEvent event) {
        oblComptes = Utils.searchPartenaire(txtNci, txtNomPrenom, txtTel, txtAdresse,  part, service, oblComptes, tblvCompte, tblcNumero, tblcSolde); 
    }

    @FXML
    private void handleSelectRowTableView(MouseEvent event) {
        Compte cpt = tblvCompte.getSelectionModel().getSelectedItem();
        oblDepots = FXCollections.observableArrayList(cpt.getDepots());
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("createAt"));
        tblcMontant.setCellValueFactory(new PropertyValueFactory<>("mnt"));
        
        tblvDepots.setItems(oblDepots);
    }
    
}
