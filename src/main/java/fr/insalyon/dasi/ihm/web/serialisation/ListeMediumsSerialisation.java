package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import static fr.insalyon.dasi.ihm.web.serialisation.SerialisationUtils.MediumToJson;
import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Cartomancien;
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
public class ListeMediumsSerialisation extends Serialisation{

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        List<Medium> listeMediums = (List<Medium>)request.getAttribute("mediums");
        
        JsonObject container = new JsonObject();
        
        JsonArray jsonListeMediums = new JsonArray();
            
        for(Medium m : listeMediums){
            JsonObject jsonMedium = MediumToJson(m);
            jsonListeMediums.add(jsonMedium);
        }
        
        container.add("mediums",jsonListeMediums);
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
