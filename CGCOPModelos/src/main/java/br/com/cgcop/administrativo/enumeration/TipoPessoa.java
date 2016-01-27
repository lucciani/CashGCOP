/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.administrativo.enumeration;

/**
 *
 * @author Gian
 */
public enum TipoPessoa {
    ADM("Administrador"),
    SOLICITANTE("Solicitante"),
    REPRESENTANTE("Representante"),
    PROCURADORIA("Procuradoria"),
    FINANCEIRO("Financeiro"),
    LOGISTICA("Logistica"),
    DIRETORIA("Diretoria"),
    OUTRO("Outro");
    
    private final String descricao;

    private TipoPessoa(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
}
