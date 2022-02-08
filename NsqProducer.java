package nsq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.brainlag.nsq.NSQConfig;
import com.github.brainlag.nsq.NSQConsumer;
import com.github.brainlag.nsq.NSQProducer;
import com.github.brainlag.nsq.exceptions.NSQException;
import com.github.brainlag.nsq.lookup.DefaultNSQLookup;
import com.github.brainlag.nsq.lookup.NSQLookup;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslProvider;
import org.apache.logging.log4j.LogManager;
import org.junit.Test;

import javax.net.ssl.SSLException;
import java.io.File;
import java.util.Date;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

/**
 * Created by smit on 7/2/22.
 */
public class NsqProducer {


    private NSQConfig getDeflateConfig() {
        final NSQConfig config = new NSQConfig();
        config.setCompression(NSQConfig.Compression.DEFLATE);
        config.setDeflateLevel(4);
        return config;
    }

    private NSQConfig getSslConfig() throws SSLException {
        final NSQConfig config = new NSQConfig();
        File serverKeyFile = new File(getClass().getResource("/server.pem").getFile());
        File clientKeyFile = new File(getClass().getResource("/client.key").getFile());
        File clientCertFile = new File(getClass().getResource("/client.pem").getFile());
        SslContext ctx = SslContextBuilder.forClient().sslProvider(SslProvider.OPENSSL).trustManager(serverKeyFile)
                .keyManager(clientCertFile, clientKeyFile).build();
        config.setSslContext(ctx);
        return config;
    }

    private NSQConfig getSslAndSnappyConfig() throws SSLException {
        final NSQConfig config = getSslConfig();
        config.setCompression(NSQConfig.Compression.SNAPPY);
        return config;
    }

    private NSQConfig getSslAndDeflateConfig() throws SSLException {
        final NSQConfig config = getSslConfig();
        config.setCompression(NSQConfig.Compression.DEFLATE);
        config.setDeflateLevel(4);
        return config;
    }


    @Test
    public void testProduceOneMsgSnappy() throws NSQException, TimeoutException, InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        ObjectMapper mapper = new ObjectMapper();
        NSQLookup lookup = new DefaultNSQLookup(mapper);
        lookup.addLookupAddress(Nsq.getNsqLookupdHost(), 4161);

        NSQProducer producer = new NSQProducer();
        producer.setConfig(getSnappyConfig());
        producer.addAddress(Nsq.getNsqdHost(), 4150);
        producer.start();
        String msg = randomString();
        producer.produce("test3", msg.getBytes());
        producer.shutdown();

        NSQConsumer consumer = new NSQConsumer(lookup, "test3", "testconsumer", (message) -> {
            LogManager.getLogger(this).info("Processing message: " + new String(message.getMessage()));
            counter.incrementAndGet();
            message.finished();
        }, getSnappyConfig());
        
        consumer.start();
        while (counter.get() == 0) {
            Thread.sleep(500);
        }
        assertEquals(1, counter.get());
        consumer.shutdown();
    }

    private String randomString() {
        return "Message" + new Date().getTime();

    }

    public NSQConfig getSnappyConfig() {
        NSQConfig snappyConfig = null;
        return snappyConfig;
    }
}
