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
    private final String requestType;
    private final String uri;
    //Constructors
    //--------------------------------------------------------------------------

    public MediaRequest(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.requestType = request.getMethod();
        this.uri = request.getRequestURI();
    }

    //Methods Private
    //--------------------------------------------------------------------------
    private Result delegateBookAction() throws IOException {
        //the result object which will be returned
        final Result result;
        //the underlying service that will actually execute the desired action 
        final MediaService mediaService = new MediaService();
        //the jackson mapper to create book objects
        final ObjectMapper mapper = new ObjectMapper();
        switch (getRequestType()) {
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

    private Result delegateDiscAction() throws IOException {
        //the result object which will be returned
        final Result result;
        //the underlying service that will actually execute the desired action 
        final MediaService mediaService = new MediaService();
        //the jackson mapper to create book objects
        final ObjectMapper mapper = new ObjectMapper();
        switch (getRequestType()) {
            case "GET":
                //just return disc into the result
                result = mediaService.getDiscs();
                break;
            case "PUT":
                //update a disc which will be specified with a disc object
                result = mediaService.updateDisc(mapper.readValue(getRequest().getInputStream(), Book.class));
                break;
            case "POST":
                //add a disc which will be specified with a disc object
                result = mediaService.addDisc(mapper.readValue(getRequest().getInputStream(), Book.class));
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

    private String getRequestType() {
        return requestType;
    }

    private String getUri() {
        return uri;
    }

}
