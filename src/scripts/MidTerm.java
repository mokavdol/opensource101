package scripts;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class MidTerm {
	private String input_file;
	private String query;
	private MidTerm(String path, String query) {
		this.query = query;
		this.input_file = path;
	}
	
	public void showSnippet() {
		
	}
}
