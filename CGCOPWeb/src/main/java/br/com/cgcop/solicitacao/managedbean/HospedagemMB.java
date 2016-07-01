/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.solicitacao.managedbean;

import br.com.cgcop.administrativo.controller.MunicipioController;
import br.com.cgcop.administrativo.controller.UnidadeFederativaController;
import br.com.cgcop.administrativo.modelo.Colaborador;
import br.com.cgcop.administrativo.modelo.Endereco;
import br.com.cgcop.administrativo.modelo.Municipio;
import br.com.cgcop.administrativo.modelo.UnidadeFederativa;
import br.com.cgcop.solicitacao.Controller.HospedagemController;
import br.com.cgcop.solicitacao.Controller.ViagemController;
import br.com.cgcop.solicitacao.modelo.Hospedagem;
import br.com.cgcop.solicitacao.modelo.Viagem;
import br.com.cgcop.utilitario.BeanGenerico;
import br.com.cgcop.utilitario.mensagens.MensagensUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
public class HospedagemMB extends BeanGenerico implements Serializable {

    @Inject
    private HospedagemController hospedagemController;
    private Hospedagem hospedagem;
    private List<Hospedagem> listaHospedagem;
    @Inject
    private MunicipioController municipioController;
    private List<Municipio> listaDeMunicpios;

    private Date data;
    private Date dataFinal;

    @PostConstruct
    @Override
    public void init() {
        try {
            criarListaDeCamposDaConsulta();
            hospedagem = (Hospedagem) lerRegistroDaSessao("hospedagem");
            hospedagem = new Hospedagem();
            data = new Date();
            dataFinal = new Date();
            hospedagem.setHospede(new Colaborador());
            listaHospedagem = new ArrayList<>();
        } catch (Exception ex) {
            Logger.getLogger(HospedagemMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salvar() {
        try {
            hospedagemController.salvar(hospedagem);
            MensagensUtil.enviarMessageParamentroInfo(MensagensUtil.REGISTRO_SUCESSO, hospedagem.getClass());
            init();
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(HospedagemMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarHospedagem() {
        try {
            listaHospedagem = hospedagemController.consultarPorPeriodo(data, dataFinal);
        } catch (Exception ex) {
            Logger.getLogger(HospedagemMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Map<String, Object> getCampo() {
        Map<String, Object> map = new HashMap<>();
        map.put("Data", "dataEntrada");
        return map;
    }
    

    public Hospedagem getHospedagem() {
        return hospedagem;
    }

    public void setHospedagem(Hospedagem hospedagem) {
        this.hospedagem = hospedagem;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public List<Hospedagem> getListaHospedagem() {
        return listaHospedagem;
    }

    public List<Municipio> getListaDeMunicpios() {
        return listaDeMunicpios;
    }

}
