/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import jsonPrinter.JsonPrinter;
import posit_if.OM.Employe;
import posit_if.Service.Service;

/**
 *
 * @author Catalin
 */
public class TerminaisonConsultationAction extends Action{

    @Override
    public void execute(HttpServletRequest request) {
        Employe e = null;
        Boolean finished = false;
        String c = request.getParameter("com");
        try{
            e = Service.trouverEmploye((int)request.getAttribute("idE"));
            finished = Service.terminerVoyance(e, c,new Date());
        } catch (Exception ex) {
            Logger.getLogger(TerminaisonConsultationAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        String json = JsonPrinter.jsonToString(JsonPrinter.printCommenceVoyance(finished));
        request.setAttribute("voyance", json);
    }
    
}
