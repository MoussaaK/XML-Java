package org.konate.tpxml.exo3;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class RSSReader {
	
	private static boolean isRoot = true, inItem, inCategory;
	private int countSubRoot, countEIN;
	/**
	 * @return the countElementsInNamespace
	 */
	public int getCountEIN() {
		return countEIN;
	}

	private int countItem;
	private List<String> titles = new ArrayList<>();
	private List<String> categories = new ArrayList<>();
	private List<String> microservices = new ArrayList<>();
	
	
	/**
	 * @return the microservices
	 */
	public List<String> getMicroservices() {
		return microservices;
	}

	/**
	 * @return the categories
	 */
	public List<String> getCategories() {
		return categories;
	}

	/**
	 * @return the items
	 */
	public List<String> getTitles() {
		return titles;
	}

	/**
	 * @return the count
	 */
	public int getCountSubRoot() {
		return countSubRoot;
	}

	/**
	 * @return the countItem
	 */
	public int getCountItem() {
		return countItem;
	}

	public InputStream read(String url) throws ClientProtocolException, IOException {
		//String url = "https://www.voxxed.com/feed/" ;
		
		// ouverture d'un client
		HttpClient httpClient = HttpClientBuilder.create()
												 .build();
		
		// création d'une méthode HTTP
		HttpGet get = new HttpGet(url);
		HttpResponse response = httpClient.execute(get);
		StatusLine statusLine = response.getStatusLine();

		// invocation de la méthode
		int statusCode = statusLine.getStatusCode();
		if (statusCode != HttpStatus.SC_OK) {
			System.err.println("Method failed : " + statusLine) ;
		}

		// lecture de la réponse dans un flux
		InputStream inputStream = response.getEntity().getContent();

		return inputStream;
	}
	
	//Handler to get rootName
	DefaultHandler rootName = new DefaultHandler() {
		@Override
		public void startElement(String uri, String localName,
				String qName, Attributes attributes) {
			if(isRoot) {
				System.out.println(localName);
				isRoot = false;
			}
		}
		
		@Override
		public void endDocument() {
				isRoot = true;
		}
	};
	
	//Handler to count sub root elements
	DefaultHandler countSubRootElements = new DefaultHandler() {
		@Override
		public void startElement(String uri, String localName,
								 String qName, Attributes attributes) {
			countSubRoot++;
			if(isRoot) {
				isRoot = false;
				countSubRoot--;
			}
		}
	};
	
	//Handler to count elements with name item
	DefaultHandler countItemElements = new DefaultHandler() {
		@Override
		public void startElement(String uri, String localName,
				                 String qName, Attributes attributes) {				
			if(localName.equals("item"))
				countItem++;
		}
	};
	
	//Add all titles to a List
	DefaultHandler itemTitles = new DefaultHandler() {
		String string; 
		@Override
		public void startElement(String uri, String localName,
				                 String qName, Attributes attributes) {				
			if(qName.equals("item"))
				inItem = true;
		}
		@Override
		public void endElement(String uri, String localName,
				               String qName) {				
			if(qName.equals("item"))
				inItem = false;
			if(qName.equals("title"))
				titles.add(string);
		}
		@Override
		public void characters(char[] ch, int start, int length) {				
			if(inItem) {
				string = new String(ch, start, length);
			}		
		}
	};
	
	//Add all categories to a List
	DefaultHandler CategoryTitles = new DefaultHandler() {
		String string; 
		@Override
		public void startElement(String uri, String localName,
				                 String qName, Attributes attributes) {				
			if(qName.equals("category"))
				inCategory = true;
		}
		@Override
		public void endElement(String uri, String localName,
				               String qName) {				
			if(qName.equals("category"))
				inCategory = false;
			if(qName.equals("title")) {
				categories.add(string);
				/*if(string != null && string.contains("microservices"))
					microservices.add(string);*/
			}
		}
		@Override
		public void characters(char[] ch, int start, int length) {				
			if(inCategory) {
				string = new String(ch, start, length);
			}		
		}
	};
	
	//Handler to count elements namespace
		DefaultHandler countElementsInNamespace = new DefaultHandler() {
			@Override
			public void startElement(String uri, String localName,
					                 String qName, Attributes attributes) {				
				if(uri.equals("http://purl.org/rss/1.0/modules/slash/"))
					countEIN++;
			}
		};
}
