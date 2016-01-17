/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.seguranca.DAO;

import br.com.cgcop.seguranca.modelo.Usuario;
import br.com.cgcop.utilitario.DAOGenerico;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author ari
 */
@Stateless
public class UsuarioDAO extends DAOGenerico<Usuario, Long> implements Serializable{
    
    public UsuarioDAO() {
        super(Usuario.class);
    }

    public Usuario usuarioLogin(String usr) {
        TypedQuery<Usuario> tq;
        tq = getEm().createQuery("SELECT u FROM Usuario u WHERE u.login = :log AND u.ativo = true", Usuario.class)
                .setParameter("log", usr);
        return tq.getResultList().isEmpty() ? new Usuario() : tq.getSingleResult();
    }
    
}
