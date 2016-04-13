package com.ldy.JavaScraping.Ajax;

import java.io.IOException;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import junit.framework.TestCase;

public class Intelius extends TestCase{
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("com.ldy.JavaScraping.Ajax.Intelius");
	}
	
	@Test
	public void test() {
		WebClient webClient = new WebClient(BrowserVersion.FIREFOX_38);
		HtmlPage page = null;
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		
		try {
			page = webClient.getPage("http://www.intelius.com/results.php?ReportType=1&formname=name&qf=Ryan&qmi=E&qn=Mitchell&qcs=Needham%2C+MA&focusfirst=1");
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		}
		DomElement name = null;
		for (int i=0;i<20;i++){
			boolean mblueExists =true;
			try {
				name = page.getElementById("name");
			} catch (ElementNotFoundException e) {
				mblueExists = false;
			}
			if (mblueExists){
				break;
			}
			synchronized (page){
				try {
					page.wait(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		webClient.close();
		try {
			assertEquals("Ryan Elizabeth Mitchell", name.getElementsByTagName("em").get(0).asText());
		} catch (NullPointerException e) {
			fail("Name not found");
		}
	}

}
