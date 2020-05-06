package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author thibautgravey
 */
public class ListerMediumsAction extends Action{

    @Override
    public void executer(HttpServletRequest request) {
       
        //Instanciation de la classe de service
        Service service = new Service();
        
        //Appel des services Métiers
        List<Medium> listeMediums = service.listeMediumTriée();
        
        //Stockage des résultats dans les attributs de la requête
        request.setAttribute("mediums", listeMediums);
       
    }
}
