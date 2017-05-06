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
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import tunisiamall.entites.Compte;
import tunisiamall.midlet.Midlet;


public class Authentification extends Form implements CommandListener, Runnable{
TextField login = new TextField("Login", "", 50, TextField.ANY);
TextField password = new TextField("Mot de passe ","",50,TextField.PASSWORD);
Command connectedButton = new Command("Se connecter", Command.OK, 0);
Command exitButton = new Command("Exit", Command.EXIT, 0);
    Command inscrireButton = new Command("S'inscrire", Command.OK, 0);
  StringItem si = new StringItem("Erreur", "Mauvais login ou mot de passe");
  int i=0;
    public Authentification() {
        super("Authentification");
         this.append(login);
        this.append(password);
        this.addCommand(exitButton);
        this.addCommand(connectedButton);
        this.addCommand(inscrireButton);
        this.setCommandListener((CommandListener) this);
    }


    protected void destroyApp(boolean unconditional) throws MIDletStateChangeException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected void pauseApp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected void startApp() throws MIDletStateChangeException {
       
       
    }

    public void commandAction(Command c, Displayable d) {
        if (c==exitButton)
            Midlet.midlet.notifyDestroyed();
        else if(c==connectedButton)
        {
            Thread th = new Thread(this);
            th.start();
            }
        else if(c==inscrireButton)
        {
            Midlet.midlet.disp.setCurrent(new Inscrire());
        }
        
    }

    public void run() {
    try {
          
                AuthHandler authHandler = new AuthHandler(login.getString(),password.getString());
                // get a parser object
              
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost:82/mobile/Walid/getXmlPersons_Attributes.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, authHandler);
            // display the result
            i++;
            
            if(authHandler.getCompte()==true)
            {
                Compte c= authHandler.getCompteClass();
                Midlet.connected=c;
                if (Midlet.connected.getType().equals("Client"))
                Midlet.midlet.disp.setCurrent(new AccueilClient());
                else if(Midlet.connected.getType().equals("Responsable Enseigne"))
                    Midlet.midlet.disp.setCurrent(new DrawStringCanv());
               else if (Midlet.connected.getType().equals("Responsable Boutique"))
                   Midlet.midlet.disp.setCurrent(new ResBoutiqueAccueilForm());
                
                
            }
            else{
                if(i<2)
                this.append(si);
            }
            
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

 
    
    
}
