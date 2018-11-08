package org.konate.TpsJavaTelecom3.XML;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class CountingHandler extends DefaultHandler {
	private int count;
	
	@Override
	public void startElement(String uri, String localName,
            				 String qName, Attributes attributes) {
		count++;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	
	
}
