package Testing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class JunitTesting {
	private static String f;

	public String FileName(String f) {
		if (f == "src/application/poem.txt") {
			return f;
		}return "wrong File";
	}
	
	 public String getPeomWords() {
	        Map<String, Word> countMap = new HashMap<String, Word>();
	        Document doc = null;
	        try {
	            doc = Jsoup.connect("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm").get();
	        } catch (IOException e) {

	            e.printStackTrace();
	        }
	        for (Element element : doc.select("div.chapter")) {
	            String text = element.text();
	            for (String word : text.replaceAll("[;—:!‘’?,“.”]", "").split(" ")) {
	                Word wordObj = countMap.get(word);
	                if (wordObj == null) {
	                    wordObj = new Word();
	                    wordObj.word = word;
	                    wordObj.count = 0;
	                    countMap.put(word, wordObj);
	                }
	                wordObj.count++;
	            }
	        }
	        ArrayList<Word> sortedWords = new ArrayList<Word>(countMap.values());
	        Collections.sort(sortedWords, Collections.reverseOrder((a, b) -> {
	            return a.count - b.count;
	        }));
	        String poem = "";
	        for (Word word : sortedWords) {
	            poem += word.count + " " + word.word + "\n";
	        }
	        return poem;
	    }
	 private static class Word {
		    String word;
		    int count;
		    Word(){
		    }
		  }
}
