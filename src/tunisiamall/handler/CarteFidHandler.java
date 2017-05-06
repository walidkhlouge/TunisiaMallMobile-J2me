
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
import tunisiamall.entites.CarteFidelite;


public class CarteFidHandler extends DefaultHandler {

    private Vector carteVector;

    public CarteFidHandler() {
        carteVector = new Vector();
    }

    public CarteFidelite[] getCarteFidelites() {
        CarteFidelite[] cTab = new CarteFidelite[carteVector.size()];
        carteVector.copyInto(cTab);
        return cTab;
    }
    private CarteFidelite currentCarte;

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("carte")) {

            //String id = attributes.getValue("idCarteFidelite");
            String nomE = attributes.getValue("nomEnseigne");
            String login = attributes.getValue("loginClient");
            String ptsTot = attributes.getValue("pointFidele");
            String reduction = attributes.getValue("reductionFixe");
            String ptsFixe = attributes.getValue("pointFideleFixe");

            currentCarte = new CarteFidelite(nomE, login, (Integer.parseInt(ptsTot)), (Float.parseFloat(reduction)), (Integer.parseInt(ptsFixe)));
            if (nomE == null || login == null) {
                throw new IllegalArgumentException("!!!!!!!!!!!!!!!!!");
            }

        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("carte")) {

            carteVector.addElement(currentCarte);

            currentCarte = null;
        }

    }

}
