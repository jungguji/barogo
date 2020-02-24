package adminLogin;


import db.LoginDAO;
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
 * @author 占쏙옙占쌩깍옙
 *     占쏙옙占쏙옙占쏙옙 占싸깍옙占쏙옙창 占쏙옙튼 占쏙옙트占싼뤄옙
 *     id占쏙옙 pw占쏙옙 占싼겨받억옙 db占쏙옙 占쏙옙占쏙옙占� 占쏙옙占쏙옙 占쏙옙
 *  占쏙옙占쏙옙占쏙옙 회占쏙옙占쏙옙占쏙옙창(AdminMainView.fxml)占쏙옙 占쏙옙占쏙옙
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
            scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
            
            Stage primaryStage = (Stage)btnLogin.getScene().getWindow();
            primaryStage.setScene(scene);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}