package javafxmvc.model.service;

import javafxmvc.model.database.ConnectionFactory;
import javafxmvc.model.database.DatabaseMySQL;
import javafxmvc.model.domain.Usuario;
import javafxmvc.model.dao.UsuarioDAO;
import javafxmvc.model.database.ConnectionFactory;
import javafxmvc.util.Session;

public class LoginService {
    
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private Usuario usuario;

    public void createConnection() {      
        ConnectionFactory.createDatabase(new DatabaseMySQL());
    }
     
    public boolean checkAccess(String login, String senha){
        return(usuarioDAO.loginUser(login, senha).getId()>0);            
    }
    
    public boolean checkUserbyName(String nomeUsuario){
        usuario = usuarioDAO.findbyNome(nomeUsuario);
        return usuario.getLogin().equals(nomeUsuario);
    }
    
    public void sendEmail() {
        Session.setUserName(usuario.getLogin());        
        System.out.println(generateRecoveryCode());        
    }
    
    private int generateRecoveryCode(){
        int recoveryCode = (int) (Math.random() * 1000000);
        Session.setRecoveryCode(recoveryCode); 
        return recoveryCode;
    }
    
    

}
