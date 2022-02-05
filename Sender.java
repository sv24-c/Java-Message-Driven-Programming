package PointtoPointMessage;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.jms.Queue;
import java.util.Hashtable;

/**
 * Created by smit on 5/2/22.
 */
public class Sender {

    public static void main(String[] args) throws NamingException, JMSException {

        //InitialContext context = new InitialContext();


            Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY,
                    "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, "ldap://ldap.wiz.com:389");
            env.put(Context.SECURITY_PRINCIPAL, "joeuser");
            env.put(Context.SECURITY_CREDENTIALS, "joepassword");

            System.getProperty(Context.PROVIDER_URL);

        Context context = new InitialContext(env);

        Queue queue = (Queue) context.lookup("queue/queue0");

        QueueConnectionFactory queueConnnectionFactory = (QueueConnectionFactory) context.lookup("queue/connectionFactory");

        QueueConnection queueConnection = queueConnnectionFactory.createQueueConnection();

        QueueSession queueSession = queueConnection.createQueueSession(false, Session.DUPS_OK_ACKNOWLEDGE);

        QueueSender queueSender = queueSession.createSender(queue);

        queueSender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        TextMessage message = queueSession.createTextMessage("Hello Guys");

        queueSender.send(message);

        System.out.println("Message is sent: "+ message.getText());

        queueConnection.close();

    }
}
