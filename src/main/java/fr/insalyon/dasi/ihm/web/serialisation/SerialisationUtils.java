package fr.insalyon.dasi.ihm.web.serialisation;

import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Cartomancien;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.Spirite;
import java.text.SimpleDateFormat;

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
     
    public static JsonObject ConsultationToJson(Consultation c, boolean com){
        JsonObject jsonConsultation = new JsonObject();

        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy 'à' hh'h'mm");

        //Sérialisation de la consultation et de ses propriétés
        if(c.getId()!=null){
            jsonConsultation.addProperty("id",c.getId());
        }
        if(c.getDateDebut()!=null){
            jsonConsultation.addProperty("dateDebut",formater.format(c.getDateDebut()));
        }
        if(c.getDuree()!=null){
            jsonConsultation.addProperty("duree",c.getDuree());
        }
        
        if(com && c.getCommentaire()!=null){jsonConsultation.addProperty("commentaire",c.getCommentaire());}
        
        if(c.getEstTerminee()!=null){
            jsonConsultation.addProperty("estTerminee",c.getEstTerminee());
        }
        
        return jsonConsultation;
    }
    
}
