package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
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
        Client client = (Client) service.authentifierUtilisateur(email, password);

        request.setAttribute("client", client);
        
        // Gestion de la Session: ici, enregistrer l'ID du Client authentifié
        HttpSession session = request.getSession();
        if (client != null) {
            session.setAttribute("idClient", client.getId());
        }
        else {
            session.removeAttribute("idClient");
        }
        
    }
    
}
