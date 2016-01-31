/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.solicitacao.Controller;

import br.com.cgcop.administrativo.controller.EnderecoController;
import br.com.cgcop.solicitacao.DAO.PassagemDAO;
import br.com.cgcop.solicitacao.modelo.Passagem;
import br.com.cgcop.utilitario.ControllerGenerico;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Gian
 */
@Stateless
public class PassagemController extends ControllerGenerico<Passagem, Long> implements Serializable {

    @Inject
    private PassagemDAO dao;
    @Inject
    private EnderecoController enderecoController;

    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }
    
    @Override
    public void salvar(Passagem pas) throws Exception{
        pas.setOrigem(enderecoController.buscarOuCriarLogradouroPor(pas.getOrigem().getAbreviacaoUnidadeFederativa(), pas.getOrigem().getNomeDaCidade()));
        pas.setDestino(enderecoController.buscarOuCriarLogradouroPor(pas.getDestino().getAbreviacaoUnidadeFederativa(), pas.getDestino().getNomeDaCidade()));
       dao.atualizar(pas);
    }
    
    public List<Passagem> consultarPorPeriodo(Date data, Date dataFinal) {
      return dao.consultarPorPeriodo(data,dataFinal);
    }

}
