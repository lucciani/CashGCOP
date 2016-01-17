/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.seguranca.modelo;

/**
 *
 * @author ari
 */
public enum Perfil {
    ADMINISTRADOR_SISTEMA("Administrador do Sistema"),
    USUARIO("Usuário");

    private final String descricao;

    private Perfil(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
