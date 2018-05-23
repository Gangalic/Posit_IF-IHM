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
import posit_if.Service.Service;

/**
 *
 * @author cgangalic
 */
public class ListeAstrologuesAction extends Action{

    @Override
    public void execute(HttpServletRequest request) {
        List<String> listeA = null;
        try{
            listeA = Service.listeAstrologues();
        } catch (Exception ex) {
            Logger.getLogger(ListeAstrologuesAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        String json = JsonPrinter.jsonToString(JsonPrinter.printListeAstrologues(listeA));
        request.setAttribute("astrologues", json);
    }
    
}
