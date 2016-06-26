/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.solicitacao.enumeration;

/**
 *
 * @author ari
 */
public enum TipoExtencaoArquivo {
    
    DWG_AutoCAD("application/acad",".dwg"),
    DXF_AutoCAD("application/dxf",".dxf"),
    DWF_AutoCAD("application/x-dwf",".dwf"),
    EXCEL("application/msexcel",".xls"),
    WORD("application/msword",".doc"),
    PDF("application/pdf",".pdf"),
    JPG("image/jpeg",".jpg");
    
    
     
    private final String mime;
    private final String extencao;

    private TipoExtencaoArquivo(String mime, String extencao) {
        this.mime = mime;
        this.extencao = extencao;
    }

    public String getMime() {
        return mime;
    }

    public String getExtencao() {
        return extencao;
    }
    
    
    
}
