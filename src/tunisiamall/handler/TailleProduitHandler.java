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
import tunisiamall.entites.PhoneNumber;
import tunisiamall.entites.Produit;
import tunisiamall.entites.ProduitTaille;


public class TailleProduitHandler extends DefaultHandler{
    
       private Vector produitTVector;

    public TailleProduitHandler() {
        produitTVector = new Vector();
    }

    public ProduitTaille[] getProduit() {
        ProduitTaille[] ProduitTTab = new ProduitTaille[produitTVector.size()];
        produitTVector.copyInto(ProduitTTab);
        return ProduitTTab;
    }

    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private ProduitTaille produitT;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("produittaille")) {
		    // create new Person object

            // "attributes" holds name and value pairs from inside the tag
            //<enseigne nomEnseigne="celio" loginResponsableEnseigne="ramilog" adresse="adresse" url="celio.jpg"/>
            String taille = attributes.getValue("taille");
            int quantite = Integer.parseInt(attributes.getValue("quantite"));
            int id = Integer.parseInt(attributes.getValue("idProduit"));
            //baddalha
            //String intitule, int id, String nomEnseigne, String loginResponsableBoutique, String url
            //int id, String nom, int quantite, float prixUnitaire, float tauxReduction, String nonEnseigne, String reference, String info
            produitT = new ProduitTaille(id, taille,quantite);

            if (taille == null) {
                throw new IllegalArgumentException("faux affichage boutique");
            }

        } 
    }

    // endElement is the closing part ("</tagname>"), or the opening part if it ends with "/>"
    // so, a tag in the form "<tagname/>" generates both startElement() and endElement()
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("produittaille")) {
            // add completed Person object to collection
            produitTVector.addElement(produitT);

            // we are no longer processing a <person.../> tag
            produitT = null;
        } 

    }

    
}
