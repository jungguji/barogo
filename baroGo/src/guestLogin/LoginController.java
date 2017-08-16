package guestLogin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DB.DBManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * 
 * @author ���߱� ������ �α���â ��ư ��Ʈ�ѷ� id�� pw�� �Ѱܹ޾� db�� ����� ���� �� ������
 *         ȸ������â(Action.fxml)�� ����
 *
 */
public class LoginController implements Initializable {

	// Slogin.fxml�� TExtField�� fx:id �� �� ���� ����
	@FXML private TextField 		ID;
	@FXML private PasswordField 	PW;
	@FXML private Button 			btnLogin;
	@FXML private Button 			btnExit;
	@FXML private Button			btnRestart;
	@FXML private Button 			btnMember;
	@FXML private Button 			btnPwfind;
	@FXML private RadioButton 		rdoFirstPay;
	@FXML private RadioButton 		rdoLaterPay;

	private DBManager db = new DBManager();
	
	private boolean result;
	private boolean result2;

	public void handleBtnLoginAction(ActionEvent action) {
		String userID = ID.getText();
		String userPW = PW.getText();

		result = db.login_query(userID, userPW, false);
		result2 = db.paycheck_query(userID, userPW);

		if (!result) {
			ID.setText("");
			PW.setText("");
		} else {
			if (rdoFirstPay.isSelected() == false && rdoLaterPay.isSelected() == false) {
				FXMLLoader another = new FXMLLoader(getClass().getResource("PlanChk.fxml"));
				try {
					AnchorPane anotherPage = (AnchorPane) another.load();
					// �ٸ�â ���� �۾� .... 2
					Scene anotherScene = new Scene(anotherPage);
					Stage stage = new Stage();
					stage.setScene(anotherScene);
					stage.show();
					// �ٸ�â ���� �۾� .... 2 ��.
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				if (rdoFirstPay.isSelected() == true && !result2) {
					ID.setText("");
					PW.setText("");
					FXMLLoader another = new FXMLLoader(getClass().getResource("paycheck.fxml"));
					try {
						AnchorPane anotherPage = (AnchorPane) another.load();
						// �ٸ�â ���� �۾� .... 2
						Scene anotherScene = new Scene(anotherPage);
						anotherScene.getStylesheets().add(getClass().getResource("Style3.css").toString()); // CSS
																											// style
																											// ����
						Stage stage = new Stage();
						stage.setScene(anotherScene);
						stage.show();
						// �ٸ�â ���� �۾� .... 2 ��.
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					if (rdoFirstPay.isSelected() == true) {
						db.paymentPlanInsert_query(0, userID, userPW);
					} else if (rdoLaterPay.isSelected() == true) {
						db.paymentPlanInsert_query(1, userID, userPW);
					}
					db.userTemp_query(userID, userPW);
					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("../userInfoView/userInfoView.fxml"));
						Parent mainView = loader.load();

						Scene scene = new Scene(mainView);
						scene.getStylesheets().add(getClass().getResource("Style3.css").toString()); // CSS
																										// style
																										// ����
						Stage primaryStage = (Stage) btnLogin.getScene().getWindow();
						primaryStage.setScene(scene);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}

	public void handleBtnExitAction(ActionEvent event) {
		btnExit.setOnAction(e -> {
			System.out.println("���� �մϴ�.");
			System.exit(0);
		});
	}

	public void handleBtnradioSelect(ActionEvent action) {
		if (rdoFirstPay.isSelected()) {
			System.out.println("�����Դϴ�.");
		}
		if (rdoLaterPay.isSelected()) {
			System.out.println("�ĺ��Դϴ�.");
		}
	}

	public void handleBtnRestart(ActionEvent event) {
		// btnExit.setOnAction(e -> {
		// restart(scene);
		System.out.println("�ٽ� �����մϴ�.");

	}

	public void handleBtnMember(ActionEvent action) {
		System.out.println("ȸ������ �Ͻðڽ��ϱ�?");
		{
			try {
				FXMLLoader another = new FXMLLoader(getClass().getResource("Membership.fxml"));
				try {
					AnchorPane anotherPage = (AnchorPane) another.load();
					// �ٸ�â ���� �۾� .... 2
					Scene anotherScene = new Scene(anotherPage);
					anotherScene.getStylesheets().add(getClass().getResource("Style3.css").toString()); // CSS
																										// Style
																										// ����
					Stage stage = new Stage();
					stage.setScene(anotherScene);
					stage.show();
					// �ٸ�â ���� �۾� .... 2 ��.

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void handleBtnPwfind(ActionEvent action) {
		System.out.println("��й�ȣ�� ã���ðڽ��ϱ�?");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
}