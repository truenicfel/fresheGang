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
public interface Result {
        
    int getCode();
    String getStatus();
    String getContent();
    String getContentType();
    
}
