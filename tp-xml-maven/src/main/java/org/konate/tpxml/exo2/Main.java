package org.konate.tpxml.exo2;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, DocumentException {
		// TODO Auto-generated method stub
		
		Marin satya = new Marin(10, "NADELLA", "Satya", 51);
		
		XMLUtil util = new XMLUtil();
		Document document = util.seriaze(satya);
		File file = new File("./XML/MarinXML.xml");
		util.write(document, file);
		
		//Print after reading the XML file
		System.out.println(util.read(file).asXML());
		
		//Create instance of Marin after reading the XML file
		System.out.println(util.deserialize(util.read(file)));
	}

}
