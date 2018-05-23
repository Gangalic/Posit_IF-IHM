/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import posit_if.OM.Client;
import posit_if.Service.Service;

/**
 *
 * @author cgangalic
 */
public class InscriptionAction extends Action{

    @Override
    public void execute(HttpServletRequest request) {
        SimpleDateFormat SDF_DATE = new SimpleDateFormat("dd/MM/yyyy");
        String j = request.getParameter("jour");
        String m = request.getParameter("mois");
        String a = request.getParameter("annee");
        Client c = null;
        Client cFinal = null;
        Boolean sameMails = false;
        if (request.getParameter("mail").equals(request.getParameter("mailC"))){
            sameMails = true;
        }
        try{
            c = new Client(request.getParameter("nom"),request.getParameter("prenom"),
                request.getParameter("civ"),SDF_DATE.parse(j+"/"+m+"/"+a),request.getParameter("mail"),
                request.getParameter("tel"),request.getParameter("adresse"));
            if (sameMails){
                cFinal = Service.inscriptionClient(c);
            }
        }catch (Exception ex){
            Logger.getLogger(InscriptionAction.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("valid",false);
            Service.envoyerMail(false, c);
        }
        if (cFinal!=null && sameMails){
            request.setAttribute("valid",true);
            Service.envoyerMail(true, cFinal);
        }else{
            request.setAttribute("valid",false);
            Service.envoyerMail(false, c);
        }
    }
    
}
