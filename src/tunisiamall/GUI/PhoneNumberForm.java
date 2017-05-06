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
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;


public class PhoneNumberForm extends Form implements CommandListener {

    Command cmdBack = new Command("Back", Command.BACK, 0);
    Display disp;

    public PhoneNumberForm(String title, Display d) {

        super(title);
        addCommand(cmdBack);
        setCommandListener(this);
        disp = d;
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdBack) {
            ListEnseigne lstp = new ListEnseigne("Person", List.IMPLICIT, disp);
            disp.setCurrent(lstp);
        }
    }

}
