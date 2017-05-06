package tunisiamall.handler;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import tunisiamall.entites.PhoneNumber;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import tunisiamall.entites.Produit;

public class CleintProduitHandler1 extends DefaultHandler {

    // this will hold all the data we read
    private Vector produitVector;
    private Vector tailleVector;

    public CleintProduitHandler1() {
        produitVector = new Vector();
        tailleVector = new Vector();
    }

    public Produit[] getProduit() {
        Produit[] ProduitTab = new Produit[produitVector.size()];
        produitVector.copyInto(ProduitTab);
        return ProduitTab;
    }
 public String[] getTaille() {
        String[] tailleTab = new String[produitVector.size()];
        tailleVector.copyInto(tailleTab);
        return tailleTab;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private Produit produit;
    private String taille;
    private PhoneNumber currentPhoneNumber;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("produit")) {
		    // create new Person object

            // "attributes" holds name and value pairs from inside the tag
            //<enseigne nomEnseigne="celio" loginResponsableEnseigne="ramilog" adresse="adresse" url="celio.jpg"/>
            String intitule = attributes.getValue("intitule");
            String nomEnseigne = attributes.getValue("nomEnseigne");
            String loginResponsableBoutique = attributes.getValue("loginResponsableBoutique");
            String url = attributes.getValue("url");
            String idBoutique = attributes.getValue("idBoutique");
            System.out.println("*****************************************" + url);
            String quantite=attributes.getValue("quantite");
            String prixUnitaire = attributes.getValue("prixVenteUnitaire");
            String tauxReduction = attributes.getValue("tauxReduction");
            String info = attributes.getValue("info");
            String nom = attributes.getValue("nom");
            String reference = attributes.getValue("reference");
            String id = attributes.getValue("idBoutique");
            String taille = attributes.getValue("taille");
            //baddalha
            //String intitule, int id, String nomEnseigne, String loginResponsableBoutique, String url
            //int id, String nom, int quantite, float prixUnitaire, float tauxReduction, String nonEnseigne, String reference, String info
            produit = new Produit(id, nom, quantite, prixUnitaire, idBoutique, tauxReduction, nomEnseigne, reference, info, url);
            this.taille=taille;
            if (nomEnseigne == null) {
                throw new IllegalArgumentException("faux affichage boutique");
            }

        } else if (qName.equals("phone")) {

            currentPhoneNumber = new PhoneNumber();
            String type = attributes.getValue("type");

            currentPhoneNumber.setType(type);
        }
    }

    // endElement is the closing part ("</tagname>"), or the opening part if it ends with "/>"
    // so, a tag in the form "<tagname/>" generates both startElement() and endElement()
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("produit")) {
            // add completed Person object to collection
            produitVector.addElement(produit);
            tailleVector.addElement(taille);
            // we are no longer processing a <person.../> tag
            produit = null;
        } else if (qName.equals("phone")) {
            // add completed PhoneNumber object to current Person
            //enseigne.addPhoneNumber(currentPhoneNumber);
            // we are no longer processing a <phone.../> tag
            currentPhoneNumber = null;
        }

    }

    // "characters" are the text inbetween tags
    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentPhoneNumber != null) {
            // don't forget to trim excess spaces from the ends of the string
            String number = new String(ch, start, length).trim();
            currentPhoneNumber.setNumber(number);
        }
    }
}
