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


public class compteFormRespE extends Form implements CommandListener {

    Command cmdModif = new Command("Modifier", Command.OK, 0);
    Command cmdSupp = new Command("Supprimer", Command.OK, 0);
    Command cmdBack = new Command("Back", Command.BACK, 0);
    TextField tf1 = new TextField("nom", null, 20, TextField.ANY);
    TextField tf2 = new TextField("login", null, 20, TextField.ANY);
    TextField tf3 = new TextField("email", null, 100, TextField.ANY);
    TextField tf4 = new TextField("prenom", null, 20, TextField.ANY);
    Display disp;
    HttpConnection hc;
    DataInputStream dis;

    String urlDE = "http://localhost:82/mobile/RE/deleteC.php?";
    String urlUE = "http://localhost:82/mobile/RE/updateC.php?";

    Alert alert = new Alert("");

    public compteFormRespE(String title, Display d) {
        super(title);
        addCommand(cmdBack);
        addCommand(cmdSupp);
        addCommand(cmdModif);
        append(tf1);
        append(tf2);
        append(tf3);
        append(tf4);
        setCommandListener(this);
        disp = d;
    }

    public void commandAction(Command c, Displayable d) {
         ListComptes lstC = new ListComptes("Comptes", List.IMPLICIT, disp);
        if (c == cmdBack) {
            disp.setCurrent(lstC);
        }
        if (c == cmdSupp) {
            alert.setType(AlertType.WARNING);
            alert.setString("Voulez vous vraiment supprimer ce responsable boutique ?");
            alert.addCommand(new Command("Yes", Command.EXIT, 1));
            alert.addCommand(new Command("No", Command.SCREEN, 2));
            alert.setCommandListener(new CommandListener() {
                public void commandAction(Command c, Displayable d) { // for debugging
                    if (c.getCommandType() != Command.EXIT) {
                        System.out.println("Suppression annuler"); // for debugging
                        ListComptes lstC = new ListComptes("Comptes", List.IMPLICIT, disp);
                        disp.setCurrent(lstC);
                    } else {
                        System.out.println("Suppression confirmer");
                        try {
                            String login = tf2.getString();
                            hc = (HttpConnection) Connector.open(urlDE + "login=" + login);
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
                String nom = tf1.getString();
                String prenom = tf4.getString();
                String login = tf2.getString();
                String email = tf3.getString();

                hc = (HttpConnection) Connector.
                        open(urlUE + "login=" + login + "&prenom=" + prenom + "&nom=" + nom + "&email=" + email);
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
