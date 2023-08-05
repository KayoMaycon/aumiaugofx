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
import javafxmvc.model.domain.Produto;
/**
 *
 * @author kayom
 */
public class ProdutoDAO {
    public int inserir(Produto produto) {
            String sql = "INSERT INTO produto (descricao, preco) VALUES (?,?)";

                PreparedStatement pst;
                ResultSet rs;
                int lastId = 0;

                try {                
                    pst = ConnectionFactory.getConnection().prepareStatement(sql);
                    pst.setString(1, produto.getDescricao());
                    pst.setFloat(2, produto.getPreco());
                    pst.execute();
                    rs = pst.getGeneratedKeys();
                    if (rs.next()) {
                        lastId = rs.getInt("id");
                    }
                    pst.close();
                    rs.close();

                } catch (SQLException ex) {
                    System.out.println(ex);
                }

                return lastId;
        }    

        public void alterar(Produto produto){

            String sql = "UPDATE produto SET descricao = ? , preco = ?, id = ?";

            PreparedStatement pst;
            try {
                pst = ConnectionFactory.getConnection().prepareStatement(sql);
                pst.setString(1, produto.getDescricao());
                pst.setFloat(2, produto.getPreco());
                pst.setInt(3, produto.getId());
                pst.execute();
                pst.close();                
            } catch (SQLException ex) {
                System.out.println(ex);
            }


        }

        public boolean excluir(Produto produto){
            String sql = "DELETE FROM produto WHERE id = ?";

            PreparedStatement pst;

            try {
                pst = ConnectionFactory.getConnection().prepareStatement(sql);
                pst.setInt(1, produto.getId());
                pst.execute();
                pst.close();                
            } catch (SQLException ex) {
                System.out.println(ex);
                return false;
            }

            return true;
        }

        // pesquisar por nome
        public ArrayList<Produto> pesquisar(String descricao){
            String sql = "SELECT * FROM produto WHERE descricao LIKE ? ORDER BY descricao,preco";

            ArrayList<Produto> lista = new ArrayList<>();

            PreparedStatement pst;
            ResultSet rs;

            try {
                pst = ConnectionFactory.getConnection().prepareStatement(sql);
                pst.setString(1, "%" + descricao + "%");
                rs = pst.executeQuery();

                while(rs.next()){
                    Produto produto = new Produto();
                    produto.setId(rs.getInt("id"));
                    produto.setDescricao(rs.getString("descricao"));
                    produto.setPreco(rs.getFloat("preco"));
                    lista.add(produto);
                }

                rs.close();
                pst.close();

            } catch (SQLException ex) {
                System.out.println(ex);
            }

            return lista;
        }

        // pesquisar por id
        public Produto pesquisar(int id){
            String sql = "SELECT * FROM produto WHERE id = ?";

            Produto produto = new Produto();

            PreparedStatement pst;
            ResultSet rs;

            try {
                pst = ConnectionFactory.getConnection().prepareStatement(sql);
                pst.setInt(1, id);
                rs = pst.executeQuery();

                if(rs.next()){
                    produto.setId(rs.getInt("id"));
                    produto.setDescricao(rs.getString("descricao"));
                    produto.setPreco(rs.getFloat("preco"));                
                }

                rs.close();
                pst.close();

            } catch (SQLException ex) {
                System.out.println(ex);
            }

            return produto;
        }

        public ArrayList<Produto> listar(){
            String sql = "SELECT * FROM produto ORDER BY descricao, preco";

            ArrayList<Produto> lista = new ArrayList<>();

            PreparedStatement pst;
            ResultSet rs;

            try {
                pst = ConnectionFactory.getConnection().prepareStatement(sql);
                rs = pst.executeQuery();

                while(rs.next()){
                    Produto produto = new Produto();
                    produto.setId(rs.getInt("id"));
                    produto.setDescricao(rs.getString("descricao"));
                    produto.setPreco(rs.getFloat("preco"));
                    lista.add(produto);
                }

                rs.close();
                pst.close();

            } catch (SQLException ex) {
                System.out.println(ex);
            }

            return lista;  
        }

        public int proximoId(){

            String sql = "SELECT AUTO_INCREMENT AS ID FROM INFORMATION_SCHEMA.TABLES " +
                            "WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?";

            int id = 0;
            PreparedStatement pst;
            ResultSet rs;

            try {
                pst = ConnectionFactory.getConnection().prepareStatement(sql);
                pst.setString(1, "bd_aula");
                pst.setString(2, "produto");
                rs = pst.executeQuery();

                if(rs.next()){
                    id = rs.getInt("ID");
                }

                rs.close();
                pst.close();

            } catch (SQLException ex) {
                System.out.println(ex);
            }

            return id;

        }

        public ArrayList<Produto> relatorio(String ordem, String descricao) {
            String sql = "SELECT * FROM produto WHERE descricao LIKE ? ORDER BY " + ordem;        

            ArrayList<Produto> lista = new ArrayList<>();

            PreparedStatement pst;
            ResultSet rs;

            try {
                pst = ConnectionFactory.getConnection().prepareStatement(sql);
                pst.setString(1, descricao);
                rs = pst.executeQuery();

                while(rs.next()){
                    Produto produto = new Produto();
                    produto.setId(rs.getInt("id"));
                    produto.setDescricao(rs.getString("descricao"));
                    produto.setPreco(rs.getFloat("preco"));
                    lista.add(produto);
                }

                rs.close();
                pst.close();

            } catch (SQLException ex) {
                System.out.println(ex);
            }

            return lista;
        }
    
}
