
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.GUI;

import tunisiamall.handler.CatHandler;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import tunisiamall.midlet.Midlet;
import tunisiamall.entites.Catalogue;


public class AfficherLesCatalogue extends List implements CommandListener{
Vector v;
Catalogue c;
    Command exitButton = new Command("Exit", Command.BACK, 0);
    Command backButton = new Command("Back", Command.EXIT, 0);
    public AfficherLesCatalogue() throws IOException, SAXException, ParserConfigurationException {
        super("Catalogue", List.IMPLICIT);
        addCommand(backButton);
        addCommand(exitButton);
                CatHandler catHandler = new CatHandler();
                // get a parser object
              
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost:82/mobile/Walid/getXmlCatalogue_Attributes.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, catHandler);
             if(catHandler.getCatalogueClass()!=null)
            {
            v= catHandler.getCatalogueClass();
            }
             for(int i=0;i<v.size();i++)
             {
                 c=(Catalogue) v.elementAt(i);
                append(c.getIntitule(),null);
                 
             }
        setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        if(this.getSelectedIndex()!=-1)
        {
            try {
                Midlet.midlet.disp.setCurrent(new AfficherCatalogue((Catalogue) v.elementAt(getSelectedIndex())));
            } catch (ParserConfigurationException ex) {
                ex.printStackTrace();
            } catch (SAXException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    
    
}
