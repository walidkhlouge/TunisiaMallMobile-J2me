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
import tunisiamall.midlet.Midlet;


public class AjEnseigneRespE extends Form implements CommandListener {

    Command cmdBack = new Command("Back", Command.BACK, 0);
    Command cmdAj = new Command("Ajouter", Command.OK, 0);
    TextField tnomE = new TextField("Nom enseigne", null, 20, TextField.ANY);
//    TextField tlogin = new TextField("Login resp", null, 20, TextField.ANY);
    TextField tadresse = new TextField("adresse", null, 20, TextField.ANY);
    TextField turl = new TextField("url", null, 20, TextField.ANY);

    HttpConnection hc;
    DataInputStream dis;

    Display disp;
    String urlIE = "http://localhost:82/mobile/RE/insertE.php?";

    public AjEnseigneRespE(String title, Display d) {
        super(title);
        addCommand(cmdBack);
        addCommand(cmdAj);
        append(tnomE);
        append(turl);
//        append(tlogin);
        append(tadresse);
        
        setCommandListener(this);
        disp = d;
    }

    public void commandAction(Command c, Displayable d) {

        ListEnseignes lstE = new ListEnseignes("Enseignes", List.IMPLICIT, disp);
        if (c == cmdBack) {

            disp.setCurrent(lstE);
        }
        if (c == cmdAj) {

            String nomE = tnomE.getString();
//            String loginE = tlogin.getString();
            String adresse = tadresse.getString();
            String url = turl.getString();
            try {
                hc = (HttpConnection) Connector.
                        open(urlIE + "nomE=" + nomE + "&loginE=" + Midlet.connected.getLogin() + "&adresse=" + adresse +"&url="+url);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                dis = hc.openDataInputStream();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            disp.setCurrent(lstE);
        }
    }

}
