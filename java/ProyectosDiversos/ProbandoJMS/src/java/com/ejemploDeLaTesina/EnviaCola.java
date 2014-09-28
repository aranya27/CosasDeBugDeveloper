package com.ejemploDeLaTesina;

import javax.jms.*;
import javax.naming.*;

public class EnviaCola{
    public static void main (String args[]){
        InitialContext contextoInicial = null;
        QueueSession sesion = null;
        try {
            // Conseguimos de la JNDI los objetos administrados
            contextoInicial = new InitialContext();
            QueueConnectionFactory factory =
            (QueueConnectionFactory)contextoInicial.lookup("QueueConnectionFactory");
            Queue cola = (Queue)contextoInicial.lookup("Cola");
            // Creamos la conexion y la sesion
            QueueConnection conexion = factory.createQueueConnection();
            sesion = conexion.createQueueSession(false,sesion.AUTO_ACKNOWLEDGE);
            // Creamos una sesion de envio
            QueueSender enviaACola = sesion.createSender(cola);
            // Creamos un mensaje
            TextMessage mensaje = sesion.createTextMessage();
            mensaje.setText("Esto es un mensaje");
            // Lo enviamos
            enviaACola.send(mensaje);
            System.out.println("Mensaje enviado: " + mensaje.getText());
            // Cerramos la conexion
            conexion.close();
        } catch (NamingException e){
                e.printStackTrace();
        } catch (JMSException e) {
                e.printStackTrace();
        }
    }
}
