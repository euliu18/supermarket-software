package view;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

public class AdministratorView {
	
User user;
	
	public AdministratorView() {
		
	}
	public AdministratorView(User user) {
		super();
		this.user = user;
	}
	
  public void show(Stage primaryStage) {
		
		BorderPane mainPane=new BorderPane();
		Accordion panes=new Accordion();
		ArrayList<TitledPane> titlepane=new ArrayList<TitledPane>();
		panes.getStyleClass().add("titledpane");
		
		MenuBar menuBar=new MenuBar();
		Menu menuEmployees = new Menu("Employees");
		Label logout = new Label("Log Out");
		Menu lg = new Menu("",logout);
		
		logout.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				(new LogIn()).show(primaryStage);
			}
		
		});
		
		MenuItem register=new MenuItem("Register Employee");
		register.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				(new AddUser(user)).show(primaryStage);
			}
		});
		menuEmployees.getItems().addAll(register);
		menuBar.getMenus().addAll(menuEmployees,lg);
		
		logout.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				(new LogIn()).show(primaryStage);
			}
		});
		
		VBox vb1=new VBox();
		vb1.getStyleClass().add("bluebox");
		Button all=new Button("All");
		Button economist=new Button("Economist");
		Button cashier=new Button("Cashier");
		vb1.getChildren().addAll(all,economist,cashier);
		vb1.setAlignment(Pos.CENTER);
		vb1.setSpacing(10);
		
		TitledPane employees=new TitledPane("Employees",vb1);
		titlepane.add(employees);
		
		VBox vb2=new VBox();
		vb2.getStyleClass().add("bluebox");
		Button list=new Button("List of all products");
		Button buy=new Button("Purchase Products");
		Button stock= new Button("View Stock");
		
		vb2.getChildren().addAll(list,buy,stock);
		vb2.setAlignment(Pos.CENTER);
		vb2.setSpacing(10);
		
		TitledPane products=new TitledPane("Products",vb2);
		titlepane.add(products);
		
		VBox vb3=new VBox();
		vb3.getStyleClass().add("bluebox");
		Button results=new Button("Statistic");
		vb3.getChildren().addAll(results);
		vb3.setAlignment(Pos.CENTER);
		vb3.setSpacing(10);
		
		TitledPane sales=new TitledPane("Sales",vb3);
		titlepane.add(sales);
		
		panes.getPanes().addAll(titlepane);
		panes.setExpandedPane(employees);
		mainPane.setCenter(panes);
		mainPane.setTop(menuBar);
		
		Scene scene=new Scene(mainPane,650,480);
		scene.getStylesheets().add(getClass().getResource("mystyle.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Administrator View");
		primaryStage.setResizable(false);
		primaryStage.show();
		
		all.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				(new UserList(user)).show(primaryStage);
				
			}
			
		});
		
		economist.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				(new UserList(user)).showEconomist(primaryStage);
				
			}
			
		});
		
		
		cashier.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				(new UserList(user)).showCashier(primaryStage);
				
			}
			
		});
		

		list.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				(new ListProducts(user)).show(primaryStage);
			}
			
		});
		
		
		buy.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				(new ListProducts(user)).showEconomist(primaryStage);
				
			}
			
		});
		
		results.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				(new SearchYearBox(user)).show();
				
			}
			
		});
		
		stock.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
			(new ViewStock(user)).show();
				
			}
			
		});
		
		
	}

}
