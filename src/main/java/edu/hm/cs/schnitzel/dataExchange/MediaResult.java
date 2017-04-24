/*
 * Autoren:     N.Dassler, P.Konopac
 * E-Mail:      dassler@hm.edu, konopac@hm.edu
 * Team:        schnitzel
 * Vorlesung:   Software Architektur
 * Dozent:      A.Boettcher
 */
package edu.hm.cs.schnitzel.dataExchange;

import edu.hm.cs.schnitzel.entities.Resource;
import java.util.List;

/**
 *
 * @author nicfel
 */
public class MediaResult implements Result{

    //Object Variables
    //--------------------------------------------------------------------------
    //Constructors
    //--------------------------------------------------------------------------
    public MediaResult(int code, String status,  List<Resource> resources) {    
    }

    //Methods Private
    //--------------------------------------------------------------------------
    //Methods Public
    //--------------------------------------------------------------------------
    @Override
    public int getCode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getStatus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getContent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //Getter + Setter (also Private)
    //--------------------------------------------------------------------------

    @Override
    public String getContentType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
