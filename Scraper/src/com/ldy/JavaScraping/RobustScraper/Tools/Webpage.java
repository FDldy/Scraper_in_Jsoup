package com.ldy.JavaScraping.RobustScraper.Tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Webpage {
	/**
	 * @param url the url of target page
	 * @param urlObj set as global variable for using by several methods
	 */
	
	public String url;
	public URL urlObj;
	
	public Webpage(String declareUrl) {
		/**
		 * throw MalformedURLException if create URL failed
		 * @param declareUrl
		 */
		url = declareUrl;
		urlObj = null;
		try {
			urlObj = new URL(url);
		} catch (MalformedURLException e) {
			System.out.println("The url was malformed!");
		}
	}
		/**
		* Used to overload the getRedirect(Boolean secondRedirect) method if no Boolean is provided.
		* By default, this will perform only one redirect and return the result.
		* @return Returns final redirected URL, or an empty string if none was found.
		* @throws MalformedURLException
		* @throws IOException
		*/
	public String getRedirect() throws IOException,MalformedURLException {
		try {
			return getRedirect(false);
		} catch (MalformedURLException e) {
			System.out.println("The url was malformed!");
		} catch (IOException e) {
			System.out.println("There was error in connecting the URL");
		}
		return "";
	}
		/**
		* Used to get the URL that a page redirects to, or, optionally, the second redirect that a page goes to.
		* @param secondRedirect "True" if the redirect should be followed twice
		* @return URL that the page redirects to
		* @throws MalformedURLException
		* @throws IOException
		*/
	public String getRedirect(boolean secondRedirect) throws IOException,MalformedURLException {
		HttpURLConnection URLCon = (HttpURLConnection) urlObj.openConnection();
		URLCon.setInstanceFollowRedirects(false);
		URLCon.connect();
		String header = URLCon.getHeaderField("Location");
		System.out.println("First header is:"+URLCon.getHeaderField(7));
		System.out.println("Redirect is: "+ header);
		//if a second redirect is required
		if(secondRedirect){
			try{
				URL newURL = new URL(header);
				HttpURLConnection newCon = (HttpURLConnection) newURL.openConnection();
				newCon.setInstanceFollowRedirects(false);
				newCon.connect();
				String finalHeader = newCon.getHeaderField("Location" );
				return finalHeader;
			} catch (MalformedURLException e) {
				System.out.println("The url was malformed!");
			}
				return "";
			} else {
		return header;
		}
	}
		
	public Document getJsoup() {
		String html = new Webpage(url).getString();
		return Jsoup.parse(html);
	}

	public String getString() {
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
	



