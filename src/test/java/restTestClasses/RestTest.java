/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restTestClasses;

import edu.hm.JettyStarter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nicfel
 */
public class RestTest {

    private JettyStarter jettyStarter;

    public RestTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        jettyStarter = new JettyStarter();
        jettyStarter.start();
    }

    @After
    public void tearDown() throws Exception {
        jettyStarter.stop();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void hello() throws IOException {
        //specify expected
        final String expected = "";
        String got;
        try (final Socket socket = new Socket("localhost", 8082);
                final PrintWriter printWriter
                = new PrintWriter(socket.getOutputStream());
                final BufferedReader buffReader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()))) {
            sendHttpHeader(printWriter);
            //place books + discs as json here
            final String content = "";
            sendContent(printWriter, content);
            readUntilBody(buffReader);
            //read content
            got = buffReader.lines().collect(Collectors.joining());
        }
        //assert equals
        assertEquals(expected, got);
    }

    //Private Methods
    private void sendHttpHeader(PrintWriter writer) {
        writer.print("GET /shareit/media/books HTTP/1.0\r\n");
        writer.print("Host: localhost\r\n");
        writer.print("\r\n");
        writer.flush();
    }

    private void readUntilBody(BufferedReader buffReader) throws IOException {
        String line = buffReader.readLine();
        while (line.startsWith("\r\n")) {
            line = buffReader.readLine();
        }
    }

    private void sendContent(PrintWriter printWriter, String content) {
        printWriter.write(content);
        printWriter.flush();
    }

}
