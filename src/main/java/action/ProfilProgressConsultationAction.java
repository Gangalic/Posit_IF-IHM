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
import posit_if.OM.Employe;
import posit_if.Service.Service;

/**
 *
 * @author cgangalic
 */
public class ProfilProgressConsultationAction extends Action{

    @Override
    public void execute(HttpServletRequest request) {
        Employe e = null;
        Client c = null;
        try{
            e = Service.trouverEmploye((int)request.getAttribute("idE"));
            c = Service.trouverClientEnProgress(e);
        } catch (Exception ex) {
            Logger.getLogger(ProfilProgressConsultationAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        String json = JsonPrinter.jsonToString(JsonPrinter.printClient(c));
        request.setAttribute("client", json);
    }
    
}
