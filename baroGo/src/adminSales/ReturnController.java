package adminSales;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import db.DBManager;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ReturnController implements Initializable {
	
	@FXML private TableView<SaleInfoBean> ReturnTableView;
	
	@FXML private TextField 		tfReceiptNo;
	@FXML private DatePicker 		dpDate;
	@FXML private Button 			btnSearch;
	@FXML private Button 			btnReturn;
	
	DBManager db = new DBManager();
	
	SaleInfoBean saleInfoBean = new SaleInfoBean();
	ArrayList<SaleInfoBean> Bean = new ArrayList<SaleInfoBean>();
	
	public void handleBtnSearchAction(ActionEvent action)
	{
		ReturnTableView.getItems().clear();
		Bean = db.SearchReceipt(tfReceiptNo.getText(), dpDate.getValue());
		if(Bean.isEmpty())
		{
			FXMLLoader another = new FXMLLoader(getClass().getResource("ReturnCheck.fxml"));
			try {
				AnchorPane anotherPage = (AnchorPane) another.load();
				Scene anotherScene = new Scene(anotherPage);
				anotherScene.getStylesheets().add(getClass().getResource("product.css").toString()); // CSS
				Stage stage = new Stage();
				stage.setScene(anotherScene);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
		{
			ReturnTableView.getItems().addAll(Bean);
		}
	}
	
	public void handleBtnReturnAction(ActionEvent action)
	{
		AnchorPane root = new AnchorPane();
		ObservableList<Node> rootList = root.getChildren();

		root.setPrefWidth(320);
		root.setPrefHeight(137);
		
		Label ReturnText = new Label();

		ReturnText.setLayoutX(90);
		ReturnText.setLayoutY(34);
		ReturnText.setText("반품 하시겠습니까?");
		ReturnText.setFont(Font.font("MDotum",15));

		Button btnOK = new Button();
		Button btnCancel = new Button();
		
		btnOK.setLayoutX(73);
		btnOK.setLayoutY(91);
		btnOK.setMnemonicParsing(false);
		btnOK.setPrefHeight(26);
		btnOK.setPrefWidth(60);
		btnOK.setText("확인");
		btnOK.setFont(Font.font("Aral",13));
		
		btnCancel.setLayoutX(191);
		btnCancel.setLayoutY(91);
		btnCancel.setMnemonicParsing(false);
		btnCancel.setPrefHeight(26);
		btnCancel.setPrefWidth(60);
		btnCancel.setText("취소");
		btnCancel.setFont(Font.font("Aral",13));

		rootList.add(ReturnText);
		rootList.add(btnOK);
		rootList.add(btnCancel);
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("Style3.css").toExternalForm());
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		
		btnOK.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				db.Return_query(tfReceiptNo.getText(), dpDate.getValue());
				db.ReturnGoods(tfReceiptNo.getText(),dpDate.getValue());
				stage.close();
				FXMLLoader another = new FXMLLoader(getClass().getResource("ReturnFinish.fxml"));
				try {
					AnchorPane anotherPage = (AnchorPane) another.load();
					Scene anotherScene = new Scene(anotherPage);
					anotherScene.getStylesheets().add(getClass().getResource("../globalLogin/global.css").toString()); // CSS
					Stage stage = new Stage();
					stage.setScene(anotherScene);
					stage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
				ReturnTableView.getItems().clear();
				ReturnTableView.getItems().add(null);
			}
		});
		
		btnCancel.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				stage.close();
            }
		});
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO 자동 생성된 메소드 스텁
		TableColumn ProductName = (TableColumn) ReturnTableView.getColumns().get(0);
		ProductName.setCellValueFactory(new PropertyValueFactory("ProductName"));
		ProductName.setStyle("-fx-alignment: CENTER;");

		TableColumn Price = (TableColumn) ReturnTableView.getColumns().get(1);
		Price.setCellValueFactory(new PropertyValueFactory("Price"));
		Price.setStyle("-fx-alignment: CENTER;");

		TableColumn Count = (TableColumn) ReturnTableView.getColumns().get(2);
		Count.setCellValueFactory(new PropertyValueFactory("Count"));
		Count.setStyle("-fx-alignment: CENTER;");
		
		ReturnTableView.getItems().add(null);
	}

}
