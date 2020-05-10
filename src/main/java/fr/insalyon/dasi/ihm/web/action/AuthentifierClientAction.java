package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Utilisateur;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DASI Team
 */
public class AuthentifierClientAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Service service = new Service();
        Utilisateur utilisateur = service.authentifierUtilisateur(email, password);
        
        if(utilisateur instanceof Client){  
            
            Client client = (Client)utilisateur;
            request.setAttribute("client", client);
            request.setAttribute("employe", null);
            // Gestion de la Session: ici, enregistrer l'ID du Client authentifié
            HttpSession session = request.getSession();
            if (client != null) {
                session.setAttribute("idClient", client.getId());
            }
            else {
                session.removeAttribute("idClient");
            }
        }
        if(utilisateur instanceof Employe){
            
            Employe employe = (Employe)utilisateur;
            request.setAttribute("client", null);
            request.setAttribute("employe", employe);
            // Gestion de la Session: ici, enregistrer l'ID du Client authentifié
            HttpSession session = request.getSession();
            if (employe != null) {
                session.setAttribute("idEmploye", employe.getId());
            }
            else {
                session.removeAttribute("idEmploye");
            }
        } 
    }
    
}
