package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author thibautgravey
 */
public class HistoriqueClientAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        //Instanciation de la classe de service
        Service service = new Service();
        
        //Recuperation du client
       Client client=null;
        Long id=new Long(request.getParameter("idClient"));
        //Recuperation du client
        if(id!=null){
            client = (Client)service.obtenirUtilisateurParId(id);
        }else{
            HttpSession session = request.getSession();
            client = (Client)service.obtenirUtilisateurParId((Long)session.getAttribute("idClient"));
        }
        
        //Appel des services Métiers
        List<Consultation> historiqueClient = service.HistoriqueClientTrié(client);
        
        //Stockage des résultats dans les attributs de la requête
        request.setAttribute("historique", historiqueClient);
    }
    
}
