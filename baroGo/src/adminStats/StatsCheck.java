package adminStats;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class StatsCheck {
	
	@FXML private AnchorPane StatsCheck;

	public void handleBtnOKAction(ActionEvent action) {
		Stage primaryStage = (Stage) StatsCheck.getScene().getWindow();
		primaryStage.close();
	}
}
