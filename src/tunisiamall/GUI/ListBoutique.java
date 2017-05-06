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
import tunisiamall.entites.Boutique;
import tunisiamall.entites.Enseigne;
import tunisiamall.entites.PhoneNumber;
import tunisiamall.handler.CleintBoutiqueHandler;
import tunisiamall.handler.CleintEnseigneHandler;
import tunisiamall.midlet.Midlet;


public class ListBoutique extends List implements CommandListener, Runnable {

    Boutique[] boutique;
    StringBuffer sb;
    PhoneNumber[] numbers;
    Display disp;
    String filter = "";
    Command cmdEnseigne = new Command("Enseigne", Command.OK, 0);
    Command cmdBoutique = new Command("Boutique", Command.OK, 0);
    Command cmdProduit = new Command("Produit", Command.OK, 0);
    Command cmdPanier = new Command("Panier", Command.OK, 0);
    Command cmdBack = new Command("Back", Command.EXIT, 0);

    public ListBoutique(String title, int listType, Display d) {
        super(title, listType);
        disp = d;
        Thread th = new Thread(this);

        th.start();
        addCommand(cmdEnseigne);
        addCommand(cmdBoutique);
        addCommand(cmdProduit);
        addCommand(cmdPanier);
        addCommand(cmdBack);
        setCommandListener(this);

    }

    public ListBoutique(String title, int listType, Display d, String filter) {
        super(title, listType);
        disp = d;
        this.filter = filter;
        Thread th = new Thread(this);

        th.start();
        addCommand(cmdEnseigne);
        addCommand(cmdBoutique);
        addCommand(cmdPanier);
        addCommand(cmdProduit);
        addCommand(cmdBack);
        setCommandListener(this);

    }

    public void commandAction(Command c2, Displayable d) {

        if (c2 == List.SELECT_COMMAND && d == this) {
            ListProduit listP2 = new ListProduit("Ajouter produit au Panier", List.IMPLICIT, disp, boutique[this.getSelectedIndex()].getId());
            disp.setCurrent(listP2);

        }
        if (c2 == cmdBack) {
            try {
                Midlet.disp.setCurrent(new AccueilClient());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (c2 == cmdEnseigne) {

            ListEnseigne listP = new ListEnseigne("Choisir Enseigne", List.IMPLICIT, Midlet.disp);
            Midlet.disp.setCurrent(listP);

        }
        if (c2 == cmdBoutique) {
            ListBoutique listP = new ListBoutique("Choisir Boutique", List.IMPLICIT, Midlet.disp, "");
            Midlet.disp.setCurrent(listP);
        }
        if (c2 == cmdProduit) {
            ListProduit listP2 = new ListProduit("Ajouter au Panier", List.IMPLICIT, Midlet.disp, -1);
            Midlet.disp.setCurrent(listP2);
        }
        if (c2 == cmdPanier) {
            ListPanier listP2 = new ListPanier("Panier", List.EXCLUSIVE, Midlet.disp, "bassemlog");
            Midlet.disp.setCurrent(listP2);
        }

    }

    public void run() {
        try {

            CleintBoutiqueHandler peopleHandler = new CleintBoutiqueHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc;
            if (filter.equals("")) {
                hc = (HttpConnection) Connector.open("http://localhost:82/mobile/Bassem/getBoutiqueAttributes.php");//people.xml est un exemple
            } else {
                hc = (HttpConnection) Connector.open("http://localhost:82/mobile/Bassem/getBoutiqueAttributesFilter.php?nom=" + filter);
            }
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, peopleHandler);
            // display the result
            boutique = peopleHandler.getBoutique();
            Image img1 = null;

            if (boutique.length > 0) {
                for (int i = 0; i < boutique.length; i++) {
                    img1 = Image.createImage("/tunisiamall/GUI/images/boutique/" + boutique[i].getUrl());
                    append(boutique[i].getIntitule(), img1);

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
