/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;


import fr.insalyon.dasi.metier.service.AstroTest;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author Corentin
 */
public class PredictionAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        String animal = request.getParameter("animal");
        String  couleur= request.getParameter("couleur");
        int amour=parseInt(request.getParameter("amour"));
        int travail=parseInt(request.getParameter("travail"));
        int sante=parseInt(request.getParameter("sante"));
        AstroTest astroTest=new AstroTest();
        
        List<String> predictions=null;
        try {
            predictions=astroTest.getPredictions(couleur,animal,amour,sante,travail);
        } catch (IOException ex) {
            Logger.getLogger(PredictionAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
         request.setAttribute("predictions", predictions);
       
    }
    
}
