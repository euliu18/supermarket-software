package view;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Cashier;
import model.Economist;
import model.RWUser;
import model.User;

public class UserList {
	
	User user;
	int choice=0;
	
	public UserList(User user) {
		super();
		this.user = user;
	}
	
	public void showEconomist(Stage primaryStage){
		choice=1;
		show(primaryStage);
	}
	
	public void showCashier(Stage primaryStage){
		choice=2;
		show(primaryStage);
	}

	public void show (Stage primaryStage) {
		RWUser rwu=new RWUser();
		ObservableList<User> users=FXCollections.observableArrayList(rwu.readUsers());
		TableView table=new TableView();
		if(choice==1) {
			ArrayList<Economist> ecn=new ArrayList();
			for(User x:rwu.readUsers()) {
				if(x instanceof Economist) {
					ecn.add((Economist) x);
				}
			}
			users=FXCollections.observableArrayList(ecn);
		}
		
		if(choice==2) {
			ArrayList<Cashier> ecn=new ArrayList();
			for(User x:rwu.readUsers()) {
				if(x instanceof Cashier) {
					ecn.add((Cashier) x);
				}
			}
			users=FXCollections.observableArrayList(ecn);
		}
		
		table.setEditable(true);
		TableColumn id=new TableColumn("ID");
		TableColumn name=new TableColumn("Name");
		TableColumn surname=new TableColumn("Surname");
		TableColumn type=new TableColumn("Type");
		TableColumn salary=new TableColumn("Salary");
		
		id.setCellValueFactory(new PropertyValueFactory<>("ID"));
		name.setCellValueFactory(new PropertyValueFactory<>("Name"));
		surname.setCellValueFactory(new PropertyValueFactory<>("Surname"));
		type.setCellValueFactory(new PropertyValueFactory<>("Type"));
		salary.setCellValueFactory(new PropertyValueFactory<>("Salary"));
		table.setItems(users);
		table.getColumns().addAll(id,name,surname,type,salary);
		
		Button add=new Button("Add");
		Button del=new Button("Delete");
		Button back=new Button("Back");
		Button edit=new Button("Edit");
		
		HBox hbox=new HBox();
		hbox.getChildren().addAll(add,del,edit,back);
		hbox.setPadding(new Insets(10,10,10,10));
		hbox.setSpacing(10);
		
		BorderPane border=new BorderPane();
		border.setCenter(table);
		border.setBottom(hbox);
		
		Scene scene=new Scene(border);
		primaryStage.setScene(scene);
		scene.getStylesheets().add(getClass().getResource("mystyle.css").toExternalForm());
		if(choice==1) {
			primaryStage.setTitle("List of Economists");
		}else if(choice==2) {
			primaryStage.setTitle("List of Cashiers");
		}else {
			primaryStage.setTitle("List of all Users");
		}
		
		add.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(choice==0)(new AddUser(user)).show(primaryStage);
				else if(choice==1)(new AddUser(user)).showEconomist(primaryStage);
				else if(choice==2)(new AddUser(user)).showCashier(primaryStage);
				
			}
			
		});
		
		edit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if(!table.getSelectionModel().getSelectedItems().isEmpty()) {
				User a=(User)table.getSelectionModel().getSelectedItem();
				if(a instanceof Economist){
					(new EditUsers(user,a)).showEconomist(primaryStage, table.getSelectionModel().getFocusedIndex());
				} else if(a instanceof Cashier){
					(new EditUsers(user,a)).showCashier(primaryStage, table.getSelectionModel().getFocusedIndex());
				}
				}else {
					Alert al=new Alert(AlertType.WARNING, "Please select a user!", ButtonType.OK);
					al.show();
				}
				
			}
			
		});
		
		del.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(!table.getSelectionModel().getSelectedItems().isEmpty()) {
				rwu.deleteUser((User)table.getSelectionModel().getSelectedItem());
				if(choice==1) {
					showEconomist(primaryStage);
					
				}else if(choice==2) {
					showCashier(primaryStage);
				}else
					show(primaryStage);
				}
				else {
					Alert al=new Alert(AlertType.WARNING, "Please select a user!", ButtonType.OK);
					al.show();
				}
			}
			
		});
		
		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				(new AdministratorView(user)).show(primaryStage);
				
			}
			
		});
		
	}

}
