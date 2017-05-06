/*
 * PieChartImageTest.java
 * 
 * Created on 29 June 2007, 15:56
 * 
 */

package org.beanizer.j2me;


import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.util.*;

import org.beanizer.j2me.charts.ChartItem;
import org.beanizer.j2me.charts.PieChart;



public class PieChartImageTest extends MIDlet implements CommandListener {
    private Display display;
    private Form mainForm;
    private final static Command CMD_EXIT = new Command("Exit", Command.EXIT, 1);
    final PieChart item3= new PieChart(""); 
    //int[] values={10,40,10,5,35,10,5,40,12,32};
    int index=0;
    public void startApp() {
        display = Display.getDisplay(this);
        
        
        Ticker ticker=new Ticker("J2ME PieChartComponent Demo");
        
        mainForm = new Form("PieChart Demo");
        initItem(item3);
        mainForm.setTicker(ticker);
        
        /**
         *          How to directly use the drawing function for custom needs.  
         *          Here, instead of appending the PieChart item to the form,
         *          we create an image and through the chart object draw into
         *          its Graphics object and then append the image itself to the 
         *          form. Thi allows, for instance, to export generated charts
         *           
         **/
        Image iii= Image.createImage(150,150);
        Graphics gg= iii.getGraphics();
        item3.drawPie(gg, 0, 0, 120, 120);
        mainForm.append(iii);
        StringItem mytext=new StringItem("","In this MIDLET what you see is an Image object. The PieChart Item is instantiated and initialized, but its drawPie method is used to draw on the Graphics object of the Image.");        
        mainForm.append(mytext);
        
        
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
        item.setPreferredSize(mainForm.getWidth()-50,mainForm.getWidth()-50);
        item.setMargins(15,15,15,25);
        try{
            Image img=Image.createImage("/org/beanizer/j2me/img/image3.png");
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


