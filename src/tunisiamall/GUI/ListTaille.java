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
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
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
import tunisiamall.entites.ProduitTaille;
import tunisiamall.handler.CleintBoutiqueHandler;
import tunisiamall.handler.CleintEnseigneHandler;
import tunisiamall.handler.TailleProduitHandler;
import tunisiamall.midlet.Midlet;


public class ListTaille extends List implements CommandListener, Runnable {

    ProduitTaille[] taille;
    StringBuffer sb;
    PhoneNumber[] numbers;
    Display disp;
    String id;
    Command cmdEnseigne = new Command("Enseigne", Command.OK, 0);
    Command cmdBoutique = new Command("Boutique", Command.OK, 0);
    Command cmdProduit = new Command("Produit", Command.OK, 0);
    Command cmdExit = new Command("Exit", Command.EXIT, 0);
    String urlUE = "http://localhost:80/highping/getTailleAttributes.php?";
    String urlUI = "http://localhost:80/highping/insertP.php?";
    HttpConnection hc;
    DataInputStream dis;
    Alert alert = new Alert("");

    public ListTaille(String title, int listType, Display d, String id) {
        super(title, listType);
        disp = d;
        Thread th = new Thread(this);
        this.id = id;
        th.start();
        addCommand(cmdEnseigne);
        addCommand(cmdBoutique);
        addCommand(cmdProduit);
        addCommand(cmdExit);
        setCommandListener(this);

    }

    public void commandAction(Command c2, Displayable d) {
        if (c2 == cmdExit) {
            Form11 Accueil = new Form11();
            disp.setCurrent(Accueil);
        }
        if (c2 == List.SELECT_COMMAND) {
            try {
                String loginp = Midlet.midlet.connected.getLogin();
                int idp = taille[this.getSelectedIndex()].getIdProduit();
                String taillep = taille[this.getSelectedIndex()].getTaille();
                hc = (HttpConnection) Connector.
                        open(urlUI + "loginp=" + loginp + "&idp=" + idp + "&taillep=" + taillep);
                dis = hc.openDataInputStream();
                
                alert.setType(AlertType.INFO);
                alert.setString("Produit Ajouter");
                disp.setCurrent(alert);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void run() {
        try {

            TailleProduitHandler peopleHandler = new TailleProduitHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc;
            hc = (HttpConnection) Connector.open(urlUE + "id=" + id);//people.xml est un exemple

            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, peopleHandler);
            // display the result
            taille = peopleHandler.getProduit();

            if (taille.length > 0) {
                for (int i = 0; i < taille.length; i++) {
                    append(taille[i].getTaille(), null);

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

}
