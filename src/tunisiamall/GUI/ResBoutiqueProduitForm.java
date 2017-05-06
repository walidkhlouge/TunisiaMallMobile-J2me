/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.GUI;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import tunisiamall.midlet.Midlet;

public class ResBoutiqueProduitForm extends Form implements CommandListener {

    Image img = null;
    Command cmdNext = new Command("Next", Command.OK, 0);
    Command cmdExit = new Command("Back", Command.EXIT, 0);
    String[] produits = {"Produit Boutique","Produits Enseigne"};
    ChoiceGroup cg1 = new ChoiceGroup("choisir", ChoiceGroup.EXCLUSIVE, produits, null);
    
    
    
    
    
    
    public ResBoutiqueProduitForm(){
    super("Produits");
          try {
          this.img = Image.createImage("/tn.jpg");
          this.append(img);
           
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.append(cg1);
       addCommand(cmdNext);
        addCommand(cmdExit);
        setCommandListener(this);
    }
    
    
    public void commandAction(Command c, Displayable d) {

    if(c==cmdExit)
    Midlet.midlet.disp.setCurrent(new ResBoutiqueAccueilForm());
    else if(cg1.getSelectedIndex()==0)
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
    else if(cg1.getSelectedIndex()==1)
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
    
}}
