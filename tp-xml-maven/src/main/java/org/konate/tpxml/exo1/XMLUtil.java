package org.konate.tpxml.exo1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.xml.sax.helpers.DefaultHandler;

public class XMLUtil extends DefaultHandler {
	
	Element root;
	Document seriaze(Marin marin) {
		
		Document document = DocumentHelper.createDocument();
		
		Element element = document.addElement("marin")
								  .addAttribute("id", Long.toString(marin.getId()));
		
		element.addElement("nom")
				.addText(marin.getNom());
		element.addElement("prenom")
				.addText(marin.getPrenom());
		element.addElement("age")
				.addText(Integer.toString(marin.getAge()));
		
		return document;
	}
	
	public void write(Document document, File  file) throws FileNotFoundException, UnsupportedEncodingException {
		
		FileOutputStream out =  new FileOutputStream(file);
		OutputFormat format = OutputFormat.createPrettyPrint();
					 format.setEncoding("UTF-8");
					 
		XMLWriter writer = new XMLWriter(out, format);
		try {
			writer.write(document);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
