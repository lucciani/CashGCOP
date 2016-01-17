/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.utilitarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author ari
 */
public class ManipuladorDeArquivo {

    public static final String PATH_WINDOWS = "C:" + File.separator;
    public static final String PATH_LINUX = "/" + File.separator;
    public static final String PASTA_LOGOS = "logos" + File.separator;

    
      public static String getDiretorioLogos(){
        if (ehLinux()) {
            return PATH_LINUX+PASTA_LOGOS;
        }else{
            return PATH_WINDOWS+PASTA_LOGOS;
        }
    }
    
    private static boolean ehLinux() {
        String os = System.getProperties().getProperty("os.name").toLowerCase();
        if (os.contains("windows")) {
            return false;
        }
        return true;
    }

    public static String retornaDiretorioDoSistemaOperacional() {
        if (ehLinux()) {
            return PATH_LINUX;
        } else {
            return PATH_WINDOWS;
        }
    }

    public static void excluirAquivo(String caminhoCompletoDoArquivo) {
        File file = new File(caminhoCompletoDoArquivo);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * Metódo para gravar arquivo localmente
     *
     * @param rotuloDaPasta nome do tipo de arquivos que seão gravados dentro da
     * pasta
     * @param identificadorPasta identificador único dauqla sub pasta
     * @param nomeArquivo
     * @param conteudo
     * @throws IOException
     * @throws Exception
     */
    private static void gravaArquivo(String rotuloDaPasta, String identificadorPasta, String nomeArquivo, byte[] conteudo) throws IOException, Exception {
        File pastaGeral = new File(PATH_WINDOWS + rotuloDaPasta + identificadorPasta);
        if (ehLinux()) {
            pastaGeral = new File(PATH_LINUX + rotuloDaPasta + identificadorPasta);
        }

        if (!pastaGeral.exists()) {
            if (!pastaGeral.mkdirs()) {
                throw new Exception("Erro ao cria pasta relativa");
            }
        } else {
            pastaGeral.delete();
        }

        try (FileOutputStream writer = new FileOutputStream(pastaGeral + File.separator + nomeArquivo)) {
            writer.write(conteudo);
            writer.flush();
        }
    }

    private static void gravaArquivo(String caminhoCompletoDoArquivo, byte[] conteudo) throws IOException {
        File pastaGeral = new File(caminhoCompletoDoArquivo);
        if (ehLinux()) {
            pastaGeral = new File(caminhoCompletoDoArquivo);
        }

        if (!pastaGeral.exists()) {
            if (!pastaGeral.mkdirs()) {
                throw new IOException("Erro ao cria pasta relativa");
            }
        } else {
            pastaGeral.delete();
        }

        FileOutputStream writer = new FileOutputStream(caminhoCompletoDoArquivo);
        writer.write(conteudo);
        writer.flush();
    }

    private static void gravaArquivo(String pasta, String arquivoComExtensao, byte[] conteudo) throws IOException {

        File pastaGeral = new File(pasta);
        File arquivo = new File(pasta + File.separator + arquivoComExtensao);
        if (ehLinux()) {
            pastaGeral = new File(pasta);
        }

        if (!pastaGeral.exists()) {
            if (!pastaGeral.mkdirs()) {
                throw new IOException("Erro ao cria pasta relativa");
            }
        }
        if (arquivo.exists()) {
            arquivo.delete();
        }

        FileOutputStream writer = new FileOutputStream(arquivo);
        writer.write(conteudo);
        writer.flush();
    }

    public static List<File> listaDeArquivosDaPasta(String pasta) {
        String relativo =  pasta;
        File f = new File(relativo);
        if (f.exists()) {
            return Arrays.asList(f.listFiles());
        } else {
            return new ArrayList<>();
        }
    }

    //Grava Arquivo localmente
    public static void gravarArquivoLocalmente(FileUploadEvent event, String rotuloDaPasta, String pasta) throws IOException, Exception {
        UploadedFile f = event.getFile();
        ManipuladorDeArquivo.gravaArquivo(rotuloDaPasta, pasta, f.getFileName(), f.getContents());
    }

    //Grava Arquivo localmente
    public static void gravarArquivoLocalmente(String pasta, String arquivoComExtensao, byte[] conteudo) throws IOException {
        ManipuladorDeArquivo.gravaArquivo(pasta, arquivoComExtensao, conteudo);
    }

    public static byte[] lerArquivoEmByte(String caminhoCompletoComExtensao) throws IOException {
        if (caminhoCompletoComExtensao != null) {
            File file = new File(caminhoCompletoComExtensao);

            InputStream is = null;
            byte[] buffer = null;

            is = new FileInputStream(file);
            buffer = new byte[is.available()];
            is.close();

            return buffer;
        }
        return null;

    }

    public static boolean checarSeExisteExcluir(String caminhoCompletoComExtensao) {
        if (caminhoCompletoComExtensao != null) {
            File file = new File(caminhoCompletoComExtensao);
            if (file.exists()) {
                file.delete();
                return true;
            }
            return false;

        }
        return false;

    }

}
