package org.konate.tpxml.exo4;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.client.ClientProtocolException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.konate.tpxml.exo3.RSSReader;
import org.xml.sax.SAXException;

public class Main {
	/***
	 * Params : DOC4J Document
	 * Return : List of all message elements attributes name under definitions/types/schema
	 **/
	public static List<String> _AttributesNames(Document doc) {
		String xpathExpression = "/*[name()='definitions']/*[name()='types']/xs:schema/xs:element/@name";
		List<String> names = new ArrayList<>();
		List<Node> nodes = doc.selectNodes(xpathExpression);
		for (Node node : nodes) {
			names.add(node.getText());
		}

		return names;
	}

	/***
	 *  Params : DOC4J Document
	 *  Return : List of all attributes "name" under definitions/message
	 **/
	public static List<String> _messageAttributesNames(Document doc) {
		String xpathExpression = "/*[name()='definitions']/*[name()='message']/@name";
		List<String> names = new ArrayList<>();
		List<Node> nodes = doc.selectNodes(xpathExpression);
		for (Node node : nodes) {
			names.add(node.getText());
		}

		return names;
	}

	public static String getPortType(Document doc, String message) {
		String xpathExpression = "/*[name()='definitions']/*[name()='portType']/*[name()='operation']/*/@message";
		List<Node> nodes = doc.selectNodes(xpathExpression);
		String operationName = "";
		for (Node node : nodes) {
			String nameWithoutPrefixe = node.getText().substring(4);
	
			if (nameWithoutPrefixe.equals(message)) {
				Node parent = node.getParent().getParent();
				operationName = doc.valueOf(parent.getPath() + "/@name");
			}
		}
		
		return operationName;
	}

	public static void main(String[] args) throws DocumentException, ClientProtocolException, IOException, SAXException, ParserConfigurationException {
		// TODO Auto-generated method stub
		String url = "https://webservices.amazon.com/AWSECommerceService/2013-08-01/AWSECommerceService.wsdl";

		InputStream in =  (new RSSReader()).read(url);
		SAXReader reader = new SAXReader();
		Document document = reader.read(in);

		String xpathExpression;
		Node root = document.getRootElement();
		//String rootName = root.getName();
		System.out.println("Root Name = " + root.getName());

		//xpathExpression = "/" + rootName + "/message" ;
		xpathExpression = "/*[name()='definitions']/*[name()='types']/xs:schema/*" ;
		List<Node> schema = document.selectNodes(xpathExpression);
		System.out.println(schema.size() + " elements are under 'types/schema'");

		List<String> names = _AttributesNames(document);
		System.out.println("Attributes Names -> " + names);

		List<String> messageAttributes = _messageAttributesNames(document);
		System.out.println("Message Attributes Name -> " + messageAttributes);
		
		//doc.selectSingleNode(parent.getPath() + "/@name").getText()
		getPortType(document, "CartModifyRequestMsg");
		//System.out.println("Operation of 'CartModifyRequestMsg' is : " + getPortType(document, "CartModifyRequestMsg"));
	}

}
