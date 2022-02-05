package PointtoPointMessage;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Created by smit on 5/2/22.
 */
public class Receiver {

    public static void main(String[] args) throws NamingException, JMSException {

        InitialContext context = new InitialContext();

        Queue queue = (Queue) context.lookup("queue/queue0");

        QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) context.lookup("queue/connectionFactory");

        QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();

        QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

        QueueReceiver queueReceiver = queueSession.createReceiver(queue);

        queueConnection.start();

        TextMessage message = (TextMessage) queueReceiver.receive();

        System.out.println("Message Received: "+ message.getText());

        queueConnection.close();


    }
}
