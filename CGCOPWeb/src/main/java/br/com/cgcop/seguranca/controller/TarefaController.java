/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.seguranca.controller;

import br.com.cgcop.seguranca.DAO.TarefaDAO;
import br.com.cgcop.seguranca.enumaration.TarefaPermissaoDTO;
import br.com.cgcop.seguranca.modelo.Modulo;
import br.com.cgcop.seguranca.modelo.Permissao;
import br.com.cgcop.seguranca.modelo.Tarefa;
import br.com.cgcop.utilitario.ConfiguracaoSistemaMB;
import br.com.cgcop.utilitario.ControllerGenerico;
import br.com.cgcop.utilitarios.ResourceUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ari
 */
@Stateless
public class TarefaController extends ControllerGenerico<Tarefa, Long> implements Serializable {

    @Inject
    private TarefaDAO dao;
    @Inject
    private ModuloController moduloController;

    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }

    public List<Tarefa> consultarPorModulo(String nomeModulo) throws Exception {
        if ("".equals(nomeModulo) || nomeModulo == null) {
            return dao.consultarTodosOrdenadosPor("modulo.nome");
        } else {
            return dao.consultarLike("modulo.nome", nomeModulo);
        }
    }

    public void criarTarefas() throws Exception {
        criarTarefaModSeguranca();
        criarTarefaModAdministrativo();
        criarTarefaModSolicitacao();
    }

    public List<TarefaPermissaoDTO> retornarTarefasPermissao(List<Tarefa> tarefas, List<Permissao> permissoes) {
        boolean contem = false;
        List<TarefaPermissaoDTO> listDTO = new ArrayList<>();
        //roda todas as taredas 
        for (Tarefa taf : tarefas) {
            contem = false;
            TarefaPermissaoDTO dto = new TarefaPermissaoDTO();
            //roda para saber se tem as taredas nas permissões
            for (Permissao per : permissoes) {
                if (per.getTarefa().equals(taf)) {
                    dto.setTarefa(taf);
                    dto.setConsultar(per.isConsultar());
                    dto.setEditar(per.isEditar());
                    dto.setExcluir(per.isExcluir());
                    dto.setIncluir(per.isIncluir());

                    listDTO.add(dto);
                    contem = true;
                    break;
                }
            }

            if (!contem) {
                dto.setTarefa(taf);
                listDTO.add(dto);
            }

        }
        return listDTO;
    }

    private void criarTarefaModSeguranca() throws Exception {
        System.out.println("--------------------------------------Criando Tarefas Mod Segurança------------------------------------------");
        ResourceBundle bundle = ResourceBundle.getBundle("br.com.cgcop.arquivos.seguranca");
        Enumeration<String> tarefa = bundle.getKeys();
        while (tarefa.hasMoreElements()) {
            Modulo md = moduloController.pegarModuloPor(ResourceUtil.lerBundle("seguranca", ResourceUtil.MODULO));
            String nome = tarefa.nextElement();
            String descricao = bundle.getString(nome);
            if (!dao.existeTarefa(nome)) {
                Tarefa taf = new Tarefa();
                taf.setModulo(md);
                taf.setNome(nome);
                taf.setDescricao(descricao);
                salvar(taf);
            }

        }
    }

    private void criarTarefaModAdministrativo() throws Exception {
        System.out.println("--------------------------------------Criando Tarefas Mod Administrativo------------------------------------------");
        ResourceBundle bundle = ResourceBundle.getBundle("br.com.cgcop.arquivos.administrativo");
        Enumeration<String> tarefa = bundle.getKeys();
        while (tarefa.hasMoreElements()) {
            Modulo md = moduloController.pegarModuloPor(ResourceUtil.lerBundle("administrativo", ResourceUtil.MODULO));

            String nome = tarefa.nextElement();
            String descricao = bundle.getString(nome);
            if (!dao.existeTarefa(nome)) {
                Tarefa taf = new Tarefa();
                taf.setModulo(md);
                taf.setNome(nome);
                taf.setDescricao(descricao);
                salvar(taf);
            }

        }
    }
    
    private void criarTarefaModSolicitacao() throws Exception {
        System.out.println("--------------------------------------Criando Tarefas Mod Solicitacao------------------------------------------");
        ResourceBundle bundle = ResourceBundle.getBundle("br.com.cgcop.arquivos.solicitacao");
        Enumeration<String> tarefa = bundle.getKeys();
        while (tarefa.hasMoreElements()) {
            Modulo md = moduloController.pegarModuloPor(ResourceUtil.lerBundle("solicitacoes", ResourceUtil.MODULO));

            String nome = tarefa.nextElement();
            String descricao = bundle.getString(nome);
            if (!dao.existeTarefa(nome)) {
                Tarefa taf = new Tarefa();
                taf.setModulo(md);
                taf.setNome(nome);
                taf.setDescricao(descricao);
                salvar(taf);
            }

        }
    }

}
