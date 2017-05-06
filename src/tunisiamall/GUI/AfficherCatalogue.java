/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tunisiamall.GUI;

import tunisiamall.handler.ProdHandler;
import tunisiamall.handler.CatProdHandler;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import tunisiamall.midlet.Midlet;
import tunisiamall.entites.Produit;
import tunisiamall.entites.Catalogue;

class AfficherCatalogue  extends Canvas implements CommandListener {
int choix=0;
Image fond;
Image logo[];
    Command exitButton = new Command("Exit", Command.BACK, 0);
    Command backButton = new Command("Back", Command.EXIT, 0);
      int w = getWidth();
        int h = getHeight();
        int page ;
        Vector catProd;
        Vector produits = new Vector() ;
        Produit produit;
        int pageAct=0;
Catalogue catalogue;
    AfficherCatalogue(Catalogue catalogue) throws ParserConfigurationException, SAXException, IOException{
        this.catalogue=catalogue;
        addCommand(backButton);
        addCommand(exitButton);
        setCommandListener(this);
        CatProdHandler catProdHandler = new CatProdHandler(catalogue.getIdCatalogue());
                // get a parser object
              
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost:82/mobile/Selim/getXmlCatProd_Attributes.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis,catProdHandler);
             if(catProdHandler.getCataProdClass()!=null)
            {
            catProd= catProdHandler.getCataProdClass();
            }
             for(int i=0; i < catProd.size();i++){
              ProdHandler prodHandler = new ProdHandler((String) catProd.elementAt(i));
                // get a parser object
                SAXParser parser1 = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc1 = (HttpConnection) Connector.open("http://localhost:82/mobile/Selim/getXmlProd_Attributes.php");
            DataInputStream dis1 = new DataInputStream(hc1.openDataInputStream());
            parser1.parse(dis1,prodHandler);
             if(prodHandler.getProdClass()!=null)
            {
                produits.addElement(prodHandler.getProdClass());
            }
             }
             page= (int) produits.size()/8;
             
           
    }

    protected void paint(Graphics g) {
         g.setColor(255,255,255);
            g.fillRect(0, 0, w, h);
           //  g.drawImage(fond, 0, 0, Graphics.TOP|Graphics.LEFT);
               g.setColor(0);
            if (choix==0)
            {
                
                g.drawRect(0, 0, w, h/8);
               // g.drawImage(logo[page*7+0], w-40, (h/8)-36, Graphics.TOP|Graphics.LEFT);
            }
          produit=(Produit) produits.elementAt(pageAct*7+0);
               g.drawString(produit.getNom(), w/2-30,h/8-30,Graphics.TOP|Graphics.LEFT);
            if (choix==1)
            {
                g.drawRect(0, h/8, w, h/8);
                 //g.drawImage(logo, w-40, (2*h/8)-36, Graphics.TOP|Graphics.LEFT);
            }                                                                                   
                 produit=(Produit) produits.elementAt(pageAct*7+1);
            g.drawString(produit.getNom(), w/2-30,2*h/8-30,Graphics.TOP|Graphics.LEFT);
                if (choix==2)
            {
                g.drawRect(0, 2*h/8, w, h/8);
                 //g.drawImage(logo, w-40, (3*h/8)-36, Graphics.TOP|Graphics.LEFT);
            }
                  produit=(Produit) produits.elementAt(pageAct*7+2);
            g.drawString(produit.getNom(), w/2-30,3*h/8-30,Graphics.TOP|Graphics.LEFT);
                 if (choix==3)
            {
                g.drawRect(0, 3*h/8, w, h/8);
                 //g.drawImage(logo, w-40, (4*h/8)-36, Graphics.TOP|Graphics.LEFT);
            }
                   produit=(Produit) produits.elementAt(pageAct*7+3);
            g.drawString(produit.getNom(), w/2-30,4*h/8-30,Graphics.TOP|Graphics.LEFT);            
                 if (choix==4)
            {
                g.drawRect(0, 4*h/8, w, h/8);
                 //g.drawImage(logo, w-40, (5*h/8)-36, Graphics.TOP|Graphics.LEFT);
            }
                   produit=(Produit) produits.elementAt(pageAct*7+4);
                 g.drawString(produit.getNom(), w/2-30,5*h/8-30,Graphics.TOP|Graphics.LEFT);
                 if (choix==5)
            {
                g.drawRect(0, 5*h/8, w, h/8);
                 //g.drawImage(logo, w-40, (6*h/8)-36, Graphics.TOP|Graphics.LEFT);
            }
                   produit=(Produit) produits.elementAt(pageAct*7+5);
            g.drawString(produit.getNom(), w/2-30,6*h/8-30,Graphics.TOP|Graphics.LEFT);
                 if (choix==6)
            {
                g.drawRect(0, 6*h/8, w, h/8);
                 //g.drawImage(logo, w-40, 7*h/8-36, Graphics.TOP|Graphics.LEFT);
            }
                   produit=(Produit) produits.elementAt(pageAct*7+6);
            g.drawString(produit.getNom(), w/2-30,7*h/8-30,Graphics.TOP|Graphics.LEFT);
    }
  protected void keyPressed(int keyCode)
    {
          if (getGameAction(keyCode)==UP)
            {
                choix--;
                if(choix<0)
                {
                    choix=produits.size()%((pageAct+1)*7)-1;
                    pageAct--;
                    if(pageAct<0)
                        pageAct=page;
                }
            }
            else if(getGameAction(keyCode)==DOWN)
            {
                choix++;
                if(choix>produits.size()%((pageAct+1)*7)-1){
                    choix=0;
                    pageAct++;
                    if(pageAct>page)
                        pageAct=0;
                }
            }
           
            else if(getGameAction(keyCode)==FIRE)
            {
                produit=(Produit) produits.elementAt(pageAct*7+choix);
                Midlet.midlet.disp.setCurrent(new AfficherProduit(produit,catalogue));
            }
      
             repaint();
    }
    public void commandAction(Command c, Displayable d) {
        if(c==backButton)
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
        else if(c==exitButton)
        {
            Midlet.midlet.notifyDestroyed();
        }
    }

}
