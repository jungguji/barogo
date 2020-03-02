package guestLogin;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GuestLogin extends Application {
	@FXML private Label labelpwch;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass()
					.getResource("Glogin.fxml"));
			Scene scene = new Scene(root); // ��� ����
            scene.getStylesheets().add(
                    getClass().getResource("Glogin.css").toString()); // CSS style 적용
            
//            primaryStage.setMaximized(true);        // 창 무조건 최대화!!!!!!!!!!!!!!!!!!!!!!!!!!
            primaryStage.initStyle(StageStyle.TRANSPARENT); // 테두리 제거

			primaryStage.setTitle("회원정보"); // ������ â ����
			primaryStage.setScene(scene); // ������â�� ��� ����
			primaryStage.show(); // ������ �����ֱ�
		} catch (Exception e) {
			throw e;
		}
	}

	public static void main(String[] args) {
		launch(args);	//main ��ü ���� �� ���� ������ ����
	}
}
