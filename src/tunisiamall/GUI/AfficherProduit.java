/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.GUI;

import java.io.IOException;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import tunisiamall.midlet.Midlet;
import tunisiamall.entites.Produit;
import tunisiamall.entites.Catalogue;

public class AfficherProduit extends Form implements CommandListener{

    Command exitButton = new Command("Exit", Command.BACK, 0);
    Command backButton = new Command("Back", Command.EXIT, 0);
    Catalogue cat;
    AfficherProduit(Produit produit, Catalogue cat){
        super(produit.getNom(),null);
        append("Prix : "+produit.getPrixUnitaire());
        append("\nInfo : "+produit.getInfo());
        append("\nQuantite :"+produit.getQuantite());
         this.cat=cat;
         addCommand(backButton);
         addCommand(exitButton);
        setCommandListener(this);
        }

    public void commandAction(Command c, Displayable d) {
          if(c==backButton)
        {
            try {
                Midlet.midlet.disp.setCurrent(new AfficherCatalogue(cat));
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (SAXException ex) {
                ex.printStackTrace();
            } catch (ParserConfigurationException ex) {
                ex.printStackTrace();
            }
        }
        else if(c==exitButton)
        {
            Midlet.midlet.notifyDestroyed();
        }

    }

   
    
}
