package tunisiamall.handler;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import tunisiamall.entites.PhoneNumber;
import java.util.Vector;
import javax.microedition.lcdui.Image;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import tunisiamall.entites.Boutique;
import tunisiamall.entites.Enseigne;

public class CleintBoutiqueHandler extends DefaultHandler {

    // this will hold all the data we read

    private Vector boutiqueVector;

    public CleintBoutiqueHandler() {
        boutiqueVector = new Vector();
    }

        public Boutique[] getBoutique() {
        Boutique[] boutiqueTab = new Boutique[boutiqueVector.size()];
        boutiqueVector.copyInto(boutiqueTab);
        return boutiqueTab;
    }

    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private Boutique boutique;
    private PhoneNumber currentPhoneNumber;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("enseigne")) {
		    // create new Person object
                
            // "attributes" holds name and value pairs from inside the tag
            
            //<enseigne nomEnseigne="celio" loginResponsableEnseigne="ramilog" adresse="adresse" url="celio.jpg"/>
            String intitule = attributes.getValue("intitule");
            String nomEnseigne = attributes.getValue("nomEnseigne");
            String loginResponsableBoutique  = attributes.getValue("loginResponsableBoutique");
            String url =attributes.getValue("url");
            int id=Integer.parseInt(attributes.getValue("idBoutique"));
            //baddalha
            //String intitule, int id, String nomEnseigne, String loginResponsableBoutique, String url
            //
            boutique = new Boutique(intitule,id,nomEnseigne,loginResponsableBoutique ,url);
            
            if (nomEnseigne==null ) {
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
        if (qName.equals("enseigne")) {
            // add completed Person object to collection
            boutiqueVector.addElement(boutique);

            // we are no longer processing a <person.../> tag
            boutique = null;
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
