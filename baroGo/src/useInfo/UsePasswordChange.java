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
		Scene scene = new Scene(root);	//��� ����
		primaryStage.setTitle("��й�ȣ ����");	//������ â ����
		primaryStage.setScene(scene);		//������â�� ��� ����
		primaryStage.show();				//������ �����ֱ�
	}
	
	public void handleBtnOkAction(ActionEvent action)
	{
		String strPw		= pwfNewPW.getText();
		String strPwChk		= pwfNewPWChk.getText();
		
		System.out.println("���� : "+strPw);
		System.out.println("����2 : "+strPwChk);
		
		if(strPw.equals("") || strPwChk.equals(""))
		{
			System.out.println("��ĭ�� �ֽ��ϴ�.");
			pwfNewPW.setText("");
			pwfNewPWChk.setText("");
		} else if(strPw.length() < 4) {
			System.out.println("��й�ȣ�� �ּ� 4�� �Դϴ�.");
		}else if(!strPw.equals(strPwChk)) {
			System.out.println("�Է��� �� ��й�ȣ�� �ٸ��ϴ�");
		} else {
			db.user_passwd_change(strPw);
			System.out.println("��й�ȣ ���� �Ϸ�");
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
		launch(args);	//main ��ü ���� �� ���� ������ ����
	}
}
