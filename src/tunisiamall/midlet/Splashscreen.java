/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.midlet;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;


class Splashscreen extends Canvas{
    Image img;
    public Splashscreen(){
        try{
            
            img=Image.createImage("/tunisiamall/GUI/images/tunisia.jpg");
        }
        catch(Exception e){
            
        }
    }

    protected void paint(Graphics g) {
         g.drawImage(img,  getWidth()/2, this.getHeight()/2, Graphics.HCENTER|Graphics.VCENTER);
    }
    
}
