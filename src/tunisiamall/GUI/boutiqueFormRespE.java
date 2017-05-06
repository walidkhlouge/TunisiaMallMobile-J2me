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
;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;




public class boutiqueFormRespE extends Form implements CommandListener {

    Command cmdBack = new Command("Back", Command.BACK, 0);
    Command cmdModif = new Command("Modifier", Command.OK, 0);
    Command cmdSupp = new Command("Supprimer", Command.OK, 0);
    TextField tf1 = new TextField("id", null, 20, TextField.ANY);
    TextField tf2 = new TextField("nom Enseigne", null, 20, TextField.ANY);
    TextField tf4 = new TextField("url", null, 20, TextField.ANY);
    TextField tf5 = new TextField("intitule", null, 20, TextField.ANY);
    HttpConnection hc;
    DataInputStream dis;

    Display disp;
    String urlD = "http://localhost:82/mobile/RE/delete.php?";
    String urlU = "http://localhost:82/mobile/RE/update.php?";

    public boutiqueFormRespE(String title, Display d) {

        super(title);
        addCommand(cmdBack);
        addCommand(cmdModif);
        addCommand(cmdSupp);
        append(tf1);
        append(tf5);
        append(tf2);
        append(tf4);

        setCommandListener(this);
        disp = d;
    }

    public void commandAction(Command c, Displayable d) {

        if (c == cmdBack) {
            ListBoutiques lstB = new ListBoutiques("Boutiques", List.IMPLICIT, disp);
            disp.setCurrent(lstB);
        }
        if (c == cmdSupp) {
            String id = tf1.getString();
            try {
                hc = (HttpConnection) Connector.
                        open(urlD + "id=" + id);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                dis = hc.openDataInputStream();
                            int ascii1;
       StringBuffer sb =new  StringBuffer();
       
       while( (ascii1=dis.read()) != -1 ){
           
           sb.append((char)ascii1);
           
       }
       
       
       
       if(sb.toString().equals("successfully deleted")){
           Alert a= new Alert("Information", sb.toString(), null, AlertType.CONFIRMATION);
           a.setTimeout(3000);
         disp.setCurrent(a);
       }else{
           Alert a= new Alert("Information", sb.toString(), null, AlertType.ERROR);
           a.setTimeout(3000);
          disp.setCurrent(a);
       }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
        if (c == cmdModif) {
            String id = tf1.getString();
            String nomE = tf2.getString();
            String intit = tf5.getString();
            try {
                hc = (HttpConnection) Connector.
                        open(urlU + "id=" + id + "&intit=" + intit + "&nomE=" + nomE);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                dis = hc.openDataInputStream();
                  int ascii1;
       StringBuffer sb =new  StringBuffer();
       
       while( (ascii1=dis.read()) != -1 ){
           
           sb.append((char)ascii1);
           
       }
       
       
       
       if(sb.toString().equals("successfully updated")){
           Alert a= new Alert("Information", sb.toString(), null, AlertType.CONFIRMATION);
           a.setTimeout(3000);
         disp.setCurrent(a);
       }else{
           Alert a= new Alert("Information", sb.toString(), null, AlertType.ERROR);
           a.setTimeout(3000);
          disp.setCurrent(a);
       }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }

}
