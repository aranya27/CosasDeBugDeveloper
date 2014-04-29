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
import javax.ejb.MessageDrivenContext;
import javax.ejb.Schedule;
import javax.ejb.SessionContext;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@Singleton
@Startup
@TransactionManagement(TransactionManagementType.BEAN)
public class ClienteSingleton {
    @Resource(mappedName = "jms/queue")
    private ConnectionFactory miConnectionFactory;
    @Resource(mappedName = "jms/dest")
    private Queue dest;
    
    @Resource
    SessionContext ejbContext;
    
    @Resource
    UserTransaction ut;
    
    @EJB
    EJBDePruebaTransac myEJB;
    
    @EJB
    MiTimer miTimer;
    
    @EJB
    MiSingleton miSingleton;
    @EJB
    MiSingleton miSingleton2;
    
    @EJB
    YLocal yLocalBean;
    
    @PostConstruct
    public void llamarEJB() {
        
        //myEJB.metodoAsincrono();
        //myEJB.metodoBusiness();
        //miTimer.imprimeFecha2();
        //yLocalBean.suma(2, 2);
        
        
        
        //UserTransaction ut = ejbContext.getUserTransaction();
        try {
            ut.begin();
            sendJMSMessageToDest("Mensaje para Message bean");
            myEJB.metodoBusiness();
            ut.commit();
           
            
        } catch (Exception ex) {
            Logger.getLogger(ClienteSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*Proceso proceso = new Proceso();
        proceso.start();
        miSingleton.procesoLargo();
                */
        
        /*
        System.out.println("====Probando pregunta 20 del cheat-test=====");
        System.out.println("Es igual? "+miSingleton.equals(miSingleton2));
        miSingleton.entero = 99;
        System.out.println("miSingleton.entero = "+miSingleton.entero);
        System.out.println("miSingleton2.entero = "+miSingleton2.entero);
        System.out.println("====FIN DE Probando pregunta 20 del cheat-test=====");
        */
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
            connection = miConnectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(dest);
            
            for(int i=0; i<10; i++){
                messageProducer.send(createJMSMessageForjmsDest(session, messageData));
            }
            
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