/*
 * Autoren:     N.Dassler, P.Konopac
 * E-Mail:      dassler@hm.edu, konopac@hm.edu
 * Team:        schnitzel
 * Vorlesung:   Software Architektur
 * Dozent:      A.Boettcher
 */
package edu.hm.cs.schnitzel.media.dataExchange;

/**
 *
 * @author nicfel
 */
public interface Result {

    /**
     * Returns the json String which will be sent back to the requesting
     * machine.
     *
     * @return The json String.
     */
    String getJsonString();

}
