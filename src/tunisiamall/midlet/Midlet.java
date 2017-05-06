/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.midlet;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.MIDlet;
import tunisiamall.GUI.Authentification;

import tunisiamall.GUI.Form11;
import tunisiamall.GUI.ListEnseigne;
import tunisiamall.GUI.SmsForm;
import tunisiamall.entites.Compte;


public class Midlet extends MIDlet {
public static Display disp;
public static Midlet midlet;
public static Compte connected;


    //ListEnseigne listP = new ListEnseigne("Person", List.IMPLICIT, disp);
Form11 Accueil=new Form11();
Splashscreen sp=new Splashscreen();

    public void startApp() {
        midlet=this;
        disp = Display.getDisplay(this);
        disp.setCurrent(sp);
    
    try {
        Thread.sleep(3000);
    } catch (InterruptedException ex) {
        ex.printStackTrace();
    }
    disp.setCurrent(new Authentification());
       
    }
    public void pauseApp() {
    }
    
    
    public void destroyApp(boolean unconditional) {
    }
}
