/**
 * 
 */
package useInfo;

import java.net.URL;
import java.util.ResourceBundle;

import DB.DBManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author 지중구
 * 	1시간을 추가하는 클래스
 *
 */
public class TimeAdd1 implements Initializable {
	@FXML private AnchorPane	paneTime;
	@FXML private Button		btnOK;
	@FXML private Label			lblAlert;
	
	private DBManager	db			= new DBManager();
	private String		strID		= null;
	private int			iAddTime =1;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		strID = db.temp_id_print();
		iAddTime = db.temp_time_print(strID);
		lblAlert.setText("'"+strID + "' 님에게 "+ iAddTime +"시간을 추가하시겠습니까?");
	}
	
	public void handleBtnOkAction(ActionEvent action)
	{
		db.user_add_time(strID,iAddTime);
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
