package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javafx.util.Pair;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author thibautgravey
 */
public class StatistiquesAction extends Action{

    @Override
    public void executer(HttpServletRequest request) {
        //Instanciation de la classe de service
        Service service = new Service();
        
        //Appel des services Métiers
        List<Pair<Medium, Long>> mediumsStats = service.ListeMediumConsultes();
        List<Pair<Employe, Long>> employesStats = service.ListeNombreClientParEmploye();
        
        HttpSession session = request.getSession();
        Employe emp = (Employe)service.obtenirUtilisateurParId((Long)session.getAttribute("idEmploye"));
       
        //Stockage des résultats dans les attributs de la requête
        request.setAttribute("mediumsStats", mediumsStats);
        request.setAttribute("employesStats", employesStats);
        request.setAttribute("nbConsultations", emp.getConsultations().size());
        request.setAttribute("nbHeures",emp.getTempsTravail());
    }
}
