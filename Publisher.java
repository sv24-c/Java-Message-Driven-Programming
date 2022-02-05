package PublisherSubcriber;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Created by smit on 5/2/22.
 */
public class Publisher {

    public static void main(String[] args) throws NamingException, JMSException {

        InitialContext context = new InitialContext();

        Topic topic = (Topic) context.lookup("topic/topic0");

        TopicConnectionFactory connectionFactory = (TopicConnectionFactory) context.lookup("topic/connectioFactory");

        TopicConnection topicConnection = connectionFactory.createTopicConnection();
        TopicSession topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        TopicPublisher  topicPublisher = topicSession.createPublisher(topic);

        topicPublisher.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        TextMessage message = topicSession.createTextMessage();

        message.setText("Message published: "+message.getText());

        topicConnection.close();
    }
}
