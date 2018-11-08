package org.konate.tpxml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		
		//Approche DOM
		Document doc = DocumentHelper.createDocument();
		Element root = 	doc.addElement("User");
		root.addElement("Name")
			.addText("Hugo");
		root.addElement("Age")
			.addText("29");
		
		File file = new File("./files/outputXML.txt");
		FileOutputStream out = new FileOutputStream(file);
		OutputFormat outputFormat = OutputFormat.createPrettyPrint();
		outputFormat.setEncoding("utf-8");
		
		try {
			XMLWriter w = new XMLWriter(out, outputFormat);
					  w.write(doc);
					  w.flush();
					  w.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		
		//Approche SAX
		FileInputStream in = new FileInputStream(file);
		SAXParserFactory sp = SAXParserFactory.newInstance();
						 sp.setNamespaceAware(true);
						 sp.setValidating(true);
		CountingHandler dh = new CountingHandler();
		SAXParser parser = sp.newSAXParser();
				  parser.parse(in, dh);
		System.out.println("Counting " + dh.getCount() + " elements in " + file.getName());
	}

}
