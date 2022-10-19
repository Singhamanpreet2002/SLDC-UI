package Testing;

import static org.junit.Assert.assertEquals;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

public class WordCheck {
	String poem;
	@BeforeClass
	public void getMapOfWords() throws IOException {
	    JunitTesting junit = new JunitTesting();
	    poem = junit.getPeomWords();
	  }
	
	 	public void testWordChamber() {
		    int n = getStringCountInPoem("chamber");
		    assertEquals(n, 11);
		  }
	public int getStringCountInPoem(String s) {
	    for (String k : poem.split("\n")) {
	      if (k.contains(s)) {
	        return Integer.parseInt(k.split(" ")[0]);
	      }
	    }
	    return 0;
	  }

}
