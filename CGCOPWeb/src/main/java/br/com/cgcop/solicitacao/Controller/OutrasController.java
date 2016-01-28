/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.solicitacao.Controller;

import br.com.cgcop.solicitacao.DAO.OutrasDAO;
import br.com.cgcop.solicitacao.modelo.Outras;
import br.com.cgcop.utilitario.ControllerGenerico;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author Gian
 */
public class OutrasController extends ControllerGenerico<Outras, Long> implements Serializable {

    @Inject
    private OutrasDAO dao;

    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }

    public List<Outras> consultarPorPeriodo(Date data, Date dataFinal) {
      return dao.consultarPorPeriodo(data,dataFinal);
    }
}
