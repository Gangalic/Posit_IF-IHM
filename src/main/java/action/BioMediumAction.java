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
import posit_if.Service.Service;

/**
 *
 * @author cgangalic
 */
public class BioMediumAction extends Action{

    @Override
    public void execute(HttpServletRequest request) {
        String biographie = "";
        try{
            biographie = Service.consulterMedium(request.getParameter("name"));
        } catch(Exception ex) {
            Logger.getLogger(BioMediumAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        String json = JsonPrinter.jsonToString(JsonPrinter.printBioMedium(biographie));
        request.setAttribute("bio", json);
    }
    
}
