//http://nnbharadwaj.wordpress.com/2012/10/02/simple-jms-example/ 

//Probar despues esto https://www.packtpub.com/books/content/setting-glassfish-jms-and-working-message-queues

package com.ejemplo1;

import javax.jms.*;
import javax.naming.*;
import java.io.*;

public class Publisher {
 private TopicConnectionFactory tCF;
 private TopicConnection tCon;
 private TopicSession pubSession;
 private String topicName = "jms/MyTopic";
 private Topic top;
 private TopicPublisher tPub;

// Constructor to initialise the required entities
public Publisher(String uname, String pwd) 
       throws NamingException,JMSException{

       InitialContext ctx = new InitialContext();

       // Step1: Lookup the Connection Factory and the Topic
       tCF = (TopicConnectionFactory)
                        ctx.lookup("jms/ConnectionFactory");
       top = (Topic)ctx.lookup(topicName);

       // Step2: Create a connection using the Factory
       tCon = tCF.createTopicConnection(uname,pwd);

       // Step3: Create Topic Sessions using the connection
       pubSession = tCon.createTopicSession
                         (false,Session.AUTO_ACKNOWLEDGE);

       // Step4: Create TopicPublisher
       tPub = pubSession.createPublisher(top);

       tCon.start();
}   //  End of Constructor
 // Method to close the connection to Topic
 public void close() throws JMSException{
     tCon.stop();
 }

 // Method to publish the message to the Topic
 public void writeMsg(String msg) throws JMSException {
       //  Creating a Text Message with the String object
       TextMessage txtMsg = pubSession.createTextMessage(msg);

       //  Publishing the message object to the Topic
       tPub.publish(txtMsg);
 }

 public static void main(String[] args) 
              throws NamingException,IOException,JMSException {
      String msg, uname = null, pwd = null;
      Publisher pub;

      /*
       *   Extracting the credentials for establishing a 
       *   connection to the topic through command line arguments
       */
      uname = "holax";
      pwd = "mundox";
      /*if(args.length == 2) {
           uname = args[0];
           pwd = args[1];
      } else {
           System.out.println("Notgh parameters");
           System.exit(0);
      }*/

      //  Calling the constructor with the credentials
      pub = new Publisher(uname,pwd);

      //  Declaring a Reader for reading the message from user
      BufferedReader br = new BufferedReader
                         (new InputStreamReader(System.in));

      //  Read input to be published till the users enters 'exit' 
      while(true) {
           msg = br.readLine();
           if(msg.equalsIgnoreCase("exit")) {
                 pub.writeMsg(msg);
                 pub.close();
                 System.exit(0);
           } else {
                 pub.writeMsg(msg);
           }
      }  //  End of while loop

   }  //  End of main()

}