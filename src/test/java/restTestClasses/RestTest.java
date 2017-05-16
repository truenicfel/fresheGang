//Checkstyle disabled at this point to prevent jdoc missing problems and various
//other errors which are negligible for test methods
//CHECKSTYLE:OFF
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restTestClasses;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.hm.JettyStarter;
import edu.hm.cs.schnitzel.media.daos.PseudoDatabaseAccessObject;
import edu.hm.cs.schnitzel.media.database.PseudoDatabase;

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
        PseudoDatabaseAccessObject.clear();
    }

    @After
    public void tearDown() throws Exception {
        jettyStarter.stop();
    }

    // Test methods
    @Test
    public void createFilledPseudoDatabase() {
        final PseudoDatabase db = new PseudoDatabase();
        final boolean expected = true;
        final boolean got = !db.getBooks().isEmpty()
                && !db.getDiscs().isEmpty();
        assertEquals(expected, got);
    }

    @Test
    public void emptyDatabaseGetAllBooks() throws IOException {
        // specify expected
        final String expected = "{\"Message\":\"OK. All books loaded!\","
                + "\"Resources\":{\"Discs\":[],\"Books\":[]},\"Code\":200}";
        // send request and receive answer
        final String got = sendAndReceive("GET", "books", "", "");
        // assert equals
        assertEquals(expected, got);
    }

    @Test
    public void emptyDatabaseGetAllDiscs() throws IOException {
        // specify expected
        final String expected = "{\"Message\":\"OK. All discs loaded!\","
                + "\"Resources\":{\"Discs\":[],\"Books\":[]},\"Code\":200}";
        // send request and receive answer
        final String got = sendAndReceive("GET", "discs", "", "");
        // assert equals
        assertEquals(expected, got);
    }

    @Test
    public void emptyDatabaseAddBook() throws IOException {
        // specify expected
        final String expected = "{\"Message\":\"OK. The book with isbn-number "
                + "12345-322 has been created successfully!\",\"Resources\":"
                + "{\"Discs\":[],\"Books\":[]},\"Code\":200}";
        // send request and receive answer
        final String got = sendAndReceive("POST", "books", "", "{\"title\":"
                + "\"The Lord of the Rings 2\",\"author\":\"Tolkien\",\"isbn\":"
                + "\"12345-322\",\"year\":1}");
        // assert equals
        assertEquals(expected, got);
    }

    @Test
    public void emptyDatabaseAddDisc() throws IOException {
        // specify expected
        final String expected = "{\"Message\":\"OK. The disc with barcode "
                + "123-45 has been created successfully!\",\"Resources\":"
                + "{\"Discs\":[],\"Books\":[]},\"Code\":200}";
        // send request and receive answer
        final String got = sendAndReceive("POST", "discs", "", "{\"title\":"
                + "\"Harry Potter\",\"barcode\":\"123-45\",\"year\":2004,\"fsk"
                + "\":12,\"director\":\"Director1\",\"writer\":\"Writer1\"}");
        // assert equals
        assertEquals(expected, got);
    }

    @Test
    public void emptyDatabaseGetBook() throws IOException {
        // specify expected
        final String expected = "{\"Message\":\"OK. The book with isbn-number "
                + "12345-322 could not be found!\",\"Resources\":"
                + "{\"Discs\":[],\"Books\":[]},\"Code\":200}";
        // send request and receive answer
        final String got = sendAndReceive("GET", "books", "12345-322", "");
        // assert equals
        assertEquals(expected, got);
    }

    @Test
    public void filledDatabaseGetBook() throws IOException {
        // specify expected
        final String expected = "{\"Message\":\"OK. The book with isbn-number "
                + "12345-322 has been loaded!\",\"Resources\":{\"Discs\":[],"
                + "\"Books\":[{\"year\":1,\"author\":\"Tolkien\",\"isbn\":"
                + "\"12345-322\",\"title\":\"The Lord of the Rings 2\"}]},"
                + "\"Code\":200}";
        // send request and receive answer
        sendAndReceive("POST", "books", "", "{\"title\":\"The Lord of the "
                + "Rings 2\",\"author\":\"Tolkien\",\"isbn\":\"12345-322\","
                + "\"year\":1}");
        final String got = sendAndReceive("GET", "books", "12345-322", "");
        // assert equals
        assertEquals(expected, got);
    }

    @Test
    public void emptyDatabaseGetDisc() throws IOException {
        // specify expected
        final String expected = "{\"Message\":\"OK. The disc with barcode "
                + "123-45 could not be found!\",\"Resources\":{\"Discs\":[],"
                + "\"Books\":[]},\"Code\":200}";
        // send request and receive answer
        final String got = sendAndReceive("GET", "discs", "123-45", "");
        // assert equals
        assertEquals(expected, got);
    }

    @Test
    public void filledDatabaseGetDisc() throws IOException {
        // specify expected
        final String expected = "{\"Message\":\"OK. The disc with barcode "
                + "123-45 has been loaded!\",\"Resources\":{\"Discs\":[{\"year"
                + "\":2004,\"director\":\"Director1\",\"writer\":\"Writer1\","
                + "\"title\":\"Harry Potter\",\"barcode\":\"123-45\",\""
                + "fsk\":12}],\"Books\":[]},\"Code\":200}";
        // send request and receive answer
        sendAndReceive("POST", "discs", "", "{\"title\":\"Harry Potter\","
                + "\"barcode\":\"123-45\",\"year\":2004,\"fsk\":12,\"director"
                + "\":\"Director1\",\"writer\":\"Writer1\"}");
        final String got = sendAndReceive("GET", "discs", "123-45", "");
        // assert equals
        assertEquals(expected, got);
    }

    @Test
    public void filledDatabaseEditBook() throws IOException {
        // specify expected
        final String expected = "{\"Message\":\"OK. The book with isbn-number "
                + "12345-322 has been updated successfully!\",\"Resources\":"
                + "{\"Discs\":[],\"Books\":[]},\"Code\":200}";
        // send request and receive answer
        sendAndReceive("POST", "books", "", "{\"title\":\"The Lord of the "
                + "Rings 2\",\"author\":\"Tolkien\",\"isbn\":\"12345-322\","
                + "\"year\":1}");
        final String got = sendAndReceive("PUT", "books", "12345-322",
                "{\"year\":2000}");
        // assert equals
        assertEquals(expected, got);
    }

    @Test
    public void filledDatabaseEditBookNoIsbn() throws IOException {
        // specify expected
        final String expected = "{\"Message\":\"Bad Request. The isbn-number "
                + "must not be emty!\",\"Resources\":{\"Discs\":[],\""
                + "Books\":[]},\"Code\":400}";
        // send request and receive answer
        sendAndReceive("POST", "books", "", "{\"title\":\"The Lord of the "
                + "Rings 2\",\"author\":\"Tolkien\",\"isbn\":\"12345-322\","
                + "\"year\":1}");
        final String got = sendAndReceive("PUT", "books", "",
                "{\"year\":2000}");
        // assert equals
        assertEquals(expected, got);
    }

    @Test
    public void filledDatabaseEditDisc() throws IOException {
        // specify expected
        final String expected = "{\"Message\":\"OK. The disc with "
                + "barcode 123-45 has been updated successfully!\","
                + "\"Resources\":{\"Discs\":[],\"Books\":[]},\"Code\":200}";
        // send request and receive answer
        sendAndReceive("POST", "discs", "", "{\"title\":\"Harry Potter\","
                + "\"barcode\":\"123-45\",\"year\":2004,\"fsk\":12,\"director"
                + "\":\"Director1\",\"writer\":\"Writer1\"}");
        final String got = sendAndReceive("PUT", "discs", "123-45",
                "{\"year\":2000}");
        // assert equals
        assertEquals(expected, got);
    }

    @Test
    public void filledDatabaseEditDiscNoBarcode() throws IOException {
        // specify expected
        final String expected = "{\"Message\":\"Bad Request. The barcode must "
                + "not be emty!\",\"Resources\":{\"Discs\":[],\"Books\":[]},"
                + "\"Code\":400}";
        // send request and receive answer
        sendAndReceive("POST", "discs", "", "{\"title\":\"Harry Potter\","
                + "\"barcode\":\"123-45\",\"year\":2004,\"fsk\":12,\"director"
                + "\":\"Director1\",\"writer\":\"Writer1\"}");
        final String got = sendAndReceive("PUT", "discs", "",
                "{\"year\":2000}");
        // assert equals
        assertEquals(expected, got);
    }

    @Test
    public void resourceNotFound() throws IOException {
        // specify expected
        final String expected = "{\"Message\":\"Not found. The requested "
                + "resource does not exist."
                + "Make sure to use the correct URI pattern."
                + "The pattern is as follows:"
                + "/shareit/media/books or discs/{isbn or barcode]\""
                + ",\"Resources\":{\"Discs\":[],\"Books\":[]},\"Code\":404}";
        // send request and receive answer
        final String got = sendAndReceive("GET", "blueray", "123", "hello");
        // assert equals
        assertEquals(expected, got);
    }

    @Test
    public void noISBNFoundWhenAddingBook() throws IOException {
        // specify expected
        final String expected = "{\"Message\":\"Policy Not Fulfilled."
                + " The book and its isbn-number/author/title"
                + " must not be null or empty!\""
                + ",\"Resources\":{\"Discs\":[],\"Books\":[]},"
                + "\"Code\":420}";
        // send request and receive answer
        final String got = sendAndReceive("POST", "books", "",
                "{\"title\":\"The Lord of the Rings 2\",\"author\":"
                + "\"Tolkien\",\"isbn\":\"\",\"year\":1}");
        // assert equals
        assertEquals(expected, got);
    }

    @Test
    public void noBarcodeFoundWhenAddingDisc() throws IOException {
        // specify expected
        final String expected = "{\"Message\":\"Policy Not Fulfilled. "
                + "The disc and its barcode/director/writer/title must not be "
                + "null or empty!\",\"Resources\":{\"Discs\":[],\"Books\":[]},"
                + "\"Code\":420}";
        // send request and receive answer
        final String got = sendAndReceive("POST", "discs", "", "{\"title\":"
                + "\"Harry Potter\",\"year\":2004,\"fsk\":12,\"director\":"
                + "\"Director1\",\"writer\":\"Writer1\"}");
        // assert equals
        assertEquals(expected, got);
    }

    @Test
    public void noAuthorFoundWhenAddingBook() throws IOException {
        // specify expected
        final String expected = "{\"Message\":\"Policy Not Fulfilled."
                + " The book and its isbn-number/author/title"
                + " must not be null or empty!\""
                + ",\"Resources\":{\"Discs\":[],\"Books\":[]},\"Code\":420}";
        // send request and receive answer
        final String got = sendAndReceive("POST", "books", "", "{\"title\":"
                + "\"The Lord of the Rings 2\",\"isbn\":\"\",\"year\":1}");
        // assert equals
        assertEquals(expected, got);
    }

    @Test
    public void noDirectorFoundWhenAddingDisc() throws IOException {
        // specify expected
        final String expected = "{\"Message\":\"Policy Not Fulfilled. "
                + "The disc and its barcode/director/writer/title must "
                + "not be null or empty!\",\"Resources\":{\"Discs\":[],"
                + "\"Books\":[]},\"Code\":420}";
        // send request and receive answer
        final String got = sendAndReceive("POST", "discs", "", "{\"title\":"
                + "\"Harry Potter\",\"barcode\":\"123-45\",\"year\":2004,\"fsk"
                + "\":12,\"writer\":\"Writer1\"}");
        // assert equals
        assertEquals(expected, got);
    }

    @Test
    public void noWriterFoundWhenAddingDisc() throws IOException {
        // specify expected
        final String expected = "{\"Message\":\"Policy Not Fulfilled. The disc"
                + " and its barcode/director/writer/title must not be null or "
                + "empty!\",\"Resources\":{\"Discs\":[],\"Books\":[]},"
                + "\"Code\":420}";
        // send request and receive answer
        final String got = sendAndReceive("POST", "discs", "", "{\"title\":"
                + "\"Harry Potter\",\"barcode\":\"123-45\",\"year\":2004,"
                + "\"fsk\":12,\"director\":\"Director1\"}");
        // assert equals
        assertEquals(expected, got);
    }

    @Test
    public void noTitleFoundWhenAddingBook() throws IOException {
        // specify expected
        final String expected = "{\"Message\":\"Policy Not Fulfilled."
                + " The book and its isbn-number/author/title"
                + " must not be null or empty!\""
                + ",\"Resources\":{\"Discs\":[],\"Books\":[]},\"Code\":420}";
        // send request and receive answer
        final String got = sendAndReceive("POST", "books", "", "{\"isbn\":\"\","
                + "\"year\":1}");
        // assert equals
        assertEquals(expected, got);
    }

    @Test
    public void noTitleFoundWhenAddingDisc() throws IOException {
        // specify expected
        final String expected = "{\"Message\":\"Policy Not Fulfilled. "
                + "The disc and its barcode/director/writer/title must not "
                + "be null or empty!\",\"Resources\":{\"Discs\":[],"
                + "\"Books\":[]},\"Code\":420}";
        // send request and receive answer
        final String got = sendAndReceive("POST", "discs", "", "{\"barcode\":"
                + "\"123-45\",\"year\":2004,\"fsk\":12,\"director\":\"Director1"
                + "\",\"writer\":\"Writer1\"}");
        // assert equals
        assertEquals(expected, got);
    }

    @Test
    public void addingBookWithAlreadyExistingISBN() throws IOException {
        // specify expected
        final String expected = "{\"Message\":\"Internal Server Error. "
                + "There was a problem adding the book!\",\"Resources\":{"
                + "\"Discs\":[],\"Books\":[]},\"Code\":500}";
        sendAndReceive("POST", "books", "", "{\"title\":\"The Lord of the Rings"
                + " 2\",\"author\":\"Tolkien\",\"isbn\":\"123\",\"year\":1}");
        // send request and receive answer
        final String got = sendAndReceive("POST", "books", "",
                "{\"title\":\"The Lord of the Rings 2\",\"author\":\"Tolkien"
                + "\",\"isbn\":\"123\",\"year\":1}");
        // assert equals
        assertEquals(expected, got);
    }

    @Test
    public void addingDiscWithAlreadyExistingBarcode() throws IOException {
        // specify expected
        final String expected = "{\"Message\":\"Internal Server Error. "
                + "There was a problem adding the disc!\",\"Resources\":"
                + "{\"Discs\":[],\"Books\":[]},\"Code\":500}";
        sendAndReceive("POST", "discs", "", "{\"title\":\"Harry Potter\","
                + "\"barcode\":\"123-45\",\"year\":2004,\"fsk\":12,\"director"
                + "\":\"Director1\",\"writer\":\"Writer1\"}");
        // send request and receive answer
        final String got = sendAndReceive("POST", "discs", "", "{\"title\":"
                + "\"Harry Potter\",\"barcode\":\"123-45\",\"year\":2004,\"fsk"
                + "\":12,\"director\":\"Director1\",\"writer\":\"Writer1\"}");
        // assert equals
        assertEquals(expected, got);
    }

    // Private Methods
    private String sendAndReceive(String method, String resource, String isbn,
            String content) throws IOException {
        String result = "";
        try (final Socket socket = new Socket("localhost", 8082);
                final PrintWriter printWriter
                = new PrintWriter(socket.getOutputStream());
                final BufferedReader buffReader
                = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()))) {
            System.out.println();
            System.out.println("SENDING...");
            // send header
            sendHttpHeader(printWriter, method, resource, isbn,
                    content.length());
            // send content
            sendContent(printWriter, content);

            System.out.println();
            System.out.println("READING...");
            // read answer
            readUntilBody(buffReader);
            // read content
            result = buffReader.lines().collect(Collectors.joining());
            System.out.println("\t" + result);
            System.out.println();
        }
        return result;
    }

    private void sendHttpHeader(PrintWriter writer, String method,
            String resource, String isbn, int contentLength) {
        if (isbn.length() > 0) {
            writer.print(method + " /shareit/media/" + resource + "/" + isbn
                    + " HTTP/1.0\r\n");
            System.out.print("\t" + method + " /shareit/media/" + resource + "/"
                    + isbn + " HTTP/1.0\r\n");
        } else {
            writer.print(method + " /shareit/media/" + resource
                    + " HTTP/1.0\r\n");
            System.out.print("\t" + method + " /shareit/media/" + resource
                    + " HTTP/1.0\r\n");
        }
        writer.print("Host: localhost\r\n");
        System.out.print("\t" + "Host: localhost\r\n");
        writer.print("Content-Type: application/json\r\n");
        System.out.print("\t" + "Content-Type: application/json\r\n");
        writer.print("Content-Length: " + contentLength + "\r\n");
        System.out.print("\t" + "Content-Length: " + contentLength + "\r\n");
        writer.print("\r\n");
        System.out.print("\t" + "\r\n");
        writer.flush();
    }

    private void readUntilBody(BufferedReader buffReader) throws IOException {
        System.out.println();
        String line = buffReader.readLine();
        while (line.length() > 0) {
            System.out.println("\t" + line);
            line = buffReader.readLine();
        }
        System.out.println();
    }

    private void sendContent(PrintWriter printWriter, String content) {
        printWriter.write(content);
        System.out.println("\t" + content);
        printWriter.flush();
    }

    //CHECKSTYLE:ON
}
