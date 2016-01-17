/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cgcop.administrativo.managedbean;


import br.com.cgcop.administrativo.controller.EventoController;
import br.com.cgcop.administrativo.enumeration.TipoEvento;
import br.com.cgcop.administrativo.modelo.Evento;
import br.com.cgcop.utilitario.BeanGenerico;
import br.com.cgcop.utilitario.UtilitarioNavegacaoMB;
import br.com.cgcop.utilitario.mensagens.MensagensUtil;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author ari
 */
@Named
@ViewScoped
public class AgendaDeEventosMB extends BeanGenerico implements Serializable {

    @Inject
    private UtilitarioNavegacaoMB navegacaoMB;
    @Inject
    private EventoController eventoController;
   
    private List<Evento> listaDeEventos;
    private Evento evento;
    private ScheduleModel eventModel;
   

    @PostConstruct
    @Override
    public void init() {
        try {

         
            evento = new Evento();
            eventModel = new DefaultScheduleModel();

            //recupera a lista de eventos
            listaDeEventos = eventoController.consultarTodosOrdenadorPor("dataInicio");

            //percorre a lista de eventos e popula o calendario
            for (Evento agenda : listaDeEventos) {

                DefaultScheduleEvent agendaDeEvento = new DefaultScheduleEvent();
                agendaDeEvento.setAllDay(agenda.isDiaTodo());
                agendaDeEvento.setEndDate(agenda.getDataFim());
                agendaDeEvento.setStartDate(agenda.getDataInicio());
                agendaDeEvento.setTitle(agenda.getTipoEvento().getDescricao().concat(": ").concat(agenda.getTitulo()));
                agendaDeEvento.setDescription(agenda.getDescricao());
                agendaDeEvento.setData(agenda.getId());
                agendaDeEvento.setEditable(true); //pertir que o usuario edite
                
                //aqui e setado um tipo especifico de css para cada tipo de evento
                switch (agenda.getTipoEvento()) {
                    case PAGAMENTO:
                        agendaDeEvento.setStyleClass("pagamento");
                        break;
                    case REUNIAO:
                        agendaDeEvento.setStyleClass("reuniao");
                        break;
                    case VISITA:
                        agendaDeEvento.setStyleClass("visita");
                        break;
                    case VIAGEM:
                        agendaDeEvento.setStyleClass("viagem");
                        break;
                    case OUTRO:
                        agendaDeEvento.setStyleClass("outro");
                        break;
                    default:
                        break;
                }

                eventModel.addEvent(agendaDeEvento); //o evento e adicionado na lista
            }
        } catch (Exception ex) {
            Logger.getLogger(AgendaDeEventosMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salvar() {
        try {
            eventoController.salvarouAtualizar(evento);
            init();
            MensagensUtil.enviarMessageInfo(MensagensUtil.REGISTRO_SUCESSO);
            init();
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(AgendaDeEventosMB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void excluir() {
        try {
            eventoController.excluir(eventoController.gerenciar(evento.getId()));
            MensagensUtil.enviarMessageInfo(MensagensUtil.EXCLUIR_SUCESSO);
            init();
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.EXCLUIR_FALHA);
            Logger.getLogger(AgendaDeEventosMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Evento para quando o usuario clica em um espaco em branco no calendario
     *
     * @param selectEvent
     */
    public void quandoNovo(SelectEvent selectEvent) {

        ScheduleEvent event = new DefaultScheduleEvent("",
                (Date) selectEvent.getObject(), (Date) selectEvent.getObject());

        evento = new Evento();

        //recupero a data em q ele clicou
        evento.setDataInicio(event.getStartDate());
        evento.setDataFim(event.getEndDate());
    }

    /**
     * Evento para quando usuario clica em um enveto ja existente
     *
     * @param selectEvent
     */
    public void quandoSelecionado(SelectEvent selectEvent) {

        ScheduleEvent event = (ScheduleEvent) selectEvent.getObject();

        for (Evento agda : listaDeEventos) {
            if (agda.getId() == (Long) event.getData()) {
                evento = agda;
                break;
            }
        }
    }

    /**
     * Evento para quando o usuario 'move' um evento atraves de 'drag and drop'
     *
     * @param event
     */
    public void quandoMovido(ScheduleEntryMoveEvent event) {
        Calendar c = Calendar.getInstance();
        for (Evento agda : listaDeEventos) {
            c.setTimeInMillis(agda.getDataInicio().getTime());

            if (agda.getId() == (Long) event.getScheduleEvent().getData()) {
                try {

                    
                    int i = event.getDayDelta();

                    agda.setDataInicio(new Date(c.getTimeInMillis()));
                    agda.setDataFim(new Date(c.getTimeInMillis()));

                    evento = agda;
                    eventoController.salvarouAtualizar(agda);
                    init();
                    break;
                } catch (Exception ex) {
                    Logger.getLogger(AgendaDeEventosMB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Evento para quando o usuario 'redimenciona' um evento
     *
     * @param event
     */
    public void quandoRedimensionado(ScheduleEntryResizeEvent event) {

        for (Evento agda : listaDeEventos) {
            if (agda.getId() == (Long) event.getScheduleEvent().getData()) {
                try {
                    evento = agda;
                    eventoController.salvarouAtualizar(agda);
                    break;
                } catch (Exception ex) {
                    Logger.getLogger(AgendaDeEventosMB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

  

    public TipoEvento[] getListaDeTiposDeEventos() {
        return TipoEvento.values();
    }

    @Override
    protected Map<String, Object> getCampo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Evento> getListaDeEventos() {
        return listaDeEventos;
    }

    public void setListaDeEventos(List<Evento> listaDeEventos) {
        this.listaDeEventos = listaDeEventos;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

 

   

}
