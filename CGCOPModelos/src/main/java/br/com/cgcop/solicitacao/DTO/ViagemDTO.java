/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.solicitacao.DTO;

import br.com.cgcop.administrativo.modelo.CentroDeCusto;
import br.com.cgcop.administrativo.modelo.Empresa;
import br.com.cgcop.solicitacao.modelo.Hospedagem;
import br.com.cgcop.solicitacao.modelo.ItemDespesas;
import br.com.cgcop.solicitacao.modelo.Passageiro;
import br.com.cgcop.solicitacao.modelo.Passagem;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gian
 */
public class ViagemDTO implements Serializable {

    private CentroDeCusto centroDeCusto;
    private Empresa empresa;
    private List<Passageiro> passageiros;
    private List<Passagem> passagens;
    private List<Hospedagem> hospedagens;
    private List<ItemDespesas> itensDespesas;

    public ViagemDTO() {
        passageiros = new ArrayList<>();
        passagens = new ArrayList<>();
        hospedagens = new ArrayList<>();
        itensDespesas = new ArrayList<>();
    }

    public CentroDeCusto getCentroDeCusto() {
        return centroDeCusto;
    }

    public void setCentroDeCusto(CentroDeCusto centroDeCusto) {
        this.centroDeCusto = centroDeCusto;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Passageiro> getPassageiros() {
        return passageiros;
    }

    public void setPassageiros(List<Passageiro> passageiros) {
        this.passageiros = passageiros;
    }

    public List<Passagem> getPassagens() {
        return passagens;
    }

    public void setPassagens(List<Passagem> passagens) {
        this.passagens = passagens;
    }

    public List<Hospedagem> getHospedagens() {
        return hospedagens;
    }

    public void setHospedagens(List<Hospedagem> hospedagens) {
        this.hospedagens = hospedagens;
    }

    public List<ItemDespesas> getItensDespesas() {
        return itensDespesas;
    }

    public void setItensDespesas(List<ItemDespesas> itensDespesas) {
        this.itensDespesas = itensDespesas;
    }

    public BigDecimal getTotal() {
        BigDecimal total = new BigDecimal(BigInteger.ZERO);
        for (ItemDespesas dsp : itensDespesas) {
            total = total.add(dsp.getTotalDiario());
        }
        return total;
    }

}
