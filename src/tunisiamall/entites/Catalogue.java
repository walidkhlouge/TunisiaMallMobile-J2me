/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.entites;

import java.util.Vector;





public class Catalogue {
     String idCatalogue;
   String type;
   String intitule;

    public Catalogue(String idCatalogue, String type, String intitule) {
        this.idCatalogue = idCatalogue;
        this.type = type;
        this.intitule = intitule;
    }

    public String getIdCatalogue() {
        return idCatalogue;
    }

    public String getType() {
        return type;
    }

    public String getIntitule() {
        return intitule;
    }
   
    
}
