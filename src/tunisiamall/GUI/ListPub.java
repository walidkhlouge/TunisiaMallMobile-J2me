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
import tunisiamall.entites.PackPublicitaire;
import tunisiamall.handler.PacksHandler;


public class ListPub extends List implements CommandListener, Runnable {

    PackPublicitaire[] packs;
    StringBuffer sb;
    Command cmdBack = new Command("Back", Command.BACK, 0);
    Display disp;

    public ListPub(String title, int listType, Display d) {
        super(title, listType);
        disp = d;
        addCommand(cmdBack);
        setCommandListener(this);

        Thread th = new Thread(this);
        th.start();
    }

    public void commandAction(Command c, Displayable d) {
        if (c == List.SELECT_COMMAND) {
            PackFormRespE form = new PackFormRespE("Pub", disp);

            form.tf1.setString(packs[this.getSelectedIndex()].getNom());
            form.tf2.setString(packs[this.getSelectedIndex()].getDesc());
            form.tf3.setString(Float.toString(packs[this.getSelectedIndex()].getPrix()));
            form.tf4.setString(Integer.toString(packs[this.getSelectedIndex()].getId()));
            disp.setCurrent(form);

        }
        if (c == cmdBack) {
            disp.setCurrent(new DrawStringCanv());
        }
    }

    public void run() {
        try {
            PacksHandler eHandler = new PacksHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost:82/mobile/RE/getXmlPUAttributes.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, eHandler);
            // display the result
            packs = eHandler.getPacks();

            if (packs.length > 0) {

                deleteAll();
                for (int i = 0; i < packs.length; i++) {

                    append(packs[i].getNom(), null);
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
