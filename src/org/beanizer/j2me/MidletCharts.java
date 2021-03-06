/*
 * VBarChartTest.java
 *
 * Created on 29 June 2007, 15:56
 */
package org.beanizer.j2me;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.util.*;

import org.beanizer.j2me.charts.ChartItem;
import org.beanizer.j2me.charts.VBarChart;


public class MidletCharts extends MIDlet implements CommandListener {

    private Display display;
    private Form mainForm;
    private final static Command CMD_EXIT = new Command("Exit", Command.EXIT, 1);
    final VBarChart item2 = new VBarChart("");

    public void startApp() {
        display = Display.getDisplay(this);

        Ticker ticker = new Ticker("Statistiques Responsable Enseigne");
        mainForm = new Form("Statistiques");
        initItem(item2);
        mainForm.setTicker(ticker);
        mainForm.append(item2);
        mainForm.addCommand(CMD_EXIT);
        mainForm.setCommandListener(this);
        display.setCurrent(mainForm);
        Runnable run = new Runnable() {
            public void run() {

            }
        };
        (new Thread(run)).start();

    }

    /**
     * Initializes given chart item
     *
     *
     */
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

    public void commandAction(Command c, Item e) {
        if (c.getLabel().equals("Exit")) {
            destroyApp(false);
            notifyDestroyed();
        }
    }

    public void commandAction(Command c, Displayable d) {
        if (c.getLabel().equals("Exit")) {
            destroyApp(false);
            notifyDestroyed();
        }
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
}
