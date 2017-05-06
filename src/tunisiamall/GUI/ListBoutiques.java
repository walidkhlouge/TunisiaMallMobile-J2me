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
import tunisiamall.handler.BoutiqueHandler;
import tunisiamall.midlet.Midlet;

public class ListBoutiques extends List implements CommandListener, Runnable {

    Boutique[] boutiques;
    StringBuffer sb;
    Command cmdAj = new Command("Ajouter", Command.OK, 0);
    Command cmdBack = new Command("Back", Command.BACK, 0);
    Display disp;
    Image img ;
    String urlB="http://localhost:82/mobile/RE/getXmlBAttributes.php?";

    public ListBoutiques(String title, int listType, Display d) {

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
            boutiqueFormRespE form = new boutiqueFormRespE("Boutique", disp);

//            form.append(showDetails(this.getSelectedIndex()));
            String ids = Integer.toString(boutiques[this.getSelectedIndex()].getId());
            form.tf1.setString(ids);
            form.tf2.setString(boutiques[this.getSelectedIndex()].getNomEnseigne());
            form.tf4.setString(boutiques[this.getSelectedIndex()].getUrl());
            form.tf5.setString(boutiques[this.getSelectedIndex()].getIntitule());
            disp.setCurrent(form);

        }
        if (c == cmdAj) {

            AjboutiqueRespE formAj = new AjboutiqueRespE("Ajout", disp);
            disp.setCurrent(formAj);
        }
        if(c==cmdBack)
             disp.setCurrent(new DrawStringCanv());
    }

    public String showDetails(int i) {
        sb = new StringBuffer();
        String res = "";
        Boutique b = boutiques[i];

        sb.append(b.getLoginResponsableBoutique());
        sb.append("* ");
        sb.append(b.getNomEnseigne());
        sb.append("* ");
        sb.append(b.getLoginResponsableBoutique());
        sb.append("* ");
        sb.append(b.getUrl());
        sb.append("* ");
        sb.append(b.getId());
        sb.append("\n");

        res = sb.toString();
        return res;
    }

    public void run() {

        try {

            BoutiqueHandler bHandler = new BoutiqueHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open(urlB + "t=" + Midlet.connected.getLogin());
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, bHandler);
            // display the result
            boutiques = bHandler.getBoutique();

            if (boutiques.length > 0) {

                deleteAll();
                for (int i = 0; i < boutiques.length; i++) {
                      String image = boutiques[i].getUrl();
                try {
                img = Image.createImage("/tunisiamall/gui/images/boutique/"+image);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
              
                    append(boutiques[i].getIntitule(), img);
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
