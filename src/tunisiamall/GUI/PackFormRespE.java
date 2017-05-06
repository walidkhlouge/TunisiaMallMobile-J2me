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


public class PackFormRespE extends Form implements CommandListener {

    Command cmdAch = new Command("Acheter", Command.OK, 0);
    Command cmdBack = new Command("Back", Command.BACK, 0);
    TextField tf1 = new TextField("Nom", null, 20, TextField.ANY);
    TextField tf3 = new TextField("Prix", null, 20, TextField.ANY);
    TextField tf2 = new TextField("Desc", null, 100, TextField.ANY);
    TextField tf4 = new TextField("ID", null, 20, TextField.ANY);
    Alert alerta = new Alert("Pack Acheter");

    HttpConnection hc;
    DataInputStream dis;
    Display disp;
    String urlUE = "http://localhost:82/mobile/RE/updateEPP.php?";

    public PackFormRespE(String title, Display d) {
        super(title);
        addCommand(cmdBack);
        addCommand(cmdAch);
        append(tf1);
        append(tf2);
        append(tf3);
        append(tf4);
        setCommandListener(this);
        disp = d;
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdBack) {
            ListPub lstp = new ListPub("Pub", List.IMPLICIT, disp);
            disp.setCurrent(lstp);
        }
        if (c == cmdAch) {

            try {
                String idp = tf4.getString();

                hc = (HttpConnection) Connector.
                        open(urlUE + "idp=" + idp + "&login=" + Midlet.connected.getLogin());

                dis = hc.openDataInputStream();
                alerta.setType(AlertType.ALARM);
                disp.setCurrent(alerta);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
