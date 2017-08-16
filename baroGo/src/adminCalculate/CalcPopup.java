package adminCalculate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CalcPopup {
	@FXML AnchorPane paneAddPeople;
	
	public void handleBtnOKAction(ActionEvent action)
	{
		Stage primaryStage = (Stage)paneAddPeople.getScene().getWindow();
		primaryStage.close();
	}
}
