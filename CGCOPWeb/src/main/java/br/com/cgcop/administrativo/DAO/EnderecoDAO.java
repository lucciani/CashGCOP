/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.administrativo.DAO;


import br.com.cgcop.administrativo.modelo.Endereco;
import br.com.cgcop.utilitario.DAOGenerico;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author ari
 */
@Stateless 
public class EnderecoDAO extends DAOGenerico<Endereco, Long> implements Serializable{

    public EnderecoDAO() {
        super(Endereco.class);
    }
     public Endereco buscarPor(String cep) {
        TypedQuery<Endereco> tq;
        tq = getEm().createQuery("SELECT l from Endereco l WHERE l.cep = :cep ", Endereco.class)
                .setParameter("cep", cep);
        return tq.getResultList().isEmpty() ? new Endereco() : tq.getSingleResult();
    }

    public Endereco buscarPor(String uf, String municipio, String cep, String logradouro, String bairro,String numero,String complemento) {
        TypedQuery<Endereco> tq;
        tq = getEm().createQuery("SELECT l FROM Endereco l WHERE l.cep = :cep "
                + "AND l.nome = :nome AND l.numero = :numero AND l.municipio.nome = :mun and l.complemento = :comp "
                + "AND l.municipio.unidadeFederativa.sigla = :uf AND l.bairro = :bai", Endereco.class)
                .setParameter("cep", cep.trim())
                .setParameter("nome", logradouro.toUpperCase())
                .setParameter("numero",numero)
                .setParameter("comp",complemento)
                .setParameter("mun", municipio)
                .setParameter("uf", uf)
                .setParameter("bai", bairro.toUpperCase());
        return tq.getResultList().isEmpty() ? new Endereco() : tq.getSingleResult();
    }
    
    public Endereco buscarPor(String municipio, String cep, String logradouro, String bairro,String numero,String complemento) {
        TypedQuery<Endereco> tq;
        tq = getEm().createQuery("SELECT l FROM Endereco l WHERE l.cep = :cep  and l.numero = :numero and l.complemento = :comp "
                + "AND l.nome = :nome AND l.municipio.nome = :mun "
                + "AND l.bairro = :bai", Endereco.class)
                .setParameter("cep", cep.trim())
                .setParameter("nome", logradouro.toUpperCase())
                .setParameter("mun", municipio)
                .setParameter("numero",numero)
                .setParameter("comp",complemento)
                .setParameter("bai", bairro.toUpperCase());
        return tq.getResultList().isEmpty() ? new Endereco() : tq.getSingleResult();
    }
}
