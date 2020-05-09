/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import static fr.insalyon.dasi.ihm.web.serialisation.SerialisationUtils.ConsultationToJson;
import static fr.insalyon.dasi.ihm.web.serialisation.SerialisationUtils.MediumToJson;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Medium;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author thibautgravey
 */
public class DetailsConsultationSerialisation extends Serialisation{

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Consultation c = (Consultation)request.getAttribute("consultation");
        Boolean com = (Boolean)request.getAttribute("commentaire");
        
        JsonObject container = new JsonObject();
        
        if(c!=null){
            //Sérialisation de la consultation
            JsonObject jsonConsultation = ConsultationToJson(c,com);

            //Sérialisation du médium associé
            Medium m = c.getMedium();
            JsonObject jsonMedium = MediumToJson(m);
            jsonConsultation.add("medium",jsonMedium);

            container.add("consultation",jsonConsultation);
        }
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
    
}
