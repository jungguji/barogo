package useInfo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * @author 지중구
 *      시간 추가창을 닫는 클래스
 */

public class TimeAddPeople {
	@FXML AnchorPane paneAddPeople;
	
	public void handleBtnOKAction(ActionEvent action)
	{
		Stage primaryStage = (Stage)paneAddPeople.getScene().getWindow();
		primaryStage.close();
	}
}
