package br.com.ppesj.portal.viewmodel

import br.com.ppesj.portal.constants.Constants
import org.zkoss.bind.annotation.BindingParam
import org.zkoss.bind.annotation.Command
import org.zkoss.bind.annotation.Init
import org.zkoss.bind.annotation.NotifyChange
import org.zkoss.zk.ui.Executions
import org.zkoss.zk.ui.Session
import org.zkoss.zk.ui.Sessions

class NavegacaoVM {

//        Session session
//        Usuario usuarioLogado = new Usuario()
//        ClassCSSMenuDTO menuClass = new ClassCSSMenuDTO()
        Constants constant = new Constants()

        String currentPage

        @Init
        public void init() {
            currentPage = constant.HOME_PAGE
        }

        @Command
        @NotifyChange(['currentPage', 'menuClass'])
        public void changePage(@BindingParam("contexto") String contexto) {
            String page = contexto
//            menuClass.mudancaMenu(page)
            if (page.equals("home")) {
                currentPage = constant.HOME_PAGE
            } else if (page.equals("aviso")) {
                currentPage = constant.AVISO_DETALHE_PAGE
            } else if (page.equals("comunidades")) {
                currentPage = constant.COMUNIDADE_PAGE
            } else if (page.equals("pastoral")) {
                currentPage = constant.PASTORAL_PAGE
            } else if (page.equals("clero")) {
                currentPage = constant.CLERO_PAGE
            } else if (page.equals("config")) {
                currentPage = constant.CONFIG_APP
            } else if (page.equals("avisos")) {
                currentPage = constant.AVISOS_APP
            }
        }

//        @Command
//        public logoff(){
//            if(session !=null){
//                session.removeAttribute("usuario")
//            }
//            Executions.sendRedirect("/login.zul")
//        }

}
