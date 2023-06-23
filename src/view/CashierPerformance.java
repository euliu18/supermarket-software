package view;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Cashier;
import model.RWUser;
import model.User;

public class CashierPerformance {

	User user;
	Cashier cashier;

	public CashierPerformance(Cashier cashier, User user) {
		super();
		this.user = user;
		this.cashier=cashier;
	}
	
	
	
	public void show(Stage primaryStage) {
		RWUser rwu = new RWUser();
		ArrayList<Cashier> ecn=new ArrayList();
		for(User x:rwu.readUsers()) {
			if(x instanceof Cashier) {
				ecn.add((Cashier) x);
			}
		}
		ObservableList<User> users=FXCollections.observableArrayList(ecn);
		TableView table=new TableView();
		table.setEditable(true);
		
		TableColumn id=new TableColumn("ID");
		TableColumn name=new TableColumn("Name");
		TableColumn surname=new TableColumn("Surname");
		TableColumn salary=new TableColumn("Salary");
		
		
		id.setCellValueFactory(new PropertyValueFactory("ID"));
		name.setCellValueFactory(new PropertyValueFactory("Name"));
		surname.setCellValueFactory(new PropertyValueFactory("Surname"));
		salary.setCellValueFactory(new PropertyValueFactory("Salary"));
		
		
		table.setItems(users);
		table.getColumns().addAll(id,name,surname,salary);
		
		Button viewPerformance=new Button("View Performance");
		Button back=new Button("Back");
		
		HBox hbox = new HBox();
		hbox.getChildren().addAll(viewPerformance,back);
		hbox.setPadding(new Insets(10,10,10,10));
		hbox.setSpacing(10);
		
		BorderPane border = new BorderPane();
		border.setCenter(table);
		border.setBottom(hbox);
		
		Scene scene=new Scene(border);
		primaryStage.setScene(scene);
		scene.getStylesheets().add(getClass().getResource("mystyle.css").toExternalForm());
		primaryStage.setTitle("Cashier list");
		primaryStage.show();

		viewPerformance.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				(new CashierPerformanceList(ecn.get(table.getSelectionModel().getFocusedIndex()), user)).show(primaryStage);
			}
		});

		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				(new EconomistView(user)).show(primaryStage);
				
			}
			
		});
		
		
	}
}
