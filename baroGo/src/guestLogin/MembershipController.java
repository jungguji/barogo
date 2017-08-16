package guestLogin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DB.DBManager;
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
 * @author 정기성
 * 	게스트 회원가입 컨트롤러
 * 	정보를 DB에 저장
 */
public class MembershipController implements Initializable{

	// Slogin.fxml의 TExtField의 fx:id 인 것 이하 동문
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
		   // 다른창 띄우는 작업 .... 2
		   Scene anotherScene = new Scene(anotherPage);
			System.out.println("중복된 ID 입니다.");
		   Stage stage = new  Stage();
		   stage.setScene(anotherScene);
		   stage.show();
		   // 다른창 띄우는 작업 .... 2 끝.
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
			System.out.println("이름이 공백입니다.");
		} else if(userbirtday.equals("")){
			System.out.println("생년월일이 공백입니다.");
		} else if(male.isSelected() == false && female.isSelected() == false){
			System.out.println("성별을 선택해주십시오.");
		} else if(userID.equals("")){
			System.out.println("ID가 공백입니다.");
		} else if(userPW.equals("")){
			System.out.println("비밀번호가 공백입니다.");
		} else if(userPWchk.equals("")){
			System.out.println("비밀번호 확인란이 공백입니다.");
		} else if(userEmail.equals("")) {
			System.out.println("이메일이 공백입니다.");
		} else if(userPW.length() < 4) {
			System.out.println("비밀번호는 최소 4자 입니다.");
		}else if(!userPW.equals(userPWchk)) {
			System.out.println("입력한 두 비밀번호가 다릅니다");
			labelpwch.setText("불일치");
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
				   // 다른창 띄우는 작업 .... 2
				   Scene anotherScene = new Scene(anotherPage);
				   
				   System.out.println("회원가입 완료!");
				   Stage stage = new  Stage();
				   stage.setScene(anotherScene);
				   stage.show();
				   // 다른창 띄우는 작업 .... 2 끝.
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
		System.out.println("로그인 하세요.");
		Stage primaryStage = (Stage)btnlog.getScene().getWindow();
		primaryStage.close();
	}
	
	public void handleBtnConfirm(ActionEvent action){
		System.out.println("사용가능한 ID입니다.");
		Stage primaryStage = (Stage)btnconfirm.getScene().getWindow();
		primaryStage.close();
	}
	
	public void handleBtnNoConfirm(ActionEvent action){
		System.out.println("사용중인 ID입니다.");
		Stage primaryStage = (Stage)btnNoconfirm.getScene().getWindow();
		primaryStage.close();
	}
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
}