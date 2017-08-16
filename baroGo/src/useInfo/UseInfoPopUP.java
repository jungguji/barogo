package useInfo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import db.DBManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UseInfoPopUP extends Application implements Initializable {
	@FXML private Button 		btnClose;
	@FXML private Button 		btnSearch;
	@FXML private TextField		tfPCNumber;
	@FXML private TextField		tfUseName;
	@FXML private TextField		tfUseID;
	@FXML private TextField		tfUseEmail;
	@FXML private RadioButton	rdoMan;
	@FXML private RadioButton	rdoWoman;
	@FXML private RadioButton	rdoFirstPay;
	@FXML private RadioButton	rdoLaterPay;
	@FXML private TextField		tfUseTime;
	@FXML private TextField		tfRemainTime;
	@FXML private TextField		tfAccrueMoney;
	@FXML private TextField		tfAccrueTime;
	@FXML private TextField		tfName;
	
	DBManager db = new DBManager();
	UseBean useBean = new UseBean();
	String strResult;
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		strResult = db.temp_id_print(); 
		
		if(strResult != null)
		{
			useBean = db.user_search(strResult);
			tfPCNumber.setText(Integer.toString(useBean.getiPCNumber()));
			tfUseName.setText(useBean.getStrName());
			tfUseID.setText(useBean.getStrID());
			tfUseEmail.setText(useBean.getStrEmail());
			
			if(useBean.isbPaymentplan())
			{
				rdoLaterPay.setSelected(true);
			} else {
				rdoFirstPay.setSelected(true);
			}
			if(useBean.isbSex())
			{
				rdoWoman.setSelected(true);
			} else {
				rdoMan.setSelected(true);
			}
			tfUseTime.setText(useBean.getStrUsetime());
			tfRemainTime.setText(useBean.getStrRemaintime());
			tfAccrueMoney.setText(Integer.toString(useBean.getiAccruemoney()));
			tfAccrueTime.setText(useBean.getStrAccruetime());
			
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		Parent root = FXMLLoader.load(getClass().getResource("useInfoPopUP.fxml"));
		Scene scene = new Scene(root);		//��� ����
		primaryStage.setTitle("ȸ������");	//������ â ����
		primaryStage.setScene(scene);		//������â�� ��� ����
		primaryStage.show();				//������ �����ֱ�
	}

	public void handleBtnExitAction(ActionEvent action)
	{
		Stage primaryStage = (Stage)btnClose.getScene().getWindow();
		db.temp_delete();
		primaryStage.close();
	}
	
	public void handleBtnUseSearchAction(ActionEvent action)
	{
		db.temp_delete();
		db.search_temp(tfName.getText());
		try{
			FXMLLoader another = new FXMLLoader( getClass().getResource( "../useInfo/useSearch.fxml" ));
			try {
			   AnchorPane anotherPage = (AnchorPane) another.load();
			   // �ٸ�â ���� �۾� .... 2
			   Scene anotherScene = new Scene(anotherPage);
			   Stage stage = new  Stage();
			   stage.setScene(anotherScene);
			   stage.show();
			   // �ٸ�â ���� �۾� .... 2 ��.
			   Stage primaryStage = (Stage)btnClose.getScene().getWindow();
			   primaryStage.close();
			} catch (IOException e) {
			   e.printStackTrace();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void handleBtnPwChangeAction(ActionEvent action)
	{
		try{
			FXMLLoader another = new FXMLLoader( getClass().getResource( "../useInfo/password.fxml" ));
			try {
			   AnchorPane anotherPage = (AnchorPane) another.load();
			   // �ٸ�â ���� �۾� .... 2
			   Scene anotherScene = new Scene(anotherPage);
			   Stage stage = new  Stage();
			   stage.setScene(anotherScene);
			   stage.show();
			   // �ٸ�â ���� �۾� .... 2 ��.
			} catch (IOException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);	//main ��ü ���� �� ���� ������ ����
	}
}