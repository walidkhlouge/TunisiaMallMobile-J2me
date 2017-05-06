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
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;


public class AjproduitRespE extends Form implements CommandListener {

    Command cmdBack = new Command("Back", Command.BACK, 0);
    Command cmdAj = new Command("Ajouter", Command.OK, 0);
    TextField tnomP = new TextField("nom Produit", null, 20, TextField.ANY);
    TextField tnomE = new TextField("nom Enseigne", null, 20, TextField.ANY);
    TextField tinfo = new TextField("informations", null, 150, TextField.ANY);
    TextField tpts = new TextField("pts", null, 150, TextField.ANY);
    HttpConnection hc;
    DataInputStream dis;

    Display disp;
    String urlI = "http://localhost/tunisiamall/insertP.php?";

    public AjproduitRespE(String title, Display d) {
        super(title);
        addCommand(cmdBack);
        addCommand(cmdAj);
        append(tnomE);
        append(tnomP);
        append(tinfo);
        append(tpts);
        setCommandListener(this);
        disp = d;
    }

    public void commandAction(Command c, Displayable d) {

        ListProduits lstP = new ListProduits("Produits", List.IMPLICIT, disp);
        if (c == cmdBack) {

            disp.setCurrent(lstP);
        }
        if (c == cmdAj) {

            String nomE = tnomE.getString();
            String nomP = tnomP.getString();
            String info = tinfo.getString();
            String ptsp = tpts.getString();
            try {
                hc = (HttpConnection) Connector.
                        open(urlI + "nomE=" + nomE + "&nomP=" + nomP + "&info=" + info+ "&pts=" + ptsp);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                dis = hc.openDataInputStream();
                int ascii1;
                StringBuffer sb = new StringBuffer();

                while ((ascii1 = dis.read()) != -1) {

                    sb.append((char) ascii1);

                }

                if (sb.toString().equals("successfully added")) {
                    Alert a = new Alert("Information", sb.toString(), null, AlertType.CONFIRMATION);
                    a.setTimeout(3000);
                    disp.setCurrent(a);
                } else {
                    Alert a = new Alert("Information", sb.toString(), null, AlertType.ERROR);
                    a.setTimeout(3000);
                    disp.setCurrent(a);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

//              disp.setCurrent(lstP);
        }
    }

}
