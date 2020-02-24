package guestLogin;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GuestLogin extends Application {
	@FXML private Label labelpwch;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass()
					.getResource("Glogin.fxml"));
			Scene scene = new Scene(root); // 장면 생성

			//primaryStage.initStyle(StageStyle.TRANSPARENT);	// 테두리 제거
			primaryStage.setTitle("회원정보"); // 윈도우 창 제목
			primaryStage.setScene(scene); // 윈도우창에 장면 설정
			primaryStage.show(); // 윈도우 보여주기
		} catch (Exception e) {
			throw e;
		}
	}

	public static void main(String[] args) {
		launch(args);	//main 객체 생성 및 메인 윈도우 생성
	}
}
