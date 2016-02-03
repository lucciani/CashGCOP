/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.solicitacao.DAO;

import br.com.cgcop.solicitacao.modelo.ItemDespesas;
import br.com.cgcop.utilitario.DAOGenerico;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Gian
 */
@Stateless
public class ItemDespesasDAO extends DAOGenerico<ItemDespesas, Long> implements Serializable {

    public ItemDespesasDAO() {
        super(ItemDespesas.class);
    }

//    public BigDecimal somarTotalDiario(Long idDespesas) {
//        Query tq;
//        tq = getEm().createQuery("SELECT SUM(d.totalDiario) FROM ItemDespesas d WHERE d.despesa = :idDespesas", BigDecimal.class)
//                .setParameter("idDespesas", idDespesas);
//        return (BigDecimal) tq.getSingleResult();
//    }

    public int quantidadeItem(Long idDespesas) {
        Query tq;
        tq = getEm().createQuery("SELECT COUNT(d.despesa) FROM ItemDespesas d WHERE d.despesa = :idDespesas").
                setParameter("idDespesas", idDespesas);
        return (Integer) tq.getSingleResult();
    }

    public BigDecimal somarTotal(Long idDespesas) {
        Query tq;
        tq = getEm().createQuery("SELECT SUM(d.totalDiario) FROM ItemDespesas d WHERE d.despesa = :idDespesas").
                setParameter("idDespesas", idDespesas);
        return (BigDecimal) tq.getSingleResult();
    }

}
