package pk.lkarten.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;



public class LernkartenApp extends Application {


	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(10.0));
		gp.setHgap(5.0);
		gp.setVgap(5.0);
		
		ListView<String> liste = new ListView<String>();
		for (int i = 0; i <100; i++) {
			liste.getItems().add("This item " + i);
		}
		
		Label label1 = new Label("Label 1");
		TextField textField1 = new TextField("Textfield1 ");
		Label label2 = new Label("Label 2");
		TextField textField2 = new TextField("Textfield 2");
		
		Button button = new Button("Start");
		
		gp.add(liste, 0, 0, 1 ,3);
		gp.add(label1, 1, 0);
		gp.add(textField1, 2, 0);
		gp.add(label2, 1, 1);
		gp.add(textField2, 2, 1);
		gp.add(button, 2, 3);
		
		
		Scene scene = new Scene(gp);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}



}
