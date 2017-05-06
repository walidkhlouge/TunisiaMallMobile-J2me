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
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Gauge;
import javax.microedition.lcdui.List;
import tunisiamall.midlet.Midlet;


public class FormQuantite extends Form implements CommandListener {

    Command cmdExit = new Command("Exit", Command.EXIT, 0);
    Command cmdModifier = new Command("Modifier", Command.OK, 0);
    Command cmdPanier = new Command("Panier", Command.OK, 0);
    Gauge gaQuantie = new Gauge("Quantite", true, 10, 0);
    String idProduit;
    String taille;
    String urlUT = "http://localhost:82/mobile/Bassem/updateT.php?";
    HttpConnection hc;
    DataInputStream dis;

    public FormQuantite(String idProduit, String taille) {
        super("Quantite");
        this.idProduit = idProduit;
        this.taille = taille;
        addCommand(cmdModifier);
        addCommand(cmdExit);
        addCommand(cmdPanier);
        append(gaQuantie);
        setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdModifier) {
            try {
                int quantite = gaQuantie.getValue();
                hc = (HttpConnection) Connector.open(urlUT + "taille=" + taille + "&idProduit=" + idProduit + "&quantite=" + quantite);
                dis = hc.openDataInputStream();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (c == cmdPanier) {
            ListPanier listP2 = new ListPanier("Panier", List.EXCLUSIVE, Midlet.disp, "bassemlog");
            Midlet.disp.setCurrent(listP2);
        }
    }

}
