package ru.fella.jms;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;


@Slf4j
@Data
@Stateless
public class JMSService {

    @Resource(lookup = "java:jboss/MQ.CONNECTIONFACTORY.NAME")
    ConnectionFactory connectionFactory;

    @Resource(lookup = "java:jboss/ELLA.TASK")
    Queue queue;

    public void send(String message) {

        try (JMSContext context = connectionFactory.createContext()) {
            context.createProducer().send(queue, message);
            log.info("Send message:" + System.lineSeparator() + message + System.lineSeparator() + "Queue:" + queue.getQueueName());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
