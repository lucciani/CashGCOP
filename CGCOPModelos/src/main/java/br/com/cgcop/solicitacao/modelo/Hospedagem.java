/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.solicitacao.modelo;

import br.com.cgcop.administrativo.modelo.Endereco;
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
@Table(name = "hospedagem", schema = "solicitacao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Hospedagem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hos_id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "hos_cidade_end_id", referencedColumnName = "end_id")
    private Endereco cidade;
    @Column(name = "hos_local_evento", length = 1024)
    private String localDoEvento;
    @Temporal(TemporalType.DATE)
    @Column(name = "hos_data_entrada")
    private Date dataEntrada;
    @Temporal(TemporalType.DATE)
    @Column(name = "hos_data_saida")
    private Date dataSaida;
    @NotNull
    @Min(value = 0)
    @Column(name = "hos_diarias", nullable = false)
    private BigDecimal diarias;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Endereco getCidade() {
        return cidade;
    }

    public void setCidade(Endereco cidade) {
        this.cidade = cidade;
    }

    public String getLocalDoEvento() {
        return localDoEvento;
    }

    public void setLocalDoEvento(String localDoEvento) {
        this.localDoEvento = localDoEvento;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public BigDecimal getDiarias() {
        return diarias;
    }

    public void setDiarias(BigDecimal diarias) {
        this.diarias = diarias;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final Hospedagem other = (Hospedagem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
