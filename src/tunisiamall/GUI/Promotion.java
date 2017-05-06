/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.GUI;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.media.control.VideoControl;
import tunisiamall.midlet.Midlet;


public class Promotion extends Canvas implements CommandListener{
 Player player = null;
   private boolean error = false;
     private Alert alert = new Alert("Error");
       private Command backCommand = new Command("Back", Command.ITEM, 1);
  private Command exitCommand = new Command("Exit", Command.EXIT, 1);
  
     public Promotion() throws Exception
     {
             try {
        if (player != null)
          player.close();
      } catch (Exception e) {
        error(e);
      }
             VideoControl videoControl = (VideoControl) player.getControl("javax.microedition.media.control.VideoControl");
        if (videoControl == null)
          throw new Exception("No VideoControl!!");
        videoControl.initDisplayMode(VideoControl.USE_DIRECT_VIDEO, this);
        videoControl.setDisplayFullScreen(true);
        videoControl.setVisible(true);
        player.start();
             addCommand(exitCommand);
    addCommand(backCommand);
    setCommandListener(this);
     }
    protected void paint(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void commandAction(Command c, Displayable d) {
            if (c == exitCommand) {
      Midlet.midlet.notifyDestroyed();
    } else if (c== backCommand) {
    }
  
      
      
    }
      private void loadPlayer() throws Exception {
    player = Manager.createPlayer(getClass().getResourceAsStream("/phantom.mpg"), "video/mpeg");
    player.realize();
  }

  private void error(Exception e) {
    alert.setString(e.getMessage());
    alert.setTimeout(Alert.FOREVER);
    Midlet.midlet.disp.setCurrent(alert);
    e.printStackTrace();
    error = true;
  }
    }
    

