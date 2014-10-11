/*
Tratar de basarme en estos ejemplos
http://javahowto.blogspot.mx/2010/04/message-driven-bean-example-with.html
http://www.roseindia.net/ejb/example-of-messageBean.shtml


*/

package com.mdbs;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "Cola"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
    ,@ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "MessageFormat = 'Version 3.4'")
})
public class MDB1 implements MessageListener {
    
    public MDB1() {
    }
    
    @Override
    public void onMessage(Message message) {
        try{
            TextMessage tm = (TextMessage)message;
            System.out.println("MENSAJEEE = "+tm.getText()+". Nombre = "+tm.getStringProperty("nombre"));
        }catch(JMSException e){
            e.printStackTrace();
        }
    }
    
}
