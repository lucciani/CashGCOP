/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.administrativo.DAO;

import br.com.cgcop.administrativo.modelo.Pessoa;
import br.com.cgcop.utilitario.DAOGenerico;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Gian
 */
@Stateless
public class PessoaDAO extends DAOGenerico<Pessoa, Integer> implements Serializable{
    
    public PessoaDAO() {
        super(Pessoa.class);
    }
    
    public Pessoa buscarPorNome(String nome) {
        TypedQuery<Pessoa> tq;
        tq = getEm().createQuery("SELECT p FROM Pessoa p WHERE p.nome = :nome", Pessoa.class)
                .setParameter("nome", nome);
        
        return tq.getResultList().isEmpty() ? new Pessoa() : tq.getSingleResult();
    }
    
}
