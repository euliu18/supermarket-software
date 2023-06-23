package view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Bill;
import model.IncomeOutcome;
import model.ProductDate;
import model.Products;
import model.ProductsSold;
import model.RWBill;
import model.RWIncomeOutcome;
import model.RWProducts;
import model.RWProductsSold;
import model.User;

public class ListStock {
	 User user;
	    int issearch=0;
	    int choice=1;
	    int count = 0;
	    ArrayList<Products> products;
	    ObservableList<Products>prod;
	    ObservableList<Products> productsObservableList;
	    Bill bill;

	    TextField barcodeTextField;
	    TextField priceProductField;
	    TextField nameProductField;
	    TextField quantityProductField;

	    int totalValue;

	    public ListStock(User user){
	    	super();
	        this.user = user;
	        products = new ArrayList<Products>();
	    }
	    
	    public void showCashier(Stage primaryStage) {
	    	choice=1;
	    	show(primaryStage);
	    }
	    
	    public void show (Stage primaryStage,ArrayList<Products> products) {
	        this.products.clear();
			this.products.addAll(products);
			issearch=1;
			show(primaryStage);
		}

	   /* public void showCashier(Stage primaryStage,ArrayList<Products> products) {
	    	this.products=products;
	    	issearch=1;
	    	showCashier(primaryStage);
	    }*/
	    
	    public void show(Stage primaryStage){
	        RWProducts rwp=new RWProducts();
	        this.prod = FXCollections.observableArrayList(new ArrayList<Products>());

	        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	        LocalDateTime now = LocalDateTime.now();
	        bill = new Bill(getFiles(), user.getID(), new ProductDate(dtf.format(now)));
	        
	        Label search =new Label("Search product by category:");
			TextField searchField=new TextField();
			
			Button searchButton=new Button("search");
			searchField.setPrefSize(300, 50);

	        TableView table = new TableView();
	        table.setEditable(true);

	        if(issearch==1) {
				productsObservableList=FXCollections.observableArrayList(products);
			}else {
				productsObservableList=FXCollections.observableArrayList(rwp.readProducts());
			}

	       HBox hbox=new HBox();
			hbox.getChildren().addAll(search,searchField,searchButton);
			hbox.setPadding(new Insets(10,10,10,10));
			hbox.setSpacing(10);
			
			searchButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					ArrayList<Products> a=new ArrayList<Products>();
					if(searchField.getText().isEmpty()) {
						Alert al=new Alert(AlertType.WARNING, "Please enter a category!", ButtonType.OK);
						al.show();
					}else {
						for(Products x:rwp.readProducts()) {
						System.out.println("---"+x.getCategory().matches(searchField.getText().toString()+".*")
								+"\n"+a.size());
						
						if(x.getCategory().matches(searchField.getText().toString()+".*")) {
							a.add(x);
							System.out.println("a :"+a.size());
						}
						
					}
						 show(primaryStage,a);
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

	        table.setItems(productsObservableList);
	        table.getColumns().addAll(barcode,name,category,supplier,cost,onStock,expiryDate,dataCreated);

	        table.setRowFactory(tv -> {
	            TableRow<Products> row = new TableRow<>();
	            row.setOnMouseClicked(event -> {
	                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
	                    Products rowData = row.getItem();
	                    addInTextFields(rowData);
	                    //this.prod.add(rowData);
	                }
	            });
	            return row;
	        });

	        barcodeTextField = new TextField();
	        barcodeTextField.setPromptText("Enter barcode");
	        nameProductField=new TextField();
	        nameProductField.setEditable(false);
	        priceProductField=new TextField();
	        priceProductField.setEditable(false);

	        quantityProductField = new TextField();
	        Button ok=new Button("OK");
	        HBox hb=new HBox();
	        hb.setPadding(new Insets(10,10,10,10));
	        hb.setSpacing(20);
	        hb.getChildren().addAll(barcodeTextField,nameProductField, priceProductField,quantityProductField,ok);

	        ok.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent actionEvent) {
	                if(!quantityProductField.getText().isEmpty()){
	                    if(Integer.parseInt(quantityProductField.getText()) <= productsObservableList.get(table.getSelectionModel().getFocusedIndex()).getStock()){
	                        totalValue = Integer.parseInt(quantityProductField.getText()) * (int)productsObservableList.get(table.getSelectionModel().getFocusedIndex()).getCost();
	                        productsObservableList.get(table.getSelectionModel().getFocusedIndex()).setTempQuantity(Integer.parseInt(quantityProductField.getText()));
	                        productsObservableList.get(table.getSelectionModel().getFocusedIndex()).setTempTotal(totalValue);
	                        prod.add(productsObservableList.get(table.getSelectionModel().getFocusedIndex()));
	                        bill.addProduct(productsObservableList.get(table.getSelectionModel().getFocusedIndex()), Integer.parseInt(quantityProductField.getText()));

	                    } else {
	                        Alert al=new Alert(Alert.AlertType.WARNING, "Quantity should not be higher than stock", ButtonType.OK);
	                        al.show();
	                    }



	                } else {
	                    Alert al=new Alert(Alert.AlertType.WARNING, "Set a quantity", ButtonType.OK);
	                    al.show();
	                }
	            }
	        });


	        TextField totalField=new TextField();
	        totalField.setEditable(false);
	        Button total=new Button("Total");
	        Button newButton=new Button("New");
	        Button save=new Button("Save");
	        Button print=new Button("Print");
	        Button back = new Button("Back");

	        HBox hbox2=new HBox();

	        hbox2.setPadding(new Insets(10,10,10,10));
	        hbox2.setSpacing(10);

	        total.setOnAction(new EventHandler<ActionEvent>() {

	            @Override
	            public void handle(ActionEvent event) {
	                double total=0;
	                for(Products x:prod) {
	                    total+=x.getTempTotal();

	                }
	                totalField.setText(""+total);
	            }
	        });

	        newButton.setOnAction(new EventHandler<ActionEvent>() {

	            @Override
	            public void handle(ActionEvent event) {
	                prod.clear();
	            }
	        });

	        save.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent actionEvent) {
	                RWBill rwBill = new RWBill(bill);
	                boolean dontAdd = false;
	                for(int i=0; i<prod.size(); i++){
	                    Products tempProducts = prod.get(i);
	                    RWProductsSold rwProductsSold = new RWProductsSold();
	                    if(rwProductsSold.readProductsSold().isEmpty()){
	                        rwProductsSold.addProductSold(new ProductsSold(tempProducts, tempProducts.getTempQuantity()));
	                    } else {
	                        ArrayList<ProductsSold> productsSoldArrayList = rwProductsSold.readProductsSold();
	                        for(ProductsSold productsSold: productsSoldArrayList){
	                            if (tempProducts.getBarcode().equals(productsSold.getBarcode())){
	                                rwProductsSold.addQuantity(tempProducts, tempProducts.getTempQuantity());
	                                break;
	                            }
	                        }
	                        for(ProductsSold productsSold: productsSoldArrayList){
	                            if(tempProducts.getBarcode().equals(productsSold.getBarcode())){
	                                dontAdd = true;
	                                break;
	                            }
	                        }
	                        if(!dontAdd){
	                            rwProductsSold.addProductSoldInThread(new ProductsSold(tempProducts, tempProducts.getTempQuantity()));
	                        }
	                    }


	                    RWProducts products = new RWProducts();
	                    products.removeQuantity(prod.get(i).getTempQuantity(), prod.get(i).getBarcode());
	                }
	                double total=0;
	                for(Products x:prod) {
	                    total+=x.getTempTotal();

	                }
	                File file =new File("incomeOutcome.ser");
	                if(!file.exists()){
	                    RWIncomeOutcome rwIncomeOutcome = new RWIncomeOutcome(new IncomeOutcome(total, 0));
	                    rwIncomeOutcome.read();
	                } else {
	                    RWIncomeOutcome rwIncomeOutcome = new RWIncomeOutcome();
	                    IncomeOutcome incomeOutcome = rwIncomeOutcome.read();
	                    incomeOutcome.setIncome(total);
	                    rwIncomeOutcome.refresh(incomeOutcome);
	                }
	                show(primaryStage);
	            }
	        });

	        print.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent actionEvent) {
	                try
	                {
	                    File file=new File("./bills/"+bill.getId()+".txt");
	                    FileWriter fw=new FileWriter(file.getAbsoluteFile());
	                    BufferedWriter bw=new BufferedWriter(fw);
	                    bw.write(bill.toString());
	                    bw.close();
	                }
	                catch (Exception e)
	                {
	                    e.printStackTrace();
	                }
	            }
	        });

	        hbox2.getChildren().addAll(totalField, total, print, save, newButton, back);
	        GridPane gp=new GridPane();
	        //this.prod.add(new Products("asd", "as", "as", "as", 100, new ProductDate("2020/01/25"), new ProductDate("2020/01/25"), 10));
	        gp.addColumn(0, table, hb, list());

	        BorderPane border=new BorderPane();
	        border.setTop(hbox);
	        border.setCenter(gp);
	        border.setBottom(hbox2);
	        Scene scene=new Scene(border, 760, 480);
	        scene.getStylesheets().add(getClass().getResource("mystyle.css").toExternalForm());
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("Products sell");

	        back.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					(new CashierView(user)).show(primaryStage);
					
				}
			});
	        
	    }

	    public  TableView list() {

	        //ObservableList<Products>prod= FXCollections.observableArrayList(products);
	        TableView table=new TableView();
	        table.setEditable(true);

	        TableColumn name=new TableColumn("Name");
	        name.setCellValueFactory(new PropertyValueFactory<>("name"));

	        TableColumn sellpr=new TableColumn("Price");
	        sellpr.setCellValueFactory(new PropertyValueFactory<>("cost"));

	        TableColumn q=new TableColumn("Quantity");
	        q.setCellValueFactory(new PropertyValueFactory<>("tempQuantity"));

	        TableColumn total=new TableColumn("Total");
	        total.setCellValueFactory(new PropertyValueFactory<>("tempTotal"));


	        table.setItems(prod);
	        table.getColumns().addAll(name,sellpr,q,total);
	        return table;
	    }

	    private void addInTextFields(Products product){
	        barcodeTextField.setText(product.getBarcode());
	        nameProductField.setText(product.getName());
	        priceProductField.setText(String.valueOf(product.getCost()));
	    }

	    private int getFiles() {
	        String dirPath = "./bills";
	        File file = new File(dirPath);
	        File[] files = file.listFiles();
	        if (files != null)
	            return files.length;
	        else
	            return 0;
	    }

}
