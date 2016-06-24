/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.solicitacao.managedbean;

import br.com.cgcop.administrativo.controller.ColaboradorController;
import br.com.cgcop.administrativo.modelo.CentroDeCusto;
import br.com.cgcop.administrativo.modelo.Colaborador;
import br.com.cgcop.solicitacao.Controller.PassageiroController;
import br.com.cgcop.solicitacao.Controller.ViagemController;
import br.com.cgcop.solicitacao.modelo.Passageiro;
import br.com.cgcop.solicitacao.modelo.Viagem;
import br.com.cgcop.utilitario.BeanGenerico;
import br.com.cgcop.utilitario.mensagens.MensagensUtil;
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
 * @author Gian
 */
@Named
@ViewScoped
public class PassageiroMB extends BeanGenerico implements Serializable{

    @Inject
    private PassageiroController passageiroController;
    private Passageiro passageiro;
    private List<Passageiro> listaPassageiro;
    @Inject
    private ColaboradorController colaboradorController;
    private Colaborador colaborador;
    private List<Colaborador> listaColaborador;
    @Inject
    private ViagemController viagemController;
    private Viagem viagem;
    
    
    @PostConstruct
    @Override
    public void init() {
        try {
            criarListaDeCamposDaConsulta();
            passageiro = (Passageiro) lerRegistroDaSessao("passageiro");
            if(passageiro == null){
                passageiro = new Passageiro();
                passageiro.setColaborador(new Colaborador());
                passageiro.setViagem(new Viagem());
            }
            listaPassageiro = new ArrayList<>();
            listaColaborador = colaboradorController.consultarTodosOrdenadorPor("nome");
        } catch (Exception ex) {
            Logger.getLogger(PassageiroMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void salvar() {
        try {
            passageiroController.salvar(passageiro);
            MensagensUtil.enviarMessageParamentroInfo(MensagensUtil.REGISTRO_SUCESSO, passageiro.getId());
            init();
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(PassageiroMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public void consultarPassageiro() {
        try {
            listaPassageiro = passageiroController.consultarLike(getCampoConsuta(), getValorCampoConsuta().toUpperCase());
        } catch (Exception ex) {
            Logger.getLogger(PassageiroMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Map<String, Object> getCampo() {
        Map<String, Object> map = new HashMap<>();
        map.put("Código", "id");
        return map;
    }
    
    public void setarColaborador(Colaborador c){
        passageiro.setColaborador(c);
    }
    
    public void setarViagem(Viagem v) {
           passageiro.setViagem(v);
    }
    
    public Passageiro getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public List<Passageiro> getListaPassageiro() {
        return listaPassageiro;
    }

    public List<Colaborador> getListaColaborador() {
        return listaColaborador;
    }

    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }
}
