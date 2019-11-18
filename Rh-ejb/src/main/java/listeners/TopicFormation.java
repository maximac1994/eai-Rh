/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import Buisiness.GestionFormateursLocal;
import MessagesTypes.EvenementFormationAnnulation;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 *
 * @author Maxime
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "TOPIC_FORMATION")
    ,
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "TOPIC_FORMATION")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "TOPIC_FORMATION")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class TopicFormation implements MessageListener {
    @EJB
    GestionFormateursLocal gfl;
    public TopicFormation() {
    }
    
    @Override
    public void onMessage(Message message) {
        ObjectMessage om = (ObjectMessage)message;
        String jmsType = "jmsType";
        try {
            jmsType = message.getJMSType();
        } catch (JMSException ex) {
            Logger.getLogger(TopicFormation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if("annulation".equals(jmsType)){
            try {
                EvenementFormationAnnulation efa = (EvenementFormationAnnulation)om.getObject();
                gfl.changeState(efa,"ann");
            } catch (JMSException ex) {
                Logger.getLogger(TopicFormation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if("validation".equals(jmsType)){
        }
        if("projet2".equals(jmsType)){
        }
            
        
    }
    
}
