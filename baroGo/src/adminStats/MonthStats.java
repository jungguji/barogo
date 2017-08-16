package adminStats;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MonthStats extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		
		Parent root = FXMLLoader.load(getClass().getResource("MonthStats.fxml"));

		Scene scene = new Scene(root);	//장면 생성
		scene.getStylesheets().add(getClass().getResource("Style3.css").toString());	//CSS style 적용
		
		primaryStage.setTitle("월별통계");	//윈도우 창 제목
		primaryStage.setScene(scene);		//윈도우창에 장면 설정
		primaryStage.show();				//윈도우 보여주기
	}

	public static void main(String[] args) {
		launch(args);	//main 객체 생성 및 메인 윈도우 생성
	}
}
