/*
 * Autoren:     N.Dassler, P.Konopac
 * E-Mail:      dassler@hm.edu, konopac@hm.edu
 * Team:        schnitzel
 * Vorlesung:   Software Architektur
 * Dozent:      A.Boettcher
 */
package edu.hm.cs.schnitzel.entities;

/**
 *
 * @author nicfel
 */
public abstract class Resource  {
    
    final String title;

    public Resource(String title) {
        this.title = title;
    }

    final public String getTitle() {
        return title;
    }
    
    
}
