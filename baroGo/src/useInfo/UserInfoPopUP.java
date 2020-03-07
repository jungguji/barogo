package useInfo;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import barogo.user.repository.UserMapper;
import db.MyBatisConnectionFactory;
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

    SqlSessionFactory sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
    
    int pcNumber;
    SqlSession sqlSession;
    UserMapper mapper;
    
    public UserInfoPopUP(int pcNumber, SqlSession sqlSession) {
        this.pcNumber = pcNumber;
        this.sqlSession = sqlSession;
        this.mapper = sqlSession.getMapper(UserMapper.class);
    }
    
    public UserInfoPopUP(SqlSession sqlSession) {
        this.pcNumber = 0;
        this.sqlSession = sqlSession;
        this.mapper = sqlSession.getMapper(UserMapper.class);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            if (StringUtil.isEmpty(pcNumber)) {
                return;
            }
            
            UserVO user = mapper.findByPcNumber(pcNumber);
            
            tfPCNumber.setText(Integer.toString(user.getPcNumber()));
            tfUseName.setText(user.getName());
            tfUseID.setText(user.getUserId());
            tfUseEmail.setText(user.getEmail());
            
            if(user.isPrepayment()) {
                rdoFirstPay.setSelected(true);
            } else {
                rdoLaterPay.setSelected(true);
            }
            
            if("male".equals(user.getSex())) {
                rdoMan.setSelected(true);
            } else {
                rdoWoman.setSelected(true);
            }
            
            tfUseTime.setText(user.getUseTime().toString());
            tfRemainTime.setText(user.getRemainTime().toString());
            tfAccrueMoney.setText(Integer.toString(user.getCumulativeAmount()));
            tfAccrueTime.setText(user.getCumulativeTime().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Object obj = new UserInfoPopUP(sqlSessionFactory.openSession());
            FXMLLoader another = ViewerUtil.getFXMLLoader(this, "../useInfo/useInfoPopUP.fxml", obj);
            
            Parent root = another.load();
            
            Scene scene = new Scene(root);     
            primaryStage.setTitle("회원정보");  
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
        SqlSessionFactory sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
        Object search = new UserSearchController(tfName.getText(), sqlSessionFactory.openSession());
        
        ViewerUtil.showStage(this, "../useInfo/useSearch.fxml", null, search);
    }
    
    public void handleBtnPwChangeAction(ActionEvent action) throws Exception {
        Object obj = new UserPasswordChange();
        ViewerUtil.showStage(this, "../useInfo/password.fxml", null, obj);
    }
}