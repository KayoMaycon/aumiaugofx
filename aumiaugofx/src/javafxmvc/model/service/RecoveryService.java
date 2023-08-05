package javafxmvc.model.service;

import javafxmvc.model.dao.UsuarioDAO;

public class RecoveryService {
    
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    public boolean updatePassword(String login, String senha){
        return usuarioDAO.updatePassword(login, senha);
    }

}
