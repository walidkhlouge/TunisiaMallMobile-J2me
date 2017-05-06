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
import tunisiamall.midlet.Midlet;


public class enseigneFormRespE extends Form implements CommandListener {

    Command cmdModif = new Command("Modifier", Command.OK, 0);
    Command cmdSupp = new Command("Supprimer", Command.OK, 0);
    Command cmdBack = new Command("Back", Command.BACK, 0);
    TextField tf1 = new TextField("nom Enseigne", null, 20, TextField.ANY);
    TextField tf3 = new TextField("adresse", null, 20, TextField.ANY);
    TextField tf4 = new TextField("url", null, 20, TextField.ANY);
    Display disp;
    HttpConnection hc;
    DataInputStream dis;

    String urlDE = "http://localhost:82/mobile/RE/deleteE.php?";
    String urlUE = "http://localhost:82/mobile/RE/updateE.php?";

    Alert alert = new Alert("");

    public enseigneFormRespE(String title, Display d) {
        super(title);
        addCommand(cmdBack);
        addCommand(cmdSupp);
        addCommand(cmdModif);
        append(tf1);
        append(tf3);
        append(tf4);
        setCommandListener(this);
        disp = d;
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdBack) {
            ListEnseignes lstE = new ListEnseignes("Enseignes", List.IMPLICIT, disp);
            disp.setCurrent(lstE);
        }
        if (c == cmdSupp) {
            alert.setType(AlertType.WARNING);
            alert.setString("Voulez vous vraiment supprimer cette enseigne ?");
            alert.addCommand(new Command("Yes", Command.EXIT, 1));
            alert.addCommand(new Command("No", Command.SCREEN, 2));
            alert.setCommandListener(new CommandListener() {
                public void commandAction(Command c, Displayable d) { // for debugging
                    if (c.getCommandType() != Command.EXIT) {
                        System.out.println("Suppression annuler"); // for debugging
                        ListEnseignes lstC = new ListEnseignes("Enseigne", List.IMPLICIT, disp);
                        disp.setCurrent(lstC);
                    } else {
                        System.out.println("Suppression confirmer");
                        try {
                            String nom = tf1.getString();
                            hc = (HttpConnection) Connector.open(urlDE + "nom=" + nom);
                            dis = hc.openDataInputStream();
                            alert.removeCommand(c);
                            alert.setType(AlertType.INFO);
                            alert.setString("Suppression effectué avec success");
                            disp.setCurrent(alert);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }// for debugging
                    }
                }
            });
            disp.setCurrent(alert);

        }
        if (c == cmdModif) {
            try {
                String nomE = tf1.getString();
                String adresse = tf3.getString();
                String url = tf4.getString();
                hc = (HttpConnection) Connector.
                        open(urlUE + "nomE=" + nomE + "&loginE=" + Midlet.connected.getLogin() + "&adresse=" + adresse + "&url=" + url);

                dis = hc.openDataInputStream();
                alert.setType(AlertType.INFO);
                alert.setString("Modification effectué avec succees");
                disp.setCurrent(alert);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }

}
