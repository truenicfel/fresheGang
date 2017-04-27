/*
 * Autoren:     N.Dassler, P.Konopac
 * E-Mail:      dassler@hm.edu, konopac@hm.edu
 * Team:        schnitzel
 * Vorlesung:   Software Architektur
 * Dozent:      A.Boettcher
 */
package edu.hm.cs.schnitzel.dataExchange;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author nicfel
 */
public interface Request {

    /**
     * Processes a Servlet Request.
     * This method will do everything necessary to get the desired output
     * which is specified by the URI and the type of request.
     * @return A result object containing.
     */
    Result processRequest();
}
