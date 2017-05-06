/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tunisiamall.GUI;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import tunisiamall.midlet.Midlet;
import tunisiamall.entites.Boutique;
import tunisiamall.entites.Produit;
import tunisiamall.handler.BoutiqueHandler;
import tunisiamall.handler.BoutiqueRBHandler;
import tunisiamall.handler.ProdEnsHandler;


public class ResBoutiqueProdEnseigneList extends List implements CommandListener, Runnable{
    
    Command cmdBack = new Command("Back", Command.EXIT, 0);
    
    
    HttpConnection hc;
    Display dis;
    StringBuffer sb;
    String nomEnseigne;
    String idBoutique;
    Vector produits = new Vector();
    
    
    public ResBoutiqueProdEnseigneList() throws ParserConfigurationException, SAXException, IOException {
        super("Liste des Produits", IMPLICIT);
             BoutiqueRBHandler boutiqueHandler1 = new BoutiqueRBHandler(Midlet.connected.getLogin());
                // get a parser object
              
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost:82/mobile/RB/getXmlBoutique_Attributes.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, boutiqueHandler1);
            nomEnseigne=boutiqueHandler1.getNomEnseigne();
            idBoutique=boutiqueHandler1.getIdBoutique();
            System.out.println(nomEnseigne+" "+idBoutique);
            ProdEnsHandler prodHandler1 = new ProdEnsHandler(idBoutique, nomEnseigne);
            SAXParser parser1 = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc1 = (HttpConnection) Connector.open("http://localhost:82/mobile/RB/getXmlProduit_Attributes.php");
            DataInputStream dis1 = new DataInputStream(hc1.openDataInputStream());
            parser1.parse(dis1, prodHandler1);
            produits=prodHandler1.getProdEnsClass();
            for(int i=0;i<produits.size();i++){
                Produit n = (Produit)produits.elementAt(i);
            super.append(n.getNom(), null);
                    }
         
        addCommand(cmdBack);
        setCommandListener(this);
    }
    
    public void commandAction(Command c, Displayable d) {
        
         if(c==cmdBack)  {
     Midlet.midlet.disp.setCurrent(new ResBoutiqueProduitForm());  
         }
        else if(super.getSelectedIndex()!=-1){
                Produit n = (Produit)produits.elementAt(super.getSelectedIndex());
             Midlet.midlet.disp.setCurrent(new ResBoutiqueModifierProForm(n.getId(),1,idBoutique));
         }
         //ajouter un produit
        
          
    }

    public void run() {
    }

}
