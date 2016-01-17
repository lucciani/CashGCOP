/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.seguranca.DAO;

import br.com.cgcop.seguranca.modelo.Modulo;
import br.com.cgcop.utilitario.DAOGenerico;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author ari
 */
@Stateless
public class ModuloDAO extends DAOGenerico<Modulo, Long> implements Serializable {

    public ModuloDAO() {
        super(Modulo.class);
    }

    public boolean existeModulo(String nome) {
        TypedQuery q;
        q = getEm().createQuery("SELECT m from Modulo m WHERE m.nome = :nome", Modulo.class)
                .setParameter("nome", nome);
        return !q.getResultList().isEmpty();
    }
    
    public Modulo pegarModuloPor(String nome) {
        TypedQuery<Modulo> q;
        q = getEm().createQuery("SELECT m from Modulo m WHERE m.nome = :nome", Modulo.class)
                .setParameter("nome", nome);
        return q.getResultList().isEmpty() ? new Modulo() : q.getSingleResult();
    }
    
    
}
