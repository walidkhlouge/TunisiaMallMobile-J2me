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
import javax.microedition.lcdui.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import tunisiamall.entites.Compte;
import tunisiamall.handler.CompteHandler;
import tunisiamall.midlet.Midlet;


public class ListComptes extends List implements CommandListener, Runnable {

    Compte[] comptes;
    Command cmdAj = new Command("Ajouter", Command.OK, 0);
    Command cmdBack = new Command("Back", Command.BACK, 0);
    Display disp;
    String urlC = "http://localhost:82/mobile/RE/getXmlCAttributes.php?";

    public ListComptes(String title, int listType, Display d) {
        super(title, listType);
        disp = d;
        addCommand(cmdAj);
        addCommand(cmdBack);
        setCommandListener(this);

        Thread th = new Thread(this);
        th.start();
    }

    public void commandAction(Command c, Displayable d) {
        if (c == List.SELECT_COMMAND) {
            compteFormRespE form = new compteFormRespE("Comptes", disp);

            form.tf1.setString(comptes[this.getSelectedIndex()].getNom());
            form.tf2.setString(comptes[this.getSelectedIndex()].getLogin());
            form.tf3.setString(comptes[this.getSelectedIndex()].getEmail());
            form.tf4.setString(comptes[this.getSelectedIndex()].getPrenom());
            disp.setCurrent(form);

        }
        if (c == cmdAj) {

            AjCompteRespE formAj = new AjCompteRespE("Ajout", disp);
            disp.setCurrent(formAj);
        }
        if (c == cmdBack) {
            disp.setCurrent(new DrawStringCanv());
        }
    }

    public void run() {
        try {
            CompteHandler eHandler = new CompteHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open(urlC + "t=" + Midlet.connected.getLogin());
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, eHandler);
            // display the result
            comptes = eHandler.getCompte();

            if (comptes.length > 0) {

                deleteAll();
                for (int i = 0; i < comptes.length; i++) {

                    append(comptes[i].getLogin(), null);
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
