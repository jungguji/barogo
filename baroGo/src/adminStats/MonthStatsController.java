package adminStats;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import DB.DBManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class MonthStatsController implements Initializable {
	
	@FXML private LineChart lineChart;
	@FXML private ComboBox<String> cmbYear;
	@FXML private ComboBox<String> cmbMonth;
	@FXML private Button btnHome;
	
	private String selectYear;
	private String selectMonth;
	
	DBManager db = new DBManager();
	
	ObservableList<String> MonthList = 
			FXCollections.observableArrayList("1 월","2 월","3 월","4 월","5 월","6 월","7 월","8 월","9 월","10 월","11 월","12 월");
	
	ObservableList<String> YearList = FXCollections.observableArrayList("2016 년");
	
	ArrayList<StatsBean> statsList = new ArrayList<StatsBean>();
	
	private ArrayList<String> day = new ArrayList<String>();
	private ArrayList<Integer> sales = new ArrayList<Integer>();
	
	public void handleBtnHomeAction(ActionEvent action) {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("../adminLogin/AdminMainView.fxml"));
			Parent mainView = loader.load();

			Scene scene = new Scene(mainView);
			scene.getStylesheets().add(getClass().getResource("../adminLogin/Style.css").toExternalForm());
			Stage primaryStage = (Stage) btnHome.getScene().getWindow();
			primaryStage.setScene(scene);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void handleCmbCheckAction(ActionEvent action)
	{
		StringTokenizer st = new StringTokenizer(cmbYear.getValue(), " ");
		selectYear = st.nextToken();
		
		StringTokenizer st1 = new StringTokenizer(cmbMonth.getValue(), " ");
		selectMonth = st1.nextToken();
		
		
		XYChart.Series series1 = new XYChart.Series();
		series1.setName("총매출");
		series1.setData(FXCollections.observableArrayList(
				new XYChart.Data("1",68000),
				new XYChart.Data("2",30000),
				new XYChart.Data("3",48000),
				new XYChart.Data("4",52000),
				new XYChart.Data("5",33000),
				new XYChart.Data("6",70000),
				new XYChart.Data("7",100000),
				new XYChart.Data("8",32000),
				new XYChart.Data("9",49000),
				new XYChart.Data("10",55000),
				new XYChart.Data("11",22000),
				new XYChart.Data("12",48000),
				new XYChart.Data("13",26000),
				new XYChart.Data("14",67000),
				new XYChart.Data("15",120000),
				new XYChart.Data("16",48000),
				new XYChart.Data("17",62000),
				new XYChart.Data("18",40000),
				new XYChart.Data("19",67000),
				new XYChart.Data("20",89000),
				new XYChart.Data("21",57000),
				new XYChart.Data("22",89000),
				new XYChart.Data("23",34000),
				new XYChart.Data("24",56000),
				new XYChart.Data("25",79000),
				new XYChart.Data("26",49000),
				new XYChart.Data("27",38000),
				new XYChart.Data("28",52000),
				new XYChart.Data("29",78000),
				new XYChart.Data("30",29000)
				));
		
		XYChart.Series series2 = new XYChart.Series();
		series2.setName("총매출");
		series2.setData(FXCollections.observableArrayList(
				new XYChart.Data("1",60000),
				new XYChart.Data("2",28000),
				new XYChart.Data("3",40000),
				new XYChart.Data("4",50000),
				new XYChart.Data("5",30000),
				new XYChart.Data("6",68000),
				new XYChart.Data("7",98000),
				new XYChart.Data("8",30000),
				new XYChart.Data("9",40000),
				new XYChart.Data("10",50000),
				new XYChart.Data("11",20000),
				new XYChart.Data("12",40000),
				new XYChart.Data("13",20000),
				new XYChart.Data("14",50000),
				new XYChart.Data("15",100000),
				new XYChart.Data("16",40000),
				new XYChart.Data("17",60000),
				new XYChart.Data("18",38000),
				new XYChart.Data("19",60000),
				new XYChart.Data("20",80000),
				new XYChart.Data("21",50000),
				new XYChart.Data("22",88000),
				new XYChart.Data("23",30000),
				new XYChart.Data("24",50000),
				new XYChart.Data("25",78000),
				new XYChart.Data("26",47000),
				new XYChart.Data("27",30000),
				new XYChart.Data("28",50000),
				new XYChart.Data("29",70000),
				new XYChart.Data("30",20000)
				));
		
		lineChart.getData().add(series1);
		lineChart.getData().add(series2);
		
		statsList = db.stats_query(selectYear, selectMonth);
		
		for(int i = 0; i<statsList.size(); i++)
		{
			StatsBean str = statsList.get(i);
			day.add(str.getDay());
			sales.add(str.getSales());
			
		}
		series1.setData(FXCollections.observableArrayList(
				new XYChart.Data(day.get(0),sales.get(0)),
				new XYChart.Data(day.get(1),sales.get(1)),
				new XYChart.Data(day.get(2),sales.get(2)),
				new XYChart.Data(day.get(3),sales.get(3)),
				new XYChart.Data(day.get(4),sales.get(4))
				));
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO 자동 생성된 메소드 스텁
		
		cmbYear.setValue("2016 년");
		cmbYear.setItems(YearList);
		cmbMonth.setValue("1 월");
		cmbMonth.setItems(MonthList);

	}

}
