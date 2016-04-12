package com.ldy.JavaScraping.tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WikiScraper {

	public WikiScraper(){
		System.out.println("Initialized");
	}
	public static String scrapeTopic() {
		String url ="/wiki/python";
		String html = getUrl("https://www.wikipedia.org/"+url);
		Document doc = Jsoup.parse(html);
		String contentText = doc.select("#mw-content-text > p").first().text();
		return contentText;
	}
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
