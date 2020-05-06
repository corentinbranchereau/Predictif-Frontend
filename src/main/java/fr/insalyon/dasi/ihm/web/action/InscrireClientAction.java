package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Genre;
import fr.insalyon.dasi.metier.modele.ProfilAstral;
import fr.insalyon.dasi.metier.service.Service;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author thibautgravey
 */
public class InscrireClientAction extends Action{

    @Override
    public void executer(HttpServletRequest request) {
        
        //Récupération des informations du formulaire d'inscription et formatage
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String telephone = request.getParameter("telephone");
        
        Genre genre = null;
        String temp = request.getParameter("genre");
        switch(temp){
            case "Masculin" :
                genre = Genre.Masculin;
                break;
            case "Feminin" :
                genre = Genre.Feminin;
                break;
            default :
                genre = Genre.Autre;
                break;
        }
        
        String adresse = request.getParameter("adresse");
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
        String naissance = request.getParameter("dateNaissance");
        
        //Création du client
        Client client;
        try {
            client = new Client(nom,prenom,genre,email,password,simpleDateFormat.parse(naissance),adresse,telephone);
        } catch (ParseException ex) {
            client = null;
        }
        
        //Inscription du client
        Service service = new Service();
        Long idClient = service.inscrireUtilisateur(client);
        
        if(idClient==null)
            client = null;
        
        //Génération du profil Astral
        ProfilAstral profil = null;
        if(client!=null)
        {
            //profil = service.genererProfilAstral(client);
        }
        
        //Ajout dans la requête
        request.setAttribute("client", client);
        request.setAttribute("profil", profil);
    }
    
}
