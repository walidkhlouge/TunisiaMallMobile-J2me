package tunisiamall.handler;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import tunisiamall.entites.Produit;


//classe qui manipule fichier xml pour parser 
public class ProduitHandler extends DefaultHandler {
    // this will hold all the data we read
    private Vector  produitVector;
 
    public ProduitHandler() {
        produitVector= new Vector();
    }
 
    public Produit[] getProduit() {
        Produit[] bTab = new Produit[ produitVector.size()];
        produitVector.copyInto(bTab);
        return bTab;
    }
 
 
 
    private Produit currentProduit;

 
    //ta9ra les balises ouvrantes 
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("produit")) {
		  
            String nomP = attributes.getValue("nom");
            String nomE = attributes.getValue("nomEnseigne");
            String info = attributes.getValue("info");
            String url = attributes.getValue("url");
            String ids = attributes.getValue("idProduit");
            String pts = attributes.getValue("ptsfidelite");
            int ptss= Integer.parseInt(pts);
            int id= Integer.parseInt(ids);
            currentProduit = new Produit(id,nomP,nomE,info,url,ptss);
            if (nomP == null || nomE == null) {
                throw new IllegalArgumentException("!!!!!!!!!!!!!!!!!");
            }
            
        }
    }
 
   
    //ta9ra les balises fermantes 
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("produit")) {
          
            produitVector.addElement(currentProduit);
            
          
            currentProduit = null;
        } 

    }
 
 
    }
