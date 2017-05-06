/*
 * LineChartTest.java
 *
 * Created on 29 June 2007, 15:56
 */

package org.beanizer.j2me;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.util.*;

import org.beanizer.j2me.charts.ChartItem;
import org.beanizer.j2me.charts.LineChart;



public class LineChartTest extends MIDlet implements CommandListener {
    private Display display;
    private Form mainForm;
    private final static Command CMD_EXIT = new Command("Exit", Command.EXIT, 1);
    final LineChart item1 = new LineChart(""); 
    public void startApp() {
        display = Display.getDisplay(this);
        
        item1.setFill(true);
        item1.setUseDefaultColor(true);
        
        Ticker ticker=new Ticker("J2ME LineChartComponent Demo");
        
        mainForm = new Form("LineChart Demo");
        initItem(item1);
        mainForm.setTicker(ticker);
        mainForm.append(item1);
        mainForm.addCommand(CMD_EXIT);
        mainForm.setCommandListener(this);
        display.setCurrent(mainForm);
       
        
    }
    
    /**
     *      Initializes given chart item
     *
     **/ 
    private void initItem(ChartItem item){
        item.setFont(Font.FACE_PROPORTIONAL,Font.STYLE_PLAIN,Font.SIZE_SMALL);
        item.setDrawAxis(true);
        item.setPreferredSize(mainForm.getWidth(),120);
        item.setMargins(5,3,10,15);
        try{
            Image img=Image.createImage("/org/beanizer/j2me/img/image1.png");
            item.setBackgroundImage(img);
        } catch(Exception ex){
            ex.printStackTrace();
        }
        item.showShadow(true);
        item.setShadowColor(20,20,20);
        item.setColor(40, 40, 200);
        item.resetData();
        item.addElement("a",10,0,0,255);
        item.addElement("b",40,0,255,0);
        item.addElement("c",10,0,0,255);
        item.addElement("d",5,0,200,50);
        item.addElement("e",35,130,100,220);
        item.addElement("f",10,220,20,20);
        item.addElement("g",22,220,120,20);
        item.setMaxValue(100);

    
    }
    public void commandAction(Command c, Item e) {
            if(c.getLabel().equals("Exit")){
                destroyApp(false);
                notifyDestroyed();        
            }
    }
    
    public void commandAction(Command c, Displayable d) {
            if(c.getLabel().equals("Exit")){
                destroyApp(false);
                notifyDestroyed();        
            }
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
}


