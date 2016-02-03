/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.solicitacao.modelo;

import br.com.cgcop.administrativo.modelo.CentroDeCusto;
import br.com.cgcop.administrativo.modelo.Colaborador;
import br.com.cgcop.administrativo.modelo.Empresa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Gian
 */
@Entity
@Table(name = "viagem", schema = "solicitacao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Viagem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "via_id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "via_emp_id", referencedColumnName = "emp_id", nullable = false)
    private Empresa empresa;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "via_cdc_id", referencedColumnName = "ctc_id", nullable = false)
    private CentroDeCusto centroDeCusto;

    @Temporal(TemporalType.DATE)
    @Column(name = "via_data_solicitacao")
    private Date dataDaSolicitacao;

    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<Passageiro> passageiros;

    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<Passagem> passagens;

    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<Hospedagem> hospedagens;

    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<ItemDespesas> itensDespesas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Passageiro> getPassageiros() {
        return Collections.unmodifiableList(passageiros);
    }

    public void setPassageiros(List<Passageiro> passageiros) {
        this.passageiros = passageiros;
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

    public Date getDataDaSolicitacao() {
        return dataDaSolicitacao;
    }

    public void setDataDaSolicitacao(Date dataDaSolicitacao) {
        this.dataDaSolicitacao = dataDaSolicitacao;
    }

    public List<Passagem> getPassagens() {
        return Collections.unmodifiableList(passagens);
    }

    public void setPassagens(List<Passagem> passagens) {
        this.passagens = passagens;
    }

    public List<Hospedagem> getHospedagens() {
        return Collections.unmodifiableList(hospedagens);
    }

    public void setHospedagens(List<Hospedagem> hospedagens) {
        this.hospedagens = hospedagens;
    }

    public List<ItemDespesas> getItensDespesas() {
        return Collections.unmodifiableList(itensDespesas);
    }

    public void setItensDespesas(List<ItemDespesas> itensDespesas) {
        this.itensDespesas = itensDespesas;
    }

    public void addPassageiros(Passageiro pas) {
        if (this.passageiros == null) {
            passageiros = new ArrayList<>();
        }
        passageiros.add(pas);
    }

    public void addPassageiros(List<Passageiro> pas) {
        if (this.passageiros == null) {
            passageiros = new ArrayList<>();
        }
        passageiros.addAll(pas);
    }

    public void removerPassageiro(Passageiro pas) {
        if (passageiros.contains(pas)) {
            passageiros.remove(pas);
        }
    }

    public void addPassagem(Passagem psg) {
        if (this.passagens == null) {
            passagens = new ArrayList<>();
        }
        passagens.add(psg);
    }

    public void addPassagens(List<Passagem> psg) {
        if (this.passagens == null) {
            passagens = new ArrayList<>();
        }
        passagens.addAll(psg);
    }

    public void removerPassagem(Passagem psg) {
        if (passagens.contains(psg)) {
            passagens.remove(psg);
        }
    }

    public void addHospedagem(Hospedagem hos) {
        if (this.hospedagens == null) {
            hospedagens = new ArrayList<>();
        }
        hospedagens.add(hos);
    }

    public void addHospedagens(List<Hospedagem> hos) {
        if (this.hospedagens == null) {
            hospedagens = new ArrayList<>();
        }
        hospedagens.addAll(hos);
    }

    public void removerHospedagem(Hospedagem hos) {
        if (hospedagens.contains(hos)) {
            hospedagens.remove(hos);
        }
    }

    public void addItemDespesa(ItemDespesas des) {
        if (this.itensDespesas == null) {
            itensDespesas = new ArrayList<>();
        }
        itensDespesas.add(des);
    }

    public void addItensDespesas(List<ItemDespesas> des) {
        if (this.itensDespesas == null) {
            itensDespesas = new ArrayList<>();
        }
        itensDespesas.addAll(des);
    }

    public void removerItemDespesa(ItemDespesas des) {
        if (itensDespesas.contains(des)) {
            itensDespesas.remove(des);
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Viagem other = (Viagem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
