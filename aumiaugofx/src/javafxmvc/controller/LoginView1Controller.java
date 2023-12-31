/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmvc.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafxmvc.model.service.LoginService;
import javafxmvc.util.AlertUtil;
import javafxmvc.Main;

/**
 * FXML Controller class
 *
 * @author kayom
 */
public class LoginView1Controller implements Initializable{
    

    private final LoginService loginService = new LoginService();
    
    @FXML
    private TextField jTextFieldUsuario;
    @FXML
    private PasswordField jPasswordFieldSenha;
    @FXML
    private Button jButtonEntrar;
    @FXML
    private Button jButtonLimpar;
    @FXML
    private Button jButtonSair;
    @FXML
    private Button jButtonRecupera;
    @FXML
    private Button jButtonCadastrar;

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginService.createConnection();
    }
    
    
    @FXML
    public void handleButtonLimpar() throws IOException{
        jTextFieldUsuario.clear();
        jPasswordFieldSenha.clear();
    }
    
    @FXML
    public void handleButtonEntrar() throws IOException{
        if(validateFields()){
            if(loginService.checkAccess(jTextFieldUsuario.getText(), jPasswordFieldSenha.getText())){
               openMainView();
            } else{
                AlertUtil.show("Acesso Negado","Nome de Usuário e/ou Senha incorreto(s)!",Alert.AlertType.ERROR);            
            }
        } else{
            AlertUtil.show("Validação de Dados", "Preencha todos os campos", Alert.AlertType.ERROR);
        }
        
    }
    
    @FXML
    public void handleButtonSair() throws IOException{
        System.exit(0);
    }
    
    @FXML
    public void handleButtonRecupera() throws IOException{
        Optional<String> result = openDialog();
        if(result.isPresent()){
            sendCode(result.get());
        }
    }
    
    @FXML
    public void handleButtonCadastrar() throws IOException{
        openPersonView(); 
        
    }
    
    private boolean validateFields(){
        if(jTextFieldUsuario.getText().isEmpty()) return false;
        else if(jPasswordFieldSenha.getText().isEmpty()) return false;
        else return true;
    }
     
    
    private void sendCode(String nomeDeUsuario) throws IOException{        
        if(loginService.checkUserbyName(nomeDeUsuario)){            
            loginService.sendEmail();            
            AlertUtil.show("Confirmação de envio","Código enviado para " + nomeDeUsuario, Alert.AlertType.INFORMATION);            
            openRecoveryView();
        } else{
            AlertUtil.show("Código não enviado","Nome de Usuário " + nomeDeUsuario + " não encontrado", Alert.AlertType.ERROR);
        }                
    }
   
    
    private Optional<String> openDialog(){
        TextInputDialog input = new TextInputDialog();
        input.setTitle("Solicitação de Código de Recuperação");        
        input.getDialogPane().setContentText("Informe seu Nome de Usuário:");
        return input.showAndWait();
    }
    
    private void openMainView() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/javafxmvc/view/MainView1.fxml"));
        Parent pane = loader.load();            
        Main.getStage().getScene().setRoot(pane);
        Main.getStage().sizeToScene();
        Main.getStage().centerOnScreen();
    } 
    
    private void openPersonView() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/javafxmvc/view/PersonView1.fxml"));
        Parent pane = loader.load();            
        Main.getStage().getScene().setRoot(pane);
        Main.getStage().sizeToScene();
        Main.getStage().centerOnScreen();
    } 

    private void openRecoveryView() throws IOException {
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("/javafxmvc/view/RecoveryView1.fxml"));
        DialogPane dialogPane = fXMLLoader.load();
        Dialog dialog = new Dialog();
        dialog.setTitle("Alteração de Senha");
        dialog.setDialogPane(dialogPane);
        dialog.showAndWait();
    }

}
