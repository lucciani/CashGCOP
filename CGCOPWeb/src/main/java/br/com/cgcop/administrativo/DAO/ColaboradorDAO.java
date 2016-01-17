/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.administrativo.DAO;

import br.com.cgcop.administrativo.modelo.Colaborador;
import br.com.cgcop.utilitario.DAOGenerico;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Giancarlo
 */
@Stateless
public class ColaboradorDAO extends DAOGenerico<Colaborador, Long> implements Serializable{
    
    public ColaboradorDAO() {
        super(Colaborador.class);
    }

    public Colaborador buscarPorNome(String nome) {
         TypedQuery<Colaborador> tq;
        tq = getEm().createQuery("SELECT c FROM Colaborador c WHERE c.nome = :nome", Colaborador.class)
                .setParameter("nome", nome);
        
        return tq.getResultList().isEmpty() ? new Colaborador() : tq.getSingleResult();
    }
    
     
}
