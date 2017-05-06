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
import tunisiamall.entites.Boutique;
import tunisiamall.entites.Enseigne;


public class EnseigneHandler extends DefaultHandler {

    private Vector enseigneVector;

    public EnseigneHandler() {
        enseigneVector = new Vector();
    }

    public Enseigne[] getEnseigne() {
        Enseigne[] eTab = new Enseigne[enseigneVector.size()];
        enseigneVector.copyInto(eTab);
        return eTab;
    }
    private Enseigne currentEnseigne;

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("enseigne")) {

            String nomE = attributes.getValue("nomEnseigne");
            String loginE = attributes.getValue("loginResponsableEnseigne");
            String adresse = attributes.getValue("adresse");
            String url = attributes.getValue("url");
            String info = attributes.getValue("info");

            currentEnseigne = new Enseigne(nomE, adresse, loginE, url,info);
            if (nomE == null || loginE == null) {
                throw new IllegalArgumentException("!!!!!!!!!!!!!!!!!");
            }

        }
    }
        public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("enseigne")) {
          
            enseigneVector.addElement(currentEnseigne);
            
          
            currentEnseigne = null;
        } 

    }
}
