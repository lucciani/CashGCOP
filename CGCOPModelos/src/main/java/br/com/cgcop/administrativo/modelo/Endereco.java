/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.administrativo.modelo;

import br.com.cgcop.utilitarios.RemoveCaracteresUtil;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 */
@Entity
@Table(name = "logradouro", schema = "administrativo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "end_id", nullable = false)
    private Long id;
    @Column(name = "end_cep", nullable = false)
    @NotBlank
    private String cep;
    @NotNull(message = "O nome do logradouro não pode ser nulo")
    @NotEmpty(message = "O nome do logradouro não pode ser vazio")
    @NotBlank
    @Column(name = "end_nome", nullable = false, length = 50)
    private String nome;
    @Column(name = "end_bairro", nullable = false, length = 50)
    private String bairro;
    @Column(name = "end_numero",  length = 50)
    private String numero;
  
    @Column(name = "end_complemento")
    private String complemento;
  
    @ManyToOne
    @NotNull
    @JoinColumn(name = "mun_id", referencedColumnName = "mun_id", nullable = false)
    private Municipio municipio;
   

    public Endereco() {
    }

   
    
    public String getNomeDaCidade() {
        return this.municipio.getNome();
    }

    public String getAbreviacaoUnidadeFederativa() {
        return this.getMunicipio().getUnidadeFederativa().getSigla();
    }

    public String getNomeUnidadeFederativa() {
        return this.getMunicipio().getUnidadeFederativa().getNome();
    }

  

    public Endereco(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep.replaceAll("[^0-9]", "");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = RemoveCaracteresUtil.removeAccentos(nome.toUpperCase());
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = RemoveCaracteresUtil.removeAccentos(bairro.toUpperCase());
    }

 
    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }
    
    
    
    
    public UnidadeFederativa getUnidadeFederativa(){
        return municipio.getUnidadeFederativa();
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final Endereco other = (Endereco) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "CEP: " + cep + ", Rua: " + nome +", "+numero+", "+complemento  + ", Bairro: " + bairro + " " + municipio.getNome()+"-"+municipio.getUnidadeFederativa().getSigla() ;
    }

}
