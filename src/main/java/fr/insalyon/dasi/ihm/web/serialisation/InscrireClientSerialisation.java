package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.ihm.web.serialisation.Serialisation;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.ProfilAstral;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author thibautgravey
 */
public class InscrireClientSerialisation extends Serialisation{

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject container = new JsonObject();

        String nom = (String)request.getAttribute("nom");
        String prenom = (String)request.getAttribute("prenom");
        Boolean inscription = (Boolean)request.getAttribute("client");
        Boolean generationProfil = (Boolean)request.getAttribute("profil");
        
        container.addProperty("inscription", inscription);
        container.addProperty("generationProfil", generationProfil);
        container.addProperty("nom",nom);
        container.addProperty("prenom",prenom);

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
