/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import static fr.insalyon.dasi.ihm.web.serialisation.SerialisationUtils.ConsultationToJson;
import static fr.insalyon.dasi.ihm.web.serialisation.SerialisationUtils.MediumToJson;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 *
 * @author Corentin
 */
public class HistoriqueEmployeSerialisation extends Serialisation{

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        List<Consultation> historiqueEmploye = (List<Consultation>)request.getAttribute("historique");
        Employe employe=(Employe)request.getAttribute("employe");
        
        JsonObject container = new JsonObject();
        
        JsonArray jsonListeConsultation = new JsonArray();
         
        if(historiqueEmploye!=null){
            for(Consultation c : historiqueEmploye){
                    //Sérialisation de la consultation
                    JsonObject jsonConsultation = ConsultationToJson(c,true);

                    //Sérialisation du médium associé
                    Medium m = c.getMedium();
                    Client client=c.getClient();
                    
                    JsonObject jsonMedium = MediumToJson(m);
                    jsonConsultation.add("medium",jsonMedium);
                    
                    JsonObject jsonClient=new JsonObject();
                    jsonClient.addProperty("id",client.getId());
                    jsonClient.addProperty("nom",client.getNom());
                    jsonClient.addProperty("prenom",client.getPrenom());
                    jsonConsultation.add("client", jsonClient);
                    
                    jsonListeConsultation.add(jsonConsultation);
                
            }
        }
        
        container.add("consultations",jsonListeConsultation);
        JsonObject jsonEmploye=new JsonObject();
        
        if(employe!=null){
            jsonEmploye.addProperty("id",employe.getId());
            jsonEmploye.addProperty("nom",employe.getNom());
            jsonEmploye.addProperty("prenom",employe.getPrenom());
            container.add("employe",jsonEmploye);
        }
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
    
}