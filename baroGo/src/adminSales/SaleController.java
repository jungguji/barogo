package adminSales;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import db.DBManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SaleController implements Initializable {
	@FXML private TableView<SaleInfoBean> productTableView;

	@FXML private Button btnHome;

	@FXML private Button snack;
	@FXML private Button drink;
	@FXML private Button noodle;

	@FXML private Button product1;
	@FXML private Button product2;
	@FXML private Button product3;
	@FXML private Button product4;
	@FXML private Button product5;
	@FXML private Button product6;
	@FXML private Button product7;
	@FXML private Button product8;
	@FXML private Button product9;
	@FXML private Button product10;
	@FXML private Button product11;
	@FXML private Button product12;
	@FXML private Button product13;
	@FXML private Button product14;
	@FXML private Button product15;
	@FXML private Button product16;
	@FXML private Button product17;
	@FXML private Button product18;
	@FXML private Button product19;
	@FXML private Button product20;

	private DBManager db = new DBManager();

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

	public void handleBtnSnackAction(ActionEvent action) {
		String menu[] = new String[20];
		ArrayList<SaleInfoBean> Bean;

		Bean = db.sale_query(snack.getId());

		int i = 0;
		for (SaleInfoBean B : Bean) {
			menu[i] = B.getProductName() + "(" + B.getPrice() + ")";
			i++;
		}
		product1.setText(menu[0]);
		product2.setText(menu[1]);
		product3.setText(menu[2]);
		product4.setText(menu[3]);
		product5.setText(menu[4]);
		product6.setText(menu[5]);
		product7.setText(menu[6]);
		product8.setText(menu[7]);
		product9.setText(menu[8]);
		product10.setText(menu[9]);
		product11.setText(menu[10]);
		product12.setText(menu[11]);
		product13.setText(menu[12]);
		product14.setText(menu[13]);
		product15.setText(menu[14]);
		product16.setText(menu[15]);
		product17.setText(menu[16]);
		product18.setText(menu[17]);
		product19.setText(menu[18]);
		product20.setText(menu[19]);

	}

	public void handleBtnDrinkAction(ActionEvent action) {

		String menu[] = new String[20];
		ArrayList<SaleInfoBean> Bean;

		Bean = db.sale_query(drink.getId());

		int i = 0;
		for (SaleInfoBean B : Bean) {
			menu[i] = B.getProductName() + "(" + B.getPrice() + ")";
			i++;
		}
		product1.setText(menu[0]);
		product2.setText(menu[1]);
		product3.setText(menu[2]);
		product4.setText(menu[3]);
		product5.setText(menu[4]);
		product6.setText(menu[5]);
		product7.setText(menu[6]);
		product8.setText(menu[7]);
		product9.setText(menu[8]);
		product10.setText(menu[9]);
		product11.setText(menu[10]);
		product12.setText(menu[11]);
		product13.setText(menu[12]);
		product14.setText(menu[13]);
		product15.setText(menu[14]);
		product16.setText(menu[15]);
		product17.setText(menu[16]);
		product18.setText(menu[17]);
		product19.setText(menu[18]);
		product20.setText(menu[19]);

	}

	public void handleBtnNoodleAction(ActionEvent action) {

		String menu[] = new String[20];
		ArrayList<SaleInfoBean> Bean;

		Bean = db.sale_query(noodle.getId());

		int i = 0;
		for (SaleInfoBean B : Bean) {
			menu[i] = B.getProductName() + "(" + B.getPrice() + ")";
			i++;
		}
		product1.setText(menu[0]);
		product2.setText(menu[1]);
		product3.setText(menu[2]);
		product4.setText(menu[3]);
		product5.setText(menu[4]);
		product6.setText(menu[5]);
		product7.setText(menu[6]);
		product8.setText(menu[7]);
		product9.setText(menu[8]);
		product10.setText(menu[9]);
		product11.setText(menu[10]);
		product12.setText(menu[11]);
		product13.setText(menu[12]);
		product14.setText(menu[13]);
		product15.setText(menu[14]);
		product16.setText(menu[15]);
		product17.setText(menu[16]);
		product18.setText(menu[17]);
		product19.setText(menu[18]);
		product20.setText(menu[19]);

	}

	public void handleBtnProduct1Action(ActionEvent action) {
		String saleInfo[] = new String[2];
		StringTokenizer st = new StringTokenizer(product1.getText(), "(");
		int i = 0;
		while (st.hasMoreTokens()) {
			saleInfo[i] = st.nextToken();
			i++;
		}

		st = new StringTokenizer(saleInfo[1], ")");
		saleInfo[1] = st.nextToken();

		SaleInfoBean sale = new SaleInfoBean();
		
		sale.setProductName(saleInfo[0]);
		sale.setPrice(Integer.parseInt(saleInfo[1]));
		sale.setCount(1);
		
		productTableView.getItems().add(sale);
		
		/*
		System.out.println(sale.getProductName().contains(saleInfo[0]));
		if (sale.getProductName().indexOf(saleInfo[0]) != -1) {
			System.out.println("�Ϳ�");
		}
		if (sale.getProductName().indexOf(saleInfo[0]) != -1) {}
		System.out.println(sale.getProductName().contains(saleInfo[0]));
		*/
		
	}

	public void handleBtnProduct2Action(ActionEvent action) {
		String saleInfo[] = new String[2];
		StringTokenizer st = new StringTokenizer(product2.getText(), "(");
		int i = 0;
		while (st.hasMoreTokens()) {
			saleInfo[i] = st.nextToken();
			i++;
		}

		st = new StringTokenizer(saleInfo[1], ")");
		saleInfo[1] = st.nextToken();

		SaleInfoBean sale = new SaleInfoBean();
		sale.setProductName(saleInfo[0]);
		sale.setPrice(Integer.parseInt(saleInfo[1]));
		sale.setCount(1);
		productTableView.getItems().add(sale);
	}

	public void handleBtnProduct3Action(ActionEvent action) {
		String saleInfo[] = new String[2];
		StringTokenizer st = new StringTokenizer(product3.getText(), "(");
		int i = 0;
		while (st.hasMoreTokens()) {
			saleInfo[i] = st.nextToken();
			i++;
		}

		st = new StringTokenizer(saleInfo[1], ")");
		saleInfo[1] = st.nextToken();

		SaleInfoBean sale = new SaleInfoBean();
		sale.setProductName(saleInfo[0]);
		sale.setPrice(Integer.parseInt(saleInfo[1]));
		sale.setCount(1);
		productTableView.getItems().add(sale);
	}

	public void handleBtnProduct4Action(ActionEvent action) {
		String saleInfo[] = new String[2];
		StringTokenizer st = new StringTokenizer(product4.getText(), "(");
		int i = 0;
		while (st.hasMoreTokens()) {
			saleInfo[i] = st.nextToken();
			i++;
		}

		st = new StringTokenizer(saleInfo[1], ")");
		saleInfo[1] = st.nextToken();

		SaleInfoBean sale = new SaleInfoBean();
		sale.setProductName(saleInfo[0]);
		sale.setPrice(Integer.parseInt(saleInfo[1]));
		sale.setCount(1);
		productTableView.getItems().add(sale);
	}

	public void handleBtnProduct5Action(ActionEvent action) {
		String saleInfo[] = new String[2];
		StringTokenizer st = new StringTokenizer(product5.getText(), "(");
		int i = 0;
		while (st.hasMoreTokens()) {
			saleInfo[i] = st.nextToken();
			i++;
		}

		st = new StringTokenizer(saleInfo[1], ")");
		saleInfo[1] = st.nextToken();

		SaleInfoBean sale = new SaleInfoBean();
		sale.setProductName(saleInfo[0]);
		sale.setPrice(Integer.parseInt(saleInfo[1]));
		sale.setCount(1);
		productTableView.getItems().add(sale);
	}

	public void handleBtnProduct6Action(ActionEvent action) {
		String saleInfo[] = new String[2];
		StringTokenizer st = new StringTokenizer(product6.getText(), "(");
		int i = 0;
		while (st.hasMoreTokens()) {
			saleInfo[i] = st.nextToken();
			i++;
		}

		st = new StringTokenizer(saleInfo[1], ")");
		saleInfo[1] = st.nextToken();

		SaleInfoBean sale = new SaleInfoBean();
		sale.setProductName(saleInfo[0]);
		sale.setPrice(Integer.parseInt(saleInfo[1]));
		sale.setCount(1);
		productTableView.getItems().add(sale);
	}

	public void handleBtnProduct7Action(ActionEvent action) {
		String saleInfo[] = new String[2];
		StringTokenizer st = new StringTokenizer(product7.getText(), "(");
		int i = 0;
		while (st.hasMoreTokens()) {
			saleInfo[i] = st.nextToken();
			i++;
		}

		st = new StringTokenizer(saleInfo[1], ")");
		saleInfo[1] = st.nextToken();

		SaleInfoBean sale = new SaleInfoBean();
		sale.setProductName(saleInfo[0]);
		sale.setPrice(Integer.parseInt(saleInfo[1]));
		sale.setCount(1);
		productTableView.getItems().add(sale);
	}

	public void handleBtnProduct8Action(ActionEvent action) {
		String saleInfo[] = new String[2];
		StringTokenizer st = new StringTokenizer(product8.getText(), "(");
		int i = 0;
		while (st.hasMoreTokens()) {
			saleInfo[i] = st.nextToken();
			i++;
		}

		st = new StringTokenizer(saleInfo[1], ")");
		saleInfo[1] = st.nextToken();

		SaleInfoBean sale = new SaleInfoBean();
		sale.setProductName(saleInfo[0]);
		sale.setPrice(Integer.parseInt(saleInfo[1]));
		sale.setCount(1);
		productTableView.getItems().add(sale);
	}

	public void handleBtnProduct9Action(ActionEvent action) {
		String saleInfo[] = new String[2];
		StringTokenizer st = new StringTokenizer(product9.getText(), "(");
		int i = 0;
		while (st.hasMoreTokens()) {
			saleInfo[i] = st.nextToken();
			i++;
		}

		st = new StringTokenizer(saleInfo[1], ")");
		saleInfo[1] = st.nextToken();

		SaleInfoBean sale = new SaleInfoBean();
		sale.setProductName(saleInfo[0]);
		sale.setPrice(Integer.parseInt(saleInfo[1]));
		sale.setCount(1);
		productTableView.getItems().add(sale);
	}

	public void handleBtnProduct10Action(ActionEvent action) {
		String saleInfo[] = new String[2];
		StringTokenizer st = new StringTokenizer(product10.getText(), "(");
		int i = 0;
		while (st.hasMoreTokens()) {
			saleInfo[i] = st.nextToken();
			i++;
		}

		st = new StringTokenizer(saleInfo[1], ")");
		saleInfo[1] = st.nextToken();

		SaleInfoBean sale = new SaleInfoBean();
		sale.setProductName(saleInfo[0]);
		sale.setPrice(Integer.parseInt(saleInfo[1]));
		sale.setCount(1);
		productTableView.getItems().add(sale);
	}

	public void handleBtnProduct11Action(ActionEvent action) {
		String saleInfo[] = new String[2];
		StringTokenizer st = new StringTokenizer(product11.getText(), "(");
		int i = 0;
		while (st.hasMoreTokens()) {
			saleInfo[i] = st.nextToken();
			i++;
		}

		st = new StringTokenizer(saleInfo[1], ")");
		saleInfo[1] = st.nextToken();

		SaleInfoBean sale = new SaleInfoBean();
		sale.setProductName(saleInfo[0]);
		sale.setPrice(Integer.parseInt(saleInfo[1]));
		sale.setCount(1);
		productTableView.getItems().add(sale);
	}

	public void handleBtnProduct12Action(ActionEvent action) {
		String saleInfo[] = new String[2];
		StringTokenizer st = new StringTokenizer(product12.getText(), "(");
		int i = 0;
		while (st.hasMoreTokens()) {
			saleInfo[i] = st.nextToken();
			i++;
		}

		st = new StringTokenizer(saleInfo[1], ")");
		saleInfo[1] = st.nextToken();

		SaleInfoBean sale = new SaleInfoBean();
		sale.setProductName(saleInfo[0]);
		sale.setPrice(Integer.parseInt(saleInfo[1]));
		sale.setCount(1);
		productTableView.getItems().add(sale);
	}

	public void handleBtnProduct13Action(ActionEvent action) {
		String saleInfo[] = new String[2];
		StringTokenizer st = new StringTokenizer(product13.getText(), "(");
		int i = 0;
		while (st.hasMoreTokens()) {
			saleInfo[i] = st.nextToken();
			i++;
		}

		st = new StringTokenizer(saleInfo[1], ")");
		saleInfo[1] = st.nextToken();

		SaleInfoBean sale = new SaleInfoBean();
		sale.setProductName(saleInfo[0]);
		sale.setPrice(Integer.parseInt(saleInfo[1]));
		sale.setCount(1);
		productTableView.getItems().add(sale);
	}

	public void handleBtnProduct14Action(ActionEvent action) {
		String saleInfo[] = new String[2];
		StringTokenizer st = new StringTokenizer(product14.getText(), "(");
		int i = 0;
		while (st.hasMoreTokens()) {
			saleInfo[i] = st.nextToken();
			i++;
		}

		st = new StringTokenizer(saleInfo[1], ")");
		saleInfo[1] = st.nextToken();

		SaleInfoBean sale = new SaleInfoBean();
		sale.setProductName(saleInfo[0]);
		sale.setPrice(Integer.parseInt(saleInfo[1]));
		sale.setCount(1);
		productTableView.getItems().add(sale);
	}

	public void handleBtnProduct15Action(ActionEvent action) {
		String saleInfo[] = new String[2];
		StringTokenizer st = new StringTokenizer(product15.getText(), "(");
		int i = 0;
		while (st.hasMoreTokens()) {
			saleInfo[i] = st.nextToken();
			i++;
		}

		st = new StringTokenizer(saleInfo[1], ")");
		saleInfo[1] = st.nextToken();

		SaleInfoBean sale = new SaleInfoBean();
		sale.setProductName(saleInfo[0]);
		sale.setPrice(Integer.parseInt(saleInfo[1]));
		sale.setCount(1);
		productTableView.getItems().add(sale);
	}

	public void handleBtnProduct16Action(ActionEvent action) {
		String saleInfo[] = new String[2];
		StringTokenizer st = new StringTokenizer(product16.getText(), "(");
		int i = 0;
		while (st.hasMoreTokens()) {
			saleInfo[i] = st.nextToken();
			i++;
		}

		st = new StringTokenizer(saleInfo[1], ")");
		saleInfo[1] = st.nextToken();

		SaleInfoBean sale = new SaleInfoBean();
		sale.setProductName(saleInfo[0]);
		sale.setPrice(Integer.parseInt(saleInfo[1]));
		sale.setCount(1);
		productTableView.getItems().add(sale);
	}

	public void handleBtnProduct17Action(ActionEvent action) {
		String saleInfo[] = new String[2];
		StringTokenizer st = new StringTokenizer(product17.getText(), "(");
		int i = 0;
		while (st.hasMoreTokens()) {
			saleInfo[i] = st.nextToken();
			i++;
		}

		st = new StringTokenizer(saleInfo[1], ")");
		saleInfo[1] = st.nextToken();

		SaleInfoBean sale = new SaleInfoBean();
		sale.setProductName(saleInfo[0]);
		sale.setPrice(Integer.parseInt(saleInfo[1]));
		sale.setCount(1);
		productTableView.getItems().add(sale);
	}

	public void handleBtnProduct18Action(ActionEvent action) {
		String saleInfo[] = new String[2];
		StringTokenizer st = new StringTokenizer(product18.getText(), "(");
		int i = 0;
		while (st.hasMoreTokens()) {
			saleInfo[i] = st.nextToken();
			i++;
		}

		st = new StringTokenizer(saleInfo[1], ")");
		saleInfo[1] = st.nextToken();

		SaleInfoBean sale = new SaleInfoBean();
		sale.setProductName(saleInfo[0]);
		sale.setPrice(Integer.parseInt(saleInfo[1]));
		sale.setCount(1);
		productTableView.getItems().add(sale);
	}

	public void handleBtnProduct19Action(ActionEvent action) {
		String saleInfo[] = new String[2];
		StringTokenizer st = new StringTokenizer(product19.getText(), "(");
		int i = 0;
		while (st.hasMoreTokens()) {
			saleInfo[i] = st.nextToken();
			i++;
		}

		st = new StringTokenizer(saleInfo[1], ")");
		saleInfo[1] = st.nextToken();

		SaleInfoBean sale = new SaleInfoBean();
		sale.setProductName(saleInfo[0]);
		sale.setPrice(Integer.parseInt(saleInfo[1]));
		sale.setCount(1);
		productTableView.getItems().add(sale);
	}

	public void handleBtnProduct20Action(ActionEvent action) {
		String saleInfo[] = new String[2];
		StringTokenizer st = new StringTokenizer(product20.getText(), "(");
		int i = 0;
		while (st.hasMoreTokens()) {
			saleInfo[i] = st.nextToken();
			i++;
		}

		st = new StringTokenizer(saleInfo[1], ")");
		saleInfo[1] = st.nextToken();

		SaleInfoBean sale = new SaleInfoBean();
		sale.setProductName(saleInfo[0]);
		sale.setPrice(Integer.parseInt(saleInfo[1]));
		sale.setCount(1);
		productTableView.getItems().add(sale);
	}
	
	public void handleBtnSalesAction(ActionEvent action)
	{
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TableColumn ProductName = (TableColumn) productTableView.getColumns().get(0);
		ProductName.setCellValueFactory(new PropertyValueFactory("ProductName"));
		ProductName.setStyle("-fx-alignment: CENTER;");

		TableColumn Price = (TableColumn) productTableView.getColumns().get(1);
		Price.setCellValueFactory(new PropertyValueFactory("Price"));
		Price.setStyle("-fx-alignment: CENTER;");

		TableColumn Count = (TableColumn) productTableView.getColumns().get(2);
		Count.setCellValueFactory(new PropertyValueFactory("Count"));
		Count.setStyle("-fx-alignment: CENTER;");
	}
}
