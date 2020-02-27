/**
 * 
 */
package useInfo;

import java.net.URL;
import java.util.ResourceBundle;

import adminChat.ServerClientTimeAddBg;
import db.DBManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jgj.util.barogo.StringUtil;

/**
 * @author 지중구
 *      시간을 추가하는 클래스
 *
 */
public class TimeAdd1 implements Initializable {
    @FXML private AnchorPane    paneTime;
    @FXML private Label         lblAlert;
    
    private DBManager   db          = new DBManager();
    private String userName;
    private int addTime = 0;
    
    public TimeAdd1(String userName, int addTime) {
        this.userName = userName;
        this.addTime = addTime;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblAlert.setText("'"+ userName + "' 님에게 "+ addTime +"시간을 추가하시겠습니까?");
    }
    
    public void handleBtnOkAction(ActionEvent action) {
        String remainTime = db.findRemainTime(userName);
        String updateTime = getUpdateTime(remainTime, addTime);
        
        db.updateUserRemaintimeById(updateTime, userName);
        ServerClientTimeAddBg timeAdd = new ServerClientTimeAddBg();
        
        timeAdd.connet(addTime);
        
        close();
    }
    
    private String getUpdateTime(String remainTime, int addTime) {
        String updateTime = "";
        if(StringUtil.isEmpty(remainTime)) {
            updateTime = String.valueOf(addTime) + ":" + "00";
        } else {
            String[] subMsg = remainTime.split(":");
            
            String hour = String.valueOf((Integer.parseInt(subMsg[0]) + addTime));
            
            updateTime = hour + ":" + subMsg[1];
        }
        
        return updateTime;
    }
    
    public void handleBtnNoAction(ActionEvent action)
    {
        close();
    }
    
    private void close() {
        Stage primaryStage = (Stage)paneTime.getScene().getWindow();
        primaryStage.close();
    }
    
}