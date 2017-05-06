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


public class AjboutiqueRespE extends Form implements CommandListener {

    Command cmdBack = new Command("Back", Command.BACK, 0);
    Command cmdAj = new Command("Ajouter", Command.OK, 0);
    TextField tintitule = new TextField("intitule", null, 20, TextField.ANY);
    TextField tnomE = new TextField("nom Enseigne", null, 20, TextField.ANY);
    TextField tlogin = new TextField("login respB", null, 20, TextField.ANY);

    HttpConnection hc;
    DataInputStream dis;

    Display disp;
    String urlI = "http://localhost:82/mobile/RE/insert.php?";

    public AjboutiqueRespE(String title, Display d) {
        super(title);
        addCommand(cmdBack);
        addCommand(cmdAj);
        append(tintitule);
        append(tnomE);
        append(tlogin);
        setCommandListener(this);
        disp = d;
    }

    public void commandAction(Command c, Displayable d) {

        ListBoutiques lstB = new ListBoutiques("Boutiques", List.IMPLICIT, disp);
        if (c == cmdBack) {

            disp.setCurrent(lstB);
        }
        if (c == cmdAj) {

            String nomE = tnomE.getString();
            String loginB = tlogin.getString();
            String intit = tintitule.getString();
            try {
                hc = (HttpConnection) Connector.
                        open(urlI + "nomE=" + nomE + "&loginB=" + loginB + "&intit=" + intit);
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
       
       
       
       if(sb.toString().equals("successfully added")){
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

//            disp.setCurrent(lstB);
        }
    }

}
