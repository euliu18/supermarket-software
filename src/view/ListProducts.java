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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Products;
import model.RWProducts;
import model.Type;
import model.User;

public class ListProducts {
	User user;
	int choice=0;
	int issearch=0;
	ArrayList<Products> a;
	
	
    public ListProducts(User user) {
		super();
		this.user = user;
	}
    
    public void showEconomist(Stage primaryStage) {
    	choice=1;
    	show(primaryStage);
    }
    
    public void show(Stage primaryStage,ArrayList<Products> a) {
    	this.a=a;
    	issearch=1;
    	show(primaryStage);
    }
    
    public void showEconomist(Stage primaryStage,ArrayList<Products> a) {
    	this.a=a;
    	issearch=1;
    	showEconomist(primaryStage);
    }


	public void show (Stage primaryStage) {
		RWProducts rwp=new RWProducts();
		
		Label search=new Label("Search Product Category: ");
		TextField searchField=new TextField();
		Button searchButton=new Button("Search");
		searchField.setPrefSize(300, 50);
		
		ObservableList<Products> prod;
		TableView table = new TableView();
		table.setEditable(true);
		
		if(issearch==1) {
			prod=FXCollections.observableArrayList(a);
		}else {
			prod=FXCollections.observableArrayList(rwp.readProducts());
		}
        
		HBox hbox=new HBox();
		hbox.getChildren().addAll(search,searchField,searchButton);
		hbox.setPadding(new Insets(10,10,10,10));
		hbox.setSpacing(10);
		
		searchButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				ArrayList<Products> a=new ArrayList<Products>();
				if(searchField.getText().isEmpty()) {
					Alert al=new Alert(AlertType.WARNING, "Please enter a categoty!", ButtonType.OK);
					al.show();
				}else {
					for(Products x:rwp.readProducts()) {
					System.out.println("---"+x.getCategory().matches(searchField.getText().toString()+".*")
							+"\n"+rwp.readProducts().size());
					
					if(x.getCategory().matches(searchField.getText().toString()+".*")) {
						a.add(x);
						System.out.println("a :"+a.size());
					}
					
				}
					if(choice==1)showEconomist(primaryStage, a);
					else show(primaryStage, a);
				}
				
				
			}
		});
		
		TableColumn barcode=new TableColumn("Barcode");
		barcode.setCellValueFactory(new PropertyValueFactory("barcode"));
		TableColumn name=new TableColumn("Name");
		name.setCellValueFactory(new PropertyValueFactory("name"));
		TableColumn category=new TableColumn("Category");
		category.setCellValueFactory(new PropertyValueFactory("category"));
		TableColumn supplier=new TableColumn("Supplier");
		supplier.setCellValueFactory(new PropertyValueFactory("supplier"));
		TableColumn dataCreated=new TableColumn("Date Created");
		dataCreated.setCellValueFactory(new PropertyValueFactory("dateCreated"));
		TableColumn expiryDate=new TableColumn("Expiry Date");
		expiryDate.setCellValueFactory(new PropertyValueFactory("expiry"));
		TableColumn cost=new TableColumn("Price");
		cost.setCellValueFactory(new PropertyValueFactory("cost"));
		TableColumn onStock=new TableColumn("On Stock");
		onStock.setCellValueFactory(new PropertyValueFactory("stock"));
		
		table.setItems(prod);
		table.getColumns().addAll(barcode,name,category,supplier,cost,onStock,expiryDate,dataCreated);
		
		Button delete=new Button("Delete");
		Button back = new Button("Back");
		Button buy= new Button("Buy");
		
		Label quantity=new Label("Quantity: ");
		TextField quantityField=new TextField();
		
		HBox hbox2=new HBox();
		
		hbox2.setPadding(new Insets(10,10,10,10));
		hbox2.setSpacing(10);
		
		if(choice==1) {
			hbox2.getChildren().addAll(quantity,quantityField,buy,back);
			
		}else {
			hbox2.getChildren().addAll(delete,back);
		}
		
		BorderPane border=new BorderPane();
		border.setTop(hbox);
		border.setCenter(table);
		border.setBottom(hbox2);
		Scene scene=new Scene(border);
		primaryStage.setScene(scene);
		scene.getStylesheets().add(getClass().getResource("mystyle.css").toExternalForm());
		primaryStage.setTitle("List of Products");
		
		
		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(user.getType()==Type.Economist) {
					(new EconomistView(user)).show(primaryStage);
				}
				else {
					(new AdministratorView(user)).show(primaryStage);
				}
				
			}
		});
		
		delete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {		
				Products p=(Products) table.getSelectionModel().getSelectedItem();
				if(p==null) {
					Alert al=new Alert(AlertType.WARNING, "Please select one!", ButtonType.OK);
					al.show();
				}else {
					rwp.deleteProduct(p);
					show(primaryStage);
				}
			}
		});
		
		buy.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				RWProducts products = new RWProducts();
				int index = table.getSelectionModel().getFocusedIndex();
				products.addQuantity(Integer.parseInt(quantityField.getText()), prod.get(index).getBarcode());
				show(primaryStage);
				
			}
			
		});
		
    }

}
