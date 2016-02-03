/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.solicitacao.Controller;

import br.com.cgcop.solicitacao.DAO.ItemDespesasDAO;
import br.com.cgcop.solicitacao.modelo.ItemDespesas;
import br.com.cgcop.utilitario.ControllerGenerico;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Gian
 */
@Stateless
public class ItemDespesasController extends ControllerGenerico<ItemDespesas, Long> implements Serializable {

    @Inject
    private ItemDespesasDAO dao;

    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }

    public BigDecimal somarTotal(Long idDespesas) {
        return dao.somarTotal(idDespesas);
    }

}
