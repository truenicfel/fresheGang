/*
 * Autoren:     N.Dassler, P.Konopac
 * E-Mail:      dassler@hm.edu, konopac@hm.edu
 * Team:        schnitzel
 * Vorlesung:   Software Architektur
 * Dozent:      A.Boettcher
 */
package edu.hm.cs.schnitzel.dataExchange;

/**
 *
 * @author nicfel
 */
public interface Request {
    
    /**
     *
     * @return
     */
    MediaResult processRequest();
    
}
