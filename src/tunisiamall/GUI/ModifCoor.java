/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.GUI;

import tunisiamall.handler.AuthHandler;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import tunisiamall.entites.Compte;
import tunisiamall.midlet.Midlet;


public class ModifCoor extends Form implements CommandListener{
Command nextButton = new Command("Next", Command.OK, 0);
        Command backButton = new Command("Back", Command.BACK, 0);
        TextField nom = new TextField("Nom", "", 50, TextField.ANY);
TextField prenom = new TextField("Prenom", "", 50, TextField.ANY);
TextField numero = new TextField("Numero", "", 50, TextField.NUMERIC);
TextField email = new TextField("email", "", 50, TextField.ANY);
TextField pwd = new TextField("password", "", 50, TextField.PASSWORD);
String tabSexe[] = {"Homme","Femme"};
String tabType[] = {"Responsable Enseigne","Responsable Boutique","Client"};
        ChoiceGroup sexe = new ChoiceGroup("Sexe", ChoiceGroup.EXCLUSIVE, tabSexe, null);
        ChoiceGroup type = new ChoiceGroup("Type", ChoiceGroup.EXCLUSIVE, tabType, null);
DateField date = new DateField("Date", DateField.DATE);
Compte c;
    public ModifCoor() throws IOException, SAXException, ParserConfigurationException
    {
        super("Modifier coordonnées");
        
                AuthHandler authHandler1 = new AuthHandler(Midlet.connected.getLogin(),Midlet.connected.getPassword());
                // get a parser object
              
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost:82/mobile/Selim/getXmlPersons_Attributes.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, authHandler1);
             if(authHandler1.getCompte()==true)
            {
            c= authHandler1.getCompteClass();
                
            }
        nom.setString(c.getNom());
        prenom.setString(c.getPrenom());
      if (c.getSexe().equals("Homme"))
        sexe.setSelectedIndex(0, true);
        else
        sexe.setSelectedIndex(1, true);
        email.setString(c.getEmail());
        pwd.setString(c.getPassword());
        if(c.getType().equals("Responsable Enseigne"))
        type.setSelectedIndex(0, true);
        if(c.getType().equals("Responsable Boutique"))
        type.setSelectedIndex(1, true);
        else
        type.setSelectedIndex(2, true);
        append(nom);
        append(prenom);
        append(sexe);
        append(numero);
        append(email);
        append(pwd);
        append(date);
        append(type);
        addCommand(nextButton);
        addCommand(backButton);
        setCommandListener(this);
        
        
    }
    public void commandAction(Command c, Displayable d) {
     if(c==nextButton)
     {
          DataInputStream dis = null;
        try {
           String url="http://localhost:82/mobile/Walid/updateCompte.php?";
            //Midlet.midlet.disp.setCurrent(new Validation());
            
            HttpConnection hc = (HttpConnection)Connector.open(url+"login="+Midlet.connected.getLogin()+"&nom="+nom.getString()+"&prenom="+prenom.getString()+"&adresseMail="+email.getString()+"&type="+type.getString(type.getSelectedIndex())+"&sexe="+sexe.getString(sexe.getSelectedIndex())+"&pwd="+pwd.getString());
            
            dis = hc.openDataInputStream();
            int ascii;
            StringBuffer sb = new  StringBuffer();
            while( (ascii=dis.read()) != -1 ){
                
                sb.append((char)ascii);
                
            }    if(sb.toString().equals("successfully added")){
                Alert a= new Alert("Information", sb.toString(), null, AlertType.CONFIRMATION);
                a.setTimeout(3000);
                 Midlet.midlet.disp.setCurrent(new AccueilClient());
            }else{
                Alert a= new Alert("Information", sb.toString(), null, AlertType.ERROR);
                a.setTimeout(3000);
                 Midlet.midlet.disp.setCurrent(new AccueilClient());
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
        else if(c==backButton)
    {
         try {
             Midlet.midlet.disp.setCurrent(new AccueilClient());
         } catch (IOException ex) {
             ex.printStackTrace();
         }
    }
    }
    
}
