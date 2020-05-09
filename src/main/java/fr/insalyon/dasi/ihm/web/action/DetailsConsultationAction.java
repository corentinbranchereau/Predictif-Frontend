/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author thibautgravey
 */
public class DetailsConsultationAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        //Instanciation de la classe de service
        Service service = new Service();
        
        //Recuperation de l'id de la consultation
        int id = Integer.parseInt(request.getParameter("id"));
        
        //Appel des services Métiers
        Consultation consultation = service.obtenirConsultationParId(Long.valueOf(id));
        
        //Stockage des résultats dans les attributs de la requête
        request.setAttribute("consultation", consultation);
    }
    
}
