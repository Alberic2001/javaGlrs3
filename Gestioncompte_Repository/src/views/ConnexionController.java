/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.net.URL;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import models.User;
import services.Transaction;

/**
 * FXML Controller class
 *
 * @author Albéric
 */
public class ConnexionController implements Initializable {
    
    
    private Transaction service;
    
    @FXML
    private TextField txtLogin;
    @FXML
    private Button btnConnect;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Text txtVerified;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        service = new Transaction();
    }    

    @FXML
    private void verifyLoginAndPassword(ActionEvent event) {
        String login = txtLogin.getText();
        String pwd = txtPassword.getText();
        
        if(login!=null){
            List<User> userList = service.getUserDao().selectAll();
            ListIterator<User> li = userList.listIterator();
            while(li.hasNext()){
                User user = li.next();
                if(login.equals(user.getLogin())){
                    if(pwd.equals(user.getPwd())){
                        String message = "Donnees valides";
                        this.getTxtVerified().setText(message);
                        break;
                    }
                } else {
                    String message = "Login ou Mdp invalides";
                    this.getTxtVerified().setText(message);
                    break;
                }
            }
        } else {
            String message = "Veuillez remplir les champs";
            this.getTxtVerified().setText(message);
        }
            
    }

    public Text getTxtVerified() {
        return txtVerified;
    }
    
}
