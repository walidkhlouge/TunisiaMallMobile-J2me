/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.GUI;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import tunisiamall.entites.PhoneNumber;
import tunisiamall.entites.Produit;
import tunisiamall.handler.CleintProduitHandler1;
import tunisiamall.midlet.Midlet;


public class ListPanier extends List implements CommandListener, Runnable {

    Command cmdBack = new Command("Back", Command.EXIT, 0);
    Produit[] boutique;
    StringBuffer sb;
    PhoneNumber[] numbers;
    Display disp;
    String id = "";
    Command cmdEnseigne = new Command("Enseigne", Command.OK, 0);
    Command cmdBoutique = new Command("Boutique", Command.OK, 0);
    Command cmdProduit = new Command("Produit", Command.OK, 0);
    Command cmdSupp = new Command("Supprimer", Command.OK, 0);
    Command cmdModifier = new Command("Modifer", Command.OK, 0);
    String urlUE = "http://localhost:82/mobile/Walid/getPanierAttributes.php?";
    String urlUD = "http://localhost:82/mobile/Walid/deleteP.php?";
    HttpConnection hc;
    DataInputStream dis;
    String[] taille;

    public ListPanier(String title, int listType, Display d, String id) {
        super(title, listType);
        disp = d;
        Thread th = new Thread(this);
        this.id = id;
        th.start();
        addCommand(cmdEnseigne);
        addCommand(cmdBoutique);
        addCommand(cmdProduit);
        addCommand(cmdSupp);
        addCommand(cmdModifier);
        addCommand(cmdBack);
        setCommandListener(this);
    }
    
    public void commandAction(Command c, Displayable d) {
        if (c == cmdSupp) {
            try {

                String idp = boutique[getSelectedIndex()].getId();
System.out.println(idp);
System.out.println(taille[getSelectedIndex()]);
                hc = (HttpConnection) Connector.open(urlUD + "idp=" + idp + "&taille=" + taille[getSelectedIndex()]);
                dis = hc.openDataInputStream();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
           if (c == cmdBack) {
            try {
                Midlet.disp.setCurrent(new AccueilClient());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (c == cmdModifier) {
            FormQuantite f = new FormQuantite(String.valueOf(boutique[getSelectedIndex()].getId()), taille[getSelectedIndex()]);
            Midlet.midlet.disp.setCurrent(f);

        }
        if (c == cmdEnseigne) {

            ListEnseigne listP = new ListEnseigne("Choisir Enseigne", List.IMPLICIT, Midlet.disp);
            Midlet.midlet.disp.setCurrent(listP);

        }
        if (c == cmdBoutique) {
            ListBoutique listP = new ListBoutique("Choisir Boutique", List.IMPLICIT, Midlet.disp, "");
            Midlet.midlet.disp.setCurrent(listP);

        }
        if (c == cmdProduit) {
            ListProduit listP2 = new ListProduit("Ajouter au Panier", List.IMPLICIT, Midlet.disp, -1);
            Midlet.midlet.disp.setCurrent(listP2);
        }

    }

    public void run() {
        try {
            CleintProduitHandler1 peopleHandler = new CleintProduitHandler1();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc;
            hc = (HttpConnection) Connector.open(urlUE + "id=" + id);//people.xml est un exemple

            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, peopleHandler);
            boutique = peopleHandler.getProduit();
            taille = peopleHandler.getTaille();
            Image img1 = null;

            if (boutique.length > 0) {
                for (int i = 0; i < boutique.length; i++) {

                    img1 = Image.createImage("/tunisiamall/GUI/images/produit/" + boutique[i].getUrl());
                    append(boutique[i].getNom(), img1);

                }
            }

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private String showNumbers(int i) {
        System.out.println("-----------------------------" + this.getString(i));

        /*
        
        
        
        
        
         numbers = enseigne[i].getAdresse();
         if (numbers.length > 0) {
         for (int j = 0; j < numbers.length; j++) {
         sb.append("* ");
         sb.append(numbers[j].getNumber());
         sb.append("\n");
         }
         }
         res = sb.toString();*/
        return "";

    }

}
