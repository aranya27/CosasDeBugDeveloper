    package com.masejemplos;

    import javax.jms.*;
    import javax.naming.*;

    public class RecibeCola{
        public static void main (String args[]){
            InitialContext contextoInicial = null;
            QueueSession sesion = null;
            try {
                // Conseguimos de la JNDI los objetos administrados
                contextoInicial = new InitialContext();
                QueueConnectionFactory factory = (QueueConnectionFactory)contextoInicial.lookup("QueueConnectionFactory");
                Queue cola = (Queue)contextoInicial.lookup("Cola");
                // Creamos la conexion y la sesion
                QueueConnection conexion = factory.createQueueConnection();
                sesion = conexion.createQueueSession(false,sesion.AUTO_ACKNOWLEDGE);
                // Creamos una sesion de recepcion
                QueueReceiver recibeDeCola = sesion.createReceiver(cola);
                // Iniciamos la recepcion de mensajes
                conexion.start();
                while (true) {
                    // Tomamos un mensaje de la cola, el 1 indica que esperara 1 milisegundo
                    Message mensaje= recibeDeCola.receive(1);
                    if (mensaje != null && mensaje instanceof TextMessage){
                        // Lo mostramos
                        TextMessage m = (TextMessage)mensaje;
                        System.out.println("Recibido mensaje:  " + m.getText());
                    } else { break; } // No hay mensajes
                }
                // Cerramos la conexion
                conexion.close();
            } 
            catch (NamingException e){ e.printStackTrace(); } 
            catch (JMSException e) { e.printStackTrace();}
        }
    }






