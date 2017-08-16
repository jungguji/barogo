/**
 * 
 */
package adminCalculate;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import db.DBManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author ���߱�
 *
 */
public class CalculateView implements Initializable {
	@FXML private TextField			tfCount1, tfCount2, tfCount3, tfCount4, tfCount5, tfCount6, tfCount7, tfCount8;
	@FXML private TextField 		tfPrice1, tfPrice2, tfPrice3, tfPrice4, tfPrice5, tfPrice6, tfPrice7, tfPrice8;
	@FXML private TextField 		tfTotalCount, tfTotalPrice;
	@FXML private TextField 		tfAdminID, tfAdminPW;
	@FXML private Button 			btnSelect, btnReset, btnLogin, btnCalc;
	@FXML private Label 			lblAdmin;
	@FXML private TableView<Sales> 	tvTotalSales;
	@FXML private DatePicker		pickDate;
	@FXML private Label				lblTotalMoney;
	@FXML private AnchorPane		paneCalc;
	
	private int iCount1, iCount2, iCount3, iCount4, iCount5, iCount6, iCount7, iCount8;
	private int iPrice1, iPrice2, iPrice3, iPrice4, iPrice5, iPrice6, iPrice7, iPrice8;
	private int iTotalCount = 0, iTotalPrice = 0;
	private int iResultCount 	= 0;
	private int iResultsum   	= 0;
	
	private DBManager	db			= new DBManager();
	private CalcBean	calcBean	= new CalcBean();
	
	LocalDate date;
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		// TODO Auto-generated method stub
		tfCount1.setOnKeyReleased(event-> {	textField_empty_chk(); price_sum();	});
		tfCount2.setOnKeyReleased(event-> { textField_empty_chk(); price_sum(); });
		tfCount3.setOnKeyReleased(event-> {	textField_empty_chk(); price_sum(); });
		tfCount4.setOnKeyReleased(event-> { textField_empty_chk(); price_sum(); });
		tfCount5.setOnKeyReleased(event-> {	textField_empty_chk(); price_sum();	});
		tfCount6.setOnKeyReleased(event-> {	textField_empty_chk(); price_sum(); });
		tfCount7.setOnKeyReleased(event-> {	textField_empty_chk(); price_sum(); });
		tfCount8.setOnKeyReleased(event-> {	textField_empty_chk(); price_sum(); });
		
		LocalDate nowDate = LocalDate.now();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		pickDate.setPromptText(String.valueOf(nowDate));		
		
		date = nowDate;
		
		ObservableList<Sales> salesList = FXCollections.observableArrayList(
				new Sales("","", "")
		);
		
		// ���̺��÷��� �� ����
		TableColumn name = tvTotalSales.getColumns().get(0);
		name.setCellValueFactory(new PropertyValueFactory("name"));
		name.setStyle("-fx-alignment: CENTER;");
		
		TableColumn count = tvTotalSales.getColumns().get(1);
		count.setCellValueFactory(new PropertyValueFactory("count"));
		count.setStyle("-fx-alignment: CENTER;");
		
		TableColumn price = tvTotalSales.getColumns().get(2);
		price.setCellValueFactory(new PropertyValueFactory("price"));
		price.setStyle("-fx-alignment: CENTER;");
		
		tvTotalSales.setItems(salesList);
		
		pickDate.setOnAction(event-> {
			date = pickDate.getValue();
		});
	}

	public void handleBtnLoginAction(ActionEvent action)
	{
		String strAdminID;
		String strAdminPW;
		String result;

		strAdminID = tfAdminID.getText();
		strAdminPW = tfAdminPW.getText();

		result = db.login_Calculate(strAdminID, strAdminPW);
		
		if (result.equals(null)) {
			tfAdminID.setText("");
			tfAdminPW.setText("");
		} else {
			tfAdminID.setDisable(true);
			tfAdminPW.setDisable(true);

			tfCount1.setDisable(false);
			tfCount2.setDisable(false);
			tfCount3.setDisable(false);
			tfCount4.setDisable(false);
			tfCount5.setDisable(false);
			tfCount6.setDisable(false);
			tfCount7.setDisable(false);
			tfCount8.setDisable(false);

			lblAdmin.setText(result);
		}
	}
	
	public void textField_empty_chk()
	{
		iCount1 = (tfCount1.getText() == null || tfCount1.getText().length() == 0) ? 0 : Integer.parseInt(tfCount1.getText());
		iCount2 = (tfCount2.getText() == null || tfCount2.getText().length() == 0) ? 0 : Integer.parseInt(tfCount2.getText());
		iCount3 = (tfCount3.getText() == null || tfCount3.getText().length() == 0) ? 0 : Integer.parseInt(tfCount3.getText());
		iCount4 = (tfCount4.getText() == null || tfCount4.getText().length() == 0) ? 0 : Integer.parseInt(tfCount4.getText());
		iCount5 = (tfCount5.getText() == null || tfCount5.getText().length() == 0) ? 0 : Integer.parseInt(tfCount5.getText());
		iCount6 = (tfCount6.getText() == null || tfCount6.getText().length() == 0) ? 0 : Integer.parseInt(tfCount6.getText());
		iCount7 = (tfCount7.getText() == null || tfCount7.getText().length() == 0) ? 0 : Integer.parseInt(tfCount7.getText());
		iCount8 = (tfCount8.getText() == null || tfCount8.getText().length() == 0) ? 0 : Integer.parseInt(tfCount8.getText());
	}
	
	public void price_sum()
	{
		iPrice1 = iCount1 * 50000;
		iPrice2 = iCount2 * 10000;
		iPrice3 = iCount3 * 5000;
		iPrice4 = iCount4 * 1000;
		iPrice5 = iCount5 * 500;
		iPrice6 = iCount6 * 100;
		iPrice7 = iCount7 * 50;
		iPrice8 = iCount8 * 10;
		
		iTotalCount = iCount1 + iCount2 + iCount3 + iCount4 + iCount5 + iCount6 + iCount7 + iCount8;
		iTotalPrice = iPrice1 + iPrice2 + iPrice3 + iPrice4 + iPrice5 + iPrice6 + iPrice7 + iPrice8;
		
		tfPrice1.setText(String.valueOf(iPrice1));
		tfPrice2.setText(String.valueOf(iPrice2));
		tfPrice3.setText(String.valueOf(iPrice3));
		tfPrice4.setText(String.valueOf(iPrice4));
		tfPrice5.setText(String.valueOf(iPrice5));
		tfPrice6.setText(String.valueOf(iPrice6));
		tfPrice7.setText(String.valueOf(iPrice7));
		tfPrice8.setText(String.valueOf(iPrice8));
		
		tfTotalCount.setText(String.valueOf(iTotalCount));
		tfTotalPrice.setText(String.valueOf(iTotalPrice));
	}

	public void handleBtnResetAction(ActionEvent action)
	{
		ObservableList<Sales> salesList = FXCollections.observableArrayList(
				new Sales("","", "")
		);
		
		tvTotalSales.setItems(salesList);
		tfCount1.setText(""); tfCount2.setText(""); tfCount3.setText(""); tfCount4.setText("");
		tfCount5.setText(""); tfCount6.setText(""); tfCount7.setText(""); tfCount8.setText("");
		
		tfPrice1.setText(""); tfPrice2.setText(""); tfPrice3.setText(""); tfPrice4.setText("");
		tfPrice5.setText(""); tfPrice6.setText(""); tfPrice7.setText(""); tfPrice8.setText("");

		tfTotalCount.setText("");
		tfTotalPrice.setText("");
		
		tfCount1.setDisable(true);	tfCount2.setDisable(true);	tfCount3.setDisable(true);	tfCount4.setDisable(true);
		tfCount5.setDisable(true);	tfCount6.setDisable(true);	tfCount7.setDisable(true);	tfCount8.setDisable(true);
		
		tfPrice1.setDisable(true);	tfPrice2.setDisable(true); tfPrice3.setDisable(true);	tfPrice4.setDisable(true);
		tfPrice5.setDisable(true);	tfPrice6.setDisable(true);	tfPrice7.setDisable(true);	tfPrice8.setDisable(true);
		
		tfAdminID.setDisable(false);
		tfAdminPW.setDisable(false);

		tfAdminID.setText("");
		tfAdminPW.setText("");
		lblAdmin.setText("");
		lblTotalMoney.setText("");
	}

	public void handleBtnSelectAction(ActionEvent action)
	{
		calcBean = db.salse_total(date);
		
		int iSalesCount 	= calcBean.getiCount();
		int iSalesSum		= calcBean.getiSales();
		int iReturnCount 	= calcBean.getiReturnCount();
		int iReturnSum		= calcBean.getiReturnSales();
		
		
		iResultCount = iSalesCount - iReturnCount;
		iResultsum	 = iSalesSum - iReturnSum;

		lblTotalMoney.setText(String.valueOf(iResultsum));
		ObservableList<Sales> salesList = FXCollections.observableArrayList(
				new Sales("�� �����", String.valueOf(iSalesCount), String.valueOf(iSalesSum)),
				new Sales("�� ��ǰ��", String.valueOf(iReturnCount) , String.valueOf(iReturnSum)),
				new Sales("�� �����", String.valueOf(iResultCount), String.valueOf(iResultsum))
		);
		tvTotalSales.setItems(salesList);
	}

	public void handleBtnCalcAction(ActionEvent acton)
	{
		String strTotalPrice = tfTotalPrice.getText();
		String strTotalMomey = lblTotalMoney.getText();
		
		if(strTotalMomey.equals(""))
		{
			popup("CalcNoSelect");
		}
		else
		{
			if(strTotalPrice.equals(strTotalMomey))
			{
				boolean isOverlap;
				
				isOverlap = db.stats_overlap_chk(date);
				if(isOverlap)
				{
					popup("CalcOverlap");
				} 
				else 
				{
					db.insert_stats_data(date, calcBean.getiSales(), iResultsum);
					popup("CalcOk");
					
					Stage primaryStage = (Stage)paneCalc.getScene().getWindow();
					primaryStage.close();
				}
			} 
			else 
			{
				popup("CalcPopUp");
			}
		}
		
		
	}
	
	public void popup(String action)
	{
		try{
			FXMLLoader another = new FXMLLoader( getClass().getResource( "../adminCalculate/" + action + ".fxml" ));
			try {
			   AnchorPane anotherPage = (AnchorPane) another.load();
			   // �ٸ�â ���� �۾� .... 2
			   Scene anotherScene = new Scene(anotherPage);
			   Stage stage = new  Stage();
			   stage.setScene(anotherScene);
			   stage.show();
			   // �ٸ�â ���� �۾� .... 2 ��.
			} catch (IOException e) {
			   e.printStackTrace();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}