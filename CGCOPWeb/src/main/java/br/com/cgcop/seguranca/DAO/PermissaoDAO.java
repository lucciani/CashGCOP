/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.seguranca.DAO;

import br.com.cgcop.seguranca.modelo.Permissao;
import br.com.cgcop.seguranca.modelo.Tarefa;
import br.com.cgcop.seguranca.modelo.Usuario;
import br.com.cgcop.utilitario.DAOGenerico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author ari
 */
@Stateless
public class PermissaoDAO  extends DAOGenerico<Permissao, Long> implements Serializable{
    
    public PermissaoDAO() {
        super(Permissao.class);
    }
 
    
    public Permissao buscarPor(Usuario usr,Tarefa tarefa){
        TypedQuery<Permissao> tq;
        tq = getEm().createQuery("SELECT p from Permissao p WHERE p.usuario = :usr and p.tarefa = :taf", Permissao.class)
                .setParameter("usr", usr)
                .setParameter("taf", tarefa);
        return tq.getResultList().isEmpty() ? new Permissao() : tq.getSingleResult();
    }
    public List<Permissao> buscarPor(Usuario usr){
        TypedQuery<Permissao> tq;
        tq = getEm().createQuery("SELECT p from Permissao p WHERE p.usuario = :usr ORDER BY p.tarefa.modulo", Permissao.class)
                .setParameter("usr", usr);
        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();
    }
}
