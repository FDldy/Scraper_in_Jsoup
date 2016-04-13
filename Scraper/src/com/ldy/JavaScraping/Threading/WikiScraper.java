package com.ldy.JavaScraping.Threading;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;
import java.sql.*;

import com.ldy.JavaScraping.HandlingErrors.LinkNotFoundException;

	
	class WikiCrawler extends Thread{
		private static Connection dbCon;
		
		public WikiCrawler(String str){
			super(str);
			
			
			String dbUrl = "jdbc:mysql://localhost:3306/mydb";
			Properties connectionProps = new Properties();
			connectionProps.put("user", "root");
			connectionProps.put("password", "1987");
			dbCon = null;
			try {
				dbCon = DriverManager.getConnection(dbUrl, connectionProps);
			} catch (SQLException e1) {
				System.out.println("There was a problem connecting to database.");
				e1.printStackTrace();
			}
			
			PreparedStatement useStmt;
			try {
				useStmt = dbCon.prepareStatement("USE mydb");
				useStmt.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}	
	public void run(){
		String newUrl = getName();
		for (int i=0;i<10;i++){
			System.out.println(i+" "+getName());
			try {
				newUrl = scrapeTopic(newUrl);
				if (newUrl == ""){
					newUrl = getName();
				}
				sleep((long) (Math.random()*1000));
				}catch (InterruptedException e) {
					e.printStackTrace();
				}catch (LinkNotFoundException e) {
					e.printStackTrace();
			}
		}
		System.out.println("DONE!"+getName());
	}	
	
	
//	private static void writeToDB(String title,String url){
//		PreparedStatement useStmt;
//		try {
//			useStmt = dbCon.prepareStatement("INSERT INTO wikipedia (title, url) VALUES (?,?)");
//			useStmt.setString(1, title);
//			useStmt.setString(2, url);
//			useStmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
		//create scrapeTopic method to parse html object:
	public static String scrapeTopic(String url) throws LinkNotFoundException {
		return getUrl("https://en.wikipedia.org"+url);
		
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
