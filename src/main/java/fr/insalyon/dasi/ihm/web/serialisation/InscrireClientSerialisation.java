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
        Client client = (Client)request.getAttribute("client");
        ProfilAstral profil = (ProfilAstral)request.getAttribute("profil");
        
        JsonObject container = new JsonObject();

        Boolean inscription = (client != null);
        Boolean generationProfil = (profil != null);
        
        container.addProperty("inscription", inscription);
        container.addProperty("generationProfil", generationProfil);

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
