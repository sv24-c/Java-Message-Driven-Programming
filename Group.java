package chatgroup;

import com.github.brainlag.nsq.NSQProducer;
import com.github.brainlag.nsq.exceptions.NSQException;
import nsq.Nsq;

import java.util.concurrent.TimeoutException;

/**
 * Created by smit on 11/2/22.
 */
public class Group {

    String groupname;
    NSQProducer producer = new NSQProducer();

    Group(String groupname) throws NSQException, TimeoutException {
        this.groupname = groupname;
        producer.addAddress("localhost", 4150);
        producer.start();
        System.out.println(groupname);
        producer.produce(groupname,"Test Message".getBytes());
    }
}
