/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.solicitacao.DAO;

import br.com.cgcop.solicitacao.modelo.FundoFixo;
import br.com.cgcop.utilitario.DAOGenerico;
import br.com.cgcop.utilitarios.MetodosUtilitariosData;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Gian
 */
@Stateless
public class FundoFixoDAO extends DAOGenerico<FundoFixo, Long> implements Serializable {

    public FundoFixoDAO() {
        super(FundoFixo.class);
    }

    public List<FundoFixo> consultarPorPeriodo(Date data, Date dataFinal) {
        TypedQuery<FundoFixo> tq;
        tq = getEm().createQuery("SELECT e FROM FundoFixo e WHERE e.dataDeSolicitacao BETWEEN :dtIni and :dtFim ORDER BY e.dataDeSolicitacao", FundoFixo.class)
                .setParameter("dtIni", MetodosUtilitariosData.processarDataInicial(data))
                .setParameter("dtFim", MetodosUtilitariosData.processarDataFinal(dataFinal));
        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();
    }
}
