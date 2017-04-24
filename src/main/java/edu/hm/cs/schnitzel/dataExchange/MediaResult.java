/*
 * Autoren:     N.Dassler, P.Konopac
 * E-Mail:      dassler@hm.edu, konopac@hm.edu
 * Team:        schnitzel
 * Vorlesung:   Software Architektur
 * Dozent:      A.Boettcher
 */
package edu.hm.cs.schnitzel.dataExchange;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hm.cs.schnitzel.entities.Book;
import edu.hm.cs.schnitzel.entities.Disc;
import edu.hm.cs.schnitzel.entities.Resource;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 * A class that will produce a json String which will be sent to the requesting
 * machine. The resulting String consist of three elements:<br>
 * <br>
 * 1. A status code similar to the http status code.<br>
 * 2. A status message similar to the http status message indicating what<br>
 * has gone wrong.<br>
 * 3. A list of resources which were requested. This list will be divided <br>
 * into a list of books and a list of discs.
 * <br>
 *
 * @author N. Dassler
 */
public class MediaResult implements Result {

    //Constants
    //--------------------------------------------------------------------------
    private static final String NODE_CODE = "Code";
    private static final String NODE_MESSAGE = "Message";
    private static final String NODE_RESOURCES = "Resources";
    private static final String NODE_BOOKS = "Books";
    private static final String NODE_DISCS = "Discs";
    private static final String NODE_BOOK = "Book";
    private static final String NODE_DISC = "Disc";

    //Object Variables
    //--------------------------------------------------------------------------
    /**
     * A status code similar to http status codes.
     */
    private final int code;
    /**
     * A status message explaining the error that ocurred.
     */
    private final String message;
    /**
     * A list containing all the resources which should be added to the json
     * String.
     */
    private final List<Resource> resources;

    //Constructors
    //--------------------------------------------------------------------------
    /**
     * A standard constructor. Initialize the class with all necessary params to
     * ensure correct/useful responses.
     *
     * @param code A code similar to http status codes providing rough
     * information on wether everything went right or not.
     * @param message A specific error message to track down mistakes that were
     * made or just "OK" if everything went right.
     * @param resources The resources which should be appended; Resources must
     * be able to be parsed to a json String using jackson.
     */
    public MediaResult(int code, String message, List<Resource> resources) {
        this.code = code;
        this.message = message;
        this.resources = resources;

    }

    //Methods Private
    //--------------------------------------------------------------------------
    /**
     * Create json-String from resources.
     *
     * All the resources given in the resources list will be added to a given
     * root JSONObject. The elements will be split up into Books and Discs.
     *
     * @param root The JSONObject which will be filled.
     * @return true if successful else if not.
     */
    private boolean createJSONForResources(JSONObject root) {
        //success variable
        boolean success = true;
        //the mapper to map: Java Object -> json String
        final ObjectMapper mapper = new ObjectMapper();
        //create books object
        final JSONObject booksNode = new JSONObject();
        //create discs 
        final JSONObject discsNode = new JSONObject();
        try {
            //add each book/disc
            for (Resource resource : getResources()) {
                //decide wether input is book, disc or unknown
                if (resource.getClass() == Book.class) {
                    //book: add node + parsed object
                    booksNode.put(NODE_BOOK, mapper.writeValueAsString(resource));
                } else if (resource.getClass() == Disc.class) {
                    //disc: add node + parsed object
                    discsNode.put(NODE_DISC, mapper.writeValueAsString(resource));
                } else {
                    //print error message
                    System.out.println("A class was used that is not yet"
                            + "implemented in this Result generator."
                            + "Element will be skipped.");
                }
            }
        } catch (JsonProcessingException exception) {
            //set success to false
            success = false;
            //print error message on console
            System.out.println(exception);
        }
        //everything went alright so far
        //add the two created json object to the given root object
        root.put(NODE_BOOKS, booksNode);
        root.put(NODE_DISCS, discsNode);
        //return true if successfull
        return success;

    }

    //Methods Public
    //--------------------------------------------------------------------------
    @Override
    public String getJsonString() {
        //root object for json creation
        final JSONObject root = new JSONObject();
        //create resources object
        final JSONObject resourcesNode = new JSONObject();
        //call method to fill resources node check for success
        if (!createJSONForResources(resourcesNode)) {
            //unsuccessful :(
            //change the given
            root.put(NODE_CODE, 500);
            //add status message
            root.put(NODE_MESSAGE, "A server error occured while processing"
                    + "your request."
                    + "Infos fuer Chefinformatiker: Beim parsen des von Objekten"
                    + "zu JSON-Strings ist ein Fehler aufgetreten.");
            //add empty resources node
            root.put(NODE_RESOURCES, new JSONObject());

        } else {
            //successful :)
            //add status code
            root.put(NODE_CODE, getCode());
            //add status message
            root.put(NODE_MESSAGE, getMessage());
            //add resources node
            root.put(NODE_RESOURCES, resourcesNode);
        }
        

        return root.toString();
    }

    //Getter + Setter (also Private)
    //--------------------------------------------------------------------------
    private int getCode() {
        return code;
    }

    private String getMessage() {
        return message;
    }

    private List<Resource> getResources() {
        return resources;
    }

}
