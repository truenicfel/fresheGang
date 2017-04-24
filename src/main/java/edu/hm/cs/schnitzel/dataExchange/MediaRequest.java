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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nicfel
 */
public class MediaRequest implements Request {

    //Object Variables
    //--------------------------------------------------------------------------
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    //Constructors
    //--------------------------------------------------------------------------

    public MediaRequest(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    //Methods Private
    //--------------------------------------------------------------------------
    /**
     * A small help method that chooses which action will performed with a book
     * with the Service.
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
        switch (getRequest().getMethod()) {
            case "GET":
                //just return books into the result
                result = mediaService.getBooks();
                break;
            case "PUT":
                //update a book which will be specified with a book object
                result = mediaService.updateBook(mapper.readValue(getRequest().getInputStream(), Book.class));
                break;
            case "POST":
                //add a book which will be specified with a book object
                result = mediaService.addBook(mapper.readValue(getRequest().getInputStream(), Book.class));
                break;
            default:
                //TODO add correct error result
                result = new MediaResult(404, "Not found", null);
                break;
        }
        return result;
    }
    /**
     * A small help method that chooses which action will performed with a disc
     * with the Service.
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
        switch (getRequest().getMethod()) {
            case "GET":
                //just return disc into the result
                result = mediaService.getDiscs();
                break;
            case "PUT":
                //update a disc which will be specified with a disc object
                result = mediaService.updateDisc(mapper.readValue(getRequest().getInputStream(), Disc.class));
                break;
            case "POST":
                //add a disc which will be specified with a disc object
                result = mediaService.addDisc(mapper.readValue(getRequest().getInputStream(), Disc.class));
                break;
            default:
                //TODO add correct error result
                result = new MediaResult(404, "Not found", null);
                break;
        }
        return result;
    }

    //Methods Public
    //--------------------------------------------------------------------------
    @Override
    public Result processRequest() {
        //the result which will be returned
        Result result;
        //book or disc request
        final String requestedResource = getRequest().getRequestURI().split("/")[3];
        try {
            if ("books".equals(requestedResource)) {
                result = delegateBookAction();
            } else if ("discs".equals(requestedResource)) {
                result = delegateDiscAction();
            } else {
                //TODO add a correct error report
                result = new MediaResult(404, "not found", null);
            }
        } catch (IOException exception) {
            //TODO add a correct error report
            result = new MediaResult(404, "not found", null);
        }

        return result;
    }
    //Getter + Setter (also Private)
    //--------------------------------------------------------------------------

    private HttpServletRequest getRequest() {
        return request;
    }

    private HttpServletResponse getResponse() {
        return response;
    }

}
