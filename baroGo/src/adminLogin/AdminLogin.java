package adminLogin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * 
 * @author 지중구
 *	관리자 로그인창
 *	
 */
public class AdminLogin extends Application {

    public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader
					.load(getClass()
					.getResource("Slogin.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("Style2.css").toExternalForm());

			primaryStage.setTitle("바로go 카운터");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
