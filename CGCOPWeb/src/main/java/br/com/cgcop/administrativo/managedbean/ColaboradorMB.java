/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.administrativo.managedbean;

import br.com.cgcop.administrativo.controller.ColaboradorController;
import br.com.cgcop.administrativo.controller.PessoaController;
import br.com.cgcop.administrativo.modelo.Colaborador;
import br.com.cgcop.administrativo.modelo.Pessoa;
import br.com.cgcop.utilitario.BeanGenerico;
import br.com.cgcop.utilitario.mensagens.MensagensUtil;
import br.com.cgcop.utilitario.relatorio.RelatorioSession;
import br.com.cgcop.utilitarios.relatorios.AssistentedeRelatorio;
import br.com.cgcop.utilitarios.relatorios.PastasRelatorio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Giancarlo
 */
@Named
@ViewScoped
public class ColaboradorMB extends BeanGenerico implements Serializable {

    @Inject
    private ColaboradorController colaboradorcontroller;
    @Inject
    private PessoaController pessoaController;
    
    private Colaborador colaborador;
    private List<Colaborador> listaDeColaborador;
    private List<Pessoa> listaDePessoas;

    @PostConstruct
    @Override
    public void init() {
        try {
            criarListaDeCamposDaConsulta();
            colaborador = (Colaborador) lerRegistroDaSessao("colaborador");
            if(colaborador == null){
                colaborador = new Colaborador();
                colaborador.setAtivo(true);
                colaborador.setPessoa(new Pessoa());
            }
            listaDeColaborador = new ArrayList<>();
            listaDePessoas = pessoaController.consultarTodosOrdenadorPor("nome");
        } catch (Exception ex) {
            Logger.getLogger(ColaboradorMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void salvar() {
        try {
            colaboradorcontroller.salvar(colaborador);
            MensagensUtil.enviarMessageParamentroInfo(MensagensUtil.REGISTRO_SUCESSO, colaborador.getNome());
            init();
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(ColaboradorMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void geraImpressaoColaborador() {
        try {
            Map<String, Object> m = new HashMap<>();
            byte[] rel = new AssistentedeRelatorio().relatorioemByte(listaDeColaborador, m, PastasRelatorio.RESOURCE_ADMINISTRATIVO, PastasRelatorio.REL_ADMINISTRATIVO_COLABORADOR, "");
            RelatorioSession.setBytesRelatorioInSession(rel);
        } catch (Exception e) {
//            erroCliente.adicionaErro(e);
        }
    }
    
    public void desativarColaborador(){
        try {
            colaborador.setAtivo(false);
            colaboradorcontroller.atualizar(colaborador);
            MensagensUtil.enviarMessageParamentroInfo(MensagensUtil.REGISTRO_ATUALIZADO, "teress","rewe");
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(ColaboradorMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void consultarColaborador() {
        try {
//            listaDeColaborador = colaboradorcontroller.consultarLike(getCampoConsuta(), getValorCampoConsuta().toUpperCase());
            listaDeColaborador = colaboradorcontroller.consultarAtivo(getCampoConsuta(), getValorCampoConsuta(), "col_ativo=true");
        } catch (Exception ex) {
            Logger.getLogger(ColaboradorMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Map<String, Object> getCampo() {
        Map<String, Object> map = new HashMap<>();
        map.put("Nome", "nome");
        map.put("CPF", "cpf");
        return map;
    }
    
    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public List<Colaborador> getListaDeColaborador() {
        return listaDeColaborador;
    }

    public List<Pessoa> getListaDePessoas() {
        return listaDePessoas;
    }

}
