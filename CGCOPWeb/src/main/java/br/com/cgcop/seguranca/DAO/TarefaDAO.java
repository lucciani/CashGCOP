/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.seguranca.DAO;

import br.com.cgcop.seguranca.modelo.Tarefa;
import br.com.cgcop.utilitario.DAOGenerico;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author ari
 */
@Stateless
public class TarefaDAO extends DAOGenerico<Tarefa, Long> implements Serializable{
    
    public TarefaDAO() {
        super(Tarefa.class);
    }
    
    
     public boolean existeTarefa(String nome) {
        TypedQuery q;
        q = getEm().createQuery("SELECT t from Tarefa t WHERE t.nome = :nome", Tarefa.class)
                .setParameter("nome", nome);
        return !q.getResultList().isEmpty();
    }
}
