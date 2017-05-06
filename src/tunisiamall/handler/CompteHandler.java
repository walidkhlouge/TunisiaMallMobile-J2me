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
import tunisiamall.entites.Compte;
import tunisiamall.entites.Enseigne;


public class CompteHandler extends DefaultHandler {

    private Vector compteVector;

    public CompteHandler() {
        compteVector = new Vector();
    }

    public Compte[] getCompte() {
        Compte[] cTab = new Compte[compteVector.size()];
        compteVector.copyInto(cTab);
        return cTab;
    }
    private Compte currentCompte;

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("compte")) {

            String nom = attributes.getValue("nom");
            String loginE = attributes.getValue("login");
            String adresse = attributes.getValue("adresseMail");
            String prenom = attributes.getValue("prenom");

            currentCompte = new Compte(nom, loginE, adresse, prenom);
            if (nom == null || loginE == null) {
                throw new IllegalArgumentException("!!!!!!!!!!!!!!!!!");
            }

        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("compte")) {

            compteVector.addElement(currentCompte);

            currentCompte = null;
        }

    }

}
