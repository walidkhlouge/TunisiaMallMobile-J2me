/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.GUI;

import tunisiamall.GUI.Authentification;
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
import tunisiamall.midlet.Midlet;


    public class Inscrire extends Form implements CommandListener{
TextField nom = new TextField("Nom", "", 50, TextField.ANY);
TextField prenom = new TextField("Prenom", "", 50, TextField.ANY);
TextField numero = new TextField("Numero", "", 50, TextField.NUMERIC);
TextField login = new TextField("login", "", 50, TextField.ANY);
TextField email = new TextField("email", "", 50, TextField.ANY);
TextField pwd = new TextField("password", "", 50, TextField.PASSWORD);
String tabSexe[] = {"Homme","Femme"};
String tabType[] = {"ResponsableEnseigne","ResponsableBoutique","Client"};
        ChoiceGroup sexe = new ChoiceGroup("Sexe", ChoiceGroup.EXCLUSIVE, tabSexe, null);
        ChoiceGroup type = new ChoiceGroup("Type", ChoiceGroup.EXCLUSIVE, tabType, null);
        String pass;
DateField date = new DateField("Date", DateField.DATE);
        Command nextButton = new Command("Next", Command.OK, 0);
        Command backButton = new Command("Back", Command.BACK, 0);
    public Inscrire() {
        super("Inscrire");
        append(nom);
        append(prenom);
        append(sexe);
        append(numero);
        append(email);
        append(login);
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
        DataInputStream dis1 = null;
        try {
              HttpConnection hc1 = (HttpConnection)Connector.open("http://localhost:82/mobile/Walid/hasher.php?"+"pass="+pwd.getString());
            
            dis1 = hc1.openDataInputStream();
            int ascii;
            StringBuffer sb = new  StringBuffer();
            while( (ascii=dis1.read()) != -1 ){
                
                sb.append((char)ascii);
                
            } 
            pass = sb.toString();
            pass = "$2a".concat(pass.substring(3));
            HttpConnection hc = (HttpConnection)Connector.open("http://localhost:82/mobile/Walid/insertCompte.php?nom="+nom.getString()+"&prenom="+prenom.getString()+"&login="+login.getString()+"&adresseMail="+email.getString()+"&type="+type.getString(type.getSelectedIndex())+"&sexe="+sexe.getString(sexe.getSelectedIndex())+"&pwd="+pass);
            
            dis1 = hc.openDataInputStream();
            while( (ascii=dis1.read()) != -1 ){
                
                sb.append((char)ascii);
                
            }    if(sb.toString().equals("successfully added")){
                Alert a= new Alert("Information", sb.toString(), null, AlertType.CONFIRMATION);
                a.setTimeout(3000);
                 Midlet.midlet.disp.setCurrent(new Authentification());
            }else{
                Alert a= new Alert("Information", sb.toString(), null, AlertType.ERROR);
                a.setTimeout(3000);
                 Midlet.midlet.disp.setCurrent(new Authentification());
            }   
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                dis1.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    else if(c==backButton)
    {
           Midlet.midlet.disp.setCurrent(new Authentification());
    }
    }
    
}
