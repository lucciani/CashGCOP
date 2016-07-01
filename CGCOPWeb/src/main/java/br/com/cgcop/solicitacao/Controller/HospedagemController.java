/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.solicitacao.Controller;

import br.com.cgcop.administrativo.controller.EnderecoController;
import br.com.cgcop.solicitacao.DAO.HospedagemDAO;
import br.com.cgcop.solicitacao.modelo.Hospedagem;
import br.com.cgcop.utilitario.ControllerGenerico;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Gian
 */
@Stateless
public class HospedagemController extends ControllerGenerico<Hospedagem, Long> implements Serializable {
    
    @Inject
    private HospedagemDAO dao;
    @Inject
    private EnderecoController enderecoController;
    
    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }
    
    public List<Hospedagem> consultarPorPeriodo(Date data, Date dataFinal) {
        return dao.consultarPorPeriodo(data, dataFinal);
    }
    
    public void salvarHospedagem(Hospedagem hospedagem) {
        try {
            dao.atualizar(hospedagem);
        } catch (Exception ex) {
            Logger.getLogger(HospedagemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
