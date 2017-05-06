/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.GUI;

import java.io.IOException;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import tunisiamall.midlet.Midlet;


public class AccueilClient extends Canvas implements CommandListener{
    Command exitButton = new Command("Exit", Command.BACK, 0);
    Command decButton = new Command("Se deconnecter", Command.EXIT, 0);
    Command modifButton = new Command("Modifier coordonnées", Command.EXIT, 0);
      int w = getWidth();
        int h = getHeight();
    int choix = 0;
    Image logo;
    Image fond;
    public AccueilClient() throws IOException {
         logo= Image.createImage("/tunisiamall/GUI/logo.jpg");
         fond= Image.createImage("/tunisiamall/GUI/fond.jpg");
         addCommand(decButton);
         addCommand(exitButton);
         addCommand(modifButton);
        setCommandListener((CommandListener) this);
        
    }

    public void commandAction(Command c, Displayable d) {
        if(c==decButton)
            Midlet.midlet.disp.setCurrent(new Authentification());
        else if(c==exitButton)
                Midlet.midlet.notifyDestroyed();
        else if(c==modifButton)
            try {
                Midlet.midlet.disp.setCurrent(new ModifCoor());
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        }
      
    }

    protected void paint(Graphics g) {
       
            g.setColor(255,255,255);
            g.fillRect(0, 0, w, h);
             g.drawImage(fond, 0, 0, Graphics.TOP|Graphics.LEFT);
            g.setColor(0);
            
            if (choix==0)
            {
                
                g.setColor(0);
                g.fillRect(0, 0, w, h/8);
                g.setColor(255,255,255);
                g.drawImage(logo, 10, (h/8)-36, Graphics.TOP|Graphics.LEFT);
                g.drawImage(logo, w-40, (h/8)-36, Graphics.TOP|Graphics.LEFT);
            }
            else
            g.setColor(0);
          
               g.drawString("Catalogue", w/2-30,h/8-30,Graphics.TOP|Graphics.LEFT);
            if (choix==1)
            {
                g.setColor(0);
                g.fillRect(0, h/8, w, h/8);
                g.setColor(255,255,255);
                 g.drawImage(logo, 10, (2*h/8)-36, Graphics.TOP|Graphics.LEFT);
                 g.drawImage(logo, w-40, (2*h/8)-36, Graphics.TOP|Graphics.LEFT);
            }
            else
            g.setColor(0);
            g.drawString("Enseigne", w/2-30,2*h/8-30,Graphics.TOP|Graphics.LEFT);
                if (choix==2)
            {
                g.setColor(0);
                g.fillRect(0, 2*h/8, w, h/8);
                g.setColor(255,255,255);
                 g.drawImage(logo, 10, (3*h/8)-36, Graphics.TOP|Graphics.LEFT);
                 g.drawImage(logo, w-40, (3*h/8)-36, Graphics.TOP|Graphics.LEFT);
            }
                else
            g.setColor(0);
            g.drawString("Boutique", w/2-30,3*h/8-30,Graphics.TOP|Graphics.LEFT);
                 if (choix==3)
            {
                g.setColor(0);
                g.fillRect(0, 3*h/8, w, h/8);
                g.setColor(255,255,255);
                 g.drawImage(logo, 10, (4*h/8)-36, Graphics.TOP|Graphics.LEFT);
                 g.drawImage(logo, w-40, (4*h/8)-36, Graphics.TOP|Graphics.LEFT);
            }
                else
            g.setColor(0);
            g.drawString("Produit", w/2-30,4*h/8-30,Graphics.TOP|Graphics.LEFT);            
                 if (choix==4)
            {
                g.setColor(0);
                g.fillRect(0, 4*h/8, w, h/8);
                g.setColor(255,255,255);
                 g.drawImage(logo, 10, (5*h/8)-36, Graphics.TOP|Graphics.LEFT);
                 g.drawImage(logo, w-40, (5*h/8)-36, Graphics.TOP|Graphics.LEFT);
            }
                else
            g.setColor(0);
            g.drawString("Abonnement", w/2-30,5*h/8-30,Graphics.TOP|Graphics.LEFT);
                 if (choix==5)
            {
                g.setColor(0);
                g.fillRect(0, 5*h/8, w, h/8);
                g.setColor(255,255,255);
                 g.drawImage(logo, 10, (6*h/8)-36, Graphics.TOP|Graphics.LEFT);
                 g.drawImage(logo, w-40, (6*h/8)-36, Graphics.TOP|Graphics.LEFT);
            }
                else
            g.setColor(0);
            g.drawString("Panier", w/2-30,6*h/8-30,Graphics.TOP|Graphics.LEFT);
                 if (choix==6)
            {
                g.setColor(0);
                g.fillRect(0, 6*h/8, w, h/8);
                g.setColor(255,255,255);
                 g.drawImage(logo, 10, 7*h/8-36, Graphics.TOP|Graphics.LEFT);
                 g.drawImage(logo, w-40, 7*h/8-36, Graphics.TOP|Graphics.LEFT);
            }
                else
            g.setColor(0);
            g.drawString("Promotion", w/2-30,7*h/8-30,Graphics.TOP|Graphics.LEFT);
    }
    protected void keyPressed(int keyCode)
    {
          if (getGameAction(keyCode)==UP)
            {
                choix--;
                if(choix<0)
                    choix=6;
            }
            else if(getGameAction(keyCode)==DOWN)
            {
                choix++;
                if(choix>6)
                    choix=0;
            }
            else if(getGameAction(keyCode)==FIRE)
            {
                if(choix==0)
        {
                    try {
                        Midlet.midlet.disp.setCurrent(new AfficherLesCatalogue());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (SAXException ex) {
                        ex.printStackTrace();
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    }
        }
         else if(choix==1)
        {
            Midlet.midlet.disp.setCurrent(new ListEnseigne("Choisir Enseigne", List.IMPLICIT, Midlet.disp));
        }               
        else if(choix==2)
        {
            Midlet.midlet.disp.setCurrent(new ListBoutique("Choisir Boutique", List.IMPLICIT, Midlet.disp, ""));
        }
        else if(choix==3)
        {
            Midlet.midlet.disp.setCurrent(new ListProduit("Ajouter au Panier", List.IMPLICIT, Midlet.disp, -1));
        }
        else if(choix==5)
        {
            Midlet.midlet.disp.setCurrent(new ListPanier("Panier", List.EXCLUSIVE, Midlet.disp, Midlet.connected.getLogin()));
        }
                   else if(choix==6)
        {
                    try {
                        Midlet.midlet.disp.setCurrent(new Promotion());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
        }
            }
             repaint();
    }
    
}
