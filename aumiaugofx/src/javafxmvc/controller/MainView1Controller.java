
package javafxmvc.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class MainView1Controller implements Initializable {

    @FXML
    private AnchorPane jPanelPrincipal;
    @FXML
    private MenuBar jMenuBarPrincipal;
    @FXML
    private Menu jMenuCadastros;
    @FXML
    private MenuItem jMenuItemPessoa;
    @FXML
    private MenuItem jMenuItemProduto;
    @FXML
    private MenuItem menuItenCadastrosClientes;
    @FXML
    private Menu jMenuProcessos;
    @FXML
    private MenuItem menuItemProcessosVendas;
    @FXML
    private Menu jMenuProcessos1;
    @FXML
    private MenuItem menuItemGraficosVendasPorMes;
    @FXML
    private Menu jMenuRelatorios;
    @FXML
    private MenuItem menuItemRelatoriosQuantidadeProdutosEstoque;
    @FXML
    private Button jButtonSair;
    @FXML
    private TextField jTextFieldPesquisar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private void openScreen(String resource) throws IOException{
        AnchorPane anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource(resource));
        jPanelPrincipal.getChildren().setAll(anchorPane);        
    }

    @FXML
    private void handleMenuItemPessoa(ActionEvent event) throws IOException {
        openScreen("/javafxmvc/view/PersonView1.fxml");    
        PersonView1Controller.setjPanelPrincipal(jPanelPrincipal);
    }

    @FXML
    private void handleMenuItemProduto(ActionEvent event) throws IOException {
        openScreen("/javafxmvc/view/ProdutoView.fxml"); 
    }

    @FXML
    private void handleMenuItemVenda(ActionEvent event) throws IOException {
        openScreen("/javafxmvc/view/TelaVendaView.fxml");  
    }

    @FXML
    private void handleMenuItemRelPessoas(ActionEvent event) throws IOException {
        openScreen("/javafxmvc/view/RelatorioPessoaView.fxml"); 
    }
    
    @FXML
    private void handleButtonSair(ActionEvent event) throws IOException {
        System.exit(0);
    }
}
