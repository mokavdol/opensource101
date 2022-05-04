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
	public MidTerm(String path, String query) {
		this.query = query;
		this.input_file = path;
	}
	//makecollection에서 documenbuilder 했던것을 이용, 타이틀 불러오기
	//문서의 첫 30음절을 문자열로 저장한다. 해당 문자열을 thir와 snippet 두개의 변수에 저장한다.
	//query 를 분석해 형태소 목록을 만들어 kwlist에 저장한다.
	//반복문을 열고 indexer에서 사용했던것과 같은 방식으로 document내의 kwlist[i]의 개수를 찾는다.
	//해당 키워드 개수가 저장된 문자열보다 snippet의 키워드 개수보다 많을경우엔 snippet에 document를 저장한다.
	//snippet의 키워드 개수를 matchingScore에 저장한다.
	//document에 이전 document보다 한음절 뒤의 문자열을 저장하고 문서가 끝날때 까지 반복한다.
	//title과 snippet, matching_score를 출력한다.
	//해당 과정을 kwlist[i]에서 i+1하며 i=kwlistSize가 될때까지 반복한다.
	
	
	
	
	//문제 : 제가 자바를 할 줄 몰라 해당과정을 진행하는데 일주일은 걸릴 것 같습니다...
	
	
	
	
	
	public void showSnippet() {
		private String query;
		private String title;
		private String snippet;
		private int matchingScore;
		private String document;
		
		
		
	}
}
