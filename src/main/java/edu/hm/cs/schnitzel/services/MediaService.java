/*
 * Autoren:     N.Dassler, P.Konopac
 * E-Mail:      dassler@hm.edu, konopac@hm.edu
 * Team:        schnitzel
 * Vorlesung:   Software Architektur
 * Dozent:      A.Boettcher
 */
package edu.hm.cs.schnitzel.services;

import java.util.ArrayList;
import java.util.Collections;
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
 * @author konopac
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
    
	/**
	 * Constructor of MediaService.
	 * 
	 * Creates an instance of the DAO
	 */
	public MediaService() {
		dao = new PseudoDatabaseAccessObject();
	}
	
    //Methods Public
    //--------------------------------------------------------------------------
    
	@Override
    public Result addBook(final Book toAdd) {
    	final int code;
    	final String message;
    	if (toAdd == null || toAdd.getIsbn() == null || "".equals(toAdd.getIsbn())) {
    		code = POLICITY_NOT_FULFILLED_CODE;
    		message = POLICITY_NOT_FULFILLED_MESSAGE + " The book and its isbn-number must not be null or empty!";
    	} else if(!getDao().addBook(toAdd)) {
    		code = INTERNAL_SERVER_ERROR_CODE;
    		message = INTERNAL_SERVER_ERROR_MESSAGE + " There was a problem adding the book!";
    	} else {
    		code = OK_CODE;
    		message = OK_MESSAGE + " The book with isbn-number " + toAdd.getIsbn() + " has been created successfully!";
    	}
		return new MediaResult(code, message, Collections.emptyList());
    }

    @Override
    public Result addDisc(final Disc toAdd) {
    	final int code;
    	final String message;
    	if (toAdd == null || toAdd.getBarcode() == null || "".equals(toAdd.getBarcode())) {
    		code = POLICITY_NOT_FULFILLED_CODE;
    		message = POLICITY_NOT_FULFILLED_MESSAGE + " The disc and its barcode must not be null or empty!";
    	} else if(!getDao().addDisc(toAdd)) {
    		code = INTERNAL_SERVER_ERROR_CODE;
    		message = INTERNAL_SERVER_ERROR_MESSAGE + " There was a problem adding the disc!";
    	} else {
    		code = OK_CODE;
    		message = OK_MESSAGE + " The disc with barcode " + toAdd.getBarcode() + " has been created successfully!";
    	}
		return new MediaResult(code, message, Collections.emptyList());
    }

    @Override
    public Result getBooks() {
    	final int code = OK_CODE;
    	final String message = OK_MESSAGE + " All books loaded!";
    	final List<Resource> resources = (List<Resource>)(List<?>) getDao().getBooks();
    	
		return new MediaResult(code, message, resources);
	}

    @Override
    public Result getDiscs() {
    	final int code = OK_CODE;
    	final String message = OK_MESSAGE + " All discs loaded!";
    	final List<Resource> resources = (List<Resource>)(List<?>) getDao().getDiscs();
    	
		return new MediaResult(code, message, resources);
	}

    @Override
    public Result updateBook(final Book toUpdate) {
    	final int code;
    	final String message;
    	if (toUpdate == null || toUpdate.getIsbn() == null || "".equals(toUpdate.getIsbn())) {
    		code = POLICITY_NOT_FULFILLED_CODE;
    		message = POLICITY_NOT_FULFILLED_MESSAGE + " The book and its isbn-number must not be null or empty!";
    	} else if(!getDao().updateBook(toUpdate)) {
    		code = INTERNAL_SERVER_ERROR_CODE;
    		message = INTERNAL_SERVER_ERROR_MESSAGE + " There was a problem updating the book!";
    	} else {
    		code = OK_CODE;
    		message = OK_MESSAGE + " The book with isbn-number " + toUpdate.getIsbn() + " has been updated successfully!";
    	}
		return new MediaResult(code, message, Collections.emptyList());
    }

    @Override
    public Result updateDisc(final Disc toUpdate) {
    	final int code;
    	final String message;
    	if (toUpdate == null || toUpdate.getBarcode() == null || "".equals(toUpdate.getBarcode())) {
    		code = POLICITY_NOT_FULFILLED_CODE;
    		message = POLICITY_NOT_FULFILLED_MESSAGE + " The disc and its barcode must not be null or empty!";
    	} else if(!getDao().updateDisc(toUpdate)) {
    		code = INTERNAL_SERVER_ERROR_CODE;
    		message = INTERNAL_SERVER_ERROR_MESSAGE + " There was a problem updating the disc!";
    	} else {
    		code = OK_CODE;
    		message = OK_MESSAGE + " The disc with barcode " + toUpdate.getBarcode() + " has been updated successfully!";
    	}
		return new MediaResult(code, message, Collections.emptyList());
	}

	@Override
	public Result getBook(final String isbn) {
    	final int code;
    	final String message;
    	final List<Resource> resources = new ArrayList<>();
    	
    	if (isbn == null || "".equals(isbn)) {
    		code = POLICITY_NOT_FULFILLED_CODE;
    		message = POLICITY_NOT_FULFILLED_MESSAGE + " The isbn-number must not be null or empty!";
    	} else {
    		code = OK_CODE;
    		resources.add(getDao().getBook(isbn));
    		if (resources.isEmpty()) {
    			message = OK_MESSAGE + " The book with isbn-number " + isbn + " could not be found!";
    		} else {
    			message = OK_MESSAGE + " The book with isbn-number " + isbn + " has been loaded!";
    		}
    	}
		return new MediaResult(code, message, resources);
	}

	@Override
	public Result getDisc(final String barcode) {
    	final int code;
    	final String message;
    	final List<Resource> resources = new ArrayList<>();
    	
    	if (barcode == null || "".equals(barcode)) {
    		code = POLICITY_NOT_FULFILLED_CODE;
    		message = POLICITY_NOT_FULFILLED_MESSAGE + " The barcode must not be null or empty!";
    	} else {
    		code = OK_CODE;
    		resources.add(getDao().getDisc(barcode));
    		if (resources.isEmpty()) {
    			message = OK_MESSAGE + " The disc with barcode " + barcode + " could not be found!";
    		} else {
    			message = OK_MESSAGE + " The disc with barcode " + barcode + " has been loaded!";
    		}
    	}
		return new MediaResult(code, message, resources);
	}

	//Getter + Setter (also Private)
	//--------------------------------------------------------------------------

	/**
	 * Getter for DAO.
	 * 
	 * @return the created dao object
	 */
	private DatabaseAccessObject getDao() {
		return dao;
	}
}
