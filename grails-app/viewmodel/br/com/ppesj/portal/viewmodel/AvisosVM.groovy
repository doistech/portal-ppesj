package br.com.ppesj.portal.viewmodel

import org.zkoss.bind.annotation.BindingParam
import org.zkoss.bind.annotation.Command
import org.zkoss.bind.annotation.Init
import org.zkoss.bind.annotation.NotifyChange
import org.zkoss.bind.annotation.QueryParam
import ppesjPortal.Aviso

import java.text.SimpleDateFormat

class AvisosVM {
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy")

    def listaAvisos = []
    def listaAvisosDetalhe = []

    List<Aviso> avisoList = new ArrayList<Aviso>()

    Aviso aviso
    def avisoDetalhe

    @Init
    public void init(){
        avisosAtivos()
        avisosAtivosTodos()
    }

    @Command
    @NotifyChange(['*'])
    public void avisosAtivos(){
        Date currentDate = new Date()
        avisoList = Aviso.createCriteria().list {
            le('dataInicioVigencia',currentDate)
            ge('dataFimVigencia', currentDate)
            order("dataEvento", "asc")
            maxResults(5)
        }
        avisoList.each { Aviso a ->
            String dataHora = df.format(a.dataEvento) + ' - ' + a.hora
            String tituloAviso = a.titulo
            String decricao = a.descricao
            Long idAvisos = a.id
            listaAvisos.add([data:dataHora, titulo:tituloAviso, descricao:decricao, id:idAvisos])
        }
    }

    @Command
    @NotifyChange(['*'])
    public void avisosAtivosTodos(){
        Date currentDate = new Date()
        avisoList = Aviso.createCriteria().list {
            le('dataInicioVigencia',currentDate)
            ge('dataFimVigencia', currentDate)
            order("dataEvento", "asc")
        }

        aviso = avisoList.get(0)
        String dataHora1 = df.format(avisoList.get(0).dataEvento) + ' - ' + avisoList.get(0).hora
        String tituloAviso1 = avisoList.get(0).titulo
        String decricao1 = avisoList.get(0).descricao
        Long idAvisos = avisoList.get(0).id
        avisoDetalhe = [data:dataHora1, titulo:tituloAviso1, descricao:decricao1, id:idAvisos]
        avisoList.remove(0)

        avisoList.each { Aviso a ->
            String dataHora = df.format(a.dataEvento) + ' - ' + a.hora
            String tituloAviso = a.titulo
            String decricao = a.descricao
            listaAvisosDetalhe.add([data:dataHora, titulo:tituloAviso, descricao:decricao, id:a.id])
        }
    }

    @Command
    @NotifyChange(['*'])
    public void leiaMais(@BindingParam("idAviso") Long id){
        listaAvisosDetalhe.clear()
        avisoList.clear()

        String dataHora
        String tituloAviso
        String decricao

        Date currentDate = new Date()
        avisoList = Aviso.createCriteria().list {
            le('dataInicioVigencia',currentDate)
            ge('dataFimVigencia', currentDate)
            order("dataEvento", "asc")
        }
        avisoList.each { Aviso aviso1 ->

            dataHora = df.format(aviso1.dataEvento) + ' - ' + aviso1.hora
            tituloAviso = aviso1.titulo
            decricao = aviso1.descricao
            if(aviso1.id == id){
                aviso = aviso1
                avisoDetalhe = [data:dataHora, titulo:tituloAviso, descricao:decricao, id:aviso1.id]
            }else{
                listaAvisosDetalhe.add([data:dataHora, titulo:tituloAviso, descricao:decricao, id:aviso1.id])
            }
        }
        if(aviso!=null){
            avisoList.remove(aviso)
        }
    }
}
