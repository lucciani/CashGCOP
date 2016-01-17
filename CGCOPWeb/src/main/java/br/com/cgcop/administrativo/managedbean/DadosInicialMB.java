/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.administrativo.managedbean;

import br.com.cgcop.utilitarios.enumeration.Mes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

/**
 *
 * @author ari
 */
@Named
@ViewScoped
public class DadosInicialMB implements Serializable {

    private List<String> listaString;
    private List<String> listaString2;
    private LineChartModel lineModel;
    private Integer ano;
   

    @PostConstruct
    public void init() {
        ano = 2015;
        createLineModels();
        listaString = new ArrayList<>();
        listaString2 = new ArrayList<>();
        String a;
        for (int i = 0; i < 10; i++) {
            a = "Projeto: ".concat(String.valueOf(i+1));
            listaString.add(a);
            a = "Obra: ".concat(String.valueOf(i+1));
            listaString2.add(a);
        }

    }

    private void createLineModels() {

        lineModel = initCategoryModel();
        lineModel.setTitle("(Projetos | Obras)");
        lineModel.setLegendPosition("e");
        lineModel.setShowPointLabels(true);
        lineModel.getAxes().put(AxisType.X, new CategoryAxis("(Mes)"));
        Axis yAxis = lineModel.getAxis(AxisType.Y);
        yAxis = lineModel.getAxis(AxisType.Y);
        yAxis.setLabel("(Quantidades)");
        yAxis.setMin(0);
        yAxis.setMax(28);
    }

    private LineChartModel initCategoryModel() {
        LineChartModel model = new LineChartModel();

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Projeto");
        boys.set(Mes.JANEIRO.getAbreviacao(), 10);
        boys.set(Mes.FEVEREIRO.getAbreviacao(), 15);
        boys.set(Mes.MARÇO.getAbreviacao(), 5);
        boys.set(Mes.ABRIL.getAbreviacao(), 8);
        boys.set(Mes.MAIO.getAbreviacao(), 3);
        boys.set(Mes.JUNHO.getAbreviacao(), 2);
        boys.set(Mes.JULHO.getAbreviacao(), 19);
        boys.set(Mes.AGOSTO.getAbreviacao(), 25);
        boys.set(Mes.SETEMBRO.getAbreviacao(), 13);
        boys.set(Mes.OUTUBRO.getAbreviacao(), 10);
        boys.set(Mes.NOVEMBRO.getAbreviacao(), 9);
        boys.set(Mes.DEZEMBRO.getAbreviacao(), 12);
      
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Obras");
        girls.set(Mes.JANEIRO.getAbreviacao(), 8);
        girls.set(Mes.FEVEREIRO.getAbreviacao(), 5);
        girls.set(Mes.MARÇO.getAbreviacao(), 1);
        girls.set(Mes.ABRIL.getAbreviacao(), 3);
        girls.set(Mes.MAIO.getAbreviacao(), 3);
        girls.set(Mes.JUNHO.getAbreviacao(), 5);
        girls.set(Mes.JULHO.getAbreviacao(), 8);
        girls.set(Mes.AGOSTO.getAbreviacao(), 10);
        girls.set(Mes.SETEMBRO.getAbreviacao(), 5);
        girls.set(Mes.OUTUBRO.getAbreviacao(), 2);
        girls.set(Mes.NOVEMBRO.getAbreviacao(), 5);
        girls.set(Mes.DEZEMBRO.getAbreviacao(), 3);

        model.addSeries(boys);
        model.addSeries(girls);

        return model;
    }

    public List<String> getListaString() {
        return listaString;
    }

    public LineChartModel getLineModel() {
        return lineModel;
    }

    public Integer getAno() {
        return ano;
    }

    public List<Integer> getListaDeAnos(){
       return Mes.ABRIL.getAnos();
    }

    public List<String> getListaString2() {
        return listaString2;
    }
           
    
}
