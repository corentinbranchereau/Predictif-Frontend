package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Employe;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DASI Team
 */
public class ProfilClientSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        Client client = (Client)request.getAttribute("client");
        Employe employe = (Employe)request.getAttribute("employe");
        
        JsonObject container = new JsonObject();

        if (client != null) {
            container.addProperty("connexion", true);
            JsonObject jsonClient = new JsonObject();
            jsonClient.addProperty("id", client.getId());
            jsonClient.addProperty("nom", client.getNom());
            jsonClient.addProperty("prenom", client.getPrenom());
            jsonClient.addProperty("mail", client.getEmail());

            container.add("client", jsonClient);
            container.add("employe", null);
        }
        else if(employe!=null){
            container.addProperty("connexion", true);
            JsonObject jsonEmploye = new JsonObject();
            jsonEmploye.addProperty("id", employe.getId());
            jsonEmploye.addProperty("nom", employe.getNom());
            jsonEmploye.addProperty("prenom", employe.getPrenom());
            jsonEmploye.addProperty("mail", employe.getEmail());

            container.add("employe", jsonEmploye);
            container.add("client", null);
        }
        else{
            container.addProperty("connexion", false);
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
        
    }

}
