/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.solicitacao.Controller;

import br.com.cgcop.solicitacao.DAO.FundoFixoDAO;
import br.com.cgcop.solicitacao.modelo.FundoFixo;
import br.com.cgcop.utilitario.ControllerGenerico;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Gian
 */
@Stateless
public class FundoFixoController extends ControllerGenerico<FundoFixo, Long> implements Serializable{
    
    @Inject
    private FundoFixoDAO dao;

    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }
    
}
