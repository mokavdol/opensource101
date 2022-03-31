package scripts;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jsoup.Jsoup;
import org.w3c.dom.Element;

/**
 * 2주차 실습 코드
 * 
 * 주어진 5개의 html 문서를 전처리하여 하나의 xml 파일을 생성하세요. 
 * 
 * input : data 폴더의 html 파일들
 * output : collection.xml 
 */

public class makeCollection {

    private String data_path;
    private String output_flie = "./collection.xml";

    public makeCollection(String path) {
        this.data_path = path;
    }

    public File[] makeFileList(String path){
        File dir = new File(path);
        File[] files = dir.listFiles();
        System.out.println(files);
        return files;
    }

    public void makeXml() throws ParserConfigurationException, IOException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        org.w3c.dom.Document doc = docBuilder.newDocument();
        Element xmlDocs = doc.createElement("docs");


        int index = 0;
        for(File file : makeFileList(this.data_path)){
            org.jsoup.nodes.Document html = Jsoup.parse(file,"UTF-8");
            String titleData = html.title();
            String bodyData = html.body().text();

            Element xmlDoc = doc.createElement("doc");
            xmlDoc.setAttribute("id",Integer.toString(index));

            Element xmlTitle = doc.createElement("title");
            Element xmlBody = doc.createElement("body");

            xmlTitle.setTextContent(titleData);
            xmlBody.setTextContent(bodyData);

            xmlDoc.appendChild(xmlTitle);
            xmlDoc.appendChild(xmlBody);
            xmlDocs.appendChild(xmlDoc);

            index++;
        }

        doc.appendChild(xmlDocs);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new FileOutputStream(output_flie));

        transformer.transform(source,result);
    }

}