/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.GUI;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.Ticker;
import org.beanizer.j2me.charts.ChartItem;
import org.beanizer.j2me.charts.VBarChart;


public class Stats extends Form implements CommandListener {

    Display disp;
    private final static Command CMD_EXIT = new Command("Exit", Command.EXIT, 1);
    final VBarChart item2 = new VBarChart("");

    public Stats(String title, Display d) {
        super(title);
        Ticker ticker = new Ticker("Statistiques Responsable Enseigne");
        initItem(item2);
         addCommand(CMD_EXIT);
        setCommandListener(this);
        setTicker(ticker);
        append(item2);
        disp = d;
        Runnable run = new Runnable() {
            public void run() {

            }
        };
        (new Thread(run)).start();

    }

    private void initItem(ChartItem item) {

        item.setFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_SMALL);
        item.setDrawAxis(true);
        item.setPreferredSize(2000, 200);
        item.setMargins(4, 4, 10, 15);

        try {
            Image img = Image.createImage("/org/beanizer/j2me/img/image2.png");
            item.setBackgroundImage(img);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        item.showShadow(true);
        item.setShadowColor(20, 20, 20);
        item.setColor(40, 40, 200);
        item.resetData();
        item.addElement("Enseignes", 10, 0, 0, 255);
        item.addElement("Produits", 40, 0, 255, 0);
        item.addElement("Boutiques", 10, 0, 0, 255);
        item.addElement("Comptes", 5, 0, 200, 50);
        item.setMaxValue(100);
    }

    public void commandAction(Command c, Displayable d) {
    if(c==CMD_EXIT){
    disp.setCurrent(new DrawStringCanv());
    }
    }

}
