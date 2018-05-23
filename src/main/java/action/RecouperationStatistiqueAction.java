/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import jsonPrinter.JsonPrinter;
import posit_if.Service.Service;

/**
 *
 * @author Catalin
 */
public class RecouperationStatistiqueAction extends Action{

    @Override
    public void execute(HttpServletRequest request) {
        HashMap<String,Long> mapM = null;
        HashMap<String,Long> mapE = null;
        HashMap<String,Double> mapRepartition = null;
        try{
            mapM = Service.nbVoyancesParMedium();
            mapE = Service.nbVoyancesParEmploye();
            mapRepartition = Service.repatitionVoyancesEmployes();
        } catch (Exception ex) {
            Logger.getLogger(RealisationPredictionAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        String json = JsonPrinter.jsonToString(JsonPrinter.printStatistique(mapM, mapE, mapRepartition));
        request.setAttribute("statistique", json);
    }
    
}
