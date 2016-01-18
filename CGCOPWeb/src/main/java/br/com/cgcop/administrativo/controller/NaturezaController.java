/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.administrativo.controller;

import br.com.cgcop.administrativo.modelo.Natureza;
import br.com.cgcop.utilitario.ControllerGenerico;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author Gian
 */
@Stateless
public class NaturezaController extends ControllerGenerico<Natureza, String> implements Serializable{

    @Override
    protected void inicializaDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
