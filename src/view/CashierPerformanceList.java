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
import model.ProductsSold;
import model.RWProductsSold;
import model.RWUser;
import model.User;

public class CashierPerformanceList {
	
	User user;
    Cashier cashier;
    ArrayList<ProductsSold> products = new ArrayList<>();

    public CashierPerformanceList(Cashier cashier, User user) {
        super();
        this.user = user;
        this.cashier = cashier;
    }


    public void show(Stage primaryStage) {
        RWUser rwu = new RWUser();
        ArrayList<Cashier> ecn = new ArrayList();
		
        RWProductsSold rwProductsSold = new RWProductsSold();
        products.addAll(rwProductsSold.readProductsSold());

        ObservableList<ProductsSold> users = FXCollections.observableArrayList(products);
        TableView table = new TableView();
        table.setEditable(true);

        TableColumn barcode = new TableColumn("Barcode");
        TableColumn name = new TableColumn("Name");
        TableColumn supplier = new TableColumn("Supplier");
        TableColumn price = new TableColumn("Price");
        TableColumn quantity = new TableColumn("Quantity");


        barcode.setCellValueFactory(new PropertyValueFactory("barcode"));
        name.setCellValueFactory(new PropertyValueFactory("name"));
        supplier.setCellValueFactory(new PropertyValueFactory("supplier"));
        price.setCellValueFactory(new PropertyValueFactory("price"));
        quantity.setCellValueFactory(new PropertyValueFactory("sold"));


        table.setItems(users);
        table.getColumns().addAll(barcode, name, supplier, price, quantity);

        
        Button back = new Button("Back");

        HBox hbox = new HBox();
        hbox.getChildren().addAll(back);
        hbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.setSpacing(10);

        BorderPane border = new BorderPane();
        border.setCenter(table);
        border.setBottom(hbox);

        Scene scene = new Scene(border);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("mystyle.css").toExternalForm());
        primaryStage.setTitle("Cashier list");
        primaryStage.show();

        back.setOnAction(new EventHandler<ActionEvent>() {


            @Override
            public void handle(ActionEvent arg0) {

                (new CashierPerformance(cashier, user)).show(primaryStage);

            }

        });


    }

}
