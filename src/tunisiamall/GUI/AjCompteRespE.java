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
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;


public class AjCompteRespE extends Form implements CommandListener {

    Command cmdBack = new Command("Back", Command.BACK, 0);
    Command cmdAj = new Command("Ajouter", Command.OK, 0);
    TextField tf1 = new TextField("nom", null, 20, TextField.ANY);
    TextField tf2 = new TextField("login", null, 20, TextField.ANY);
    TextField tf3 = new TextField("email", null, 100, TextField.ANY);
    TextField tf4 = new TextField("prenom", null, 20, TextField.ANY);

    HttpConnection hc;
    DataInputStream dis;

    Display disp;
    String urlIE = "http://localhost/tunisiamall/insertC.php?";

    public AjCompteRespE(String title, Display d) {
        super(title);
        addCommand(cmdBack);
        addCommand(cmdAj);
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
        if (c == cmdAj) {

            String nom = tf1.getString();
            String prenom = tf4.getString();
            String login = tf2.getString();
            String email = tf3.getString();
            try {
                hc = (HttpConnection) Connector.
                        open(urlIE + "nom=" + nom + "&prenom=" + prenom + "&login=" + login+ "&email=" + email);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                dis = hc.openDataInputStream();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            disp.setCurrent(lstC);
        }
    }

}
