/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.seguranca.controller;

import br.com.cgcop.seguranca.DAO.PermissaoDAO;
import br.com.cgcop.seguranca.enumaration.TarefaPermissaoDTO;
import br.com.cgcop.seguranca.modelo.Permissao;
import br.com.cgcop.seguranca.modelo.Tarefa;
import br.com.cgcop.seguranca.modelo.Usuario;
import br.com.cgcop.utilitario.ControllerGenerico;
import groovy.util.PermutationGenerator;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ari
 */
@Stateless
public class PermissaoController extends ControllerGenerico<Permissao, Long> implements Serializable {

    @Inject
    private PermissaoDAO dao;
    @Inject
    private TarefaController tarefaController;
    @Inject
    private UsuarioController usuarioController;

    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }

    public void criarPermissaoUsuarioAdmim() throws Exception {
        System.out.println("--------------------------------------Criando Permissões ADM------------------------------------------");
        List<Tarefa> listaTarefas = tarefaController.consultarTodosOrdenadorPor("id");
        Usuario usuario = usuarioController.usuarioLogin("adm");

        for (Tarefa taf : listaTarefas) {
            Permissao per = dao.buscarPor(usuario, taf);
            per.setTarefa(taf);
            per.setUsuario(usuario);
            per.setConsultar(true);
            per.setEditar(true);
            per.setExcluir(true);
            per.setIncluir(true);

            dao.atualizar(per);
        }

    }

    public List<Permissao> buscarPermissao(Usuario usr) {
        return dao.buscarPor(usr);

    }

    public void clonarAcessos(Usuario usuarioAlvo, Usuario usuarioClonado) throws Exception {
        List<Permissao> listaDePermissaos = dao.buscarPor(usuarioClonado);
        for (Permissao p : listaDePermissaos) {
            Permissao per = dao.buscarPor(usuarioAlvo, p.getTarefa());
            per.setConsultar(p.isConsultar());
            per.setEditar(p.isEditar());
            per.setExcluir(p.isExcluir());
            per.setIncluir(p.isIncluir());
            per.setUsuario(usuarioAlvo);
            per.setTarefa(p.getTarefa());

            dao.atualizar(per);
        }
    }

    public Permissao incluirPermissao(TarefaPermissaoDTO tar, Usuario usuario) throws Exception {
        Permissao permissao = dao.buscarPor(usuario, tar.getTarefa());
        permissao.setTarefa(tar.getTarefa());
        permissao.setConsultar(tar.isConsultar());
        permissao.setEditar(tar.isEditar());
        permissao.setIncluir(tar.isIncluir());
        permissao.setExcluir(tar.isExcluir());
        permissao.setUsuario(usuario);

        return dao.atualizarGerenciar(permissao);
    }

}
