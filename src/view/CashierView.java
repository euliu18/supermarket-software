package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.User;

public class CashierView {
User user;
	
	public CashierView(User user) {
		super();
		this.user = user;
	}
	
	public CashierView() {
		
	}

	public void show(Stage primaryStage) {
		BorderPane border=new BorderPane();
		GridPane grid=new GridPane();
		border.getStyleClass().add("bluebox");
		
		grid.setAlignment(Pos.CENTER);
		Label welcome=new Label("Welcome "+user.getName());
		welcome.setStyle("-fx-font-size:40px;"
				+ "-fx-font-weight :normal;"+"-fx-font-size: 28px;\r\n" + 
						"   -fx-font-family: \"Segoe UI Semibold\";\r\n" + 
						"   -fx-fill: #818181;\r\n" + 
						"   -fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.7) , 6, 0.0 , 0 , 2 );");
		
		Button view =new Button("View Products");
		Button logout=new Button("Log Out");
		grid.addColumn(0, welcome,view,logout);
		grid.setVgap(10);
		grid.setHgap(10);
		grid.setPadding(new Insets(10,10,10,10));
		border.setCenter(grid);

		view.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				(new ListStock(user)).show(primaryStage);
			}
		});

		logout.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				(new LogIn()).show(primaryStage);
				
			}
			
		});
		
		Scene scene=new Scene(border,650,480);
		scene.getStylesheets().add(getClass().getResource("mystyle.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Cashier View");
		primaryStage.setResizable(false);
		primaryStage.show();
		
		
	}

}
