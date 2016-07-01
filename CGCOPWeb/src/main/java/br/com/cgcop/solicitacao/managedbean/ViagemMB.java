/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.solicitacao.managedbean;

import br.com.cgcop.administrativo.controller.AeroportoController;
import br.com.cgcop.administrativo.controller.ColaboradorController;
import br.com.cgcop.administrativo.controller.EmpresaController;
import br.com.cgcop.administrativo.modelo.Aeroporto;
import br.com.cgcop.administrativo.modelo.CentroDeCusto;
import br.com.cgcop.administrativo.modelo.Colaborador;
import br.com.cgcop.administrativo.modelo.Empresa;
import br.com.cgcop.administrativo.modelo.Municipio;
import br.com.cgcop.administrativo.modelo.UnidadeFederativa;
import br.com.cgcop.solicitacao.Controller.HospedagemController;
import br.com.cgcop.solicitacao.Controller.PassagemController;
import br.com.cgcop.solicitacao.Controller.ViagemController;
import br.com.cgcop.solicitacao.modelo.Hospedagem;
import br.com.cgcop.solicitacao.modelo.Passagem;
import br.com.cgcop.solicitacao.modelo.Viagem;
import br.com.cgcop.utilitario.BeanGenerico;
import br.com.cgcop.utilitario.mensagens.MensagensUtil;
import br.com.cgcop.utilitario.relatorio.RelatorioSession;
import br.com.cgcop.utilitarios.ManipuladorDeArquivo;
import br.com.cgcop.utilitarios.relatorios.AssistentedeRelatorio;
import br.com.cgcop.utilitarios.relatorios.PastasRelatorio;
import static java.io.File.separator;
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
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

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
    @Inject
    private EmpresaController empresaController;
    private Empresa empresa;
    private List<Empresa> listaEmpresa;
    @Inject
    private ColaboradorController colaboradorController;
    private Colaborador colaborador;
    private List<Colaborador> passageiros;
    @Inject
    private PassagemController passagemController;
    private Passagem passagem;
    private List<Passagem> listaDePassagens;
    @Inject
    private HospedagemController hospedagemController;
    private Hospedagem hospedagem;
    private List<Hospedagem> listaDeHospedagens;
    @Inject
    private AeroportoController aeroportoController;
    private Aeroporto aeroporto;
    private List<Aeroporto> aeroportos;

    private List<CentroDeCusto> listaCentroDeCusto;

    private UploadedFile arquivoUpload;
    private byte documento[];
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
                viagem.setDataDaSolicitacao(new Date());
                dataFinal = new Date();
                viagem.setEmpresa(new Empresa());
                viagem.setCentroDeCusto(new CentroDeCusto());
                viagem.setPassagens(new ArrayList<>());

                passagem = new Passagem();
                passagem.setPassageiro(new Colaborador());
                passagem.setOrigem(new Aeroporto());
                passagem.setDestino(new Aeroporto());
                passagem.setDataPartida(new Date());

                hospedagem = new Hospedagem();
                hospedagem.setHospede(new Colaborador());
                hospedagem.setCidade(new String());
                hospedagem.setDataEntrada(new Date());
                hospedagem.setDataSaida(new Date());
                hospedagem.setLocalDoEvento(new String());
            } else {
                documento = ManipuladorDeArquivo.lerArquivoEmByte(getDiretorioReal("resources" + separator + "documentos" + separator + "CODIGO_VIAGEM-" + viagem.getId().toString() + ".pdf"));
            }
            listaViagem = new ArrayList<>();
            listaEmpresa = empresaController.consultarTodosOrdenadorPor("nome");
            aeroportos = aeroportoController.consultarTodosOrdenadorPor("cidade");
        } catch (Exception ex) {
            Logger.getLogger(ViagemMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salvar() {
        try {
            viagem = viagemController.salvarViagem(viagem);
            viagemController.addDoc(viagem.getId().toString(), documento, getDiretorioReal("resources" + separator + "documentos"));
            MensagensUtil.enviarMessageParamentroInfo(MensagensUtil.REGISTRO_SUCESSO, viagem.getId());
            init();
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(ViagemMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salvarPassagem() {
        try {
            viagem.addPassagem(passagem);
            passagemController.salvarPassagem(passagem);
            MensagensUtil.enviarMessageParamentroInfo(MensagensUtil.REGISTRO_SUCESSO, viagem.getId());
            init();
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(ViagemMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salvarHospedagem() {
        try {
            hospedagemController.salvarHospedagem(hospedagem);
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

    public void fileUploud(FileUploadEvent event) {
        try {
            documento = event.getFile().getContents();
        } catch (Exception ex) {
            Logger.getLogger(PassagemMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setarColaborador(Colaborador c) {
        passagem.setPassageiro(c);
    }

    public void setarCentroDeCusto(CentroDeCusto c) {
        viagem.setCentroDeCusto(c);
    }

    public void addPassagem(Passagem p) {
        try {
            viagem.addPassagem(p);
            viagemController.atualizar(viagem);
            MensagensUtil.enviarMessageInfo(MensagensUtil.REGISTRO_SUCESSO);
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(ViagemMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delPassagem(Passagem p) {
        try {
            viagem.removerPassagem(p);
            viagemController.atualizar(viagem);
            MensagensUtil.enviarMessageInfo(MensagensUtil.REGISTRO_SUCESSO);
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(ViagemMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addHospedagem(Hospedagem h) {
        try {
            viagem.addHospedagem(h);
            viagemController.atualizar(viagem);
            MensagensUtil.enviarMessageInfo(MensagensUtil.REGISTRO_SUCESSO);
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(ViagemMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delHospedagem(Hospedagem h) {
        try {
            viagem.removerHospedagem(h);
            viagemController.atualizar(viagem);
            MensagensUtil.enviarMessageInfo(MensagensUtil.REGISTRO_SUCESSO);
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(ViagemMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public boolean renderBtnAdd(Colaborador c) {
//        return viagem.getPassagens().contains(c);
//    }

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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public List<Colaborador> getPassageiros() {
        return passageiros;
    }

    public Passagem getPassagem() {
        return passagem;
    }

    public void setPassagem(Passagem passagem) {
        this.passagem = passagem;
    }

    public List<Passagem> getListaDePassagens() {
        return listaDePassagens;
    }

    public Hospedagem getHospedagem() {
        return hospedagem;
    }

    public void setHospedagem(Hospedagem hospedagem) {
        this.hospedagem = hospedagem;
    }

    public List<Hospedagem> getListaDeHospedagens() {
        return listaDeHospedagens;
    }

    public List<Aeroporto> getAeroportos() {
        return aeroportos;
    }

    public UploadedFile getArquivoUpload() {
        return arquivoUpload;
    }

    public void setArquivoUpload(UploadedFile arquivoUpload) {
        this.arquivoUpload = arquivoUpload;
    }

    public byte[] getDocumento() {
        return documento;
    }

    public void setDocumento(byte[] documento) {
        this.documento = documento;
    }

}
