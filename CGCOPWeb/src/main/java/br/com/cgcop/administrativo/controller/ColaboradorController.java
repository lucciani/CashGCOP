/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.administrativo.controller;

import br.com.cgcop.administrativo.DAO.ColaboradorDAO;
import br.com.cgcop.administrativo.modelo.Colaborador;
import br.com.cgcop.utilitario.ControllerGenerico;
import br.com.cgcop.utilitarios.StringUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Giancarlo
 */
@Stateless
public class ColaboradorController extends ControllerGenerico<Colaborador, Long> implements Serializable{

    @Inject
    private ColaboradorDAO dao;
    
    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }
    
    @Override
    public void salvar(Colaborador col) throws Exception{
       col.setCpf(StringUtil.removerCaracteresEspeciais(col.getCpf()));
       dao.atualizar(col);    
    }
    
    
}
