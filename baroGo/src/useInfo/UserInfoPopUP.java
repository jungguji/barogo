package useInfo;

import java.net.URL;
import java.util.ResourceBundle;

import db.DBManager;
import db.UserDAO;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jgj.util.barogo.StringUtil;
import jgj.util.barogo.ViewerUtil;

public class UserInfoPopUP extends Application implements Initializable {
    @FXML private Button         btnClose;
    @FXML private Button         btnSearch;
    @FXML private TextField        tfPCNumber;
    @FXML private TextField        tfUseName;
    @FXML private TextField        tfUseID;
    @FXML private TextField        tfUseEmail;
    @FXML private RadioButton    rdoMan;
    @FXML private RadioButton    rdoWoman;
    @FXML private RadioButton    rdoFirstPay;
    @FXML private RadioButton    rdoLaterPay;
    @FXML private TextField        tfUseTime;
    @FXML private TextField        tfRemainTime;
    @FXML private TextField        tfAccrueMoney;
    @FXML private TextField        tfAccrueTime;
    @FXML private TextField        tfName;
    
    DBManager db = new DBManager();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            String userId = db.getTempId(); 
            
            UserDAO dao = new UserDAO();
            
            if (StringUtil.isEmpty(userId)) {
                return;
            }
            
            UseBean useBean = dao.userSearch(userId);
            
            tfPCNumber.setText(Integer.toString(useBean.getiPCNumber()));
            tfUseName.setText(useBean.getStrName());
            tfUseID.setText(useBean.getStrID());
            tfUseEmail.setText(useBean.getStrEmail());
            
            if(useBean.isbPaymentplan()) {
                rdoLaterPay.setSelected(true);
            } else {
                rdoFirstPay.setSelected(true);
            }
            
            if(useBean.isbSex()) {
                rdoWoman.setSelected(true);
            } else {
                rdoMan.setSelected(true);
            }
            
            tfUseTime.setText(useBean.getStrUsetime());
            tfRemainTime.setText(useBean.getStrRemaintime());
            tfAccrueMoney.setText(Integer.toString(useBean.getiAccruemoney()));
            tfAccrueTime.setText(useBean.getStrAccruetime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader another = new FXMLLoader( getClass().getResource( "../useInfo/useInfoPopUP.fxml" ));
            Object obj = new UserInfoPopUP();
            another.setController(obj);
            
            Parent root = another.load();
            
            Scene scene = new Scene(root);     
            primaryStage.setTitle("ȸ������");  
            primaryStage.setScene(scene);      
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleBtnExitAction(ActionEvent action) {
        Stage primaryStage = (Stage)btnClose.getScene().getWindow();
        primaryStage.close();
    }
    
    public void handleBtnUseSearchAction(ActionEvent action) throws Exception {
        Object search = new UserInfoSearch(tfName.getText());
        
        ViewerUtil.showStageNotCss(this, "../useInfo/useSearch.fxml", search);
    }
    
    public void handleBtnPwChangeAction(ActionEvent action) throws Exception {
        
        Object obj = new UserPasswordChange();
        ViewerUtil.showStageNotCss(this, "../useInfo/password.fxml", obj);
    }
}