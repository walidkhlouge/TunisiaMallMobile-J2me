/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.GUI;
import tunisiamall.entites.Compte;
import tunisiamall.midlet.Midlet;
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
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.Ticker;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import tunisiamall.handler.CompteRBHandler;


public class ModifierResBForm extends Form implements CommandListener,Runnable{
    TextField tfNom = new TextField("Nom           ", "", 10, TextField.ANY);
    TextField tfPrenom = new TextField("Prenom          ", "", 10, TextField.ANY);
    TextField tfMdp1 = new TextField("Nouveau mot de passe", "", 10, TextField.PASSWORD);
    TextField tfMdp2 = new TextField("Confirmer mot de passe", "", 10, TextField.PASSWORD);
    TextField tfmail = new TextField("Nouveau mail", "", 10, TextField.ANY);
    Command cmdUpd = new Command("Modifier", Command.OK, 0);
    HttpConnection hc;
    DataInputStream dis;
    StringBuffer sb;
    Compte compte;
    
    private Ticker ticker = new Ticker("Modifier les coordonées de "+Midlet.connected.getLogin()); 
    String url="http://localhost:82/mobile/RB/update.php?";  
    public ModifierResBForm() {
        super("Modifier vos coordonnées");
        try {
            CompteRBHandler compteHandler = new CompteRBHandler(Midlet.connected.getLogin());
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost:82/mobile/RB/getXmlPersons_Attributes.php");//people.xml est un exemple
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, compteHandler);
            // display the result
            
            compte = compteHandler.getCompte();
            tfNom.setString(compte.getNom());
            tfPrenom.setString(compte.getPrenom());
            tfmail.setString(compte.getEmail());
            
                
            
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        append(tfNom);
        append(tfPrenom);
        append(tfMdp1);
        append(tfMdp2);
        append(tfmail);
        setTicker(ticker);
        addCommand(cmdUpd);
        setCommandListener(this);
        
    }
    
        
    public void commandAction(Command c, Displayable d) { 
        if(c==cmdUpd){
            if (tfNom.size()==0 || tfPrenom.size()==0 || tfmail.size()==0){
           Alert a= new Alert("Erreur", "Champs Vides", null, AlertType.ERROR);
           a.setTimeout(3000);
           Midlet.midlet.disp.setCurrent(a);
            }
            else if(!tfMdp1.getString().equals(tfMdp2.getString()))
            {
                Alert a= new Alert("Erreur", "Password différents", null, AlertType.ERROR);
           a.setTimeout(3000);
           Midlet.midlet.disp.setCurrent(a);
            }
                
            else{
        try {
            String nom, prenom, mail, password;
            nom= tfNom.getString();
            prenom= tfPrenom.getString();
            mail = tfmail.getString();
            password=tfMdp1.getString();
            //System.out.println(mail);
 hc=(HttpConnection)Connector.open(url+"&nom="+nom+"&prenom="+prenom+"&pwd="
         +password+"&mail="+mail+"&loginc="+Midlet.connected.getLogin());
       dis=hc.openDataInputStream();
           
       int ascii;
       sb =new  StringBuffer();
       
       while( (ascii=dis.read()) != -1 ){
           
           sb.append((char)ascii);
           
       }
       
       
    if(sb.toString().equals("successfully updated")){
           Alert a= new Alert("Information", sb.toString(), null, AlertType.CONFIRMATION);
           a.setTimeout(3000);
           Midlet.midlet.disp.setCurrent(a);
       }else{
           Alert a= new Alert("Information", sb.toString(), null, AlertType.ERROR);
           a.setTimeout(3000);
          Midlet.midlet.disp.setCurrent(a);
       }
       
        
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    } 
    }

    }

    public void run() {
        
    }
    
}
