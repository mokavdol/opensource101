package scripts;

import org.jsoup.Jsoup;
import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Array;
import java.util.*;

public class searcher {
    private String html_file = "./index.xml";
    private String input_file;
    private String query;
    private static final Integer DOC_COUNT = 5;
    private static final Integer RANK_COUNT = 3;


    public searcher(String path, String query){
        this.query = query;
        this.input_file = path;
    }

    public void calcSim() throws IOException, ClassNotFoundException, ParserConfigurationException, SAXException {

        KeywordExtractor ke = new KeywordExtractor();
        KeywordList kl = ke.extractKeyword(query,true);

        Integer keywordCount = kl.size();

        String[] keywords = new String[keywordCount];

        int[] keywordTF = new int[keywordCount];

        for(int j = 0; j < keywordCount ; j++){
            Keyword kwrd = kl.get(j);
            String noun = kwrd.getString();
            Integer nounCount = kwrd.getCnt();
            keywords[j] = noun;
            keywordTF[j] = nounCount;
        }

        /* index.post -> HashMap */
        FileInputStream readFileStream = new FileInputStream(input_file);
        ObjectInputStream objectInputStream = new ObjectInputStream(readFileStream);

        Object object = objectInputStream.readObject();
        objectInputStream.close();

        System.out.println("읽어온 객체의 type -> " + object.getClass());

        HashMap hashMap = (HashMap) object;

        double[][] w = new double[DOC_COUNT][keywordCount];

        for (int i = 0; i < keywordCount ; i++){
            String value = (String) hashMap.get(keywords[i]);
            String[] tfidfArray = value.split(" ");
            for(int j = 0 ; j < tfidfArray.length ; j++){
                if((j % 2) == 0){
                    w[Integer.parseInt(tfidfArray[j])][i] = Double.parseDouble(tfidfArray[j + 1]);
                }
            }
        }

        HashMap<Integer,Double> docAndSim = new HashMap<>();
        Double[] sim = new Double[DOC_COUNT];
        for(int i = 0 ; i < DOC_COUNT ; i++){
            Double calc = 0.0;
            for(int j = 0 ; j < keywordCount ; j++){
                calc = calc + (keywordTF[j] * w[i][j]);
            }
            docAndSim.put(i,calc);
            sim[i] = calc;
        }

        Arrays.sort(sim,Collections.reverseOrder());

        Integer[] docRank = new Integer[RANK_COUNT];

        int rankCounter = 0;
        for(int i = 0; i < docRank.length;i++){
            int finalI = i;
            int finalRankCounter = rankCounter;
            docAndSim.forEach((key, value) -> {
                if(value.equals(sim[finalI])){
                    docRank[finalRankCounter] = key;
                }
            });
            rankCounter++;
        }


        File collection = new File(this.html_file);

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.parse(collection);
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("doc");

        System.out.println(Arrays.toString(docRank));
        for(int i = 0 ; i < RANK_COUNT ; i++){
            Element element = (Element) nList.item(docRank[i]);
            System.out.println(element.getElementsByTagName("title").item(0).getTextContent());
        }
    }
}
