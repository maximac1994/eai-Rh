/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package senders;

import MessagesTypes.ListeFormateursCompatibles;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Maxime
 */
public class FileListeRessources {
    Context context = null;
    ConnectionFactory factory = null;
    Connection connection = null;
    String factoryName = null;
    String destName = null;
    Destination dest = null;
    Session session = null;
    MessageProducer sender = null;
    boolean connected = false;
    
    public void connect(){
        
        factoryName = "jms/__defaultConnectionFactory";
        destName = "FILE_LISTE_RESSOURCES";
        try{
            context = new InitialContext();

            // look up the ConnectionFactory
            factory = (ConnectionFactory) context.lookup(factoryName);

            // look up the Destination
            dest = (Destination) context.lookup(destName);
           
        }catch (NamingException exception) {
            exception.printStackTrace();
        }
    }
        
    
    public void sendListeResources(ListeFormateursCompatibles liste){
       if(!connected){
       connect();
       connected = true;
       }
        try {
            connection = factory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            sender = session.createProducer(dest);
            connection.start();
        } catch (JMSException ex) {
            Logger.getLogger(FileListeRessources.class.getName()).log(Level.SEVERE, null, ex);
        }
           
       try {
            ObjectMessage om = session.createObjectMessage(liste);
            sender.send(om);
        } catch (JMSException exception) {
            exception.printStackTrace();
        }
       finally {
            // close the context
            if (context != null) {
                try {
                    context.close();
                } catch (NamingException exception) {
                    exception.printStackTrace();
                }
            }

            // close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException exception) {
                    exception.printStackTrace();
                }
            }
        }
        try {
            session.close();
        } catch (JMSException ex) {
            Logger.getLogger(FileListeRessources.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
