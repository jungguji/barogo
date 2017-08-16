package guestLogin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Paycheck {
	@FXML private AnchorPane panePlanChk;
	
	public void handleBtnOKAction(ActionEvent action)
	{
		Stage primaryStage = (Stage)panePlanChk.getScene().getWindow();
		primaryStage.close();
	}
}
