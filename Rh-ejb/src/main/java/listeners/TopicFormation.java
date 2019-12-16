/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import Buisiness.GestionFormateursLocal;
import MessagesTypes.EvenementFormationAnnulation;
import MessagesTypes.EvenementFormationProjet2;
import MessagesTypes.EvenementFormationValidation;
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
 * topic formation
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

    /**
     *
     */
    public TopicFormation() {
    }
    
    /**
     * à chaque message, change l'etat des formtateurs selon l'evenement (ex : pressenti)
     * @param message
     */
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
                gfl.removeState(efa);
            } catch (JMSException ex) {
                Logger.getLogger(TopicFormation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if("validation".equals(jmsType)){
            EvenementFormationValidation efa;
            try {
                efa = (EvenementFormationValidation)om.getObject();
                gfl.changeState(efa, "pressenti");
            } catch (JMSException ex) {
                Logger.getLogger(TopicFormation.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        if("projet2".equals(jmsType)){
            EvenementFormationProjet2 efa;
            try {
                efa = (EvenementFormationProjet2)om.getObject();
                gfl.changeState(efa, "projet");
            } catch (JMSException ex) {
                Logger.getLogger(TopicFormation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
        
    }
    
}
