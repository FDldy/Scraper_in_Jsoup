package com.ldy.JavaScraping.ComplicatedScraper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.ldy.JavaScraping.HandlingErrors.LinkNotFoundException;

public class WikiScraper {
	private static Random generator;
	public static void main(String[] args) {
		//Creates a new random number generator using a single seed
		generator =	 new Random(31415926);
		//call scrapeTopic method to catch uri "/wiki/java"
		try {
			scrapeTopic("/wiki/Java");
		} catch (LinkNotFoundException e) {
			System.out.println("Try again!");;
		}
	}
		//create scrapeTopic method to parse html object:
	public static void scrapeTopic(String url) throws LinkNotFoundException {
		String html = getUrl("https://en.wikipedia.org"+url);
		Document doc = Jsoup.parse(html);
		Elements links = doc.select("#mw-content-text [href~=^/wiki/((?!:).)*$]");
		if(links.size()==0){
			throw new LinkNotFoundException("No links on page, or page was malformed");
		}
		int r = generator.nextInt(links.size());
		System.out.println("Random link is:"
				+links.get(r).text() + " at url:"
				+links.get(r).attr("href"));
		scrapeTopic(links.get(r).attr("href"));
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
