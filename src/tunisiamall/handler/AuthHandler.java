/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.handler;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import tunisiamall.entites.Compte;


public class AuthHandler extends DefaultHandler{
    // this will hold all the data we read
String loginC;
String pwdC;
boolean result;
Compte c;
    public AuthHandler(String login, String pwd) {
        this.loginC=login;
        this.pwdC=pwd;
        result=false;
    }
   
 
    public boolean getCompte() {
        return result;
    }
    public Compte getCompteClass()
    {
        return c;
    }
 
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
 
 
    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
 
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException, NullPointerException {
        if (qName.equals("person")) {
		    // create new Person object
            // "attributes" holds name and value pairs from inside the tag
            String login = attributes.getValue("login");
            String pwd = attributes.getValue("pwd");
            String nom = attributes.getValue("nom");
            String prenom = attributes.getValue("prenom");
            String email = attributes.getValue("adresseMail");
            String type = attributes.getValue("type");
            String sexe = attributes.getValue("sexe");
            String dateNaissance = attributes.getValue("DateNaissance");
            if(loginC.equals(login))
            {
                   try {
                       DataInputStream diss = null;
                       
                       String url="http://localhost:82/mobile/Walid/decrpt.php?";
                       pwd="$2y".concat(pwd.substring(3));
                       HttpConnection hcc = (HttpConnection)Connector.open(url+"hash="+pwd+"&candidate="+pwdC);
                       
                       diss = hcc.openDataInputStream();
                       int ascii;
                       StringBuffer sb = new  StringBuffer();
                       while( (ascii=diss.read()) != -1 ){
                           
                           sb.append((char)ascii);
                           
                       }
                       System.out.println(sb.toString());
                       if(sb.toString().equals("Password is valid!")){
                       result=true;
                       c = new Compte(nom, prenom, email, pwd, login, type, sexe, dateNaissance, email);
                       }
                       else
                           result=false;
                   } catch (IOException ex) {
                       ex.printStackTrace();
                   }
                
            }
            if (login == null ||pwd == null) {
                throw new IllegalArgumentException("Person requires both givenname and familyname");
            }
            
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

