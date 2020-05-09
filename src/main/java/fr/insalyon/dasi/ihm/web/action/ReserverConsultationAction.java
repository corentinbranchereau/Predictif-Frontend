/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Corentin
 */
public class ReserverConsultationAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        Long idMedium =new Long(request.getParameter("idMedium"));
        System.out.println("id Medium: "+idMedium);
        Service service = new Service();
        HttpSession session = request.getSession();
        Client client = (Client)service.obtenirUtilisateurParId((Long)session.getAttribute("idClient"));
        Medium medium= service.detailMediumParId(idMedium);
        Consultation consultation=service.demanderConsultation(client,medium);
        
        
        request.setAttribute("consultation",consultation);
    }
    
}
