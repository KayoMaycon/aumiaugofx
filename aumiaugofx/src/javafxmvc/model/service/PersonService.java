package javafxmvc.model.service;

import javafxmvc.model.dao.PessoaDAO;
import javafxmvc.model.domain.Pessoa;

public class PersonService {
    
    private final PessoaDAO pessoaDAO = new PessoaDAO();
    private Pessoa pessoa;
    
    
    public boolean insertPerson(String nome, String cpf, String sexo, String telefone){
        pessoa = new Pessoa(nome, cpf, sexo, telefone);
        return pessoaDAO.insert(pessoa);
    }

}
