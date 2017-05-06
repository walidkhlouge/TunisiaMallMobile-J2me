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
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import tunisiamall.entites.CarteFidelite;
import tunisiamall.handler.CarteFidHandler;
import tunisiamall.midlet.Midlet;


public class CarteF extends Form implements CommandListener {

    TextField tftaux = new TextField("taux", null, 20, TextField.DECIMAL);
    TextField tfpts = new TextField("points ", null, 20, TextField.DECIMAL);
    Command cmdModif = new Command("valider", Command.OK, 0);
    Command cmdBack = new Command("Back", Command.BACK, 0);
 
    HttpConnection hc;
    DataInputStream dis;
    CarteFidelite[] cartes;
    Display disp;
    String urlCarte = "http://localhost:82/mobile/RE/updateCarte.php?";
    Alert alert = new Alert("");
    
    public CarteF(String title, Display d) {
        super(title);
       

        append(tftaux);
        append(tfpts);

        addCommand(cmdBack);
        addCommand(cmdModif);
        setCommandListener(this);
        disp = d;

    }

    public void commandAction(Command c, Displayable d) {

        if (c == cmdBack) {
            disp.setCurrent(new DrawStringCanv());

        }
        if (c == cmdModif) {
            
            String pts = tfpts.getString();
            String taux = tftaux.getString();
          
            try {
                hc = (HttpConnection) Connector.
                        open(urlCarte + "taux=" + taux + "&pts=" + pts + "&t=" + Midlet.connected.getLogin());
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            try {
                dis = hc.openDataInputStream();
                int ascii1;
                StringBuffer sb = new StringBuffer();

                while ((ascii1 = dis.read()) != -1) {

                    sb.append((char) ascii1);

                }

                if (sb.toString().equals("successfully updated")) {
                    Alert a = new Alert("Information", sb.toString(), null, AlertType.CONFIRMATION);
                    a.setTimeout(3000);
                    disp.setCurrent(a);
                   
                } else {
                    Alert a = new Alert("Information", sb.toString(), null, AlertType.ERROR);
                    a.setTimeout(3000);
                    disp.setCurrent(a);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }

    public void run() {

        try {

            CarteFidHandler fHandler = new CarteFidHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/tunisiamall/getXmlCarteAttributes.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, fHandler);
            // display the result
            cartes = fHandler.getCarteFidelites();

            if (cartes.length > 0) {

                deleteAll();
                for (int i = 0; i < cartes.length; i++) {

                    append(cartes[i].getLogin());
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
