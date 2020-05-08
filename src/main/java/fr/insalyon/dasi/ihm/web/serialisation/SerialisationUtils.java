/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Cartomancien;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.Spirite;

/**
 *
 * @author thibautgravey
 */
public class SerialisationUtils {
    
    public static JsonObject MediumToJson(Medium m){
        JsonObject jsonMedium = new JsonObject();
        jsonMedium.addProperty("id",m.getId());
        jsonMedium.addProperty("denomination",m.getDenomination());
        jsonMedium.addProperty("genre",m.getGenre().toString());
        jsonMedium.addProperty("presentation",m.getPresentation());

        if(m instanceof Spirite){
            Spirite s = (Spirite)m;
            jsonMedium.addProperty("type","Spirite");
            jsonMedium.addProperty("support",s.getSupport());
        } else if (m instanceof Astrologue){
            Astrologue a = (Astrologue)m;
            jsonMedium.addProperty("type","Astrologue");
            jsonMedium.addProperty("formation",a.getFormation());
            jsonMedium.addProperty("promotion", a.getPromotion());
        } else if (m instanceof Cartomancien){
            jsonMedium.addProperty("type","Cartomancien");
        }
        
        return jsonMedium;
    }
    
}
