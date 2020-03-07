package guestLogin;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.ibatis.session.SqlSessionFactory;

import db.LoginDAO;
import db.MyBatisConnectionFactory;
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
import javafx.stage.Stage;
import jgj.util.barogo.ViewerUtil;
import userInfoView.UserInfoController;

/**
 * 
 * @author 吏�以묎뎄 愿�由ъ옄 濡쒓렇�씤李� 踰꾪듉 而⑦듃濡ㅻ윭 id�� pw瑜� �꽆寃⑤컺�븘 db�뿉 ���옣�맂 媛믨낵 鍮꾧탳 留욎쑝硫�
 *         �쉶�썝�젙蹂댁갹(Action.fxml)�쓣 �떎�뻾
 *
 */
public class LoginController implements Initializable {

    // Slogin.fxml�쓽 TExtField�쓽 fx:id �씤 寃� �씠�븯 �룞臾�
    @FXML private TextField         ID;
    @FXML private PasswordField     PW;
    @FXML private Button             btnLogin;
    @FXML private Button             btnExit;
    @FXML private Button            btnRestart;
    @FXML private Button             btnMember;
    @FXML private Button             btnPwfind;
    @FXML private Button            btnPwOK;
    @FXML private RadioButton         rdoFirstPay;
    @FXML private RadioButton         rdoLaterPay;

    LoginDAO dao = new LoginDAO();
    
    SqlSessionFactory sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
    
    public void handleBtnLoginAction(ActionEvent action) throws Exception {
//        String userID = ID.getText();
//        String userPW = PW.getText();
        
        String userId = "jgji";
//
//        boolean isLogin = dao.guestLogin(userID, userPW);

        try {
//            if (!isLogin) {
//                ID.setText("");
//                PW.setText("");
//                
//                return;
//            }
//            
//            if (!rdoFirstPay.isSelected() && !rdoLaterPay.isSelected()) {
//                ViewerUtil.showStage(this, "PlanChk.fxml", "Style3.css", new PlanChk());
//                return;
//            }
//            
//            String remaintime = dao.findRemainTime(userID);
//            if (rdoFirstPay.isSelected() && StringUtil.isEmpty(remaintime)) {
//                ID.setText("");
//                PW.setText("");
//                ViewerUtil.showStage(this, "Paycheck.fxml", "Style3.css", new Paycheck());
//                return;
//            }
//            
//            if (rdoFirstPay.isSelected()) {
//                dao.paymentPlanInsert_query(0, userID, userPW);
//            } else if (rdoLaterPay.isSelected()) {
//                dao.paymentPlanInsert_query(1, userID, userPW);
//            }
//            
//            dao.userTemp_query(userID, userPW);
//            
//            Calendar calendar = Calendar.getInstance();
//            
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            String date = dateFormat.format(calendar.getTime());
//            
//            dateFormat = new SimpleDateFormat("HH:mm");
//            String time = dateFormat.format(calendar.getTime());
//            
//            dao.startTime_query(date, time);
//            
//            int pcNumber = (1+ (int)(Math.random()*10));
//            System.out.println(" pc 번호 : " +pcNumber);
//            dao.pcNumber_query(userID, userPW, pcNumber);
            
            boolean isPrepayment = false;
            if (rdoFirstPay.isSelected()) {
                isPrepayment = true;
            }
            
            FXMLLoader loader = ViewerUtil.getFXMLLoader(this, "../userInfoView/userInfoView.fxml", new UserInfoController(userId, isPrepayment, sqlSessionFactory.openSession(true)));
            Parent mainView = loader.load();

            Scene scene = new Scene(mainView);
            scene.getStylesheets().add(getClass().getResource("../userInfoView/userInfoView.css").toString()); // CSS style 적용
            Stage primaryStage = (Stage) btnLogin.getScene().getWindow();
            primaryStage.setScene(scene);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void handleBtnExitAction(ActionEvent event) {
        btnExit.setOnAction(e -> {
            System.out.println("醫낅즺 �빀�땲�떎.");
            System.exit(0);
        });
    }

    public void handleBtnradioSelect(ActionEvent action) {
        if (rdoFirstPay.isSelected()) {
            System.out.println("�꽑遺덉엯�땲�떎.");
        }
        if (rdoLaterPay.isSelected()) {
            System.out.println("�썑遺덉엯�땲�떎.");
        }
    }

    public void handleBtnRestart(ActionEvent event) {
        // btnExit.setOnAction(e -> {
        // restart(scene);
        System.out.println("�떎�떆 �떆�옉�빀�땲�떎.");

    }

    public void handleBtnMember(ActionEvent action) {
        try { 
            ViewerUtil.showStage(this, "Membership.fxml", "global.css", new MembershipController());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void handleBtnPwfind(ActionEvent action) {
        System.out.println("비밀번호 찾기");
        try{
            ViewerUtil.showStage(this, "PWfind.fxml", "global.css", new LoginController());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void handleBtnPwOK(ActionEvent action) {
        System.out.println("확인");
        Stage primaryStage = (Stage) btnPwOK.getScene().getWindow();
        primaryStage.close();   
        }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub

    }
}