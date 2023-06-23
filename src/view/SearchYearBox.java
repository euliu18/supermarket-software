package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.User;

public class SearchYearBox {
	private User user;
	
	public SearchYearBox(User user) {
		super();
		this.user = user;
	}

	public void show() {
		GridPane grid=new GridPane();
		grid.getStyleClass().add("bluebox");
		Label year=new Label("Select year");
		TextField yearField=new TextField();
		
		Button ok=new Button("OK");
		grid.addColumn(0, year,ok);
		grid.addColumn(1, yearField);
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(20);
		grid.setHgap(10);
		
		ok.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(!yearField.getText().isEmpty()) {
					if(!yearField.getText().matches(".*\\D{1,}.*")) {
						yearField.setText("");
						(new Statistics(user)).showM(year.getText().toString());
					}else {
						Alert al=new Alert(AlertType.WARNING, "Please enter a valid year", ButtonType.OK);
						al.show();
					}
				}
				
			}
		});
		
		Stage primaryStage=new Stage();
		Scene scene=new Scene(grid,650,480);
		scene.getStylesheets().add(getClass().getResource("mystyle.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Search Box");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}
