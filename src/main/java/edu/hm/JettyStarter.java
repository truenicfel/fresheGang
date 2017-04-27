package edu.hm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;import java.net.SocketAddress;

import org.eclipse.jetty.server.*;
import org.eclipse.jetty.webapp.*;

/**
 * Start the application without an AppServer like tomcat.
 * @author <a mailto:axel.boettcher@hm.edu>Axel B&ouml;ttcher</a>
 *
 */
public class JettyStarter {

    public static final String APP_URL = "/";
    public static final int PORT = 8082;
    public static final String WEBAPP_DIR = "./src/main/webapp/";

    /**
     * Deploy local directories using Jetty without needing a container-based deployment.
     * @param args unused
     * @throws Exception might throw for several reasons.
     */
    public static void main(String... args) throws Exception {
        final Server jetty = new Server(PORT);
        jetty.setHandler(new WebAppContext(WEBAPP_DIR, APP_URL));
        jetty.start();
        System.out.println("Jetty listening on port " + PORT);
        
        final Socket socket = new Socket("localhost", 8082);
        try (final PrintWriter pw = new PrintWriter(socket.getOutputStream());
        		final BufferedReader buffReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
        	
        	pw.print("GET /shareit/media/books HTTP/1.0\r\n");
        	pw.print("Host: localhost\r\n");
        	pw.print("\r\n");
        	pw.flush();
        	
        	buffReader.lines().forEach(System.out::println);
        	
        }
        jetty.join();
    }

}
