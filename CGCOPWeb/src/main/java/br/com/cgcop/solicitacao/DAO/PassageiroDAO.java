/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.solicitacao.DAO;

import br.com.cgcop.solicitacao.modelo.Passageiro;
import br.com.cgcop.utilitario.DAOGenerico;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author Gian
 */
@Stateless
public class PassageiroDAO extends DAOGenerico<Passageiro, Long> implements Serializable{
    
    public PassageiroDAO() {
        super(Passageiro.class);
    }
    
}
