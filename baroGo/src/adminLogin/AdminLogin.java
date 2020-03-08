package adminLogin;

import org.apache.ibatis.session.SqlSessionFactory;

import db.MyBatisConnectionFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jgj.util.barogo.ViewerUtil;


/**
 * 
 * @author ���߱�
 *	������ �α���â
 *	
 */
public class AdminLogin extends Application {

    public void start(Stage primaryStage) {
		try {
		    
		    SqlSessionFactory sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
		    LoginController controller = new LoginController(sqlSessionFactory.openSession());
		    
		    FXMLLoader loader = ViewerUtil.getFXMLLoader(this, "Slogin.fxml", controller);
			AnchorPane root = loader.load();
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());

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
