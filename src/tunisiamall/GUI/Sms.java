/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.GUI;

import java.io.IOException;
import javax.microedition.io.Connector;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;


public class Sms {
  public static void Envoyer(final String destination, final String message) {
 
		Thread thread = new Thread(new Runnable() {
 
			public void run() {
				MessageConnection msgConnection;
				try {
					msgConnection = (MessageConnection)Connector.open("sms://"+destination+":5000");
					TextMessage textMessage = (TextMessage)msgConnection.newMessage(
							MessageConnection.TEXT_MESSAGE);
					textMessage.setPayloadText(message);
					msgConnection.send(textMessage);
					msgConnection.close();
                                        
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
 
		thread.start();
	}
}
