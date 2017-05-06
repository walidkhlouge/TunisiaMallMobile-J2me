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
import tunisiamall.entites.Enseigne;
import tunisiamall.handler.EnseigneHandler;
import tunisiamall.midlet.Midlet;


public class ListEnseignes extends List implements CommandListener, Runnable {

    Enseigne[] enseignes;
    StringBuffer sb;
    Command cmdAj = new Command("Ajouter", Command.OK, 0);
    Command cmdBack = new Command("Back", Command.BACK, 0);
    Display disp;
    Image img;
    
    String urlD = "http://localhost:82/mobile/RE/getXmlEAttributes.php?";

    public ListEnseignes(String title, int listType, Display d) {
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
            enseigneFormRespE form = new enseigneFormRespE("Enseigne", disp);

            form.tf1.setString(enseignes[this.getSelectedIndex()].getNom());
            form.tf3.setString(enseignes[this.getSelectedIndex()].getAdresse());
            form.tf4.setString(enseignes[this.getSelectedIndex()].getUrl());
            disp.setCurrent(form);

        }
        if (c == cmdAj) {

            AjEnseigneRespE formAj = new AjEnseigneRespE("Ajout", disp);
            disp.setCurrent(formAj);
        }
        if (c == cmdBack) {
            disp.setCurrent(new DrawStringCanv());
        }
    }

    public void run() {

        try {
            EnseigneHandler eHandler = new EnseigneHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open(urlD + "t=" + Midlet.connected.getLogin());
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, eHandler);
            // display the result
            enseignes = eHandler.getEnseigne();

            if (enseignes.length > 0) {

                deleteAll();
                for (int i = 0; i < enseignes.length; i++) {
                    String image = enseignes[i].getUrl();
                    try{
                    img=Image.createImage("/tunisiamall/GUI/images/enseigne/"+image);
                    }catch(IOException ex){
                    ex.printStackTrace();
                    }
                    append(enseignes[i].getNom(), img);
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
