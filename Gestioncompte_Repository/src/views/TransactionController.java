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
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.Compte;
import models.Depot;
import services.Transaction;
import views.Utils.Utils;

/**
 * FXML Controller class
 *
 * @author Albéric
 */
public class TransactionController implements Initializable {

    private Transaction service;
    
    @FXML
    private TextField txtCode;
    @FXML
    private Button btnValidateCode;
    @FXML
    private TextField txtNciEnvoi;
    @FXML
    private TextField txtNomEnvoi;
    @FXML
    private TextField txtTelEnvoi;
    @FXML
    private TextField txtNciBenefice;
    @FXML
    private TextField txtNomBenefice;
    @FXML
    private TextField txtTelBenefice;
    @FXML
    private TextField txtMontant;
    @FXML
    private Button btnValidateMontant;
    @FXML
    private TableView<Depot> tblvTransactions;
    @FXML
    private TableColumn<Depot, String> tblcDateTransaction;
    @FXML
    private TableColumn<Depot, String> tblcMontantTransaction;
    @FXML
    private RadioButton btnDepot;
    @FXML
    private RadioButton btnRetrait;
    
    // Sujet d'observation
    private ObservableList<Depot> oblDepots;
    private ObservableList<Compte> oblComptes;
    private ObservableList<Transaction> oblTransactions;

    
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        service = new Transaction();
    }    

    @FXML
    private void disableCodeAndOkBtn(MouseEvent event) {
        Utils.enableOrDisableFields(true, txtCode, btnValidateCode);
        btnRetrait.setSelected(false);
        Utils.enableOrDisableFields(false, txtNciEnvoi, txtNomEnvoi, txtTelEnvoi, txtNciBenefice, txtNomBenefice, txtTelBenefice);
        
    }

    @FXML
    private void disableSenderAndReceiverInfos(MouseEvent event) {
        Utils.enableOrDisableFields(true, txtNciEnvoi, txtNomEnvoi, txtTelEnvoi, txtNciBenefice, txtNomBenefice, txtTelBenefice);
        btnDepot.setSelected(false);
        Utils.enableOrDisableFields(false, txtCode, btnValidateCode);
        
    }

    @FXML
    private void validateDepositOrWithdraw(ActionEvent event) {
        oblDepots = Utils.validateDeposit(txtNciEnvoi, txtNomEnvoi, txtTelEnvoi, txtNciBenefice, txtNomBenefice, txtTelBenefice, txtMontant, service, tblvTransactions, tblcDateTransaction, tblcMontantTransaction, oblDepots);
    }
}
