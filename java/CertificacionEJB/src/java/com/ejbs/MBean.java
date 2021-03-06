package com.ejbs;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author armando
 */
@MessageDriven(mappedName = "jms/dest", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MBean implements MessageListener {
    
    public MBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        TextMessage tmsg = (TextMessage)message;
        try {
            System.out.println("===============MBean: Mensaje = "+tmsg.getText());
        } catch (JMSException ex) {
            Logger.getLogger(MBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        //throw new RuntimeException("Error inesperado");
    }
    
}
