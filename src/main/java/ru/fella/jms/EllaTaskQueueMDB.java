package ru.fella.jms;

import lombok.extern.slf4j.Slf4j;
import org.jboss.annotation.ejb.ResourceAdapter;

import javax.ejb.*;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;


@MessageDriven( activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType",propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "useJNDI", propertyValue = "false"),
        @ActivationConfigProperty(propertyName = "hostName", propertyValue = "devedu-01.vm.cmx.ru"),
        @ActivationConfigProperty(propertyName = "port", propertyValue = "1414"),
        @ActivationConfigProperty(propertyName = "channel", propertyValue = "SYSTEM.DEF.SVRCONN"),
        @ActivationConfigProperty(propertyName = "queueManager", propertyValue = "QMTEST"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "ELLA.TASK"),
        @ActivationConfigProperty(propertyName = "transportType", propertyValue = "CLIENT")
})

@ResourceAdapter(value = "wmq.jmsra.rar")
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@Slf4j
public class EllaTaskQueueMDB implements MessageListener {


    @Override
    public void onMessage(Message message) {

        try {
            log.info(System.lineSeparator() + "Received Message : " + System.lineSeparator() + message.getBody(String.class));
        } catch (JMSException e) {
           throw new RuntimeException(e);
        }

    }

}
