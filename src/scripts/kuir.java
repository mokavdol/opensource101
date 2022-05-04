package scripts;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class kuir {

	public static void main(String[] args) throws ParserConfigurationException, IOException, TransformerException, SAXException, ClassNotFoundException {
		
		String command = args[0];   
		String path = args[1];
<<<<<<< HEAD
		String command2 = args[2];
		String query = args[3];
=======
>>>>>>> 44d4964848e8fc090cdf48a818992cf16cc635ec

		if(command.equals("-c")) {
			makeCollection collection = new makeCollection(path);
			collection.makeXml();
		}
		else if(command.equals("-k")) {
			makeKeyword keyword = new makeKeyword(path);
			keyword.convertXml();
		}
		else if(command.equals("-i")) {
			indexer indexer = new indexer(path);
			indexer.convertTFIDF();
		}
<<<<<<< HEAD
		else if(command.equals("-m")) {
			if(command2.equals("-q")) {
				MidTerm MidTerm = new MidTerm(path, query);
				MidTerm.showSnippet();
			}
		}
=======
>>>>>>> 44d4964848e8fc090cdf48a818992cf16cc635ec
	}
}
