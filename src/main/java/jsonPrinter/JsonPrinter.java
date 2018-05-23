/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonPrinter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import posit_if.OM.Client;

/**
 *
 * @author cgangalic
 */
public class JsonPrinter {
        
    public static JsonObject printListeVoyants( List<String> voyants){
        JsonArray jsonListe = new JsonArray();
        for (String v : voyants){
            JsonObject jsonVoyant = new JsonObject();
            jsonVoyant.addProperty("nom",v);
            jsonListe.add(jsonVoyant);
        }
        JsonObject container = new JsonObject();
        container.add("voyants", jsonListe);
        return container;
    }
        
    public static JsonObject printListeTarologues( List<String> tarologues){
        JsonArray jsonListe = new JsonArray();
        for (String t : tarologues){
            JsonObject jsonTarologue = new JsonObject();
            jsonTarologue.addProperty("nom",t);
            jsonListe.add(jsonTarologue);
        }
        JsonObject container = new JsonObject();
        container.add("tarologues", jsonListe);
        return container;
    }
    
    public static JsonObject printListeAstrologues( List<String> astrologues){
        JsonArray jsonListe = new JsonArray();
        for (String a : astrologues){
            JsonObject jsonAstrologue = new JsonObject();
            jsonAstrologue.addProperty("nom",a);
            jsonListe.add(jsonAstrologue);
        }
        JsonObject container = new JsonObject();
        container.add("astrologues", jsonListe);
        return container;
    }
    
    public static JsonObject printBioMedium (String bio){
        JsonObject jsonBio = new JsonObject();
        jsonBio.addProperty("bio", bio);
        JsonObject container = new JsonObject();
        container.add("bio", jsonBio);
        return container;
    }
    
    public static JsonObject printDemandeVoyance (Boolean dispo){
        JsonObject jsonDispo = new JsonObject();
        jsonDispo.addProperty("disponibilite", dispo);
        JsonObject container = new JsonObject();
        container.add("dispo", jsonDispo);
        return container;
    }
    
    public static JsonObject printClient( Client c ){
        JsonObject jsonClient = new JsonObject();
        jsonClient.addProperty("attendu", false);
        if (c!=null){
            Format formatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateFull = (String) formatter.format(c.getDateNaissance());
            String[] dateParts = dateFull.split("/");
            
            jsonClient.addProperty("attendu", true);
            jsonClient.addProperty("mail",c.getMail());
            jsonClient.addProperty("civ", c.getCivilite());
            jsonClient.addProperty("nom",c.getNom());
            jsonClient.addProperty("prenom",c.getPrenom());
            jsonClient.addProperty("zodiaque",c.getSigne_z());
            jsonClient.addProperty("signeChinois",c.getSigne_ch());
            jsonClient.addProperty("animalTotem",c.getTotem());
            jsonClient.addProperty("tel",c.getTel());
            jsonClient.addProperty("adresse",c.getAddresse());
            jsonClient.addProperty("civilite",c.getCivilite());
            jsonClient.addProperty("couleurFavorite",c.getCouleur());
            jsonClient.addProperty("jour", dateParts[0]);
            jsonClient.addProperty("mois", dateParts[1]);
            jsonClient.addProperty("annee", dateParts[2]);
        }
        
        JsonObject container = new JsonObject();
        container.add("profil", jsonClient);
        return container;
    }
    
    public static JsonObject printHistorique( List<String[]> hist){
        JsonArray jsonHistorique = new JsonArray();
        if(hist != null)
        {    
            for (String[] a : hist){
                JsonObject jsonUnHistorique = new JsonObject();
                jsonUnHistorique.addProperty("medium",a[0]);
                jsonUnHistorique.addProperty("date",a[1]);
                jsonUnHistorique.addProperty("heureDebut",a[2]);
                jsonUnHistorique.addProperty("heureFin",a[3]);
                jsonHistorique.add(jsonUnHistorique);
            }
        }
        JsonObject container = new JsonObject();
        container.add("historique", jsonHistorique);
        return container;
    }
    
    public static JsonObject printHistoriqueConsultation( List<String[]> hist){
        JsonArray jsonHistorique = new JsonArray();
        if(hist != null)
        {    
            for (String[] a : hist){
                JsonObject jsonUnHistorique = new JsonObject();
                jsonUnHistorique.addProperty("medium",a[0]);
                jsonUnHistorique.addProperty("employe",a[1]);
                jsonUnHistorique.addProperty("date",a[2]);
                jsonUnHistorique.addProperty("heureDebut",a[3]);
                jsonUnHistorique.addProperty("heureFin",a[4]);
                jsonUnHistorique.addProperty("commentaire",a[5]);
                jsonHistorique.add(jsonUnHistorique);
            }
        }
        JsonObject container = new JsonObject();
        container.add("historique", jsonHistorique);
        return container;
    }
    
    public static JsonObject printRedirect( Boolean access ){
        JsonObject jsonAccess = new JsonObject();
        jsonAccess.addProperty("access", access);
        JsonObject container = new JsonObject();
        container.add("rights", jsonAccess);
        return container;
    }
    
    public static JsonObject printCommenceVoyance( Boolean started ){
        JsonObject jsonAccess = new JsonObject();
        jsonAccess.addProperty("passed", started);
        JsonObject container = new JsonObject();
        container.add("voyance", jsonAccess);
        return container;
    }
    
    public static JsonObject printPrediction( List<String> prediction){
        JsonArray jsonPrediction = new JsonArray();
        if(prediction != null)
        {    
            for (String p : prediction){
                JsonObject jsonUnPrediction = new JsonObject();
                jsonUnPrediction.addProperty("prediction",p);
                jsonPrediction.add(jsonUnPrediction);
            }
        }
        JsonObject container = new JsonObject();
        container.add("prediction", jsonPrediction);
        return container;
    }
    
    public static JsonObject printStatistique( HashMap<String,Long> mapM,
            HashMap<String,Long> mapE, HashMap<String,Double> mapR){
        JsonArray jsonStatMed = new JsonArray();
        JsonArray jsonStatEmp = new JsonArray();
        JsonArray jsonStatRep = new JsonArray();
        Map<String, Long> mapMe = mapM;
        Map<String, Long> mapEm = mapE;
        Map<String, Double> mapRe = mapR;
        if(mapMe != null)
        {   
            for (String key: mapM.keySet()) {
                JsonObject jsonUnStat = new JsonObject();
                jsonUnStat.addProperty("medium",key);
                jsonUnStat.addProperty("nb",mapM.get(key));
                jsonStatMed.add(jsonUnStat);
            }
        }
        if(mapEm != null)
        {   
            for (String key: mapE.keySet()) {
                JsonObject jsonUnStat = new JsonObject();
                jsonUnStat.addProperty("employe",key);
                jsonUnStat.addProperty("nb",mapE.get(key));
                jsonStatEmp.add(jsonUnStat);
            }
        }
        if(mapRe != null)
        {   
            for (String key: mapR.keySet()) {
                JsonObject jsonUnStat = new JsonObject();
                jsonUnStat.addProperty("name",key);
                jsonUnStat.addProperty("y",mapR.get(key));
                jsonStatRep.add(jsonUnStat);
            }
        }
        JsonObject container = new JsonObject();
        container.add("statMed", jsonStatMed);
        container.add("statEmp", jsonStatEmp);
        container.add("statRep", jsonStatRep);
        return container;
    }
         
    public static String jsonToString(JsonObject jsonObj){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(jsonObj);
        return json;
    }
    
    public static String jsonConcat(JsonObject j1, JsonObject j2){
        JsonObject container = new JsonObject();
        container.add("", j1);
        container.add("", j2);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(container);
        return json;
    }
}
