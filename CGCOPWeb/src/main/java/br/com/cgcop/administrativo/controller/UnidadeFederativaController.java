/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.administrativo.controller;


import br.com.cgcop.administrativo.DAO.UnidadeFederativaDAO;
import br.com.cgcop.administrativo.modelo.UnidadeFederativa;
import br.com.cgcop.utilitario.ControllerGenerico;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ari
 */
@Stateless
public class UnidadeFederativaController extends ControllerGenerico<UnidadeFederativa, Integer> implements Serializable{

    @Inject
    private UnidadeFederativaDAO dao;
    
    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }

   
    
}
