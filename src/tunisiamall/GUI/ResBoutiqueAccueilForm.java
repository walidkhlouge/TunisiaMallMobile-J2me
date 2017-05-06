/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tunisiamall.GUI;


import java.io.IOException;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import tunisiamall.midlet.Midlet;


public class ResBoutiqueAccueilForm extends Form implements CommandListener{ 
    
    Image img = null;
    Command cmdNext = new Command("Next", Command.OK, 0);
    Command cmdExit = new Command("Back", Command.EXIT, 0);
    String[] Gestion = {"Gestion Compte","Gestion produits"};
    ChoiceGroup cg = new ChoiceGroup("choisir", ChoiceGroup.EXCLUSIVE, Gestion, null);
    
    public ResBoutiqueAccueilForm(){
    super("Accueil");
          try {
          this.img = Image.createImage("/tn.jpg");
          this.append(img);
           
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.append(cg);
       addCommand(cmdNext);
        addCommand(cmdExit);
        setCommandListener(this);
    }
    public void commandAction(Command c, Displayable d) {
        if(c==cmdExit)
    Midlet.midlet.notifyDestroyed();
    else if(cg.getSelectedIndex()==0)
    {
    Midlet.midlet.disp.setCurrent(new ModifierResBForm());
    }
    else if(cg.getSelectedIndex()==1)
    {
    Midlet.midlet.disp.setCurrent(new ResBoutiqueProduitForm());
        
    }
        }}
