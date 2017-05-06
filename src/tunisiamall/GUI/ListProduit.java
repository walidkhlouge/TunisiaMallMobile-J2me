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


public class ListProduit extends List implements CommandListener, Runnable {

    Command cmdBack = new Command("Back", Command.EXIT, 0);
    Produit[] boutique;
    StringBuffer sb;
    PhoneNumber[] numbers;
    Display disp;
    int filter = -1;
    Command cmdEnseigne = new Command("Enseigne", Command.OK, 0);
    Command cmdBoutique = new Command("Boutique", Command.OK, 0);
    Command cmdProduit = new Command("Produit", Command.OK, 0);
    Command cmdPanier = new Command("Panier", Command.OK, 0);
 
 
    public ListProduit(String title, int listType, Display d, int filter) {
        super(title, listType);
        disp = d;
        Thread th = new Thread(this);
        this.filter = filter;
        th.start();
        addCommand(cmdEnseigne);
        addCommand(cmdBoutique);
        addCommand(cmdProduit);
        addCommand(cmdPanier);
        addCommand(cmdBack);
        setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == List.SELECT_COMMAND) {
            ListTaille listP2 = new ListTaille("Les tailles disponibles", List.IMPLICIT, Midlet.disp, boutique[this.getSelectedIndex()].getId());
            disp.setCurrent(listP2);
        }
   if (c == cmdBack) {
            try {
                Midlet.disp.setCurrent(new AccueilClient());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (c == cmdEnseigne) {

            ListEnseigne listP = new ListEnseigne("Choisir Enseigne", List.IMPLICIT, Midlet.disp);
            Midlet.disp.setCurrent(listP);

        }
        if (c == cmdBoutique) {
            ListBoutique listP = new ListBoutique("Choisir Boutique", List.IMPLICIT, Midlet.disp, "");
            Midlet.disp.setCurrent(listP);

        }
        if (c == cmdProduit) {
            ListProduit listP2 = new ListProduit("Ajouter au Panier", List.IMPLICIT, Midlet.disp, -1);
            Midlet.disp.setCurrent(listP2);
        }
        if (c == cmdPanier) {
            ListPanier listP2 = new ListPanier("Panier", List.EXCLUSIVE, Midlet.disp, "bassemlog");
            Midlet.disp.setCurrent(listP2);
        }

    }

    public void run() {
        try {
            HttpConnection hc;
            CleintProduitHandler1 peopleHandler = new CleintProduitHandler1();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)

            if (filter == -1) {
                hc = (HttpConnection) Connector.open("http://localhost:82/mobile/Bassem/getProduitAttributes.php");//people.xml est un exemple
            } else {
                System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh" + filter);
                hc = (HttpConnection) Connector.open("http://localhost:82/mobile/Bassem/getProduitAttributesFilter.php?id=" + filter);
            }

//people.xml est un exemple
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, peopleHandler);
            // display the result
            boutique = peopleHandler.getProduit();
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
