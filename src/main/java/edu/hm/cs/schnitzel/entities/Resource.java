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
public abstract class Resource {

    private String title;

    /**
     * Standard constructor.
     *
     * @param titleInput The title for the resource.
     */
    public Resource(final String titleInput) {
        this.title = titleInput;
    }

    /**
     * Getter for field title.
     *
     * @return String representation forTitle.
     */
    public final String getTitle() {
        return title;
    }

    /**
     * Setter for title.
     *
     * @param titleInput is the title
     */
    public final void setTitle(final String titleInput) {
        this.title = titleInput;
    }

}
