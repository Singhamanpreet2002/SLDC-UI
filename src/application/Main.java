package application;
	
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Main extends Application {

	public static final String BLANK = "";

	private static final int List = 0;

	private static final int String = 0;

	GridPane grid;
	Node enterWordLabel;

	Label countLabel = null;
	Node wordField;

	TextField countField;
	Button countButton;

	Map<String, Integer> map = new HashMap<String, Integer>();

	@Override
	public void start(Stage stage) throws Exception {

		loadWordsMap();
		
		stage.setTitle("Word Counter for the Poem");

		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		enterWordLabel = new Label("Search Word:");
		grid.add(enterWordLabel, 0, 0);
		
		wordField = new TextField();
		grid.add(wordField, 1, 0);
		
		countButton = new Button("Count");
		grid.add(countButton, 1, 1);

		countLabel = new Label("Word Count:");
		grid.add(countLabel, 0, 2);

		countField = new TextField();
		countField.setEditable(false);
		grid.add(countField, 1, 2);

		countButton.setOnAction(actionEvent -> {
			countField.setText(BLANK);
			String word = ((TextInputControl) wordField).getText();
			Integer count = map.get(word);
			countField.setText("" +count );
		});
		
		Scene scene = new Scene(grid, 700, 275);
		stage.setScene(scene);

		stage.show();
	}

	private void loadWordsMap() throws Exception {

		String Word;
		File poem = new File("src/application/poem.txt");
		BufferedReader in;
		in = new BufferedReader(new FileReader(poem));

		// Mapping
		while ((Word = in.readLine()) != null) {
			String[] word = Word.split("[ \n\t\r.,;:!?(){}]");
			for (String val : word)
				if (map.containsKey(val) == false)
					map.put(val, 1);
				else {
					int count = (int) (map.get(val));
					map.remove(val);
					map.put(val, count + 1);
				}
		}
		Set<Map.Entry<String, Integer>> set = map.entrySet();
		List<Map.Entry<String, Integer>> sortedList = new ArrayList<Map.Entry<String, Integer>>(set);
		Collections.sort(sortedList, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
				return (b.getValue()).compareTo(a.getValue());
			}
		});
				int j=0;
				List<String> words = new ArrayList<String>();
				for (Map.Entry<String, Integer> i : sortedList) {
					j++;
					if(j < 21) {
						words.add(i.getKey() + i.getValue());
						System.out.println(i.getKey() + " -> " + i.getValue());
					}
					else {
						break;
					}
				}
		in.close();

	}

	public void alert(String title, String message, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
