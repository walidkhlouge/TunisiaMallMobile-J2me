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
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Gauge;
import javax.microedition.lcdui.TextField;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import tunisiamall.midlet.Midlet;



public class ResBoutiqueModifierProForm extends Form implements CommandListener {
    
  Command cmdBack=new Command("Back", Command.EXIT, 0);    
  Command cmdNext=new Command("Modifer", Command.OK, 0);     
    Command cmdSupp=new Command("Supprimer", Command.OK, 0);     
  Gauge g= new Gauge("Stock", true, 1000, 0);
  Gauge g1= new Gauge("Réduction", true, 1000, 0);
  TextField tx2 = new TextField("Prix en détails", null,40, TextField.NUMERIC);
        DataInputStream dis = null;
        String idProduit;
    int choix;
   String idBoutique;

    public ResBoutiqueModifierProForm(String idProduit,int choix,String idBoutique) {
            super("Modifier produit"); 
            this.choix=choix;
            this.idBoutique=idBoutique;
            this.idProduit=idProduit;
            System.out.println(idProduit);
            this.append(g);
            this.append(g1);
            this.append(tx2);
            addCommand(cmdNext);
            addCommand(cmdBack);
            if(choix==0)
                addCommand(cmdSupp);
            setCommandListener(this);
            
    }

   
    public void commandAction(Command c, Displayable d) {
   if(c==cmdNext)
    {String url = null;
     HttpConnection hc = null;
     try {
      
           url="http://localhost:82/mobile/RB/updateProduitBoutique.php?";
             hc = (HttpConnection)Connector.open(url+"idProduit="+idProduit+"&prixVenteUnitaire="+tx2.getString()+"&quantite="+g.getValue()+"&tauxReduction="+g1.getValue()+"&idBoutique="+idBoutique);
          
            dis = hc.openDataInputStream();
            int ascii;
            StringBuffer sb = new  StringBuffer();
            while( (ascii=dis.read()) != -1 ){
                
                sb.append((char)ascii);
                
            }    if(sb.toString().equals("successfully updated")){
                Alert a= new Alert("Information", sb.toString(), null, AlertType.CONFIRMATION);
                a.setTimeout(3000);
              
                try {
                      if(choix==0)
                    Midlet.midlet.disp.setCurrent(new ResBoutiqueProdBoutiqueList());
                       else
                 Midlet.midlet.disp.setCurrent(new ResBoutiqueProdEnseigneList());
                } catch (ParserConfigurationException ex) {
                    ex.printStackTrace();
                } catch (SAXException ex) {
                    ex.printStackTrace();
                }
               
            }else{
                Alert a= new Alert("Information", sb.toString(), null, AlertType.ERROR);
                a.setTimeout(3000);
                try {
                      if(choix==0)
                    Midlet.midlet.disp.setCurrent(new ResBoutiqueProdBoutiqueList());
                       else
                 Midlet.midlet.disp.setCurrent(new ResBoutiqueProdEnseigneList());
                } catch (ParserConfigurationException ex) {
                    ex.printStackTrace();
                } catch (SAXException ex) {
                    ex.printStackTrace();
                }
            }   
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                dis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
   else if(c==cmdBack)
   {
       switch(choix)
       {
           case 0:
       {
           try {
               Midlet.midlet.disp.setCurrent(new ResBoutiqueProdBoutiqueList());
           } catch (ParserConfigurationException ex) {
               ex.printStackTrace();
           } catch (SAXException ex) {
               ex.printStackTrace();
           } catch (IOException ex) {
               ex.printStackTrace();
           }
       }
               break;
           case 1:
       {
           try {
               Midlet.midlet.disp.setCurrent(new ResBoutiqueProdEnseigneList());
           } catch (ParserConfigurationException ex) {
               ex.printStackTrace();
           } catch (SAXException ex) {
               ex.printStackTrace();
           } catch (IOException ex) {
               ex.printStackTrace();
           }
       }
               break;
               
       }
   }
   else if(c==cmdSupp)
   {
       String url = null;
     HttpConnection hc = null;
     try {
      
           url="http://localhost:82/mobile/RB/delete.php?";
             hc = (HttpConnection)Connector.open(url+"idProduit="+idProduit);
          
            dis = hc.openDataInputStream();
            int ascii;
            StringBuffer sb = new  StringBuffer();
            while( (ascii=dis.read()) != -1 ){
                
                sb.append((char)ascii);
                
            }    if(sb.toString().equals("successfully updated")){
                Alert a= new Alert("Information", sb.toString(), null, AlertType.CONFIRMATION);
                a.setTimeout(3000);
              
                try {
                      if(choix==0)
                    Midlet.midlet.disp.setCurrent(new ResBoutiqueProdBoutiqueList());
                       else
                 Midlet.midlet.disp.setCurrent(new ResBoutiqueProdEnseigneList());
                } catch (ParserConfigurationException ex) {
                    ex.printStackTrace();
                } catch (SAXException ex) {
                    ex.printStackTrace();
                }
               
            }else{
                Alert a= new Alert("Information", sb.toString(), null, AlertType.ERROR);
                a.setTimeout(3000);
                try {
                      if(choix==0)
                    Midlet.midlet.disp.setCurrent(new ResBoutiqueProdBoutiqueList());
                       else
                 Midlet.midlet.disp.setCurrent(new ResBoutiqueProdEnseigneList());
                } catch (ParserConfigurationException ex) {
                    ex.printStackTrace();
                } catch (SAXException ex) {
                    ex.printStackTrace();
                }
            }   
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                dis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
   }
       
        
    }

    

   
}
