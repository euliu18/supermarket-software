package controller;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import view.LogIn;

public class Supermarket extends Application{

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage){
		(new LogIn()).show(primaryStage);
		primaryStage.getIcons().add(new Image("images/supermarketLogo.png"));
	}
	
	/*admin log in--username:admin password: admin
	 * cashier log in--username: cash password: cash
	 * economist log in -- username: eco password: eco */

}
