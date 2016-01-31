/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.administrativo.controller;

import br.com.cgcop.administrativo.DAO.EnderecoDAO;
import br.com.cgcop.administrativo.DAO.MunicipioDAO;
import br.com.cgcop.administrativo.modelo.Endereco;
import br.com.cgcop.administrativo.modelo.Municipio;
import br.com.cgcop.utilitario.ControllerGenerico;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ari
 */
@Stateless
public class EnderecoController extends ControllerGenerico<Endereco, Long> implements Serializable {

    @Inject
    private EnderecoDAO dao;
    @Inject
    private MunicipioDAO municipioDAO;

    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }

    
      public Endereco buscarOuCriarLogradouroPor(String unidadeFederativa, String nomeCidade) throws Exception {
//        Logradouro logradouro = daoConsulta.buscarPor(unidadeFederativa, nomeCidade, cep, nomeLogradouro, tipoLogradouro, bairro);
        Endereco endereco = dao.buscarPor(nomeCidade);

        if (endereco.getId() != null) {
            return endereco;
        } else {
            Municipio cidade = municipioDAO.buscarPor(nomeCidade, unidadeFederativa);

            if (cidade.getId() == null) {
                throw new Exception("Não existe cidade cadastrada!");
            }
            endereco.setMunicipio(cidade);

            return salvarComRetorno(endereco);
        }
    }

    private Endereco salvarComRetorno(Endereco logradouro) {
       return dao.atualizarGerenciar(logradouro);
    }
}
