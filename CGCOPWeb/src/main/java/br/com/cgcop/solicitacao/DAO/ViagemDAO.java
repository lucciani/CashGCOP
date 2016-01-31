/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.solicitacao.DAO;

import br.com.cgcop.solicitacao.modelo.Viagem;
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
public class ViagemDAO extends DAOGenerico<Viagem, Long> implements Serializable{
    
    public ViagemDAO() {
        super(Viagem.class);
    }
    
    public List<Viagem> consultarPorPeriodo(Date data, Date dataFinal) {
        TypedQuery<Viagem> tq;
        tq = getEm().createQuery("SELECT e FROM Viagem e WHERE e.dataDaSolicitacao BETWEEN :dtIni and :dtFim ORDER BY e.dataDaSolicitacao", Viagem.class)
                .setParameter("dtIni", MetodosUtilitariosData.processarDataInicial(data))
                .setParameter("dtFim", MetodosUtilitariosData.processarDataFinal(dataFinal));
        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();
    }
    
}
