package com.ldy.JavaScraping.SimpleScraper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WikiScraper {

	public static void main(String[] args) {
		//call scrapeTopic method to catch uri "/wiki/Python"
		scrapeTopic("/wiki/Python");
	}
		//scrapeTopic method:
	public static void scrapeTopic(String url) {
		String html = getUrl("https://en.wikipedia.org"+url);
		Document doc = Jsoup.parse(html);
		String contentText = doc.select("#mw-content-text > p").first().text();
		System.out.println(contentText);
	}
		//getUrl method:
	public static String getUrl(String url) {
		URL urlObj =null;
		try {
			urlObj = new URL(url);
		} catch (MalformedURLException e) {
			System.out.println("The url was malformed!");
			return "";
		}
		URLConnection urlCon = null;
		BufferedReader in = null;
		String outputText="";
		try {
			urlCon = urlObj.openConnection();
		in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
		String line="";
			while ((line=in.readLine())!=null){
				outputText+=line;
			}
			in.close();
		
		} catch (IOException e) {
			System.out.println("There was an error connecting to URL");
			return "";
		}
		return outputText;
	}
}
