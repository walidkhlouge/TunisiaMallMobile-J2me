/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.GUI;

import tunisiamall.entites.PhoneNumber;
import tunisiamall.handler.CleintEnseigneHandler;
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
import tunisiamall.entites.Enseigne;
import tunisiamall.midlet.Midlet;


public class ListEnseigne extends List implements CommandListener, Runnable {

    Command cmdBack = new Command("Back", Command.EXIT, 0);
    Enseigne[] enseigne;
    StringBuffer sb;
    PhoneNumber[] numbers;
    Display disp;
    Image img1;
    HttpConnection hc;
    DataInputStream dis;

    String url = "http://localhost:82/sms-type.png";
    Command cmdEnseigne = new Command("Enseigne", Command.OK, 0);
    Command cmdBoutique = new Command("Boutique", Command.OK, 0);
    Command cmdProduit = new Command("Produit", Command.OK, 0);
    Command cmdPanier = new Command("Panier", Command.OK, 0);

    public ListEnseigne(String title, int listType, Display d) {
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

    public void commandAction(Command c, Displayable d) {
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
            ListPanier listP2 = new ListPanier("Panier", List.EXCLUSIVE, Midlet.disp, Midlet.connected.getLogin());
            Midlet.disp.setCurrent(listP2);
        }
        if (c == List.SELECT_COMMAND) {

            ListBoutique listP = new ListBoutique("ChoisirEnseigne", List.IMPLICIT, disp, this.getString(this.getSelectedIndex()));
            disp.setCurrent(listP);
        }
    }

    public void run() {
        try {
            CleintEnseigneHandler peopleHandler = new CleintEnseigneHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost:82/mobile/Bassem/getEnseigneAttributes.php");//people.xml est un exemple
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, peopleHandler);
            // display the result
            enseigne = peopleHandler.getEnseigne();
            Image img = null;
            if (enseigne.length > 0) {
                for (int i = 0; i < enseigne.length; i++) {
                    img1 = Image.createImage("/tunisiamall/GUI/images/enseigne/" + enseigne[i].getUrl());
                    append(enseigne[i].getNom(), img1);
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
