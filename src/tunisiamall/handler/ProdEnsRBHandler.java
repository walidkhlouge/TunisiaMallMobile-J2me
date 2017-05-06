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
import tunisiamall.entites.Produit;


public class ProdEnsRBHandler extends DefaultHandler{
        // this will hold all the data we read
Vector v = new Vector();
Vector v1 = new Vector();
String nomEnseigne;
Produit p;

String idBoutique;
public ProdEnsRBHandler(String idBoutique, String nomEnseigne)
{
this.nomEnseigne = nomEnseigne;
this.idBoutique=idBoutique;
}
   
 
    public Vector getProdEnsClass()
    {
        return v;
    }
 
    public Vector getProdBoutClass()
    {
        return v1;
    }
 
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
 
 
    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
 
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException, NullPointerException {
        if (qName.equals("produit")) {
		    // create new Person object
            // "attributes" holds name and value pairs from inside the tag
            String idProduit = attributes.getValue("idProduit");
            String nomEnseigne = attributes.getValue("nomEnseigne");
            String idBoutique = attributes.getValue("idBoutique");
            String nom = attributes.getValue("nom");
            String quantite = attributes.getValue("quantite");
            String prixVenteUnitaire = attributes.getValue("prixVenteUnitaire");
            String tauxReduction = attributes.getValue("tauxReduction");
            String reference = attributes.getValue("reference");
            String info = attributes.getValue("info");
            String url = attributes.getValue("url");
            String categorie = attributes.getValue("categorie");
            if(nomEnseigne.equals(this.nomEnseigne)){
                System.out.println(this.idBoutique+ " "+idBoutique);
            if(idBoutique==null || !idBoutique.equals(this.idBoutique)){
                p=new Produit(idProduit,nomEnseigne,idBoutique,nom,quantite,prixVenteUnitaire,tauxReduction,reference,info,url,categorie);
               v.addElement(p);
            }
            else{
                p=new Produit(idProduit,nomEnseigne,idBoutique,nom,quantite,prixVenteUnitaire,tauxReduction,reference,info,url,categorie);
               v1.addElement(p);
            } }
                
                }
            
    
    }
    // endElement is the closing part ("</tagname>"), or the opening part if it ends with "/>"
    // so, a tag in the form "<tagname/>" generates both startElement() and endElement()
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("person")) {
            // add completed Person object to collection
            
            // we are no longer processing a <person.../> tag
        }
    }
 
    // "characters" are the text inbetween tags
    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        }
    
}
