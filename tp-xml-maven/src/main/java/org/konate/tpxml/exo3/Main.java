package org.konate.tpxml.exo3;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		RSSReader reader = new RSSReader();
		String url = "https://www.voxxed.com/feed/";
		InputStream in =  reader.read(url);
		SAXParserFactory sp = SAXParserFactory.newInstance();
						 sp.setNamespaceAware(true);
						 sp.setValidating(true);
						 
		RSSReader dh = new RSSReader();
		
		SAXParser parser = sp.newSAXParser();
				  parser.parse(in, dh.rootName);
				  
		in =  reader.read(url);		  
		parser.parse(in, dh.countSubRootElements);		  
		System.out.println("Sub Elements of root : " + dh.getCountSubRoot());
		
		in =  reader.read(url);		  
		parser.parse(in, dh.countItemElements);
		System.out.println("Number of items : " + dh.getCountItem());
		
		in =  reader.read(url);		  
		parser.parse(in, dh.itemTitles);
		System.out.println("Titles : " + dh.getTitles());
		
		in =  reader.read(url);		  
		parser.parse(in, dh.CategoryTitles);
		System.out.println("Categories : " + dh.getCategories());
		
		in =  reader.read(url);		  
		parser.parse(in, dh.countElementsInNamespace);
		System.out.println("Elements in Namespace : " + dh.getCountEIN());
		
		in =  reader.read(url);		  
		parser.parse(in, dh.CategoryTitles);
		System.out.println("Microservices Titles : " + dh.getMicroservices());
	
	}

}
