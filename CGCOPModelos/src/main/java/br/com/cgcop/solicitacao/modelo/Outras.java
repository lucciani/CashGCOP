/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.solicitacao.modelo;

import br.com.cgcop.administrativo.modelo.CentroDeCusto;
import br.com.cgcop.administrativo.modelo.Colaborador;
import br.com.cgcop.administrativo.modelo.Empresa;
import br.com.cgcop.administrativo.modelo.Natureza;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author Gian
 */
@Entity
@Table(name = "outras", schema = "solicitacao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Outras implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "out_id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "out_emp_id", referencedColumnName = "emp_id", nullable = false)
    private Empresa empresa;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "out_col_id", referencedColumnName = "col_id", nullable = false)
    private Colaborador colaborador;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "out_cdc_id", referencedColumnName = "ctc_id", nullable = false)
    private CentroDeCusto centroDeCusto;

    @Temporal(TemporalType.DATE)
    @Column(name = "out_data_solicitacao")
    private Date dataDaSolicitacao;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "nat_id", referencedColumnName = "nat_id", nullable = false)
    private Natureza natureza;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "col_id", referencedColumnName = "col_id", nullable = false)
    private Colaborador diretor;

    @NotNull
    @Min(value = 0)
    @Column(name = "out_valor", nullable = false)
    private BigDecimal valor;

    @Temporal(TemporalType.DATE)
    @Column(name = "out_data_recebimento")
    private Date dataLimiteRecebimento;

    @Column(name = "out_motivo",length = 1024)
    private String motivo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Natureza getNatureza() {
        return natureza;
    }

    public void setNatureza(Natureza natureza) {
        this.natureza = natureza;
    }

    public Colaborador getDiretor() {
        return diretor;
    }

    public void setDiretor(Colaborador diretor) {
        this.diretor = diretor;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getDataLimiteRecebimento() {
        return dataLimiteRecebimento;
    }

    public void setDataLimiteRecebimento(Date dataLimiteRecebimento) {
        this.dataLimiteRecebimento = dataLimiteRecebimento;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.id);
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
        final Outras other = (Outras) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
