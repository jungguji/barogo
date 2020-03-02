package adminLogin;


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
 *  관리자 로그인창 버튼 컨트롤러
 *  id와 pw를 넘겨받아 db에 저장된 값과 비교
 *  맞으면 회원정보창(AdminMainView.fxml)을 실행
 *
 */
public class LoginController{

    @FXML private TextField         id;
    @FXML private PasswordField        pw;
    @FXML private Button             btnLogin;

    public void handleBtnLoginAction(ActionEvent action)
    {
        try {
//            String adminId = id.getText();
//            String adminPw = pw.getText();
//            
//            boolean isLogin = LoginDAO.adminLogin(adminId, adminPw);
//            
//            if(!isLogin) {
//                id.setText("");
//                pw.setText("");
//                
//                return;
//            }
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../adminView/AdminMainView.fxml"));
            Parent mainView = loader.load();
    
            //Parent mainView = FXMLLoader.load(getClass().getResource("AdminMainView.fxml"));
            Scene scene = new Scene(mainView);
            scene.getStylesheets().add(getClass().getResource("../adminView/AdminMainView.css").toExternalForm());
            Stage primaryStage = (Stage)btnLogin.getScene().getWindow();
            
            primaryStage.setResizable(false); // 창 크기 조절 불가능
            primaryStage.setX(0);
            primaryStage.setY(0);
            primaryStage.setScene(scene);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}