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
import tunisiamall.entites.Catalogue;


public class CatProdHandler extends DefaultHandler{
        // this will hold all the data we read
Catalogue c; 
Vector v = new Vector();
String idC;
public CatProdHandler(String idCatalogue)
{
    idC=idCatalogue;
}
   
 
    public Vector getCataProdClass()
    {
        return v;
    }
 
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
 
 
    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
 
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException, NullPointerException {
        if (qName.equals("catalogue")) {
		    // create new Person object
            // "attributes" holds name and value pairs from inside the tag
            String idProduit = attributes.getValue("idProduit");
            String idCString = attributes.getValue("idCatalogue");
            if(idCString.equals(idC))
                   v.addElement(idProduit);
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
