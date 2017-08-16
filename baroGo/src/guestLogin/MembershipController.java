package guestLogin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import db.DBManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * 
 * @author ���⼺
 * 	�Խ�Ʈ ȸ������ ��Ʈ�ѷ�
 * 	������ DB�� ����
 */
public class MembershipController implements Initializable{

	// Slogin.fxml�� TExtField�� fx:id �� �� ���� ����
	@FXML private TextField			name;
	@FXML private TextField			birthday;
	@FXML private RadioButton		male;
	@FXML private RadioButton		female;
	@FXML private TextField			id;
	@FXML private TextField			password;
	@FXML private TextField			repassword;	
	@FXML private TextField			email;
	@FXML private Button			btnOverlap;
	@FXML private Button			btnJoin;
	@FXML private Button			btnlog;
	@FXML private Button			btnconfirm;
	@FXML private Button			btnNoconfirm;
	@FXML private Label				lbl3;
	@FXML private Label				labelpwch;
	
	private DBManager db = new DBManager();
	
	public void handleBtnRadioselect(ActionEvent action){
		String Message = "";
		if(male.isSelected()){
			Message += male.getText() + "";
		}
		if(female.isSelected()){
			Message += female.getText() + "";
		}
		lbl3.setText(Message);
		lbl3.setTextFill(Color.rgb(210, 39, 30));
	}
	public void handleBtnOverlap(ActionEvent action){
	try{
		FXMLLoader another = new FXMLLoader( getClass().getResource( "Confirm.fxml" ));
		try {
		   AnchorPane anotherPage = (AnchorPane) another.load();
		   // �ٸ�â ���� �۾� .... 2
		   Scene anotherScene = new Scene(anotherPage);
			System.out.println("�ߺ��� ID �Դϴ�.");
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
	public void handleBtnJoin(ActionEvent action)
	{
		String userName = name.getText();
		String userbirtday = birthday.getText();
		//String userSex = (((RadioButton) this.RadioGroup.getSelectedToggle()).getText());
		String userID = id.getText();
		String userPW = password.getText();
		String userPWchk = repassword.getText();
		String userEmail = email.getText();
		boolean bUserSex;

		if(userName.equals("")) {
			System.out.println("�̸��� �����Դϴ�.");
		} else if(userbirtday.equals("")){
			System.out.println("��������� �����Դϴ�.");
		} else if(male.isSelected() == false && female.isSelected() == false){
			System.out.println("������ �������ֽʽÿ�.");
		} else if(userID.equals("")){
			System.out.println("ID�� �����Դϴ�.");
		} else if(userPW.equals("")){
			System.out.println("��й�ȣ�� �����Դϴ�.");
		} else if(userPWchk.equals("")){
			System.out.println("��й�ȣ Ȯ�ζ��� �����Դϴ�.");
		} else if(userEmail.equals("")) {
			System.out.println("�̸����� �����Դϴ�.");
		} else if(userPW.length() < 4) {
			System.out.println("��й�ȣ�� �ּ� 4�� �Դϴ�.");
		}else if(!userPW.equals(userPWchk)) {
			System.out.println("�Է��� �� ��й�ȣ�� �ٸ��ϴ�");
			labelpwch.setText("����ġ");
		} else {
			if(male.isSelected())
			{
				bUserSex = false;
			} else {
				bUserSex = true;
			}
			db.membership_insert(userName, userbirtday, bUserSex, userID, userPW, userEmail);
			try{
				FXMLLoader another = new FXMLLoader( getClass().getResource("JoinOK.fxml"));
				try {
				   AnchorPane anotherPage = (AnchorPane) another.load();
				   // �ٸ�â ���� �۾� .... 2
				   Scene anotherScene = new Scene(anotherPage);
				   
				   System.out.println("ȸ������ �Ϸ�!");
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
			Stage primaryStage = (Stage)btnJoin.getScene().getWindow();
			primaryStage.close();
		}
	}
	
	public void handleBtnlog(ActionEvent action){
		System.out.println("�α��� �ϼ���.");
		Stage primaryStage = (Stage)btnlog.getScene().getWindow();
		primaryStage.close();
	}
	
	public void handleBtnConfirm(ActionEvent action){
		System.out.println("��밡���� ID�Դϴ�.");
		Stage primaryStage = (Stage)btnconfirm.getScene().getWindow();
		primaryStage.close();
	}
	
	public void handleBtnNoConfirm(ActionEvent action){
		System.out.println("������� ID�Դϴ�.");
		Stage primaryStage = (Stage)btnNoconfirm.getScene().getWindow();
		primaryStage.close();
	}
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
}