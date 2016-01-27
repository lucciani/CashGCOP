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
@Table(name = "fundo_fixo", schema = "solicitacao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FundoFixo implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fdf_id", nullable = false)
    private Long id;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "fdf_emp_id", referencedColumnName = "emp_id", nullable = false)
    private Empresa empresa;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "fdf_cdc_id", referencedColumnName = "ctc_id", nullable = false)
    private CentroDeCusto centroDeCusto;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "fdf_col_id", referencedColumnName = "col_id", nullable = false)
    private Colaborador colaborador;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "fdf_data_solicitacao")
    private Date dataDeSolicitacao;
    
    @NotNull
    @Min(value = 0)
    @Column(name = "fdf_valor", nullable = false)
    private BigDecimal valor;

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

    public Date getDataDeSolicitacao() {
        return dataDeSolicitacao;
    }

    public void setDataDeSolicitacao(Date dataDeSolicitacao) {
        this.dataDeSolicitacao = dataDeSolicitacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final FundoFixo other = (FundoFixo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
