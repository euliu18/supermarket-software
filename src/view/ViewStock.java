package view;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Products;
import model.RWProducts;
import model.User;

public class ViewStock {
	 User user;

		public ViewStock(User user) {
			super();
			this.user = user;
		}

		public void show() {
			Stage st=new Stage();
			RWProducts rwp=new RWProducts();
			st.setTitle("Stock ");
	        CategoryAxis xAxis = new CategoryAxis();
	        NumberAxis yAxis = new NumberAxis();
	        BarChart<String,Number> bc =  new BarChart<String,Number>(xAxis,yAxis);
	        bc.setTitle("Products with less than 5 items in stock");
	        xAxis.setLabel("Product name");       
	        yAxis.setLabel("Number");
	 
	        XYChart.Series series1 = new XYChart.Series();
	        
	        series1.setName("Quantity");    
	        
	        for(Products p: rwp.getProducts()) {
	        	int stock= p.getStock();
	        	if(stock<5) {
	        	series1.getData().add(new XYChart.Data(p.getName(),stock));
	        	}
	        }
	        BorderPane bp = new BorderPane();
	        bp.setCenter(bc);
	        bp.setPadding(new Insets(40, 100 , 40, 100));
	        Scene scene  = new Scene(bp,800,600);
	        bc.getData().addAll(series1);
	        st.setScene(scene);
	        st.setTitle("Stock");
	        st.show();
			
		}

}
