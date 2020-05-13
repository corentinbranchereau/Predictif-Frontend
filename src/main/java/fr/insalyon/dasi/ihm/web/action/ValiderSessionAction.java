package fr.insalyon.dasi.ihm.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author thibautgravey
 */
public class ValiderSessionAction extends Action{

    @Override
    public void executer(HttpServletRequest request) {
        String type = request.getParameter("verificationType");
        boolean valid = false;
        
        HttpSession session = request.getSession();
        
        if("client".equals(type)) {
            if(session.getAttribute("idClient")!=null){valid=true;}
        } else if("employe".equals(type)){
            if(session.getAttribute("idEmploye")!=null){valid=true;}
        } else if("both".equals(type)){
            if(session.getAttribute("idClient")!=null){
                valid=true;
            } else if(session.getAttribute("idEmploye")!=null){
                valid=true;
            }
        }
        
        request.setAttribute("valid",valid);
    }
    
}
