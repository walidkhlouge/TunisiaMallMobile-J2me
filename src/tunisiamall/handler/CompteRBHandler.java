package tunisiamall.handler;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import tunisiamall.entites.Compte;


//classe qui manipule fichier xml pour parser 
public class CompteRBHandler extends DefaultHandler {
    // this will hold all the data we read
 private String login;
    public CompteRBHandler(String login) {
        this.login=login;
    }
 
    public Compte getCompte() {
        return currentCompte;
    }
 
 
 
    private Compte currentCompte;

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("person")) {
            String login = attributes.getValue("login");
            String nom = attributes.getValue("nom");
            String prenom = attributes.getValue("prenom");
            String pwd = attributes.getValue("pwd");
            String mail = attributes.getValue("adresseMail");
            if(this.login.equals(login))
            currentCompte = new Compte(nom,prenom,pwd,mail);
            
        } 
    }
 
 
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("person")) {
          
            
          
        } 
        
    }
 

    }
