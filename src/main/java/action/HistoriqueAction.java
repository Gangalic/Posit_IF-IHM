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

public class HistoriqueAction extends Action{

    @Override
    public void execute(HttpServletRequest request) {
        Client c = null;
        List<String[]> listeHistorique = null;
        try{
            c = Service.trouverClient((String)request.getAttribute("mail"));
            listeHistorique=Service.historiqueClient(c, (Boolean) request.getAttribute("version"));
        } catch (Exception ex) {
            Logger.getLogger(ProfilAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        String json;
        if ((Boolean) request.getAttribute("version")){
            json = JsonPrinter.jsonToString(JsonPrinter.printHistoriqueConsultation(listeHistorique));
        }else{
            json = JsonPrinter.jsonToString(JsonPrinter.printHistorique(listeHistorique));
        }
        request.setAttribute("historique", json);
    }
    
}
