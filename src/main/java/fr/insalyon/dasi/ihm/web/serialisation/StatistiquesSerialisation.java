package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import static fr.insalyon.dasi.ihm.web.serialisation.SerialisationUtils.ConsultationToJson;
import static fr.insalyon.dasi.ihm.web.serialisation.SerialisationUtils.MediumToJson;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javafx.util.Pair;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author thibautgravey
 */
public class StatistiquesSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        List<Pair<Medium, Long>> mediumsStats = (List<Pair<Medium, Long>>)request.getAttribute("mediumsStats");
        List<Pair<Employe, Long>> employesStats = (List<Pair<Employe, Long>>)request.getAttribute("employesStats");
        Integer nbConsultations = (Integer)request.getAttribute("nbConsultations");
        Integer nbHeures = (Integer)request.getAttribute("nbHeures");
        
        JsonObject container = new JsonObject();
        
        JsonArray jsonListeMediums = new JsonArray();
         
        if(mediumsStats!=null){
            for(Pair<Medium,Long> p : mediumsStats){
                JsonObject jsonMedium = new JsonObject();
                jsonMedium.addProperty("denomination", p.getKey().getDenomination());
                jsonMedium.addProperty("value", p.getValue());
                
                jsonListeMediums.add(jsonMedium);
            }
        }
        
        JsonArray jsonListeEmployes = new JsonArray();
         
        if(employesStats!=null){
            for(Pair<Employe, Long> p : employesStats){
                JsonObject jsonEmploye = new JsonObject();
                jsonEmploye.addProperty("prenom", p.getKey().getPrenom());
                jsonEmploye.addProperty("nom", p.getKey().getNom());
                jsonEmploye.addProperty("value", p.getValue());
                jsonEmploye.addProperty("horaires",p.getKey().getTempsTravail());
                
                jsonListeEmployes.add(jsonEmploye);
            }
        }
    
    
        container.add("mediums",jsonListeMediums);
        container.add("employes",jsonListeEmployes);
        container.addProperty("nbConsultations",nbConsultations);
        container.addProperty("nbHeures",nbHeures);
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
