package com.ldy.JavaScraping.tests;

import org.junit.Test;

import junit.framework.TestCase;

public class ScraperTest extends TestCase{
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("tests.ScraperTest");
	}
	@Test
	public void test() {
		String scrapedTest = WikiScraper.scrapeTopic();
		String openingTest = "A python is a constricting snake belonging to the Python (genus), or, more generally, any snake in the family Pythonidae (containing the Python genus).";
		assertEquals("Nothing has changed!",scrapedTest, openingTest);
	}

}
