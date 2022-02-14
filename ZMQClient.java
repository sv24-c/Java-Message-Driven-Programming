package jeromq;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * Created by smit on 14/2/22.
 */
public class ZMQClient {

    public static void main(String[] args) {

        try(ZContext context = new ZContext())
        {
            System.out.println("Connecting to server");

            ZMQ.Socket socket = context.createSocket(SocketType.REQ);
            socket.connect("tcp://localhost:5555");

            for (int requestNumber = 0; requestNumber!=10;requestNumber++)
            {
                String request = "Hiii";

                System.out.println("Sending hiii: "+requestNumber);
                socket.send(request.getBytes(ZMQ.CHARSET),0);

                byte[] reply = socket.recv(0);
                System.out.println("Received "+new String(reply, ZMQ.CHARSET)+" "+requestNumber);
            }
        }
    }
}
