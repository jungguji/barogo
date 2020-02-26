/**
 * 
 */
package useInfo;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import db.DBManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author 지중구
 *      시간을 추가하는 클래스
 *
 */
public class TimeAdd1 implements Initializable {
    @FXML private AnchorPane    paneTime;
    @FXML private Button        btnOK;
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
    
    public void handleBtnOkAction(ActionEvent action)
    {
        db.user_add_time(userName, addTime);
        ServerClientTimeAddBg timeAdd = new ServerClientTimeAddBg();
        //timeAdd.connet(iAddTime);
        window_close();
    }
    public void handleBtnNoAction(ActionEvent action)
    {
        window_close();
    }
    
    public void window_close()
    {
        Stage primaryStage = (Stage)paneTime.getScene().getWindow();
        primaryStage.close();
    }
    
}