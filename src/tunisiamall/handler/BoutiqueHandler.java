package tunisiamall.handler;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import tunisiamall.entites.Boutique;


//classe qui manipule fichier xml pour parser 
public class BoutiqueHandler extends DefaultHandler {
    // this will hold all the data we read
    private Vector boutiqueVector;
 
    public BoutiqueHandler() {
        boutiqueVector= new Vector();
    }
 
    public Boutique[] getBoutique() {
        Boutique[] bTab = new Boutique[boutiqueVector.size()];
        boutiqueVector.copyInto(bTab);
        return bTab;
    }
 
 
 
    private Boutique currentBoutique;
//    private PhoneNumber currentPhoneNumber;
 
    //ta9ra les balises ouvrantes 
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("boutique")) {
		  
            String intitule = attributes.getValue("intitule");
            String nomE = attributes.getValue("nomEnseigne");
            String loginB = attributes.getValue("loginResponsableBoutique");
            String url = attributes.getValue("url");
            String ids = attributes.getValue("idBoutique");
            int id= Integer.parseInt(ids);
            currentBoutique = new Boutique(intitule,nomE,loginB,url,id);
            if (intitule == null || nomE == null) {
                throw new IllegalArgumentException("!!!!!!!!!!!!!!!!!");
            }
            
        }
    }
 
   
    //ta9ra les balises fermantes 
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("boutique")) {
          
            boutiqueVector.addElement(currentBoutique);
            
          
            currentBoutique = null;
        } 

    }
 
 
    }
