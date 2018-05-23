package actionServlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import action.Action;
import action.BioMediumAction;
import action.CommenceVoyanceAction;
import action.ConnectionAction;
import action.ConnectionEmployeAction;
import action.DemandeVoyanceAction;
import action.HistoriqueAction;
import action.InscriptionAction;
import action.ListeAstrologuesAction;
import action.ListeTarologuesAction;
import action.ListeVoyantsAction;
import action.ModificationCoordonneesAction;
import action.ModificationIdentiteAction;
import action.ProfilAction;
import action.ProfilConsultationAction;
import action.ProfilProgressConsultationAction;
import action.RealisationPredictionAction;
import action.RecouperationStatistiqueAction;
import action.RedirectAction;
import action.TerminaisonConsultationAction;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import posit_if.DAO.JPAUtil;

/**
 *
 * @author cgangalic
 */
@WebServlet(urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    
    @Override
    public void init() throws ServletException {
        super.init();
        JPAUtil.init(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy() {
        JPAUtil.destroy(); //To change body of generated methods, choose Tools | Templates.
        super.destroy();
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String todo = request.getParameter("action");
        HttpSession session = request.getSession(true);
        switch(todo){
            //partie Client
            case "accessClient": {
                String mail=(String)session.getAttribute("mail");
                if (mail!=null && !(mail.equals(""))){
                    request.setAttribute("access",true);
                }else{
                    request.setAttribute("access",false);
                }
                Action action = new RedirectAction();
                action.execute(request);
                response.getWriter().println(request.getAttribute("rights"));
                break;
            }
            case "connection": {
                Action action= new ConnectionAction();
                action.execute(request);
                if((Boolean)request.getAttribute("exist")){
                    //it will connect the user and automatically disconnect the employer
                    session.setAttribute("mail",request.getAttribute("client"));
                    session.setAttribute("idEmploye",-1);
                }
                response.getWriter().println(request.getAttribute("exist"));
                System.out.println("Connexion effectuee");
                break;
            }
            case "deconnection": {
                session.setAttribute("mail","");
                response.getWriter().println(request.getAttribute("action"));
                System.out.println("Deconnexion effectuee");
                break;
            }
            case "inscription": {
                Action action = new InscriptionAction();
                action.execute(request);
                response.getWriter().println(request.getAttribute("valid"));
                System.out.println("Inscription effectuee");
                break;
            }
            case "listeVoyants": {
                Action action = new ListeVoyantsAction();
                action.execute(request);
                response.getWriter().println(request.getAttribute("voyants"));
                System.out.println("Liste Voyants affichee");
                break;
            }
            case "listeTarologues": {
                Action action = new ListeTarologuesAction();
                action.execute(request);
                response.getWriter().println(request.getAttribute("tarologues"));
                System.out.println("Liste Tarologues affichee");
                break;
            }
            case "listeAstrologues": {
                Action action = new ListeAstrologuesAction();
                action.execute(request);
                response.getWriter().println(request.getAttribute("astrologues"));
                System.out.println("Liste Astrologues affichee");
                break;
            }
            case "bioMedium": {
                Action action = new BioMediumAction();
                action.execute(request);
                response.getWriter().println(request.getAttribute("bio"));
                System.out.println("Profil de "+request.getParameter("name")+" affiche");
                break;
            }
            case "demandeVoyance": {
                Action action = new DemandeVoyanceAction();
                String mail = (String)session.getAttribute("mail");
                request.setAttribute("mailClient", mail);
                action.execute(request);
                response.getWriter().println(request.getAttribute("dispo"));
                System.out.println("Demande de voyance pour "+request.getParameter("nameMedium"));
                break;
            }
            case "profil":{
                String mail=(String)session.getAttribute("mail");
                request.setAttribute("mail", mail);
                Action action = new ProfilAction();
                action.execute(request);
                response.getWriter().println(request.getAttribute("client"));
                System.out.println("Profil affiche");
                break;
            }
            case "modificationIndentiteClient": {
                Action action = new ModificationIdentiteAction();
                String mail = (String)session.getAttribute("mail");
                request.setAttribute("mailClient", mail);
                action.execute(request);
                response.getWriter().println(request.getAttribute("valid"));
                System.out.println("Modification Identite effectuee");
                break;
            }
            case "modificationCoordonneesClient": {
                Action action = new ModificationCoordonneesAction();
                String mail = (String)session.getAttribute("mail");
                request.setAttribute("mailClient", mail);
                action.execute(request);
                if((Boolean)request.getAttribute("valid")){
                    session.setAttribute("mail",request.getParameter("mail"));
                }
                response.getWriter().println(request.getAttribute("valid"));
                System.out.println("Modification Identite effectuee");
                break;
            }
            case "historique":{
                String mail=(String)session.getAttribute("mail");
                request.setAttribute("mail", mail);
                request.setAttribute("version", false);
                Action action = new HistoriqueAction();
                action.execute(request);
                response.getWriter().println(request.getAttribute("historique"));
                System.out.println("Historique affiche");
                break;
            }
            
            //partie Employe
            case "accessEmploye": {
                if (session.getAttribute("idEmploye")== null){
                    request.setAttribute("access",false);
                }else{
                    if (((int)session.getAttribute("idEmploye"))>0){
                        request.setAttribute("access",true);
                    }
                }
                Action action = new RedirectAction();
                action.execute(request);
                response.getWriter().println(request.getAttribute("rights"));
                break;
            }
            case "connectionEmploye": {
                Action action= new ConnectionEmployeAction();
                action.execute(request);
                if((Boolean)request.getAttribute("exist")){
                    //it will connect the employer and automatically disconnect the user
                    session.setAttribute("idEmploye",request.getAttribute("id"));
                    session.setAttribute("mail","");
                }
                response.getWriter().println(request.getAttribute("exist"));
                System.out.println("Connexion effectuee");
                break;
            }
            case "deconnectionEmploye": {
                session.setAttribute("idEmploye",-1);
                response.getWriter().println(request.getAttribute("action"));
                System.out.println("Deconnexion effectuee");
                break;
            }
            case "profilConsultation":{
                int id;
                if (session.getAttribute("idEmploye")==null){
                    id = -1;
                }else{
                    id = (int) session.getAttribute("idEmploye");
                }
                request.setAttribute("idE", id);
                Action action = new ProfilConsultationAction();
                action.execute(request);
                response.getWriter().println(request.getAttribute("client"));
                System.out.println("Profil Consultation affiche");
                break;
            }
            case "historiqueConsultation":{
                request.setAttribute("mail", request.getParameter("mail"));
                request.setAttribute("version", true);
                Action action = new HistoriqueAction();
                action.execute(request);
                response.getWriter().println(request.getAttribute("historique"));
                System.out.println("Historique affiche");
                break;
            }
            case "commenceVoyance": {
                int id;
                if (session.getAttribute("idEmploye")==null){
                    id = -1;
                }else{
                    id = (int) session.getAttribute("idEmploye");
                }
                request.setAttribute("idE", id);
                Action action = new CommenceVoyanceAction();
                action.execute(request);
                response.getWriter().println(request.getAttribute("voyance"));
                System.out.println("Voyance commence");
                break;
            }
            case "profilProgressConsultation":{
                int id;
                if (session.getAttribute("idEmploye")==null){
                    id = -1;
                }else{
                    id = (int) session.getAttribute("idEmploye");
                }
                request.setAttribute("idE", id);
                Action action = new ProfilProgressConsultationAction();
                action.execute(request);
                response.getWriter().println(request.getAttribute("client"));
                System.out.println("Profil Consultation affiche");
                break;
            }
            case "realisationPrediction": {
                Action action = new RealisationPredictionAction();
                action.execute(request);
                response.getWriter().println(request.getAttribute("prediction"));
                System.out.println("Prediction commence");
                break;
            }
            case "terminaisonConsultation": {
                int id;
                if (session.getAttribute("idEmploye")==null){
                    id = -1;
                }else{
                    id = (int) session.getAttribute("idEmploye");
                }
                request.setAttribute("idE", id);
                Action action = new TerminaisonConsultationAction();
                action.execute(request);
                response.getWriter().println(request.getAttribute("voyance"));
                System.out.println("Consultation finit");
                break;
            }
            case "recouperationStatistique": {
                Action action = new RecouperationStatistiqueAction();
                action.execute(request);
                response.getWriter().println(request.getAttribute("statistique"));
                System.out.println("Statistique recoupere");
                break;
            }
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
