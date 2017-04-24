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
     *
     * @param request
     * @return
     */
    Result processRequest();
    
}
