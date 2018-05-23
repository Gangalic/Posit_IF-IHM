/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import jsonPrinter.JsonPrinter;
import posit_if.OM.Client;
import posit_if.OM.Medium;
import posit_if.Service.Service;

/**
 *
 * @author cgangalic
 */
public class DemandeVoyanceAction extends Action{

    @Override
    public void execute(HttpServletRequest request) {
        Client c = null;
        Medium m = null;
        Boolean dispo = false; 
        try{
            c = Service.trouverClient((String) request.getAttribute("mailClient"));
            m = Service.trouverMedium(request.getParameter("nameMedium"));
            if(c!=null && m!=null){
                dispo = Service.demandeVoyance(c, m);
            }
            if (dispo){
                Service.recevoirNotifications(m, c);
            }
        } catch(Exception ex) {
            Logger.getLogger(BioMediumAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        String json = JsonPrinter.jsonToString(JsonPrinter.printDemandeVoyance(dispo));
        request.setAttribute("dispo", json);
    }
    
}
