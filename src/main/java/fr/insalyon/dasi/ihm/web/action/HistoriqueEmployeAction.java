/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Corentin
 */
public class HistoriqueEmployeAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        //Instanciation de la classe de service
        Service service = new Service();
        
        //Recuperation du employe
        HttpSession session = request.getSession();
        Employe employe = (Employe)service.obtenirUtilisateurParId((Long)session.getAttribute("idEmploye"));
        
        //Appel des services Métiers
        List<Consultation> historiqueEmploye = service.HistoriqueEmployeTrié(employe);
        
        //Stockage des résultats dans les attributs de la requête
        request.setAttribute("historique", historiqueEmploye);
        request.setAttribute("employe", employe);
    }
    
}