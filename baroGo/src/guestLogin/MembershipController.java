package guestLogin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import db.LoginDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * 
 * @author 정기성 게스트 회원가입 컨트롤러 정보를 DB에 저장
 */
public class MembershipController implements Initializable {

	// Slogin.fxml의 TExtField의 fx:id 인 것 이하 동문
	@FXML
	private TextField name;
	@FXML
	private TextField birthday;
	@FXML
	private RadioButton male;
	@FXML
	private RadioButton female;
	@FXML
	private TextField id;
	@FXML
	private TextField password;
	@FXML
	private TextField passwordchk;
	@FXML
	private TextField email;
	@FXML
	private Button btnOverlap;
	@FXML
	private Button btnJoin;
	@FXML
	private Button btnlog;
	@FXML
	private Button btnconfirm;
	@FXML
	private Button btnNoconfirm;
	@FXML
	private Label lbl3;
	@FXML
	private Label labelpwch;
	@FXML
	private Label lblName;
	@FXML
	private Label lblBirt;
	@FXML
	private Label lblId;
	@FXML
	private Label lblPw;
	@FXML
	private Label lblPwOK;
	@FXML
	private Label lblEmail;

	private LoginDAO db = new LoginDAO();

	boolean Result = true || false;

	public void handleBtnRadioselect(ActionEvent action) {
		String Message = "";
		if (male.isSelected()) {
			Message += male.getText() + "";
		}
		if (female.isSelected()) {
			Message += female.getText() + "";
		}
		lbl3.setText(Message);
		lbl3.setTextFill(Color.rgb(15, 15, 49));
	}

	public void handleBtnOverlap(ActionEvent action) {
		String strOverlpID = id.getText();

		final String regex = "^[a-zA-Z0-9]{4,12}$";
		String n = id.getText();
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(n);
		Result = m.find();
		
		System.out.println(strOverlpID + " : " + Result);

		// 중복검사 유효성 됨!!!!!!
		if (id.getLength() == 0 || id == null) {
			lblId.setText("사용불가능");
			lblId.setTextFill(Color.rgb(210, 39, 30));
		} else {
		    boolean isOverlap = db.isDuplicateUserId(strOverlpID);
		    
			if (Result && !isOverlap) {

				lblId.setText("사용가능");
				lblId.setTextFill(Color.rgb(15, 15, 49));
				popup("Confirm", "global");
			} else if (!Result || isOverlap) {
				System.out.println("사용할수 없습니다.");
				lblId.setText("사용불가능");
				lblId.setTextFill(Color.rgb(210, 39, 30));
				popup("NoConfirm", "global");
			}
		}
	}

	/*
	 * if (id.getLength() == 0 || id == null) { lblId.setText("사용불가능");
	 * lblId.setTextFill(Color.rgb(210, 39, 30)); } else if(!isOverlap ||
	 * Result){ String regex = "^[a-zA-Z0-9]{4,12}$"; String n = id.getText();
	 * Pattern p = Pattern.compile(regex); Matcher m = p.matcher(n); Result =
	 * m.find(); System.out.println(strOverlpID + " : " + Result);
	 * lblId.setText("사용가능"); lblId.setTextFill(Color.rgb(15, 15, 49));
	 * popup("Confirm","global"); } else{ System.out.println("사용할수 없습니다.");
	 * lblId.setText("사용불가능"); lblId.setTextFill(Color.rgb(210, 39, 30));
	 * popup("NoConfirm","global");
	 * 
	 * 
	 * } }
	 */
	/*
	 * String strOverlpID = id.getText();
	 * 
	 * isOverlap = db.id_overlap_chk(strOverlpID, true);
	 * 
	 * if(strOverlpID.equals("")){ lblId.setText("공백입니다.");
	 * lblId.setTextFill(Color.rgb(210, 39, 30)); }
	 * 
	 * else if(!isOverlap){ String regex = "^[a-zA-Z0-9]{4,12}$"; String n =
	 * id.getText(); Pattern p = Pattern.compile(regex); Matcher m =
	 * p.matcher(n); Result = m.find(); System.out.println(strOverlpID + " : " +
	 * Result); lblId.setText("사용가능"); lblId.setTextFill(Color.rgb(15, 15, 49));
	 * popup("Confirm","global"); } else if(isOverlap || !Result) {
	 * lblId.setText("사용불가능"); lblId.setTextFill(Color.rgb(210, 39, 30));
	 * popup("NoConfirm","global"); } Stage primaryStage =
	 * (Stage)btnOverlap.getScene().getWindow(); }
	 */
	public void popup(String action, String strcss) {
		try {
			FXMLLoader another = new FXMLLoader(getClass().getResource(
					"../guestLogin/" + action + ".fxml"));
			try {
				AnchorPane anotherPage = (AnchorPane) another.load();
				// 다른창 띄우는 작업 .... 2
				Scene anotherScene = new Scene(anotherPage);
				anotherScene.getStylesheets().add(
						getClass().getResource(
								"../guestLogin/" + strcss + ".css")
								.toExternalForm());
				Stage stage = new Stage();
				stage.setScene(anotherScene);
				stage.show();
				// 다른창 띄우는 작업 .... 2 끝.
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handleBtnJoin(ActionEvent action) {
		String userName = name.getText();
		String userbirtday = birthday.getText();
		// String userSex = (((RadioButton)
		// this.RadioGroup.getSelectedToggle()).getText());
		String userID = id.getText();
		String userPW = password.getText();
		String userPWchk = passwordchk.getText();
		String userEmail = email.getText();
		
		boolean isOverlap = db.isDuplicateUserId(userID);

		String regex = null;
		String Message = "사용가능";
		String Message2 = "사용불가능";

		// * 이름 유효성 검사
		if (name.getLength() == 0 || name == null) {
			System.out.println("이름을 입력해주세요");
			lblName.setText(Message2);
			lblName.setTextFill(Color.rgb(210, 39, 30));
		} else {
			regex = "^[가-?]{1,8}$";
			String n1 = name.getText();
			Pattern p1 = Pattern.compile(regex);
			Matcher m1 = p1.matcher(n1);
			Result = m1.find();
			System.out.println(userName + " : " + Result);
			lblName.setText(Message);
			lblName.setTextFill(Color.rgb(15, 15, 49));
			if (!Result) {
				System.out.println("사용할수 없습니다.");
				lblName.setText(Message2);
				lblName.setTextFill(Color.rgb(210, 39, 30));

			} else if (birthday.getLength() == 0 || birthday == null) {
				System.out.println("생년월일을 입력해주세요");
				lblBirt.setText(Message2);
				lblBirt.setTextFill(Color.rgb(210, 39, 30));
			} else {
				regex = "^[0-9]{6}$";
				String n2 = birthday.getText();
				Pattern p2 = Pattern.compile(regex);
				Matcher m2 = p2.matcher(n2);
				Result = m2.find();
				System.out.println(userbirtday + " : " + Result);
				lblBirt.setText(Message);
				lblBirt.setTextFill(Color.rgb(15, 15, 49));
				if (!Result) {
					System.out.println("사용할수 없습니다.");
					lblBirt.setText(Message2);
					lblBirt.setTextFill(Color.rgb(210, 39, 30));

				} else if (male.isSelected() == false
						&& female.isSelected() == false) {
					System.out.println("성별을 선택해주세요");
					lbl3.setText(Message2);
					lbl3.setTextFill(Color.rgb(210, 39, 30));
				} else if (id.getLength() == 0 || id == null || isOverlap) {
					System.out.println("id를 입력해주세요");
					lblId.setText(Message2);
					lblId.setTextFill(Color.rgb(210, 39, 30));
				} else {
					regex = "^[a-zA-Z0-9]{4,12}$";
					String n3 = id.getText();
					Pattern p3 = Pattern.compile(regex);
					Matcher m3 = p3.matcher(n3);
					Result = m3.find();

					System.out.println(userID + " : " + Result);
					lblId.setText(Message);
					lblId.setTextFill(Color.rgb(15, 15, 49));
					if (!Result) {
						System.out.println("사용할수 없습니다.");
						lblId.setText(Message2);
						lblId.setTextFill(Color.rgb(210, 39, 30));

					} else if (password.getLength() == 0 || password == null) {
						System.out.println("비밀번호를 입력해주세요");
						lblPw.setText(Message2);
						lblPw.setTextFill(Color.rgb(210, 39, 30));
					} else {
						regex = "^[a-zA-Z0-9~!@#$%^&*()]{8,15}$";
						String n4 = password.getText();
						Pattern p4 = Pattern.compile(regex);
						Matcher m4 = p4.matcher(n4);
						Result = m4.find();
						System.out.println(userPW + " : " + Result);
						lblPw.setText(Message);
						lblPw.setTextFill(Color.rgb(15, 15, 49));
						if (!Result) {
							System.out.println("사용할수 없습니다.");
							lblPw.setText(Message2);
							lblPw.setTextFill(Color.rgb(210, 39, 30));
						} else if (!userPW.equals(userPWchk)) {
							System.out.println("입력한 두 비밀번호가 다릅니다.");
							labelpwch.setText("불일치");
							labelpwch.setTextFill(Color.rgb(210, 39, 30));
						} else {
							labelpwch.setText("일치");
							labelpwch.setTextFill(Color.rgb(15, 15, 49));
						}
					}
				}
				// * 이메일 유효성 코드
				if (email.getLength() == 0 || email == null) {
					System.out.println("이메일을 입력해주세요");
					lblEmail.setText(Message2);
					lblEmail.setTextFill(Color.rgb(210, 39, 30));
				} else {
					regex = "^[a-zA-Z0-9]+[@][a-zA-Z0-9]+[\\.][a-zA-Z0-9]+$";
					String n5 = email.getText();
					Pattern p5 = Pattern.compile(regex);
					Matcher m5 = p5.matcher(n5);
					Result = m5.find();
					System.out.println(userEmail + " : " + Result);
					lblEmail.setText(Message);
					lblEmail.setTextFill(Color.rgb(15, 15, 49));
					if (!Result) {
						System.out.println("사용할수 없습니다.");
						lblEmail.setText(Message2);
						lblEmail.setTextFill(Color.rgb(210, 39, 30));

					} else {
					    boolean bUserSex = false;
						if (male.isSelected()) {
							bUserSex = false;
						} else {
							bUserSex = true;
						}

						db.membership_insert(userName, userbirtday, bUserSex,
								userID, userPW, userEmail);
						try {
							FXMLLoader another = new FXMLLoader(getClass()
									.getResource("JoinOK.fxml"));

							try {
								AnchorPane anotherPage = (AnchorPane) another
										.load();
								// 다른창 띄우는 작업 .... 2
								Scene anotherScene = new Scene(anotherPage);
								anotherScene.getStylesheets().add(
										getClass().getResource("global.css")
												.toString()); // CSS style
																// 적용

								System.out.println("회원가입 완료!");
								Stage stage = new Stage();
								stage.setScene(anotherScene);
								stage.show();

								// 다른창 띄우는 작업 .... 2 끝.
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						Stage primaryStage = (Stage) btnJoin.getScene()
								.getWindow();
						primaryStage.close();
					}
				}
			}
		}
	}

	public void handleBtnlog(ActionEvent action) {
		System.out.println("로그인 하세요.");
		Stage primaryStage = (Stage) btnlog.getScene().getWindow();
		primaryStage.close();
	}

	public void handleBtnConfirm(ActionEvent action) {
		System.out.println("사용가능한 ID입니다.");
		Stage primaryStage = (Stage) btnconfirm.getScene().getWindow();
		primaryStage.close();
	}

	public void handleBtnNoConfirm(ActionEvent action) {
		System.out.println("이미 사용중인 ID입니다.");
		Stage primaryStage = (Stage) btnNoconfirm.getScene().getWindow();
		primaryStage.close();
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
}