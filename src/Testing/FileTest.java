package Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.Main;

class FileTest {

	@Test
	void test() {
		JunitTesting test = new JunitTesting();
		String output1 = test.FileName("src/application/poem.txt");
		assertEquals("src/application/poem.txt", output1);
	}

}
