package fr.insalyon.dasi.ihm.web;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.ihm.web.action.Action;
import fr.insalyon.dasi.ihm.web.action.AuthentifierClientAction;
import fr.insalyon.dasi.ihm.web.action.DeconnecterAction;
import fr.insalyon.dasi.ihm.web.action.DetailsConsultationAction;
import fr.insalyon.dasi.ihm.web.action.HistoriqueClientAction;
import fr.insalyon.dasi.ihm.web.action.HistoriqueEmployeAction;
import fr.insalyon.dasi.ihm.web.action.InscrireClientAction;
import fr.insalyon.dasi.ihm.web.action.ListerMediumsAction;
import fr.insalyon.dasi.ihm.web.action.ProfilAstralAction;
import fr.insalyon.dasi.ihm.web.action.ReserverConsultationAction;
import fr.insalyon.dasi.ihm.web.serialisation.DeconnecterSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.DetailsConsultationSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.HistoriqueClientSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.HistoriqueEmployeSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.InscrireClientSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.ListeMediumsSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.ProfilAstralSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.ProfilClientSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.ReserverConsultationSerialisation;
import fr.insalyon.dasi.ihm.web.serialisation.Serialisation;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DASI Team
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        JpaUtil.init();
    }

    @Override
    public void destroy() {
        JpaUtil.destroy();
        super.destroy();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        request.setCharacterEncoding("UTF-8");

        String todo = request.getParameter("todo");

        Action action = null;
        Serialisation serialisation = null;

        if (todo != null) {
            switch (todo) {
                case "connecter":
                    action = new AuthentifierClientAction();
                    serialisation = new ProfilClientSerialisation();
                    break;
                case "deconnecter":
                    action=new DeconnecterAction();
                    serialisation=new DeconnecterSerialisation();
                    break;
                case "inscrire":
                    action = new InscrireClientAction();
                    serialisation = new InscrireClientSerialisation();
                    break;
                case "listerMediums":
                    action = new ListerMediumsAction();
                    serialisation = new ListeMediumsSerialisation();
                    break;
                case "profilAstral":
                    action = new ProfilAstralAction();
                    serialisation = new ProfilAstralSerialisation();
                    break;
                case "historiqueClient":
                    action = new HistoriqueClientAction();
                    serialisation = new HistoriqueClientSerialisation();
                    break;
                case "historiqueEmploye":
                    action = new HistoriqueEmployeAction();
                    serialisation = new HistoriqueEmployeSerialisation();
                    break;
                case "detailsConsultation":
                    action = new DetailsConsultationAction();
                    serialisation = new DetailsConsultationSerialisation();
                    break;
                case "reserverConsultation":
                    action = new ReserverConsultationAction();
                    serialisation = new ReserverConsultationSerialisation();
                    break;
            }
        }
        
        if (action != null) {
            action.executer(request);
            serialisation.serialiser(request, response);
        }
        else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erreur dans les paramètres de la requête");
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
