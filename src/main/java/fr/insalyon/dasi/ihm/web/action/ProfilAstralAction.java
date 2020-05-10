package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.service.Service;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        Client client=null;
        //Recuperation du client
        if(request.getAttribute("idClient")!=null){
            client = (Client)service.obtenirUtilisateurParId((Long)request.getAttribute("idClient"));
        }else{
            HttpSession session = request.getSession();
            client = (Client)service.obtenirUtilisateurParId((Long)session.getAttribute("idClient"));
        }
        
        //Stockage des résultats dans les attributs de la requête
        request.setAttribute("profilAstral", client.getProfilAstral());
        request.setAttribute("prenomClient", client.getPrenom());
        request.setAttribute("nomClient", client.getNom());
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");  
        String date = dateFormat.format(client.getDateNaissance());  
        request.setAttribute("dateNaissance",date);
    }
    
}
