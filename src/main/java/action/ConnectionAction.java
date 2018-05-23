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
public class ConnectionAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        Client c = null;
        String mail = request.getParameter("mail");
        try{
            c = Service.authentificationClient(mail);
        } catch(Exception ex){
            Logger.getLogger(ConnectionAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (c!=null){
            request.setAttribute("exist", true);
            request.setAttribute("client", mail);
        }else{
            request.setAttribute("exist", false);
        }
    }
    
}
