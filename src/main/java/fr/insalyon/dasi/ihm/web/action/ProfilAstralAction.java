package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author thibautgravey
 */
public class ProfilAstralAction extends Action  {

    @Override
    public void executer(HttpServletRequest request) {
        //Instanciation de la classe de service
        Service service = new Service();
        
        //Recuperation du client
        HttpSession session = request.getSession();
        Client client = (Client)service.obtenirUtilisateurParId((Long)session.getAttribute("idClient"));
        
        //Stockage des résultats dans les attributs de la requête
        request.setAttribute("profilAstral", client.getProfilAstral());
        request.setAttribute("prenomClient", client.getPrenom());
    }
    
}
