/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tunisiamall.GUI;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;
import tunisiamall.midlet.Midlet;


public class produitFormRespE extends Form implements CommandListener {

      Command cmdBack = new Command("Back", Command.BACK, 0);
      Command cmdModif = new Command("Modifier", Command.OK, 0);
      Command cmdSupp = new Command("Supprimer", Command.OK, 0);
      TextField tf1 = new TextField("id", null, 20, TextField.ANY);
      TextField tf2 = new TextField("nom Enseigne", null, 20, TextField.ANY);
      TextField tf3 = new TextField("nom Produit", null, 20, TextField.ANY);
      TextField tf4 = new TextField("informations", null, 150, TextField.ANY);
      TextField tf5 = new TextField("Points", null, 150, TextField.ANY);
     
    
      	HttpConnection hc;
        DataInputStream dis;
      

       Display disp;
       String urlD="http://localhost:82/mobile/RE/deleteP.php?";  
       String urlU="http://localhost:82/mobile/RE/updateP.php?"; 
    public produitFormRespE(String title, Display d) {
      
        
        super(title);
        addCommand(cmdBack);
        addCommand(cmdModif);
        addCommand(cmdSupp);
        append(tf1);
        append(tf2);
        append(tf3);
        append(tf4);
        append(tf5);
        setCommandListener(this);
        disp = d;
    }

    public void commandAction(Command c, Displayable d) {
       
        
         ListProduits lstP = new ListProduits("Produits", List.IMPLICIT, disp);
         if (c == cmdBack) 
         {
           
            disp.setCurrent(lstP);
        }
         
         if(c==cmdSupp){
             String id=tf1.getString();
             try {
                 hc=(HttpConnection)Connector.
                         open(urlD+"id="+id);
             } catch (IOException ex) {
                 ex.printStackTrace();
             }
             try {
                 dis=hc.openDataInputStream();
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
           
                 
           
//          disp.setCurrent(lstP);   
         }
         if(c==cmdModif){
             String id=tf1.getString();
             String nomE =tf2.getString();
             String nomP=tf3.getString();
             String info=tf4.getString();
             String pts=tf5.getString();
             try {
                 hc=(HttpConnection)Connector.
                         open(urlU+"id="+id+"&nomE="+nomE+"&nomP="+nomP+"&info="+info+"&pts="+pts);
             } catch (IOException ex) {
                 ex.printStackTrace();
             }
             try {
                 dis=hc.openDataInputStream();
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
           
//         disp.setCurrent(lstP);
         }
         
    }
    

}
