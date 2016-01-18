/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.administrativo.controller;

import br.com.cgcop.administrativo.DAO.NaturezaDAO;
import br.com.cgcop.administrativo.modelo.Natureza;
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
public class NaturezaController extends ControllerGenerico<Natureza, String> implements Serializable {

    @Inject
    private NaturezaDAO dao;

    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }

}
