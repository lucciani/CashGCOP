/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.solicitacao.DAO;

import br.com.cgcop.solicitacao.modelo.Passagem;
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
public class PassagemDAO extends DAOGenerico<Passagem, Long> implements Serializable{
    
    public PassagemDAO() {
        super(PassagemDAO.class);
    }

    public List<Passagem> consultarPorPeriodo(Date data, Date dataFinal) {
        TypedQuery<Passagem> tq;
        tq = getEm().createQuery("SELECT e FROM Passagem e WHERE e.dataPartida BETWEEN :dtIni and :dtFim ORDER BY e.dataPartida", Passagem.class)
                .setParameter("dtIni", MetodosUtilitariosData.processarDataInicial(data))
                .setParameter("dtFim", MetodosUtilitariosData.processarDataFinal(dataFinal));
        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();
    }
    
}
