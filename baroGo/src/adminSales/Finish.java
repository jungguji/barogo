package adminSales;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Finish {
	@FXML private AnchorPane Finish;
	
	public void handleBtnOKAction(ActionEvent action)
	{
		Stage primaryStage = (Stage)Finish.getScene().getWindow();
		primaryStage.close();
	}

}
