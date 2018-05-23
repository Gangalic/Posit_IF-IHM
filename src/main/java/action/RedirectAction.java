/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javax.servlet.http.HttpServletRequest;
import jsonPrinter.JsonPrinter;

/**
 *
 * @author cgangalic
 */
public class RedirectAction extends Action{

    @Override
    public void execute(HttpServletRequest request) {
        Boolean redirectPage = (Boolean) request.getAttribute("access");
        String json = JsonPrinter.jsonToString(JsonPrinter.printRedirect(redirectPage));
        request.setAttribute("rights", json);
    }
    
}
