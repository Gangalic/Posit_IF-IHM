/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import posit_if.OM.Client;
import posit_if.Service.Service;

/**
 *
 * @author cgangalic
 */
public class ModificationCoordonneesAction extends Action{

    @Override
    public void execute(HttpServletRequest request) {
        String mail = (String) request.getAttribute("mailClient");
        Client c = null;
        Client cFinal = null;
        try{
            c = Service.trouverClient(mail);
            c.setAddresse(request.getParameter("adresse"));
            c.setTel(request.getParameter("tel"));
            c.setMail(request.getParameter("mail"));
            cFinal = Service.modifierCoordonneesClient(c);
        }catch (Exception ex){
            Logger.getLogger(InscriptionAction.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("valid",false);
        }
        if (cFinal!=null){
            request.setAttribute("valid",true);
        }else{
            request.setAttribute("valid",false);
        }}
    
}
