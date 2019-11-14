/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import MessagesTypes.EvenementFormation;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


/**
 *
 * @author Maxime
 */
public class ListenerJms {
    
    
        public static void main(String[] args) {
            System.setProperty("java.naming.factory.initial",	
            "com.sun.enterprise.naming.SerialInitContextFactory");
            System.setProperty("org.omg.CORBA.ORBInitialHost",	"localhost");
            System.setProperty("org.omg.CORBA.ORBInitialPort",	"3700");
        Context context = null;
        ConnectionFactory factory = null;
        Connection connection = null;
        String factoryName = "jms/__defaultConnectionFactory";
        String destName = null;
        Destination dest = null;
        int count = 1;
        Session session = null;
        MessageConsumer receiver = null;

       

        destName = "TOPIC_FORMATION";

        try {
            // create the JNDI initial context
            context = new InitialContext();

            // look up the ConnectionFactory
            factory = (ConnectionFactory) context.lookup(factoryName);

            // look up the Destination
            dest = (Destination) context.lookup(destName);

            // create the connection
            connection = factory.createConnection();

            // create the session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // create the receiver
            receiver = session.createConsumer(dest);

            // start the connection, to enable message receipt
            connection.start();
            boolean run = true;
            while (run) {
                Message message = receiver.receive();
                if (message instanceof ObjectMessage) {
                    if("annulation".equals(message.getJMSType())){
                        EvenementFormation ev = (EvenementFormation)((ObjectMessage) message).getObject();
                        // remove dates salle / formateur
                    } else
                    if("validation".equals(message.getJMSType())){
                        EvenementFormation ev = (EvenementFormation)((ObjectMessage) message).getObject();
                        // set etat occupe salle / formateur
                    } else
                    if("projet2".equals(message.getJMSType())){
                        EvenementFormation ev = (EvenementFormation)((ObjectMessage) message).getObject();
                        // set etat pressenti salle / formateur
                    }
                }
            }
        } catch (JMSException exception) {
            exception.printStackTrace();
        } catch (NamingException exception) {
            exception.printStackTrace();
        } finally {
            // close the contexts
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
    }
}
