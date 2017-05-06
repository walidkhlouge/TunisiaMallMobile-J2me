/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.GUI;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.*;

public class SmsForm extends Form implements CommandListener {
TextField msg;
TextField number;
Display d;
Command send;
Command back;
private Alert alert;
    
    
    public SmsForm(Display d,String title)
    {
     super(title);
        this.d=d;
    msg=new TextField("Message", "", 600, TextField.ANY);
    number=new TextField("Number", "", 20, TextField.PHONENUMBER);
    send=new Command("Send", Command.OK, 0);
    back=new Command("Back",Command.EXIT,0);
        append(msg);
        append(number);
        addCommand(send);
        addCommand(back);
        setCommandListener(this);
        
        
    }
    
    public void commandAction(Command c, Displayable d) {
String x=number.getString();
String y=msg.getString();
    if(c== send)
    {
    Sms.Envoyer(x,y );
   
    
    }
    if(c== back)
    {
    
    
    }
    
    }
    
}
