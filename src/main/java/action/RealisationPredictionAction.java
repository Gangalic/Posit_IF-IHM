/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import jsonPrinter.JsonPrinter;
import posit_if.OM.Client;
import posit_if.Service.Service;

/**
 *
 * @author Catalin
 */
public class RealisationPredictionAction extends Action{

    @Override
    public void execute(HttpServletRequest request) {
        Client c = null;
        List<String> lP = null;
        try{
            int a = Integer.parseInt(request.getParameter("amour"));
            int s = Integer.parseInt(request.getParameter("sante"));
            int t = Integer.parseInt(request.getParameter("travail"));
            c = Service.trouverClient(request.getParameter("mailC"));
            lP = Service.prediction(c, a, s, t);
        } catch (Exception ex) {
            Logger.getLogger(RealisationPredictionAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        String json = JsonPrinter.jsonToString(JsonPrinter.printPrediction(lP));
        request.setAttribute("prediction", json);
    }
    
}
