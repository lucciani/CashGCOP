/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.administrativo.DAO;

import br.com.cgcop.administrativo.modelo.CentroDeCusto;
import br.com.cgcop.utilitario.DAOGenerico;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author Gian
 */
@Stateless
public class CentroDeCustoDAO extends DAOGenerico<CentroDeCusto, String> implements Serializable{
    
    public CentroDeCustoDAO() {
        super(CentroDeCusto.class);
    }
    
}
