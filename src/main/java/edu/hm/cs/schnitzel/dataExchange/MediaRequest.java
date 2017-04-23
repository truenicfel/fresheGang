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
import edu.hm.cs.schnitzel.entities.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletInputStream;
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
    private final List<Resource> resources;
    //Constructors
    //--------------------------------------------------------------------------

    public MediaRequest(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.requestType = request.getMethod();
        this.uri = request.getRequestURI();
        this.resources = new ArrayList<>();
    }

    //Methods Private
    //--------------------------------------------------------------------------
    /**
     * Create a resource from a given InputStream and a given type of the
     * object. Using the jackson library the given input will be converted into
     * an object. The type of the object is defined by the second param.
     *
     * @param input The servletStream from which the data will be read.
     * @param type The type of the result object.
     * @throws IOException Will be handled in calling method.
     * @return true if successful else if not.
     */
    private boolean createResource(final ServletInputStream input, final String type) throws IOException {
        //success variable
        boolean result = true;
        //create an object mapper
        final ObjectMapper mapper = new ObjectMapper();
        //determine which type to create
        if ("books".equals(type)) {
            getResources().add(mapper.readValue(input, Book.class));
        } else if ("discs".equals(type)) {
            getResources().add(mapper.readValue(input, Disc.class));
        } else {
            result = false;
            System.out.println("Bad request.");
        }
        return result;
    }

    //Methods Public
    //--------------------------------------------------------------------------
    @Override
    public MediaResult processRequest() {
        //success variable if anything goes wrong this will be false
        boolean success = true;
        //create book/disc if request is post/put
        if (!"GET".equals(getRequestType())) {
            try {
                System.out.println(Arrays.toString(getUri().split("/")));
                success = createResource(getRequest().getInputStream(), getUri().split("/")[2]);
            } catch (IOException exception) {
                success = false;
                System.out.println("An error occured.");
                System.out.println(exception.toString());
            }
        }
        System.out.println(getResources().toString());
        return new MediaResult();

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

    private List<Resource> getResources() {
        return resources;
    }

}
