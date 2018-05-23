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
 * @author cgangalic
 */
public class CommenceVoyanceAction extends Action{

    @Override
    public void execute(HttpServletRequest request) {
        Employe e = null;
        Boolean started = false;
        try{
            e = Service.trouverEmploye((int)request.getAttribute("idE"));
            e = Service.commencerVoyance(e, new Date());
        } catch (Exception ex) {
            Logger.getLogger(CommenceVoyanceAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (e!=null){
            started=true;
        }
        String json = JsonPrinter.jsonToString(JsonPrinter.printCommenceVoyance(started));
        request.setAttribute("voyance", json);
    }
    
}
