/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import posit_if.OM.Employe;
import posit_if.Service.Service;

/**
 *
 * @author cgangalic
 */
public class ConnectionEmployeAction extends Action{

    @Override
    public void execute(HttpServletRequest request) {
        Employe e = null;
        String idString = request.getParameter("id");
        int id=-1;
        try{
            id = Integer.parseInt(idString);
            e = Service.authentificationEmploye(id);
        } catch(Exception ex){
            Logger.getLogger(ConnectionEmployeAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (e!=null){
            request.setAttribute("exist", true);
            request.setAttribute("id", id);
        }else{
            request.setAttribute("exist", false);
        }
    }
    
}
