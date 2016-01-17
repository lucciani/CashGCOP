/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.administrativo.modelo;


import br.com.cgcop.administrativo.enumeration.TipoEvento;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author ari
 */
@Entity
@Table(name = "evento",schema = "administrativo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Evento implements Serializable{
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eve_id", nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "eve_titulo", nullable = false, length = 255)
    private String titulo;

    @NotBlank
    @Column(name = "eve_descricao", nullable = false, length = 1024)
    private String descricao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "eve_data_ini", nullable = false)
    private Date dataInicio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "eve_data_fim", nullable = false)
    private Date dataFim;

    @Column(name = "eve_dia_todo", nullable = false, columnDefinition = "boolean default false")
    private boolean diaTodo;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "eve_tipo_evento",nullable = false)
    private TipoEvento tipoEvento;
    
  
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public boolean isDiaTodo() {
        return diaTodo;
    }

    public void setDiaTodo(boolean diaTodo) {
        this.diaTodo = diaTodo;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

 

    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Evento other = (Evento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
