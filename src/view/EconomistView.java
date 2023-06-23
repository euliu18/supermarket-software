package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Cashier;
import model.User;

public class EconomistView {

	User user;
	Cashier cashier;
	
	public EconomistView() {
		
	}
	
	public EconomistView(User user) {
		super();
		this.user=user;
	}
	
	public void show(Stage primaryStage) {
		BorderPane border=new BorderPane();
		MenuBar menuBar=new MenuBar();
		border.getStyleClass().add("bluebox");
		
		Label logout=new Label("Log Out");
		Menu lg =new Menu("",logout);
		menuBar.getMenus().addAll(lg);
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		Label welcome=new Label("    Welcome "+user.getName());
		welcome.setStyle("-fx-font-size:40px;"
				+ "-fx-font-weight :normal;"+"-fx-font-size: 28px;\r\n" + 
						"   -fx-font-family: \"Segoe UI Semibold\";\r\n" + 
						"   -fx-fill: #818181;\r\n" + 
						"   -fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.7) , 6, 0.0 , 0 , 2 );");
		
		Button products=new Button("List of products");
		Button supplier=new Button("Supplier");
		Button add=new Button("Add products");
		Button buy=new Button("Purchase products");
		Button performance=new Button("Cashier performance");
		
		VBox vbox=new VBox();
		vbox.getChildren().addAll(products,supplier,add,buy,performance);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(10);
		
		grid.addColumn(0, welcome,vbox);
		grid.setVgap(10);
		grid.setHgap(10);
		grid.setPadding(new Insets(10,10,10,10));
		grid.setAlignment(Pos.CENTER);
		border.setTop(menuBar);
		border.setCenter(grid);
		
		logout.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent e) {
				(new LogIn()).show(primaryStage);
				
			}
		});
		
		
		Scene scene=new Scene(border,650,480);
		primaryStage.setScene(scene);
		scene.getStylesheets().add(getClass().getResource("mystyle.css").toExternalForm());
		primaryStage.setTitle("Economist View");
		primaryStage.setResizable(false);
		primaryStage.show();
		
		supplier.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				(new ListSupplier(user)).show(primaryStage);
				
			}
			
		});

		add.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				(new AddProducts(user)).show(primaryStage);
			}
		});
		
		products.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				(new ListProducts(user)).show(primaryStage);
				
			}
			
		});
		
		buy.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				(new ListProducts(user)).showEconomist(primaryStage);
				
			}
			
		});
		
		performance.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				(new CashierPerformance(cashier, user)).show(primaryStage);
				
			}
			
		});
		
	}
}
