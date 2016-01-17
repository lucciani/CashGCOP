/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.utilitarios;

import java.io.File;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author ari
 */
public class StringUtil {

    public static String[] quebrarStringPorSeparador(String separador, String valor) {
        String[] i = valor.split(separador);
        return i;
    }

    public static String formatarTelefone(String telefone) {
        if (telefone == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(telefone);
        if (sb.length() == 10) {
            sb.insert(0, "(");
            sb.insert(3, ")");
            sb.insert(8, "-");

        } else if (sb.length() == 8) {
            sb.insert(4, "-");
        } else if (sb.length() == 11) {
            sb.insert(0, "(");
            sb.insert(3, ")");
            sb.insert(9, "-");
        } else if (sb.length() == 9) {
            sb.insert(5, "-");
        }

        return sb.toString();

    }
    
    public static String removerCaracteresEspeciais(String st) {
        
        return st.replaceAll("[^a-zZ-Z0-9 ]", "");
      }

    public static void main(String[] args) {
        List<File> listaDeArquivosDaPasta = ManipuladorDeArquivo.listaDeArquivosDaPasta(ManipuladorDeArquivo.PASTA_LOGOS);

        for (File file : listaDeArquivosDaPasta) {
            System.out.println(file.getName());
        }

//        System.out.println(StringUtil.removerCaracteresEspeciais("005.222.403-13"));
//        System.out.println(StringUtil.formatarTelefone("8836110665"));
//        System.out.println(StringUtil.formatarTelefone("88936110665"));
//        System.out.println(StringUtil.formatarTelefone("936110665"));

//        String[] s = StringUtil.quebrarStringPorSeparador("-", "tarefa_teste-fdsddf-rewwer");
//        System.out.println(s[0]);
//        System.out.println(s[1]);
//        System.out.println(s[2]);
    }
}
