package com.ldy.JavaScraping.RobustScraper.Tools;
	/**
	* IPResolver uses the free service provided by freegeoip.net to resolve IP addresses into a geographic location
	* @author rmitchell
	*
	*/
public class IPResolver {
	String ipAddress;
	String ipInfo;
	
	public IPResolver(String declareAddress) {
		ipAddress = declareAddress;
		Webpage ipInfoObj = new Webpage("http://freegeoip.net/xml/"+ipAddress);
		ipInfo = ipInfoObj.getString();
	}
	/**
	* Simple method, returns anything between the \<CountryName\>tags (presumably the country of origin)
	* @return String containing the name of the ipAddress country of origin
	*/
	public String getCountry() {
		int startCtry = ipInfo.indexOf("<CountryName>") + 13;
		int endCtry = ipInfo.indexOf("</CountryName>");
		return ipInfo.substring(startCtry, endCtry);
	}

}
