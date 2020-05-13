/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Corentin
 */
public class TerminerConsultationAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        //Instanciation de la classe de service
        Service service = new Service();
        
        //Recuperation de l'id de la consultation
        int id = Integer.parseInt(request.getParameter("id"));
        String commentaire=(String)request.getParameter("commentaire");
        System.out.println("id: "+id+"  commentaire: "+commentaire);
        //Appel des services Métiers
        Consultation consultation = service.obtenirConsultationParId(Long.valueOf(id));
        System.out.println("consultation avant validation: " +consultation);
        consultation = service.validerConsultation(consultation,commentaire);
        System.out.println("consultation après validation: " +consultation);
        //Stockage des résultats dans les attributs de la requête
        request.setAttribute("consultation", consultation);
    }
    
}