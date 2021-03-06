package scripts;

<<<<<<< HEAD
import org.jsoup.Jsoup;
import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;
=======
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

>>>>>>> 44d4964848e8fc090cdf48a818992cf16cc635ec
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

<<<<<<< HEAD
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

=======
>>>>>>> 44d4964848e8fc090cdf48a818992cf16cc635ec
/**
 * 3주차 실습 코드
 * 
 * kkma 형태소 분석기를 이용하여 index.xml 파일을 생성하세요.
 * 
 * index.xml 파일 형식은 아래와 같습니다.
 * (키워드1):(키워드1에 대한 빈도수)#(키워드2):(키워드2에 대한 빈도수)#(키워드3):(키워드3에 대한 빈도수) ... 
 * e.g., 라면:13#밀가루:4#달걀:1 ...
 * 
 * input : collection.xml
 * output : index.xml 
 */

public class makeKeyword {

	private String input_file;
	private String output_flie = "./index.xml";
	
	public makeKeyword(String file) {
		this.input_file = file;
	}

<<<<<<< HEAD
	public void convertXml() throws ParserConfigurationException, IOException, SAXException, TransformerException {
		File collection = new File(this.input_file);
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		Document indexDoc = docBuilder.newDocument();
		Element xmlDocs = indexDoc.createElement("docs");

		Document doc = docBuilder.parse(collection);
		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("doc");
		for (int i = 0 ; i < nList.getLength() ; i++){
			Node nNode = nList.item(i);

			Element element = (Element) nNode;
			String title = element.getElementsByTagName("title").item(0).getTextContent();
			String body = element.getElementsByTagName("body").item(0).getTextContent();

			KeywordExtractor ke = new KeywordExtractor();
			KeywordList kl = ke.extractKeyword(body,true);

			String indexBody = "";
			for(int j = 0; j < kl.size() ; j++){
				Keyword kwrd = kl.get(j);
				String noun = kwrd.getString();
				Integer nounCount = kwrd.getCnt();
				indexBody = indexBody + noun + " : " + nounCount + " # ";
			}

			Element xmlDoc = indexDoc.createElement("doc");
			xmlDoc.setAttribute("id",Integer.toString(i));

			Element xmlTitle = indexDoc.createElement("title");
			Element xmlBody = indexDoc.createElement("body");

			xmlTitle.setTextContent(title);
			xmlBody.setTextContent(indexBody);

			xmlDoc.appendChild(xmlTitle);
			xmlDoc.appendChild(xmlBody);
			xmlDocs.appendChild(xmlDoc);
		}

		indexDoc.appendChild(xmlDocs);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");

		DOMSource source = new DOMSource(xmlDocs);
		StreamResult result = new StreamResult(new FileOutputStream(output_flie));

		transformer.transform(source,result);
	}
=======
	public void convertXml() throws ParserConfigurationException, SAXException, IOException {
		File collection = new File(this.input_file);
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(collection);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("doc");
        for (int i = 0 ; i < nList.getLength() ; i++){
            Node nNode = nList.item(i);
            Element element = (Element) nNode;
            System.out.println(element.getElementsByTagName("title").item(0).getTextContent());
            System.out.println(element.getElementsByTagName("body").item(0).getTextContent());
        }
        
        
		System.out.println("3주차 실행완료");
	}

>>>>>>> 44d4964848e8fc090cdf48a818992cf16cc635ec
}
