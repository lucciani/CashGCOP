/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.administrativo.enumeration;

/**
 *
 * @author ari
 */
public enum TipoEvento {
    
    REUNIAO("Reunião"),
    PAGAMENTO("Pagamento"),
    VISITA("Visita"),
    VIAGEM("Viagem"),
    OUTRO("Outro");
    
    private final String descricao;

    private TipoEvento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
    
    
    
}
