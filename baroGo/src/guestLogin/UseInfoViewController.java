package guestLogin;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UseInfoViewController implements Initializable {
	@FXML private Button 		btnSeat;
	private Stage primaryStage;
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	public void handleBtnUseInfoViewAction(ActionEvent action)
	{
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Glogin.fxml"));
			Parent mainView = loader.load();

			System.out.println("�α���â���� ���ư��ϴ�.");
			//Parent mainView = FXMLLoader.load(getClass().getResource("AdminMainView.fxml"));
			Scene scene = new Scene(mainView);
			Stage primaryStage = (Stage)btnSeat.getScene().getWindow();
			primaryStage.setScene(scene);
			
			} catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}

}
