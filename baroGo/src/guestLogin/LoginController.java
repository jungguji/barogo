package guestLogin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import db.LoginDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jgj.util.barogo.ViewerUtil;

/**
 * 
 * @author 지중구 관리자 로그인창 버튼 컨트롤러 id와 pw를 넘겨받아 db에 저장된 값과 비교 맞으면
 *         회원정보창(Action.fxml)을 실행
 *
 */
public class LoginController implements Initializable {

    // Slogin.fxml의 TExtField의 fx:id 인 것 이하 동문
    @FXML private TextField         ID;
    @FXML private PasswordField     PW;
    @FXML private Button             btnLogin;
    @FXML private Button             btnExit;
    @FXML private Button            btnRestart;
    @FXML private Button             btnMember;
    @FXML private Button             btnPwfind;
    @FXML private RadioButton         rdoFirstPay;
    @FXML private RadioButton         rdoLaterPay;

    public void handleBtnLoginAction(ActionEvent action) {
        String userID = ID.getText();
        String userPW = PW.getText();

        LoginDAO db = new LoginDAO();
        
        boolean isLogin = LoginDAO.guestLogin(userID, userPW);
        boolean isRemaintime = db.paycheck_query(userID, userPW);

        if (!isLogin) {
            ID.setText("");
            PW.setText("");
            
            return;
        }
        
        if (!rdoFirstPay.isSelected() && !rdoLaterPay.isSelected()) {
            FXMLLoader another = new FXMLLoader(getClass().getResource("PlanChk.fxml"));
            try {
                AnchorPane anotherPage = (AnchorPane) another.load();
                // 다른창 띄우는 작업 .... 2
                Scene anotherScene = new Scene(anotherPage);
                Stage stage = new Stage();
                stage.setScene(anotherScene);
                stage.show();
                // 다른창 띄우는 작업 .... 2 끝.
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if (rdoFirstPay.isSelected() == true && !isRemaintime) {
                ID.setText("");
                PW.setText("");
                FXMLLoader another = new FXMLLoader(getClass().getResource("paycheck.fxml"));
                try {
                    AnchorPane anotherPage = (AnchorPane) another.load();
                    // 다른창 띄우는 작업 .... 2
                    Scene anotherScene = new Scene(anotherPage);
                    anotherScene.getStylesheets().add(getClass().getResource("Style3.css").toString()); // CSS
                                                                                                        // style
                                                                                                        // 적용
                    Stage stage = new Stage();
                    stage.setScene(anotherScene);
                    stage.show();
                    // 다른창 띄우는 작업 .... 2 끝.
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                if (rdoFirstPay.isSelected() == true) {
                    db.paymentPlanInsert_query(0, userID, userPW);
                } else if (rdoLaterPay.isSelected() == true) {
                    db.paymentPlanInsert_query(1, userID, userPW);
                }
                db.userTemp_query(userID, userPW);
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../userInfoView/userInfoView.fxml"));
                    Parent mainView = loader.load();

                    Scene scene = new Scene(mainView);
                    scene.getStylesheets().add(getClass().getResource("Style3.css").toString()); // CSS
                                                                                                    // style
                                                                                                    // 적용
                    Stage primaryStage = (Stage) btnLogin.getScene().getWindow();
                    primaryStage.setScene(scene);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public void handleBtnExitAction(ActionEvent event) {
        btnExit.setOnAction(e -> {
            System.out.println("종료 합니다.");
            System.exit(0);
        });
    }

    public void handleBtnradioSelect(ActionEvent action) {
        if (rdoFirstPay.isSelected()) {
            System.out.println("선불입니다.");
        }
        if (rdoLaterPay.isSelected()) {
            System.out.println("후불입니다.");
        }
    }

    public void handleBtnRestart(ActionEvent event) {
        // btnExit.setOnAction(e -> {
        // restart(scene);
        System.out.println("다시 시작합니다.");

    }

    public void handleBtnMember(ActionEvent action) {
        System.out.println("회원가입 하시겠습니까?");
        try { 
            ViewerUtil.showStage(this, "Membership.fxml", "Style3.css");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleBtnPwfind(ActionEvent action) {
        System.out.println("비밀번호를 찾으시겠습니까?");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub

    }
}