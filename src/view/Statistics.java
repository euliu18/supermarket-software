package view;

import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import model.IncomeOutcome;
import model.ProductsSold;
import model.RWIncomeOutcome;
import model.RWProducts;
import model.RWProductsSold;
import model.RWUser;
import model.User;

public class Statistics {
	User user;
	String year;
	
	

	public Statistics(User user) {
		super();
		this.user = user;
	}

	public void showM(String year) {
		this.year=year;
		show();
	}


	private void show() {
		Stage primaryStage=new Stage();
		RWUser rwu=new RWUser();
		RWProductsSold rwps=new RWProductsSold();
		
		CategoryAxis xAxis= new CategoryAxis();
		NumberAxis yAxis=new NumberAxis();
		BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
		
		xAxis.setLabel("Month");
		yAxis.setLabel("Value");
		
		XYChart.Series series1 = new XYChart.Series();
		series1.setName("incomes");
		XYChart.Series series2 = new XYChart.Series();
		series2.setName("outcomes");
		XYChart.Series series3 = new XYChart.Series();
		series3.setName("profit");
		String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		
		for(int i=0;i<12;i++) {
			double cost=0;
			double incomes=0;
			double profit=0;
			
			for(ProductsSold x:rwps.getSoldList()) {
				
			}
			
			
		}
		
		Scene scene = new Scene(bc,800,600);
		bc.getData().addAll(series1,series2,series3);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Statistisc");
		primaryStage.show();
		
	}
	

}
