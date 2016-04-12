package com.ldy.JavaScraping.Undercover;

import java.io.IOException;
import java.net.MalformedURLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import junit.framework.TestCase;

public class FreeProxyLists extends TestCase{

	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		org.junit.runner.JUnitCore.main("com.ldy.JavaScraping.Undercover.FreeProxyLists");
		//new FreeProxyLists().test();		
	}

	@Test
	public void test() throws FailingHttpStatusCodeException, MalformedURLException, IOException{
		WebClient webClient = new WebClient(BrowserVersion.FIREFOX_38);
		//webClient.setThrowExceptionOnScriptError(false);
		webClient.setJavaScriptTimeout(5000);
		HtmlPage page = webClient.getPage("http://www.66ip.cn/");
		Document doc = Jsoup.parse(page.asXml());
		String ipAddress =
				doc.getElementsByClass("DataGrid").get(0).
				getElementsByTag("tbody").get(0).
				getElementsByClass("odd").get(0).getElementsByTag
				("td").get(0).getElementsByTag("a").get(0).text();
		System.out.println(ipAddress);
		webClient.close();
		assertTrue(ipAddress.matches("(?:[0-9]{1,3}.){3}[0-9]{1,3}"));
		
	}
}
