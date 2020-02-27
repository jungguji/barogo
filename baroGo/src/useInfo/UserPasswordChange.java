package useInfo;

import db.DBManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author 지중구
 *		회원정보에서 출력된 회원의 비밀버호를 바꿔주는 클래스
 */
public class UsePasswordChange extends Application {
	@FXML private AnchorPane	panePasswd;
	@FXML private Button		btnOK;
	@FXML private Button		btnClose;
	@FXML private PasswordField pwfNewPW;
	@FXML private PasswordField pwfNewPWChk;
	
	DBManager db = new DBManager();
	@Override
	public void start(Stage primaryStage) throws Exception{
		
		Parent root = FXMLLoader.load(getClass().getResource("password.fxml"));
		Scene scene = new Scene(root);	//장면 생성
		scene.getStylesheets().add(getClass().getResource("../guestLogin/global.css").toExternalForm());
		primaryStage.initStyle(StageStyle.UTILITY);	// 테두리 제거
		primaryStage.setTitle("비밀번호 변경");	//윈도우 창 제목
		primaryStage.setScene(scene);		//윈도우창에 장면 설정
		primaryStage.show();				//윈도우 보여주기
	}
	
	public void handleBtnOkAction(ActionEvent action)
	{
		String strPw		= pwfNewPW.getText();
		String strPwChk		= pwfNewPWChk.getText();
		
		System.out.println("으악 : "+strPw);
		System.out.println("으악2 : "+strPwChk);
		
		if(strPw.equals("") || strPwChk.equals(""))
		{
			System.out.println("빈칸이 있습니다.");
			pwfNewPW.setText("");
			pwfNewPWChk.setText("");
		} else if(strPw.length() < 4) {
			System.out.println("비밀번호는 최소 4자 입니다.");
		}else if(!strPw.equals(strPwChk)) {
			System.out.println("입력한 두 비밀번호가 다릅니다");
		} else {
			db.user_passwd_change(strPw);
			System.out.println("비밀번호 변경 완료");
			pwfNewPW.setText("");
			pwfNewPWChk.setText("");
			Stage primaryStage = (Stage)panePasswd.getScene().getWindow();
			primaryStage.close();
		}
	}
	
	public void handleBtnCloseAction(ActionEvent action)
	{
		Stage primaryStage = (Stage)panePasswd.getScene().getWindow();
		primaryStage.close();
	}
	
	public static void main(String[] args) {
		launch(args);	//main 객체 생성 및 메인 윈도우 생성
	}
}
