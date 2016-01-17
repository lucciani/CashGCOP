/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.administrativo.controller;

import br.com.cgcop.administrativo.DAO.PessoaDAO;
import br.com.cgcop.administrativo.modelo.Pessoa;
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
public class PessoaController extends ControllerGenerico<Pessoa, Integer> implements Serializable{
    
    @Inject
    private PessoaDAO dao;

    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }
    
}
