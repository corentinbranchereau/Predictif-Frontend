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
import static fr.insalyon.dasi.ihm.web.serialisation.SerialisationUtils.MediumToJson;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.ProfilAstral;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author thibautgravey
 */
public class ProfilAstralSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        ProfilAstral profil = (ProfilAstral)request.getAttribute("profilAstral");
        String prenom = (String)request.getAttribute("prenomClient");
        String nom = (String)request.getAttribute("nomClient");
        String date = (String)request.getAttribute("dateNaissance");
        System.out.println("date naissance:"+date);
        
        JsonObject container = new JsonObject();
        
        //Sérialisation du prenom du client
        container.addProperty("prenom", prenom);
        container.addProperty("nom", nom);
        container.addProperty("dateNaissance",date);
       
        JsonObject jsonProfil = new JsonObject();
            
        //Sérialisation du profil astral
        jsonProfil.addProperty("zodiaque", profil.getSigneZodiaque());
        jsonProfil.addProperty("astroChinois", profil.getSigneAstroChinois());
        jsonProfil.addProperty("couleur", profil.getCouleur());
        jsonProfil.addProperty("animalTotem", profil.getAnimalTotem());     
            
        container.add("profilAstral", jsonProfil);
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
    
}
