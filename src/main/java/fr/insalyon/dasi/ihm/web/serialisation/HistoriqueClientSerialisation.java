package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Cartomancien;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.Spirite;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author thibautgravey
 */
public class HistoriqueClientSerialisation extends Serialisation{

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        List<Consultation> historiqueClient = (List<Consultation>)request.getAttribute("mediums");
        
        JsonObject container = new JsonObject();
        
        JsonArray jsonListeConsultation = new JsonArray();
         
        if(historiqueClient!=null){
            for(Consultation c : historiqueClient){
                JsonObject jsonConsultation = new JsonObject();

                //Sérialisation de la consultation et de ses propriétés
                jsonConsultation.addProperty("id",c.getId());
                jsonConsultation.addProperty("dateDebut",c.getDateDebut().toString());
                jsonConsultation.addProperty("duree",c.getDuree());
                jsonConsultation.addProperty("commentaire",c.getCommentaire());
                jsonConsultation.addProperty("estTerminee",c.getEstTerminee());

                //Sérialisation du médium associé
                Medium m = c.getMedium();
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

                jsonConsultation.add("medium",jsonMedium);

                jsonListeConsultation.add(jsonConsultation);
            }
        }
        container.add("consultations",jsonListeConsultation);
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
    
}
