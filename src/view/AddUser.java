package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Administrator;
import model.Cashier;
import model.Economist;
import model.MyDate;
import model.RWUser;
import model.User;

public class AddUser {
	
	User user;
	int choice=0;
	User editable;
	boolean isedit=false;
	
	public AddUser(User user) {
		super();
		this.user = user;
	}

	public void showEconomist(Stage primaryStage) {
		choice=1;
		show(primaryStage);
	}
	
	public void showCashier(Stage primaryStage) {
		choice=2;
		show(primaryStage);
	}


	public void show(Stage primaryStage) {
		RWUser rwu=new RWUser();
		int id=rwu.readUsers().size()+1;
		GridPane gp=new GridPane();
		
		Label username=new Label("Username: ");
		Label password=new Label("Password: ");
		Label confirmPass=new Label("Confirm Password: ");
		TextField userField=new TextField();
		PasswordField passField=new PasswordField();
		PasswordField confirmField=new PasswordField();
		
		Label type=new Label("Type ");
		ChoiceBox choicebox=new ChoiceBox(FXCollections.observableArrayList("Administrator","Economist","Cashier"));
		
		if(choice==1) {
			choicebox.getSelectionModel().select(1);
		}else if(choice==2) {
			choicebox.getSelectionModel().select(2);
		}
		
		Label name=new Label("Name: ");
		Label surname=new Label("Surname");
		TextField nameField=new TextField();
		TextField surnameField=new TextField();
		
		DatePicker datepicker=new DatePicker();
		Label birthdayDate=new Label("Birthday: ");
		
		Label phone =new Label("Phone number: ");
		TextField phoneField=new TextField();
		phoneField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					Alert al=new Alert(AlertType.WARNING, "Please enter a valid phone number", ButtonType.OK);
					al.show();
				}
			}
		});
		
		Label salary=new Label("Salary: ");
		TextField salaryField=new TextField();
		salaryField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
				if (!newValue.matches("\\d*\\.*")) {
					Alert al=new Alert(AlertType.WARNING, "Please enter a valid salary", ButtonType.OK);
					al.show();
				}
			}
		});
		
		gp.addColumn(0, username,password,confirmPass,name,surname,type,birthdayDate,phone,salary);
		gp.addColumn(1, userField,passField,confirmField,nameField,surnameField,choicebox,datepicker,phoneField,salaryField);
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
		
		back.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				(new AdministratorView(user)).show(primaryStage);
			}
		});
		
		create.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if(choicebox.getSelectionModel().getSelectedItem()!=null) {
					if(salaryField.getText().isEmpty()||userField.getText().isEmpty()||passField.getText().isEmpty()||confirmField.getText().isEmpty()
							||nameField.getText().isEmpty()||surnameField.getText().isEmpty()||datepicker.getValue() == null
							||phoneField.getText().isEmpty()){
						Alert al=new Alert(AlertType.WARNING, "Please fill all the boxes", ButtonType.OK);
						al.show();
					}  else if(!passField.getText().equals(confirmField.getText())){
						Alert al=new Alert(AlertType.WARNING, "The passwords should be the same", ButtonType.OK);
						al.show();
					}else {
						if(choicebox.getSelectionModel().getSelectedItem().equals("Administrator")) {
							double s=Double.parseDouble(salaryField.getText());
							rwu.addUser(new Administrator(userField.getText(),passField.getText(),nameField.getText(),surnameField.getText(),id,s,new MyDate(datepicker.getValue().toString())));
							(new UserList(user)).show(primaryStage);
							primaryStage.show();
						}else if(choicebox.getSelectionModel().getSelectedItem().equals("Economist")) {
							double s=Double.parseDouble(salaryField.getText());
							rwu.addUser(new Economist(userField.getText(),passField.getText(),nameField.getText(),surnameField.getText(),id,s,new MyDate(datepicker.getValue().toString()),phoneField.getText().toString()));
							(new UserList(user)).show(primaryStage);
							primaryStage.show();
						}else if(choicebox.getSelectionModel().getSelectedItem().equals("Cashier")) {
							double s=Double.parseDouble(salaryField.getText());
							rwu.addUser(new Cashier(userField.getText(), passField.getText(), nameField.getText(), surnameField.getText(), id, s,new MyDate(datepicker.getValue().toString())));
							(new UserList(user)).show(primaryStage);
							primaryStage.show();
						} else {
							Alert al=new Alert(AlertType.WARNING, "Please enter the type of the user", ButtonType.OK);
							al.show();
						}
					}


				}
				
			}
			
		});
		
			
	}

	private void Alert(String message){
		Alert al=new Alert(AlertType.WARNING, message, ButtonType.OK);
		al.show();
	}

}
