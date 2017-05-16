/*
 * Autoren:     N.Dassler, P.Konopac
 * E-Mail:      dassler@hm.edu, konopac@hm.edu
 * Team:        schnitzel
 * Vorlesung:   Software Architektur
 * Dozent:      A.Boettcher
 */
package edu.hm.cs.schnitzel.media.dataExchange;

import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import edu.hm.cs.schnitzel.media.entities.Book;
import edu.hm.cs.schnitzel.media.entities.Disc;
import edu.hm.cs.schnitzel.media.entities.Resource;

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
    /**
     * Constant for the node "Code" in the json-String.
     */
    private static final String NODE_CODE = "Code";
    /**
     * Constant for the node "Message" in the json-String.
     */
    private static final String NODE_MESSAGE = "Message";
    /**
     * Constant for the node "Resources" in the json-String.
     */
    private static final String NODE_RESOURCES = "Resources";
    /**
     * Constant for the node "Books" in the json-String.
     */
    private static final String NODE_BOOKS = "Books";
    /**
     * Constant for the node "Discs" in the json-String.
     */
    private static final String NODE_DISCS = "Discs";
    /**
     * Constant for the node "Book" in the json-String.
     */
    private static final String NODE_BOOK = "Book";
    /**
     * Constant for the node "Disc" in the json-String.
     */
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
     * ensure correct/useful responses. Note that this will throw an illegal
     * argument exception if one of the params is null.
     *
     * @param codeInput A code similar to http status codes providing rough
     * information on wether everything went right or not.
     * @param messageInput A specific error message to track down mistakes that
     * were made or just "OK" if everything went right.
     * @param resourcesInput The resources which should be appended; Resources
     * must be able to be parsed to a json String using jackson.
     */
    public MediaResult(final int codeInput, final String messageInput,
            final List<Resource> resourcesInput) {
        if (messageInput == null || resourcesInput == null) {
            throw new IllegalArgumentException("Null as argument "
                    + "is not allowed here!");
        }
        this.code = codeInput;
        this.message = messageInput;
        this.resources = resourcesInput;
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
     */
    private void createJSONForResources(final JSONObject root) {
        //create books object
        final JSONArray booksNode = new JSONArray();
        //create discs
        final JSONArray discsNode = new JSONArray();
        //add each book/disc
        getResources().forEach((resource) -> {
            //decide wether input is book, disc or unknown
            if (resource.getClass() == Book.class) {
                //book: add node + parsed object
                booksNode.put(new JSONObject(resource));
            } else if (resource.getClass() == Disc.class) {
                //disc: add node + parsed object
                discsNode.put(new JSONObject(resource));
            } else {
                //print error message
                System.out.println("A class was used that is not yet"
                        + "implemented in this Result generator."
                        + "Element will be skipped.");
            }
        });
        //everything went alright so far
        //add the two created json object to the given root object
        root.put(NODE_BOOKS, booksNode);
        root.put(NODE_DISCS, discsNode);
    }

    //Methods Public
    //--------------------------------------------------------------------------
    @Override
    public final String getJsonString() {
        //root object for json creation
        final JSONObject root = new JSONObject();
        //create resources object
        final JSONObject resourcesNode = new JSONObject();
        //call method to fill resources node check for success
        createJSONForResources(resourcesNode);
        //add status code
        root.put(NODE_CODE, getCode());
        //add status message
        root.put(NODE_MESSAGE, getMessage());
        //add resources node
        root.put(NODE_RESOURCES, resourcesNode);
        return root.toString();
    }

    //Getter + Setter (also Private)
    //--------------------------------------------------------------------------
    /**
     * Getter for code field.
     *
     * @return int representation of code.
     */
    private int getCode() {
        return code;
    }

    /**
     * Getter for message field.
     *
     * @return String representation of Message.
     */
    private String getMessage() {
        return message;
    }

    /**
     * Getter for resources field.
     *
     * @return List of resources.
     */
    private List<Resource> getResources() {
        return resources;
    }

}
