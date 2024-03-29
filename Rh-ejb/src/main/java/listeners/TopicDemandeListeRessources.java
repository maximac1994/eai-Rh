/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import Buisiness.GestionFormateursLocal;
import MessagesTypes.DemandeRessources;
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
 * Topic demande liste resource
 * @author Maxime
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "TOPIC_DEMANDE_LISTE_RESSOURCES")
    ,
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "TOPIC_DEMANDE_LISTE_RESSOURCES")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "TOPIC_DEMANDE_LISTE_RESSOURCES")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class TopicDemandeListeRessources implements MessageListener {
    @EJB
    GestionFormateursLocal gfl;
    
    /**
     *
     */
    public TopicDemandeListeRessources() {
    }
    
    /**
     * à chaque reception de message : envoi de la liste des formateurs correspondant aux criteres de la demande
     * @param message
     */
    @Override
    public void onMessage(Message message) {
        ObjectMessage om = (ObjectMessage)message;
        try {
            DemandeRessources dr = (DemandeRessources)om.getObject();
            Logger.getLogger(TopicDemandeListeRessources.class.getName()).log(Level.INFO, "[APPLI RH] TopicDemandeListeRessourcesListener - onMessage() : " + dr.toString());
            gfl.sendListFormateurs(dr);
        } catch (JMSException ex) {
            Logger.getLogger(TopicDemandeListeRessources.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
