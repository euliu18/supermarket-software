package view;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.IncomeOutcome;
import model.ProductDate;
import model.Products;
import model.RWIncomeOutcome;
import model.RWProducts;
import model.User;

public class AddProducts {
	
	User user;
    String sup;
   
    public AddProducts(User user) {
        super();
        this.user = user;
    }

    public AddProducts(User user, String sup) {
        super();
        this.user = user;
        this.sup = sup;

    }
	
    public void show(Stage primaryStage) {
        RWProducts product = new RWProducts();
        BorderPane border = new BorderPane();
        border.getStyleClass().add("bluebox");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        Label barcode = new Label("Barcode: ");
        TextField barcodeField = new TextField();

        Label name = new Label("Name: ");
        TextField nameField = new TextField();

        Label category = new Label("Category: ");
        TextField categoryField = new TextField();

        Label supplier = new Label("Supplier: ");
        TextField supplierField;
        if(sup!=null){
            supplierField = new TextField(sup);
            supplierField.setDisable(true);
        } else {
            supplierField = new TextField();
        }

        Label cost = new Label("Cost: ");
        TextField costField = new TextField();

        Label supplierPrice = new Label("Supplier Price: ");
        TextField supplierPriceField = new TextField();

		Label onStock = new Label("On Stock: ");
		TextField onStockField = new TextField();


        Label expiryDate = new Label("Expiry Date: ");
        DatePicker datePicker = new DatePicker();

        Label createdDate = new Label("Created Date: ");
        Date date = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateText = dateFormat.format(date);
        Text cd = new Text(dateText);

        Button create = new Button("Create");
        Button back = new Button("Back");

        HBox hbox = new HBox();
        hbox.getChildren().addAll(create, back);
        hbox.setSpacing(25);
        hbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.setAlignment(Pos.CENTER);
        grid.addColumn(0, barcode, name, category, supplier, cost, supplierPrice, onStock, expiryDate, createdDate);
        grid.addColumn(1, barcodeField, nameField, categoryField, supplierField, costField, supplierPriceField, onStockField, datePicker, cd);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        VBox vbox = new VBox();
        vbox.getStyleClass().add("bluebox");
        vbox.getChildren().addAll(grid, hbox);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 650, 480);
        scene.getStylesheets().add(getClass().getResource("mystyle.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Add Products");
        primaryStage.show();

        create.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (barcodeField.getText().isEmpty() || nameField.getText().isEmpty() || categoryField.getText().isEmpty() || supplierField.getText().isEmpty()
                        || costField.getText().isEmpty() || supplierField.getText().isEmpty() || onStockField.getText().isEmpty()
                        || datePicker.getValue()==null) {
                    Alert al = new Alert(Alert.AlertType.WARNING, "Please fill all the boxes", ButtonType.OK);
                    al.show();
                } else {
                    product.addProduct(new Products(nameField.getText(), categoryField.getText(), barcodeField.getText(), supplierField.getText(), Double.parseDouble(costField.getText()), new ProductDate(dateText), new ProductDate(datePicker.getValue().toString()), Integer.parseInt(onStockField.getText())));
                    File file = new File("incomeOutcome.ser");
                    if (!file.exists()) {
                        RWIncomeOutcome rwIncomeOutcome = new RWIncomeOutcome(new IncomeOutcome(0, Double.parseDouble(supplierPriceField.getText())));
                        rwIncomeOutcome.read();
                    } else {
                        RWIncomeOutcome rwIncomeOutcome = new RWIncomeOutcome();
                        IncomeOutcome incomeOutcome = rwIncomeOutcome.read();
                        incomeOutcome.setOutcome(Double.parseDouble(supplierPriceField.getText()));
                        rwIncomeOutcome.refresh(incomeOutcome);
                    }
                    (new ListSupplier(user)).show(primaryStage);
                }
            }
        });


        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                (new ListSupplier(user)).show(primaryStage);

            }
        });

    }


}
