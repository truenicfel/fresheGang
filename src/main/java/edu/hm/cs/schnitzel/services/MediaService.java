/*
 * Autoren:     N.Dassler, P.Konopac
 * E-Mail:      dassler@hm.edu, konopac@hm.edu
 * Team:        schnitzel
 * Vorlesung:   Software Architektur
 * Dozent:      A.Boettcher
 */
package edu.hm.cs.schnitzel.services;

import java.util.ArrayList;
import java.util.List;

import edu.hm.cs.schnitzel.daos.DatabaseAccessObject;
import edu.hm.cs.schnitzel.daos.PseudoDatabaseAccessObject;
import edu.hm.cs.schnitzel.dataExchange.MediaResult;
import edu.hm.cs.schnitzel.dataExchange.Result;
import edu.hm.cs.schnitzel.entities.Book;
import edu.hm.cs.schnitzel.entities.Disc;
import edu.hm.cs.schnitzel.entities.Resource;

/**
 *
 * @author nicfel
 */
public class MediaService implements Service {
    //Constant Variables
    //--------------------------------------------------------------------------
	
	private static final int OK_CODE							= 200;
	private static final String OK_MESSAGE						= "OK.";
	private static final int POLICITY_NOT_FULFILLED_CODE		= 420;
	private static final String POLICITY_NOT_FULFILLED_MESSAGE	= "Policy Not Fulfilled.";
	private static final int INTERNAL_SERVER_ERROR_CODE			= 500;
	private static final String INTERNAL_SERVER_ERROR_MESSAGE	= "Internal Server Error.";
	
	//Object Variables
	//--------------------------------------------------------------------------
	
	private final DatabaseAccessObject dao;
	
    //Constructors
    //--------------------------------------------------------------------------
    
	public MediaService() {
		dao = new PseudoDatabaseAccessObject();
	}
	
	//Methods Private
    //--------------------------------------------------------------------------
    //Methods Public
    //--------------------------------------------------------------------------
    
	@Override
    public Result addBook(Book toAdd) {
    	final int code;
    	final String message;
    	final List<Resource> resources = new ArrayList<>();
    	if (toAdd == null || toAdd.getIsbn() == null || "".equals(toAdd.getIsbn())) {
    		code = POLICITY_NOT_FULFILLED_CODE;
    		message = POLICITY_NOT_FULFILLED_MESSAGE + " The book and its isbn-number must not be null or empty!";
    	} else if(!dao.addBook(toAdd)) {
    		code = INTERNAL_SERVER_ERROR_CODE;
    		message = INTERNAL_SERVER_ERROR_MESSAGE + " The book and its isbn-number must not be null or empty!";
    	} else {
    		code = OK_CODE;
    		message = OK_MESSAGE + " The book with isbn-number " + toAdd.getIsbn() + " has been created successfully!";
    	}
		return new MediaResult(code, message, resources);
    }

    @Override
    public Result addDisc(Disc toAdd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Result getBooks() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Result getDiscs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Result updateBook(Book toUpdate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Result updateDisc(Disc toUpdate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public Result getBook(String isbn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result getDisc(String isbn) {
		// TODO Auto-generated method stub
		return null;
	}

	//Getter + Setter (also Private)
	//--------------------------------------------------------------------------

}
