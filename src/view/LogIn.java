package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Administrator;
import model.Cashier;
import model.Economist;
import model.RWUser;
import model.User;

public class LogIn {
public void show(Stage primaryStage){
		
		Text username=new Text("Username:");
		username.setFont(Font.font("Aharoni",FontWeight.BOLD,FontPosture.ITALIC,20));
		username.setFill(Color.SKYBLUE);
		TextField userField = new TextField();
		userField.setPromptText("Enter Username");
		
		Text password=new Text("Password:");
		password.setFont(Font.font("Aharoni",FontWeight.BOLD,FontPosture.ITALIC,20));
		password.setFill(Color.SKYBLUE);
		PasswordField passField = new PasswordField();
		passField.setPromptText("Enter Password");
		
		Button login = new Button("Log In");
		login.getStyleClass().add("login-but");
		Button cancel = new Button("Cancel");
		cancel.getStyleClass().addAll("cancel","login-but");
		
		GridPane gp = new GridPane();
		gp.setHgap(20);
		gp.setVgap(20);
		gp.setPadding(new Insets(10,10,10,10));
		gp.addRow(0, username, userField);
		gp.addRow(1, password, passField);
		gp.setAlignment(Pos.CENTER);
		
		HBox hb = new HBox(login, cancel);
		hb.setPadding(new Insets(5, 5, 5, 5));
		HBox.setMargin(login, new Insets(0, 10, 0, 0));
		hb.setAlignment(Pos.CENTER);
		
		
		GridPane mainPane = new GridPane();
		Text welcome=new Text("    SuperMarket Software");
		welcome.setId("welcome-text");
		welcome.setFont(Font.font("Tahoma", FontWeight.BOLD, 40));
		welcome.setFill(Color.LIGHTSKYBLUE);
		Image img=new Image("images\\supermarket4.jpg",200,200,false,false);
		ImageView iv=new ImageView(img);
		HBox hbox=new HBox();
		hbox.getChildren().addAll(iv);
		hbox.setSpacing(40);
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(20));
		mainPane.addColumn(0, hbox,welcome, gp,hb);
		mainPane.setId("actiontarget");
		mainPane.setAlignment(Pos.CENTER);
		mainPane.setStyle("-fx-background-image: url('images/background3.jpg');"
				+ "-fx-background-repeat: stretch;");
		
		Scene scene = new Scene(mainPane,650,480);
		scene.getStylesheets().add(getClass().getResource("mystyle.css").toExternalForm());
		primaryStage.setTitle("Supermarket-Log In");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
		login.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				RWUser rwu=new RWUser();
				String username=userField.getText();
				String password=passField.getText();
				User user=rwu.checkLogIn(username, password);
				if(user instanceof Administrator) {
					(new AdministratorView(user)).show(primaryStage);
				}else if(user instanceof Economist) {
					(new EconomistView(user)).show(primaryStage);
				}else if(user instanceof Cashier) {
					(new CashierView(user)).show(primaryStage);
				}else {
					Alert al=new Alert(AlertType.WARNING, "Username and Password is incorrect", ButtonType.OK);
					al.show();
				}
				
			}
			
		});
		
		cancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent ev) {
				System.exit(0);
				
			}
			
		});
	}

}
