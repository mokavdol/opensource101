package scripts;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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

}
