/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxmvc.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafxmvc.model.database.ConnectionFactory;
import javafxmvc.model.domain.Pessoa;
import javax.swing.JOptionPane;

/**
 *
 * @author kayom
 */
public class PessoaDAO implements GenericDAO<Pessoa>{

    @Override
    public boolean insert(Pessoa object) {
        String sql = "INSERT INTO pessoa (nome,cpf,sexo,telefone) VALUES (?,?,?,?)";
        
            PreparedStatement pst;
            
            try {
                pst = ConnectionFactory.getConnection().prepareStatement(sql);
                pst.setString(1, object.getNome());
                pst.setString(2, object.getCpf());
                pst.setString(3, object.getSexo());
                pst.setString(4, object.getTelefone());
                pst.execute();
                pst.close();
            } catch (SQLException ex) {
                System.out.println(ex);
                return false;
            } 
            
            return true;
    }

    @Override
    public boolean update(Pessoa object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Pessoa> list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pessoa findbyFilter(Pessoa object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pessoa getbyId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
