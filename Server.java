package SSL;

import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;

/**
 * Created by smit on 8/2/22.
 */
public class Server {

    public static void main(String[] args) throws IOException {

        SSLServerSocketFactory sssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        ServerSocket serverSocket =  sssf.createServerSocket(1234);

        while (true)
        {
            SSLSocket s = (SSLSocket) serverSocket.accept();
            SSLParameters sslp = s.getSSLParameters();
            s.setSSLParameters(sslp);

            BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String str = null;

            PrintStream out = new PrintStream(s.getOutputStream());

            while (((str = reader.readLine()) !=null))
            {
                System.out.println(str);
                out.println("Hii Client");
            }
            reader.close();
            out.close();s.close();
        }
    }
}
