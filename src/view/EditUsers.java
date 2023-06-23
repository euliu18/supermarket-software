package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.MyDate;
import model.RWUser;
import model.User;

public class EditUsers {
	
	private User user;
	private User edit;
	int choice=0;
	int position = 0;
	private Label phone=new Label("Phone: ");
	private TextField phoneField=new TextField();

	public EditUsers(User user,User edit) {
		super();
		this.user = user;
		this.edit=edit;
	}
	public void showEconomist(Stage primaryStage, int position) {
		choice=1;
		this.position = position;
		show(primaryStage);
	}
	public void showCashier(Stage primaryStage, int position) {
		choice=2;
		this.position = position;
		show(primaryStage);
	}
	public void show(Stage primaryStage) {
		BorderPane border=new BorderPane();
		GridPane grid=new GridPane();
		grid.setAlignment(Pos.CENTER);
		Label name=new Label("Name: ");
		Label surname=new Label("Surname: ");
		TextField nameField=new TextField(edit.getName());
		TextField surnameField=new TextField(edit.getSurname());
		DatePicker datePicker =new DatePicker();

		Label date=new Label("Birthday: ");

		Label salary = new Label("Salary: ");
		TextField salaryField =new TextField();
		salaryField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
				if (!newValue.matches("\\d*\\.*")) {
					salaryField.setText(newValue.replaceAll("[^\\d\\.]", ""));
				}
			}
		});

		phoneField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					phoneField.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		if(choice==1){
			grid.addColumn(0,name,surname,date,salary,phone );
			grid.addColumn(1, nameField,surnameField,datePicker,salaryField,phoneField);
		} else {
			grid.addColumn(0,name,surname,date,salary );
			grid.addColumn(1, nameField,surnameField,datePicker,salaryField);
		}



		Button ok =new Button("Save Changes");
		Button back=new Button("Back");

		grid.setVgap(10);
		grid.setHgap(10);
		grid.setPadding(new Insets(10,10,10,10));
		border.setCenter(grid);
		HBox hb=new HBox();
		hb.getChildren().addAll(ok,back);
		hb.setSpacing(10);
		hb.setAlignment(Pos.CENTER);
		hb.setPadding(new Insets(0, 0, 40, 0));
		border.setBottom(hb);
		Scene scene=new Scene(border,650,480);
		scene.getStylesheets().add(getClass().getResource("mystyle.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Edit User");
		primaryStage.setResizable(false);
		primaryStage.show();

		//actioncs


	ok.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent actionEvent) {
			RWUser editUser = new RWUser();

			if(choice==1){
				if(phoneField.getText().isEmpty()) {
					Alert al=new Alert(Alert.AlertType.WARNING, "Don't leave the phone field empty", ButtonType.OK);
					al.show();
				}
			}

			if(datePicker.getValue() == null){
				Alert al=new Alert(Alert.AlertType.WARNING, "Don't leave the birthday field empty", ButtonType.OK);
				al.show();
			} else {
				if(choice == 1) {
					String salary = "0";
					if(salaryField.getText()==null)
						salary = "0";
					else
						salary = salaryField.getText();
					editUser.edit(position, nameField.getText(), surnameField.getText(), new MyDate(datePicker.getValue().toString()), phoneField.getText(), Double.parseDouble(salary));
					(new UserList(edit)).showEconomist(primaryStage);
				} else if(choice == 2) {
					editUser.edit(position, nameField.getText(), surnameField.getText(), new MyDate(datePicker.getValue().toString()), Double.parseDouble(salaryField.getText()));
					(new UserList(edit)).showCashier(primaryStage);
				}
			}
		}
	});

	  back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if(choice==1)
					(new UserList(edit)).showEconomist(primaryStage);
				else if(choice==2)
					(new UserList(edit)).showCashier(primaryStage);
				else
					(new UserList(edit)).show(primaryStage);
			}
		});

	}


}
