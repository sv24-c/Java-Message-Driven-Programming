package PublisherSubcriber;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Created by smit on 5/2/22.
 */
public class Subscriber {

    public static void main(String[] args) throws NamingException, JMSException {

        InitialContext context = new InitialContext();

        Topic topic = (Topic) context.lookup("topic/topic0");

        TopicConnectionFactory topicConnectionFactory = (TopicConnectionFactory) context.lookup("topic/connectionFactory");

        TopicConnection topicConnection = topicConnectionFactory.createTopicConnection();

        TopicSession topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

        TopicSubscriber topicSubscriber = topicSession.createSubscriber(topic);

        topicConnection.start();

        TextMessage message = (TextMessage) topicSubscriber.receive();

        System.out.println("Message received : "+message.getText());

        topicConnection.close();
    }
}
