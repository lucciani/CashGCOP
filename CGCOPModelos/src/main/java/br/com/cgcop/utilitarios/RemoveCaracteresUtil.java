/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.utilitarios;

import java.text.Normalizer;

/**
 *
 * @author gilmario
 */
public class RemoveCaracteresUtil {

    public static String removeAccentos(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    public static String removerCaracteres(String text) {
        return text.replaceAll("[^0-9]", "");
    }
}
