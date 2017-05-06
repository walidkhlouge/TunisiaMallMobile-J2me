/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.GUI;

import tunisiamall.handler.ProduitHandler;
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
import tunisiamall.entites.Produit;
import tunisiamall.midlet.Midlet;


public class ListProduits extends List implements CommandListener, Runnable {

    Produit[] produits;
    StringBuffer sb;
    Image img;
    Command cmdAj = new Command("Ajouter", Command.OK, 0);
    Command cmdBack = new Command("Back", Command.BACK, 0);
    Display disp;
    String urlP = "http://localhost:82/mobile/RE/getXmlPAttributes.php?";

    public ListProduits(String title, int listType, Display d) {

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
            produitFormRespE formP = new produitFormRespE("Produit", disp);

//            form.append(showDetails(this.getSelectedIndex()));
            String ids = produits[this.getSelectedIndex()].getId();
            String pts = Integer.toString(produits[this.getSelectedIndex()].getPts());
            formP.tf1.setString(ids);
            formP.tf5.setString(pts);
            formP.tf2.setString(produits[this.getSelectedIndex()].getNomEnseigne());
            formP.tf3.setString(produits[this.getSelectedIndex()].getNom());
            formP.tf4.setString(produits[this.getSelectedIndex()].getInfo());

            disp.setCurrent(formP);

        }
        if (c == cmdAj) {

            AjproduitRespE formAj = new AjproduitRespE("Ajout", disp);
            disp.setCurrent(formAj);
        }
        if (c == cmdBack) {
            disp.setCurrent(new DrawStringCanv());
        }
    }

    public String showDetails(int i) {
        sb = new StringBuffer();
        String res = "";
        Produit p = produits[i];

        sb.append(p.getNom());
        sb.append("* ");
        sb.append(p.getNomEnseigne());
        sb.append("* ");
        sb.append(p.getInfo());
        sb.append("* ");
        sb.append(p.getUrl());
        sb.append("* ");
        sb.append(p.getId());
        sb.append("\n");
        sb.append(p.getPts());
        sb.append("\n");

        res = sb.toString();
        return res;
    }

    public void run() {

        try {

            ProduitHandler pHandler = new ProduitHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open(urlP + "t=" + Midlet.connected.getLogin());
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, pHandler);
            // display the result
            produits = pHandler.getProduit();

            if (produits.length > 0) {

                deleteAll();
                for (int i = 0; i < produits.length; i++) {
                    String image = produits[i].getUrl();
                    try {
                        img = Image.createImage("/tunisiamall/gui/images/produit/" + image);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    append(produits[i].getNom(), img);
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
