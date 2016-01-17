/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.administrativo.DAO;


import br.com.cgcop.administrativo.modelo.Municipio;
import br.com.cgcop.administrativo.modelo.UnidadeFederativa;
import br.com.cgcop.utilitario.DAOGenerico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author ari
 */
@Stateless
public class MunicipioDAO extends DAOGenerico<Municipio, Integer> implements Serializable {

    public MunicipioDAO() {
        super(Municipio.class);
    }

    public List<Municipio> consultarMunicipioPor(UnidadeFederativa uf) {
        TypedQuery tq;
        tq = getEm().createQuery("SELECT u FROM Municipio u WHERE u.unidadeFederativa = :uf  ORDER BY u.nome  ", Municipio.class)
                .setParameter("uf", uf);
        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();
    }

    public Municipio buscarPor(String nomeCidade, String ufAbreviacao) {
        TypedQuery<Municipio> tq;
        tq = getEm().createQuery("SELECT c from Municipio c WHERE c.ativo = :at AND c.nome = :nom and c.unidadeFederativa.sigla = :uf", Municipio.class)
                .setParameter("at", true)
                .setParameter("nom", nomeCidade)
                .setParameter("uf", ufAbreviacao);
        return tq.getResultList().isEmpty() ? new Municipio() : tq.getSingleResult();
    }
}
