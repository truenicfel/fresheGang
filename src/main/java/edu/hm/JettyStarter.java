package edu.hm;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;



/**
 * Start the application without an AppServer like tomcat.
 *
 * @author <a> mailto:axel.boettcher@hm.edu>Axel B&ouml;ttcher</a>
 *
 */
public class JettyStarter {

    public static final String APP_URL = "/";
    public static final int PORT = 8082;
    public static final String WEBAPP_DIR = "./src/main/webapp/";
    private final Server jetty;

    /**
     * Initializes this jetty Starter.
     */
    public JettyStarter() {
        jetty = new Server(PORT);
        jetty.setHandler(new WebAppContext(WEBAPP_DIR, APP_URL));
        System.out.println("Jetty listening on port " + PORT);

    }

    /**
     * Start jetty server.
     *
     * @throws java.lang.Exception From jetty.
     */
    public final void start() throws Exception {
        getJetty().start();
    }

    /**
     * Stop jetty server.
     *
     * @throws Exception
     */
    public final void stop() throws Exception {
        getJetty().stop();
    }

    /**
     * Private getter for jetty Server object variable.
     * @return The server.
     */
    private Server getJetty() {
        return jetty;
    }

}
