package com.ldy.JavaScraping.HandlingErrors;

public class LinkNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LinkNotFoundException(String msg){
		super(msg);
	}

}
