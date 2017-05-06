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
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;
import tunisiamall.midlet.Midlet;


public class Form11 extends Form implements
        CommandListener {

    // TextField tfNom = new TextField("Nom", "", 10, TextField.ANY);
    //TextField tfPrenom = new TextField("Prenom", "", 10, TextField.ANY);
    Command cmdExit = new Command("Exit", Command.EXIT, 0);
    Command cmdEnseigne = new Command("Enseigne", Command.OK, 0);
    Command cmdBoutique = new Command("Boutique", Command.OK, 0);
    Command cmdProduit = new Command("Produit", Command.OK, 0);
    Command cmdPanier = new Command("Panier", Command.OK, 0);
public Form11(){
        super("TUNISIA MALL");
        // append(tfNom);
        //append(tfPrenom);
        Image img = null;
        try {
            img = Image.createImage("/tunisiamall/GUI/images/tn.jpg");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        append(new ImageItem(null, img, ImageItem.LAYOUT_CENTER, null));
        addCommand(cmdEnseigne);
        addCommand(cmdBoutique);
        addCommand(cmdProduit);
        addCommand(cmdPanier);
        addCommand(cmdExit);
        setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdExit) {
            Midlet.midlet.notifyDestroyed();
        }

        if (c == cmdEnseigne) {

            ListEnseigne listP;
            listP = new ListEnseigne("Choisir Enseigne", List.IMPLICIT, Midlet.disp);
            Midlet.midlet.disp.setCurrent(listP);

        }
        if (c == cmdBoutique) {
            ListBoutique listP = new ListBoutique("Choisir Boutique", List.IMPLICIT, Midlet.disp, "");
            Midlet.midlet.disp.setCurrent(listP);

        }
        if (c == cmdProduit) {
            ListProduit listP2 = new ListProduit("Ajouter au Panier", List.IMPLICIT, Midlet.disp, -1);
            Midlet.disp.setCurrent(listP2);
        }
        if (c == cmdPanier) {
            ListPanier listP2 = new ListPanier("Panier", List.EXCLUSIVE, Midlet.disp, "bassemlog");
            Midlet.disp.setCurrent(listP2);
        }
    }

}
