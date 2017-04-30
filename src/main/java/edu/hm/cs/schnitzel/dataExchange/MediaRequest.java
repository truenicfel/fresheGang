/*
 * Autoren:     N.Dassler, P.Konopac
 * E-Mail:      dassler@hm.edu, konopac@hm.edu
 * Team:        schnitzel
 * Vorlesung:   Software Architektur
 * Dozent:      A.Boettcher
 */
package edu.hm.cs.schnitzel.dataExchange;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hm.cs.schnitzel.entities.Book;
import edu.hm.cs.schnitzel.entities.Disc;
import edu.hm.cs.schnitzel.services.MediaService;
import java.io.IOException;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nicfel
 */
public class MediaRequest implements Request {

    //Constants
    //--------------------------------------------------------------------------
    private static final int INDEX_RESOURCE_TYPE = 3;
    private static final int INDEX_ISBN = 4;
    //Object Variables
    //--------------------------------------------------------------------------
    private final HttpServletRequest request;
    //Constructors
    //--------------------------------------------------------------------------

    /**
     * Standard Constructor which initializes object with request and response.
     *
     * @param requestInput The servlet request object.
     */
    public MediaRequest(final HttpServletRequest requestInput) {
        this.request = requestInput;
    }

    //Methods Private
    //--------------------------------------------------------------------------
    /**
     * A small help method that chooses which action will performed with a book
     * with the Service.
     *
     * @return Returns the result coming from the performed action.
     * @throws IOException Exception must be handled to give the user a status
     * report on what happened.
     */
    private Result delegateBookAction() throws IOException {
        //the result object which will be returned
        final Result result;
        //the underlying service that will actually execute the desired action
        final MediaService mediaService = new MediaService();
        //the jackson mapper to create book objects
        final ObjectMapper mapper = new ObjectMapper();
        final String[] splittedURI = getRequest()
                .getRequestURI().split("/");
        switch (getRequest().getMethod()) {
            case "GET":
                //check if isbn is in url
                if (splittedURI.length == INDEX_ISBN + 1) {
                    //request only for one book
                    result = mediaService.getBook(splittedURI[INDEX_ISBN]);
                } else {
                    //request for all books
                    result = mediaService.getBooks();
                }
                break;
            case "PUT":
                if (splittedURI.length < (INDEX_ISBN + 1)) {
                    result = new MediaResult(HttpServletResponse.SC_BAD_REQUEST,
                            "Bad Request. The isbn-number must not be emty!",
                            Collections.emptyList());
                } else {
                    //update a book which will be specified with a book object
                    final Book book = mapper.readValue(getRequest()
                            .getInputStream(), Book.class);
                    book.setIsbn(splittedURI[INDEX_ISBN]);
                    result = mediaService.updateBook(book);
                }
                break;
            case "POST":
                //add a book which will be specified with a book object
                result = mediaService.addBook(mapper.readValue(getRequest()
                        .getInputStream(), Book.class));
                break;
            default:
                //send an error answer
                result = new MediaResult(HttpServletResponse.SC_BAD_REQUEST,
                        "Bad Request. The used http method is not supported for"
                        + "this REST service.", Collections.emptyList());
                break;
        }
        return result;
    }

    /**
     * A small help method that chooses which action will performed with a disc
     * with the Service.
     *
     * @return Returns the result coming from the performed action.
     * @throws IOException Exception must be handled to give the user a status
     * report on what happened.
     */
    private Result delegateDiscAction() throws IOException {
        //the result object which will be returned
        final Result result;
        //the underlying service that will actually execute the desired action
        final MediaService mediaService = new MediaService();
        //the jackson mapper to create book objects
        final ObjectMapper mapper = new ObjectMapper();
        final String[] splittedURI = getRequest()
                .getRequestURI().split("/");
        switch (getRequest().getMethod()) {
            case "GET":
                //check for isbn in uri
                if (splittedURI.length == INDEX_ISBN + 1) {
                    //return just the requested book
                    result = mediaService.getDisc(splittedURI[INDEX_ISBN]);
                } else {
                    //return all discs
                    result = mediaService.getDiscs();
                }
                break;
            case "PUT":
                if (splittedURI.length < (INDEX_ISBN + 1)) {
                    result = new MediaResult(HttpServletResponse.SC_BAD_REQUEST,
                            "Bad Request. The barcode must not be emty!",
                            Collections.emptyList());
                } else {
                    final Disc disc = mapper.readValue(getRequest()
                            .getInputStream(), Disc.class);
                    disc.setBarcode(splittedURI[INDEX_ISBN]);
                    //update a disc which will be specified with a disc object
                    result = mediaService.updateDisc(disc);
                }
                break;
            case "POST":
                //add a disc which will be specified with a disc object
                result = mediaService.addDisc(mapper.readValue(getRequest()
                        .getInputStream(), Disc.class));
                break;
            default:
                //send an error answer
                result = new MediaResult(HttpServletResponse.SC_BAD_REQUEST,
                        "Bad Request. The used http method is not supported for"
                        + "this REST service.", Collections.emptyList());
                break;
        }
        return result;
    }

    //Methods Public
    //--------------------------------------------------------------------------
    @Override
    public final Result processRequest() {
        //the result which will be returned
        Result result;
        //book or disc request
        final String requestedResource = getRequest()
                .getRequestURI().split("/")[INDEX_RESOURCE_TYPE];
        try {
            if ("books".equals(requestedResource)) {
                result = delegateBookAction();
            } else if ("discs".equals(requestedResource)) {
                result = delegateDiscAction();
            } else {
                result = new MediaResult(HttpServletResponse.SC_NOT_FOUND,
                        "Not found. The requested resource does not exist."
                        + "Make sure to use the correct URI pattern."
                        + "The pattern is as follows:"
                        + "/shareit/media/books or discs/{isbn or barcode]",
                        Collections.emptyList());
            }
        } catch (IOException exception) {
            result = new MediaResult(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "A server error occured. This is not your fault. "
                    + "You can calm down again. Info fuer den Chefinformatiker:"
                    + "Ein Fehler is beim parsen des Requests aufgetreten.",
                    Collections.emptyList());
        }
        return result;
    }
    //Getter + Setter (also Private)
    //--------------------------------------------------------------------------

    /**
     * Simple getter for Request Object Variable.
     *
     * @return Request object.
     */
    private HttpServletRequest getRequest() {
        return request;
    }

}
