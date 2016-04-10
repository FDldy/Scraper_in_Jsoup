package com.ldy.JavaScraping.RobustScraper.Tools;

public class PageDoesNotExist extends Exception{

	/**
	 * Error is thrown is the page not existed
	 */
	private static final long serialVersionUID = 1L;

	public PageDoesNotExist(String msg){
		System.out.println(msg);
	}

}
