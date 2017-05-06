/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.handler;

import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import tunisiamall.entites.PackPublicitaire;


public class PacksHandler extends DefaultHandler{
    
    private Vector packsVector;

    public PacksHandler() {
        packsVector = new Vector();
    }

    public PackPublicitaire[] getPacks() {
        PackPublicitaire[] eTab = new PackPublicitaire[packsVector.size()];
        packsVector.copyInto(eTab);
        return eTab;
    }
    private PackPublicitaire currentPack;

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("pack")) {

            String id = attributes.getValue("id");
            String nom = attributes.getValue("nom");
            String prix = attributes.getValue("prix");
            String desc = attributes.getValue("description");
            int idi= Integer.parseInt(id);
            float prixf = Float.parseFloat(prix);
            currentPack = new PackPublicitaire(idi,nom,prixf,desc);
            if (nom == null || desc == null) {
                throw new IllegalArgumentException("!!!!!!!!!!!!!!!!!");
            }

        }
    }
        public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("pack")) {
          
            packsVector.addElement(currentPack);
            
          
            currentPack = null;
        } 

    }
}
