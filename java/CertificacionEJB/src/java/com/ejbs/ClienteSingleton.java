/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ejbs;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

@Singleton
@Startup
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ClienteSingleton {
    @Resource(mappedName = "jms/dest")
    private Queue dest;
    @Resource(mappedName = "jms/queue")
    private ConnectionFactory queue;
    
    @EJB
    EJBDePruebaTransac myEJB;
    
    @EJB
    MiTimer miTimer;
    
    @EJB
    MiSingleton miSingleton;
    
    @PostConstruct
    public void llamarEJB() {
        
        myEJB.metodoAsincrono();
        //myEJB.metodoBusiness();
        //miTimer.imprimeFecha2();
        
        /*
        try {
            sendJMSMessageToDest("Mensaje para Message bean");
        } catch (JMSException ex) {
            Logger.getLogger(ClienteSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        Proceso proceso = new Proceso();
        proceso.start();
        miSingleton.procesoLargo();
    }    

    private Message createJMSMessageForjmsDest(Session session, Object messageData) throws JMSException {
        // TODO create and populate message to send
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData.toString());
        return tm;
    }

    private void sendJMSMessageToDest(Object messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = queue.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(dest);
            messageProducer.send(createJMSMessageForjmsDest(session, messageData));
            messageProducer.send(createJMSMessageForjmsDest(session, messageData));
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    
    
    
    public class Proceso extends Thread{
	
	
	public void run()
	{
            miSingleton.procesoLargo();
	}
	

}
    
    
    
    
    
}