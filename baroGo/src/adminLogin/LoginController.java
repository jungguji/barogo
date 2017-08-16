package adminLogin;


import DB.DBManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * 
 * @author 지중구
 * 	관리자 로그인창 버튼 컨트롤러
 * 	id와 pw를 넘겨받아 db에 저장된 값과 비교
 *  맞으면 회원정보창(AdminMainView.fxml)을 실행
 *
 */
public class LoginController{

	// Slogin.fxml의 TExtField의 fx:id 인 것 이하 동문
	@FXML private TextField 		id;
	@FXML private PasswordField		pw;
	@FXML private Button 			btnLogin;

	private DBManager db = new DBManager();
	private boolean result;

	public void handleBtnLoginAction(ActionEvent action)
	{
		String strID = id.getText();
		String strPW = pw.getText();
		
		result = db.login_query(strID, strPW, true);
		
		if(!result)
		{
			id.setText("");
			pw.setText("");
		} else  {
			try{
				
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../adminView/AdminMainView.fxml"));
			Parent mainView = loader.load();

			//Parent mainView = FXMLLoader.load(getClass().getResource("AdminMainView.fxml"));
			Scene scene = new Scene(mainView);
			scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
			Stage primaryStage = (Stage)btnLogin.getScene().getWindow();
			primaryStage.setScene(scene);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}