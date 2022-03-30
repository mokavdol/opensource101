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

public class indexer {
    private String input_file;
    private String output_file = "./index.post";

    public indexer(String path){
        this.input_file = path;
    }

    public void convertTFIDF() throws ParserConfigurationException, IOException, SAXException, ClassNotFoundException {
        File collection = new File(this.input_file);
        List<HashMap<String,Integer>> keywordHashs = new ArrayList<>();

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.parse(collection);
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("doc");

        for (int i = 0 ; i < nList.getLength() ; i++){
            HashMap<String,Integer> keywordHash = new HashMap<>();
            Node nNode = nList.item(i);

            Element element = (Element) nNode;

            String body = element.getElementsByTagName("body").item(0).getTextContent();

            String[] keywords = body.split(" # ");
            for(String keyword : keywords){
                String word = keyword.split(" : ")[0];
                Integer cnt = Integer.parseInt(keyword.split(" : ")[1]);
                keywordHash.put(word,cnt);
            }

            keywordHashs.add(keywordHash);
        }

        HashMap<String,String> keywordTFIDF = new HashMap<>();

        for (int i = 0 ; i < keywordHashs.size() ; i++){
            HashMap<String,Integer> keywordHash = keywordHashs.get(i);

            for(String keyword : keywordHash.keySet()){
                if(!keywordTFIDF.containsKey(keyword)){
                    keywordTFIDF.put(keyword,String.valueOf(i) + " " + Math.round(getTFIDF(keywordHashs,keyword,i) * 100.0) / 100.0);
                }
                else{
                    keywordTFIDF.put(keyword,keywordTFIDF.get(keyword) + " " + i + " " + Math.round(getTFIDF(keywordHashs,keyword,i) * 100.0) / 100.0);
                }
            }
        }

        FileOutputStream fileStream = new FileOutputStream(output_file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileStream);
        objectOutputStream.writeObject(keywordTFIDF);
        objectOutputStream.close();

        FileInputStream readFileStream = new FileInputStream(output_file);
        ObjectInputStream objectInputStream = new ObjectInputStream(readFileStream);

        Object object = objectInputStream.readObject();
        objectInputStream.close();

        System.out.println("읽어온 객체의 type -> " + object.getClass());

        HashMap hashMap = (HashMap) object;
        Iterator<String> it = hashMap.keySet().iterator();

        while (it.hasNext()){
            String key = it.next();
            String value = (String) hashMap.get(key);
            System.out.println(key + " -> " + value);
        }
    }

    private Double getTFIDF(List<HashMap<String,Integer>> keywordHashs, String keyword, Integer documentNo){
        int n = keywordHashs.size();
        int tf = getTF(keywordHashs,keyword,documentNo);
        int df = getDF(keywordHashs,keyword);

        return tf * this.baseLog((n / (double)df),2);
    }

    private Integer getTF(List<HashMap<String,Integer>> keywordHashs,String keyword, Integer documentNo){
        return keywordHashs.get(documentNo).get(keyword);
    }

    private Integer getDF(List<HashMap<String,Integer>> keywordHashs, String keyword){
        int count = 0;
        for(HashMap<String,Integer> keywordHash : keywordHashs){
            Integer keywordCount =  keywordHash.getOrDefault(keyword,0);
            if(keywordCount != 0){
                count = count + 1;
            }
        }
        return count;
    }

    private double baseLog(double x, double base){
        return Math.log(x) / Math.log(base);
    }
}
