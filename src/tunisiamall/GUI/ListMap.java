/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.GUI;

import googlemapapiproject.GoogleMapsMarkerCanvas;
import googlemapapiproject.GoogleMapsMoveCanvas;
import googlemapapiproject.GoogleMapsPathCanvas;
import googlemapapiproject.GoogleMapsSimpleCanvas;
import googlemapapiproject.GoogleMapsTestForm;
import googlemapapiproject.GoogleMapsZoomCanvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
import tunisiamall.midlet.Midlet;


public class ListMap extends List implements CommandListener {
    Midlet m;
    Display disp;

    public ListMap(String title, int listType, Display d) {
        super(title, listType);
        disp = d;
        append("Map Simple", null);
        append("Avec Marqueur ", null);
        append("Avec Chemin", null);
        append("Moving maps", null);
        append("Zooming maps", null);
        setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        if(c==List.SELECT_COMMAND)
        {
            switch(getSelectedIndex())
			{
			case 0:
				disp.setCurrent(new GoogleMapsSimpleCanvas(m, d));
				break;
			case 1:
				disp.setCurrent(new GoogleMapsMarkerCanvas(m, d));
				break;
			case 2:
				disp.setCurrent(new GoogleMapsPathCanvas(m, d));
				break;
			case 3:
				disp.setCurrent(new GoogleMapsMoveCanvas(m, d));
				break;
			case 4:
				disp.setCurrent(new GoogleMapsZoomCanvas(m, d));
				break;
			case 5:
				disp.setCurrent(new GoogleMapsTestForm(m, d));
				break;
			}
        }
    }



}
