/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Corentin
 */
public class DeconnecterAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
       HttpSession session = request.getSession();
       
       if(session.getAttribute("idClient")!=null){
           session.removeAttribute("idClient");
       } else if(session.getAttribute("idEmploye")!=null){
           session.removeAttribute("idEmploye");
       }
    }
}