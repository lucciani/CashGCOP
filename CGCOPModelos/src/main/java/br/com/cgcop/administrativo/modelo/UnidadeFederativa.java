/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.administrativo.modelo;

import br.com.cgcop.utilitarios.RemoveCaracteresUtil;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Classe do Projeto GuardiaoModelos criada em 26/06/2013
 *
 * @author: ari
 */
@Entity
@Table(name = "unidade_federativa", schema = "administrativo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UnidadeFederativa implements Serializable {

    @Id
    @Column(name = "und_fed_id", nullable = false)
    @NotNull
    private Integer id;
    @Column(name = "und_fed_nome", unique = true)
    @NotBlank
    private String nome;
    @Column(name = "und_fed_sigla", unique = true)
    @NotBlank
    private String sigla;
   
    @Column(nullable = false, name = "und_ativo", columnDefinition = " boolean DEFAULT true ")
    @NotNull
    private boolean ativo;

    public UnidadeFederativa() {
    }

    public UnidadeFederativa(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = RemoveCaracteresUtil.removeAccentos(nome.toUpperCase());
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = RemoveCaracteresUtil.removeAccentos(sigla.toUpperCase());
    }

   

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
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
        final UnidadeFederativa other = (UnidadeFederativa) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "UnidadeFederativa{" + "nome=" + nome + ", abreviacao=" + sigla + '}';
    }
}
