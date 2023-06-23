package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.RWSupplier;
import model.Supplier;
import model.User;

public class AddSupplier {
	
	User u;
	String s;
	int i=0;

	public AddSupplier(User u) {
		super();
		this.u = u;
	}
	public AddSupplier(User u,String s) {
		super();
		this.u = u;
		this.s=s;
		i=1;
	}
	
	
	public void show(Stage primaryStage) {
		GridPane gp=new GridPane();

		Label name=new Label("Name: ");
		TextField nameField=new TextField();

		gp.addColumn(0, name);
		gp.addColumn(1, nameField);
		gp.setVgap(10);
		gp.setHgap(10);
		gp.setAlignment(Pos.CENTER);

		Button create=new Button("Create");
		Button back=new Button("Back");

		HBox hbox=new HBox();
		hbox.getChildren().addAll(create,back);
		hbox.setSpacing(10);
		hbox.setAlignment(Pos.CENTER);

		VBox vbox=new VBox();
		vbox.getStyleClass().add("bluebox");
		vbox.setPadding(new Insets(20,20,10,10));
		vbox.getChildren().addAll(gp,hbox);
		vbox.setSpacing(5);
		vbox.setAlignment(Pos.CENTER);

		Scene scene=new Scene(vbox,650,480);
		scene.getStylesheets().add(getClass().getResource("mystyle.css").toExternalForm());
		primaryStage.setTitle("Add User");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

		create.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				RWSupplier rws = new RWSupplier();
				rws.addSupplier(new Supplier(nameField.getText().toString()));
				if(nameField.getText().isEmpty()) {
					Alert al=new Alert(AlertType.WARNING, "Please enter the name of supplier", ButtonType.OK);
					al.show();
				}else {
					Alert al=new Alert(AlertType.INFORMATION, "Supplier is succesfully created", ButtonType.OK);
					al.show();
				}
			}
		});

		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				new ListSupplier(u).show(primaryStage);
			}
		});
	}

}
