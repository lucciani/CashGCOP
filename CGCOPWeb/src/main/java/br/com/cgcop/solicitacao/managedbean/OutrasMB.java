/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.solicitacao.managedbean;

import br.com.cgcop.administrativo.controller.CentroDeCustoController;
import br.com.cgcop.administrativo.controller.ColaboradorController;
import br.com.cgcop.administrativo.controller.EmpresaController;
import br.com.cgcop.administrativo.controller.NaturezaController;
import br.com.cgcop.administrativo.modelo.CentroDeCusto;
import br.com.cgcop.administrativo.modelo.Colaborador;
import br.com.cgcop.administrativo.modelo.Empresa;
import br.com.cgcop.administrativo.modelo.Natureza;
import br.com.cgcop.solicitacao.Controller.OutrasController;
import br.com.cgcop.solicitacao.modelo.Outras;
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
public class OutrasMB extends BeanGenerico implements Serializable {

    @Inject
    private OutrasController outrasController;
    private Outras outras;
    private List<Outras> listaOutras;
    @Inject
    private EmpresaController empresaController;
    private Empresa empresa;
    private List<Empresa> listaDeEmpresas;
    @Inject
    private CentroDeCustoController centroDeCustoController;
    private CentroDeCusto centroDeCusto;
    private List<CentroDeCusto> listaDeCentroDeCusto;
    @Inject
    private ColaboradorController colaboradorController;
    private Colaborador colaborador;
    private List<Colaborador> listaDeColaboradores;
    @Inject
    private NaturezaController naturezaController;
    private Natureza natureza;
    private List<Natureza> listaNatureza;
    
    private Date dataSolicitacao;
    private Date dataRecebimento;

    @PostConstruct
    @Override
    public void init() {
        try {
            criarListaDeCamposDaConsulta();
            outras = (Outras) lerRegistroDaSessao("outras");
            if(outras == null){
                outras = new Outras();
                dataSolicitacao = new Date();
                dataRecebimento = new Date();
                outras.setEmpresa(new Empresa());
                outras.setCentroDeCusto(new CentroDeCusto());
                outras.setColaborador(new Colaborador());
                outras.setNatureza(new Natureza());
            }
            listaOutras = new ArrayList<>();
            listaDeCentroDeCusto = centroDeCustoController.consultarTodosOrdenadorPor("id");
            listaDeColaboradores = colaboradorController.consultarTodosOrdenadorPor("nome");
            listaDeEmpresas = empresaController.consultarTodosOrdenadorPor("nome");
            listaNatureza = naturezaController.consultarTodosOrdenadorPor("descricao");
        } catch (Exception ex) {
            Logger.getLogger(OutrasMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salvar() {
        try {
            outrasController.salvar(outras);
            MensagensUtil.enviarMessageParamentroInfo(MensagensUtil.REGISTRO_SUCESSO, outras.getClass());
            init();
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(OutrasMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarOutras() {
        try {
            listaOutras = outrasController.consultarLike(getCampoConsuta(), getValorCampoConsuta().toUpperCase());
        } catch (Exception ex) {
            Logger.getLogger(OutrasMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Map<String, Object> getCampo() {
        Map<String, Object> map = new HashMap<>();
        map.put("Código", "id");
        return map;
    }
    
    public void setarCentroDeCusto(CentroDeCusto c){
        outras.setCentroDeCusto(c);
    }
    
    public void setarNatureza(Natureza n){
        outras.setNatureza(n);
    }
    public void setarColaborador(Colaborador col){
        outras.setColaborador(col);
    }

    public Outras getOutras() {
        return outras;
    }

    public void setOutras(Outras outras) {
        this.outras = outras;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public CentroDeCusto getCentroDeCusto() {
        return centroDeCusto;
    }

    public void setCentroDeCusto(CentroDeCusto centroDeCusto) {
        this.centroDeCusto = centroDeCusto;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Natureza getNatureza() {
        return natureza;
    }

    public void setNatureza(Natureza natureza) {
        this.natureza = natureza;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public Date getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(Date dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public List<Outras> getListaOutras() {
        return listaOutras;
    }

    public List<Empresa> getListaDeEmpresas() {
        return listaDeEmpresas;
    }

    public List<CentroDeCusto> getListaDeCentroDeCusto() {
        return listaDeCentroDeCusto;
    }

    public List<Colaborador> getListaDeColaboradores() {
        return listaDeColaboradores;
    }

    public List<Natureza> getListaNatureza() {
        return listaNatureza;
    }
    
}
