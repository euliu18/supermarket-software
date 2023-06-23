package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import model.RWSupplier;
import model.Supplier;
import model.User;

public class ListSupplier {

	User user;
	int choice=0;
	
	public ListSupplier(User user) {
		super();
		this.user=user;
	}
	
	public void showAddProducts(Stage primaryStage) {
		choice=1;
		show(primaryStage);
	}
	
	public void show(Stage primaryStage) {
		RWSupplier rws= new RWSupplier();
		ObservableList<Supplier> supplier=FXCollections.observableArrayList(rws.readSupplier());
		TableView table=new TableView();
		table.setEditable(true);
		TableColumn name = new TableColumn("Name");
		name.setCellValueFactory(new PropertyValueFactory("name"));
		table.setItems(supplier);
		table.getColumns().add(name);
		
		Button add=new Button("Add");
		Button delete= new Button("Delete");
		Button back=new Button("Back");
		Button addProducts=new Button("Add more products: ");
		
		HBox hbox=new HBox();
		hbox.getChildren().addAll(add,delete,addProducts,back);
		hbox.setSpacing(10);
		
		BorderPane border=new BorderPane();
		border.setCenter(table);
		border.setBottom(hbox);
		Scene scene=new Scene(border);
		scene.getStylesheets().add(getClass().getResource("mystyle.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("List of suppliers");
		
		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				(new EconomistView(user)).show(primaryStage);
				
			}
		});
		
		delete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {		
				Supplier supplier=(Supplier) table.getSelectionModel().getSelectedItem();
				if(supplier==null) {
					Alert al=new Alert(AlertType.WARNING, "Please select a supplier", ButtonType.OK);
					al.show();
				}else {
					rws.deleteSupplier(supplier);
					show(primaryStage);
				}
			}
		});
		
		add.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				(new AddSupplier(user)).show(primaryStage);
			}
			
		});
		
		addProducts.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Supplier supplier=(Supplier) table.getSelectionModel().getSelectedItem();
				if(supplier==null) {
					Alert al=new Alert(AlertType.WARNING, "Please select a supplier", ButtonType.OK);
					al.show();
				}else {
					(new AddProducts(user, supplier.getName())).show(primaryStage);
				}
				
				
			}
			
		});
		
		
	}
	
}
