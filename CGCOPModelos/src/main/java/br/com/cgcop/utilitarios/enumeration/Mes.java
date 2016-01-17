/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.utilitarios.enumeration;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ari
 */
public enum Mes {
    JANEIRO("Jan"),
    FEVEREIRO("Fev"),
    MARÇO("Mar"),
    ABRIL("Abr"),
    MAIO("Mai"),
    JUNHO("Jun"),
    JULHO("Jul"),
    AGOSTO("Ago"),
    SETEMBRO("Set"),
    OUTUBRO("Out"),
    NOVEMBRO("Nov"),
    DEZEMBRO("Dez");
    
    private final String abreviacao;

    private Mes(String abreviacao) {
        this.abreviacao = abreviacao;
    }

    public String getAbreviacao() {
        return abreviacao;
    }
    
    
    public List<Integer> getAnos(){
        List<Integer> an = new ArrayList<>();
        
        an.add(2015);
        an.add(2014);
        an.add(2013);
        
        return an;
    }
    
}
