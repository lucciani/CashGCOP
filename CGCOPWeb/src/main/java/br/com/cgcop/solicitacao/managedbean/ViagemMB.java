/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.solicitacao.managedbean;

import br.com.cgcop.administrativo.controller.CentroDeCustoController;
import br.com.cgcop.administrativo.controller.ColaboradorController;
import br.com.cgcop.administrativo.controller.EmpresaController;
import br.com.cgcop.administrativo.modelo.CentroDeCusto;
import br.com.cgcop.administrativo.modelo.Colaborador;
import br.com.cgcop.administrativo.modelo.Empresa;
import br.com.cgcop.solicitacao.Controller.ViagemController;
import br.com.cgcop.solicitacao.modelo.Passageiro;
import br.com.cgcop.solicitacao.modelo.Viagem;
import br.com.cgcop.utilitario.BeanGenerico;
import br.com.cgcop.utilitario.mensagens.MensagensUtil;
import br.com.cgcop.utilitario.relatorio.RelatorioSession;
import br.com.cgcop.utilitarios.relatorios.AssistentedeRelatorio;
import br.com.cgcop.utilitarios.relatorios.PastasRelatorio;
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
public class ViagemMB extends BeanGenerico implements Serializable {

    @Inject
    private ViagemController viagemController;
    private Viagem viagem;
    private List<Viagem> listaViagem;
    private List<Empresa> listaEmpresa;
    private List<CentroDeCusto> listaCentroDeCusto;

    private Date dataDaSolicitacao;
    private Date dataFinal;

    @PostConstruct
    @Override
    public void init() {
        try {
            criarListaDeCamposDaConsulta();
            viagem = (Viagem) lerRegistroDaSessao("viagem");
            if (viagem == null) {
                viagem = new Viagem();
                dataDaSolicitacao = new Date();
                dataFinal = new Date();
                viagem.setEmpresa(new Empresa());
                viagem.setCentroDeCusto(new CentroDeCusto());
            }
            listaViagem = new ArrayList<>();
        } catch (Exception ex) {
            Logger.getLogger(ViagemMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salvar() {
        try {
            viagemController.salvar(viagem);
            MensagensUtil.enviarMessageParamentroInfo(MensagensUtil.REGISTRO_SUCESSO, viagem.getId());
            init();
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(ViagemMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public void consultarViagem() {
        try {
            listaViagem = viagemController.consultarPorPeriodo(dataDaSolicitacao, dataFinal);
        } catch (Exception ex) {
            Logger.getLogger(ViagemMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Map<String, Object> getCampo() {
        Map<String, Object> map = new HashMap<>();
        map.put("Data", "dataDeSolicitacao");
        return map;
    }
    
    public void setarCentroDeCusto(CentroDeCusto c){
        viagem.setCentroDeCusto(c);
    }
    
    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    public Date getDataDaSolicitacao() {
        return dataDaSolicitacao;
    }

    public void setDataDaSolicitacao(Date dataDaSolicitacao) {
        this.dataDaSolicitacao = dataDaSolicitacao;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public List<Viagem> getListaViagem() {
        return listaViagem;
    }

    public List<Empresa> getListaEmpresa() {
        return listaEmpresa;
    }

    public List<CentroDeCusto> getListaCentroDeCusto() {
        return listaCentroDeCusto;
    }

}
