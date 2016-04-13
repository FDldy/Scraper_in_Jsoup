package com.ldy.JavaScraping.SubmittingForm;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import junit.framework.TestCase;

public class WikiLogin extends TestCase{
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("com.ldy.JavaScraping.SubmittingForm.WikiLogin");
	}
	@Test
	public void test() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		//webClient.getOptions().setCssEnabled(true);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setTimeout(10000);
		
		HtmlPage page1 = webClient.getPage("http://en.wikipedia.org/w/index.php?title=Special:UserLogin");
		HtmlForm form = page1.getForms().get(0);
		HtmlSubmitInput button = form.getInputByName("wpLoginAttempt");
		HtmlTextInput userField = form.getInputByName("wpName");
		HtmlPasswordInput passField = form.getInputByName("wpPassword");
		
		userField.setValueAttribute("Fdliudaiyuan");
		passField.setValueAttribute("3331196");
		
		HtmlPage page2 = button.click();
		
		webClient.waitForBackgroundJavaScript(30000);
		
		String headerText = page2.getElementById("firstHeading").getElementsByTagName("span").get(0).asText();
		webClient.close();
		assertEquals(headerText, "Login successful");
	}

}
