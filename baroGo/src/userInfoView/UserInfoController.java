package userInfoView;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import DB.DBManager;
import adminChat.ServerBackground;
import clientChat.ClientBack2;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import userChat.ClientChatGui;

/**
 * 
 * @author 정기성
 * 	게스트 게스트 Action 컨트롤러
 * 	게스트의 정보 확인창
 */
public class UserInfoController implements Initializable{

	@FXML private Button			btnproduct;
	@FXML private Button			btntalk;
	@FXML private Button			btnSeat;
	@FXML private Button			btnExit;
	
	@FXML private Label				salesType;
	@FXML private Label				lblid;
	@FXML private Label				lblusetime;
	@FXML private Label				lblremaintime;
	@FXML private Label				lblfirstmoney;
	@FXML private Label				lblLatermoney;
	@FXML private Text				tfPCNum;
	
	private DBManager db = new DBManager();
	private ClientBack2 client = new ClientBack2();
	
	private int startMoney = 1000;
	private int laterMoney = 0;
	
	private int hour = 0;
	private int minute = 0;
	
	public void handleBtnproduct(ActionEvent  action){
	try{
		FXMLLoader another = new FXMLLoader( getClass().getResource( "../userInfoView/Order.fxml" ));
		try {
		   AnchorPane anotherPage = (AnchorPane) another.load();
		   // 다른창 띄우는 작업 .... 2
		   Scene anotherScene = new Scene(anotherPage);
		   anotherScene.getStylesheets().add(
					getClass().getResource("Style3.css").toString()); // CSS Style 적용
			System.out.println("상품을 주문하시겠습니까?");
		   Stage stage = new  Stage();
		   stage.setScene(anotherScene);
		   stage.show();
		   // 다른창 띄우는 작업 .... 2 끝.
		} catch (IOException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		}
	} catch(Exception e) {
		e.printStackTrace();
	}
}
	public void handleBtnTalk(ActionEvent action) throws Exception
	{
		
		ClientChatGui gui = new ClientChatGui(tfPCNum.getText());
		gui.start(null);
	}
	
	public void handleBtnSeat(ActionEvent action)
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../guestLogin/Glogin.fxml"));
			Parent mainView = loader.load();

			System.out.println("로그인창으로 돌아갑니다.");
			Scene scene = new Scene(mainView);

			scene.getStylesheets().add(getClass().getResource("Style3.css").toExternalForm());
			Stage primaryStage = (Stage) btnSeat.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void handleBtnExitAction(ActionEvent action) throws InterruptedException 
	{
		if(salesType.getText().equals("선불"))
		{
			db.user_data_save(lblid.getText(), lblremaintime.getText());
			client.connet();
			client.sendMessage(tfPCNum.getText());
			client.socket_close();
		} else {
			client.connet();
			client.sendMessage(tfPCNum.getText());
			client.socket_close();
		}

		/*
		System.out.println("컴퓨터를 종료합니다.");
		Runtime runtime = Runtime.getRuntime();
		try
		{
			Process process = runtime.exec("C:\\WINDOWS\\system32\\cmd.exe");
			OutputStream os = process.getOutputStream();
			os.write("shutdown -s -f -c -t \n\r".getBytes()); // 5초 이내로 컴터 꺼짐
			os.close();
			process.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
	}
	
	public void actioninit()
	{
		userInfoBean beanUserInfo = new userInfoBean();
		String user = db.userTempPrint_query();
		
		String userInfo[] = new String[2];
		StringTokenizer st = new StringTokenizer(user, ",");
		int i = 0;
		while (st.hasMoreTokens()) {
			userInfo[i] = st.nextToken();
			i++;
		}
		int payPlan = db.paymentPlanOutput_query(userInfo[0],userInfo[1]);
		switch(payPlan)
		{
		case 0:
			salesType.setText("선불");
			
			beanUserInfo = db.actioninit_query(payPlan, userInfo[0], userInfo[1]);
			lblid.setText(beanUserInfo.getUserID());
			
			lblfirstmoney.setText(beanUserInfo.getFirstMoney()+" 원");
			
			String strInitRemainTime = beanUserInfo.getstrRemainTime();
			
			StringTokenizer strToken = new StringTokenizer(strInitRemainTime, ":");
			String[] strTime = new String[2];
			int j = 0;
			
			while(strToken.hasMoreElements())
			{
				strTime[j++] = strToken.nextToken();
			}
			
			hour = Integer.parseInt(strTime[0]); 
			minute = Integer.parseInt(strTime[1]);
			
			Task<Void> task1 = new Task<Void>() {
				@Override
				public Void call() throws Exception {
					while (true) {
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								
								if(minute == 0)
								{
									hour--;
									minute = 59;
								}
								
								String time = hour + ":" + minute;
								Calendar calendar = Calendar.getInstance();
								SimpleDateFormat dateforamt = new SimpleDateFormat("HH:mm");
								
								String strStartTime = dateforamt.format(calendar.getTime());
								lblremaintime.setText(time);
								
								client.connet();
								client.sendMessage(tfPCNum.getText() + "\n" 
													+ strStartTime + "\n" 
													+ salesType.getText() + "\n" 
													+ lblid.getText() + "\n" 
													+ lblremaintime.getText()+ "\n" 
													+ lblfirstmoney.getText());
								client.socket_close();
								minute--;
								
							}
						});
						Thread.sleep(60000);
					}
				}
			};
			Thread th1 = new Thread(task1);
			th1.setDaemon(true);
			th1.start();
			
			break;
			
		case 1:
			salesType.setText("후불");
			beanUserInfo = db.actioninit_query(payPlan, userInfo[0], userInfo[1]);
			lblid.setText(beanUserInfo.getUserID());
			lblLatermoney.setText(startMoney + "원");
			
			Task<Void> task = new Task<Void>() {
				@Override
				public Void call() throws Exception {
					while (true) {
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								
								String time = hour + ":" + minute;
								
								Calendar calendar = Calendar.getInstance();
								SimpleDateFormat dateforamt = new SimpleDateFormat("HH:mm");
								
								String strStartTime = dateforamt.format(calendar.getTime());
								lblusetime.setText(time);
								lblLatermoney.setText(startMoney + "원");
								
								client.connet();
								client.sendMessage(tfPCNum.getText() + "\n" 
													+ strStartTime + "\n" 
													+ salesType.getText() + "\n" 
													+ lblid.getText() + "\n" 
													+ lblusetime.getText() + "\n" 
													+ lblLatermoney.getText());
								client.socket_close();
								minute++;
								if(minute == 60)
								{
									minute = 0;
									hour++;
								}
								
								if(hour>=1)
								{
									laterMoney++;
									if(laterMoney%6 == 0)
									{
										startMoney =  startMoney + 100;
									}
								}
							}
						});
						Thread.sleep(60000);
					}
				}
			};
			Thread th = new Thread(task);
			th.setDaemon(true);
			th.start();
			
			break;
		}
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		actioninit();
		
		// 관리자에서 채팅 올 경우 대기
	}
}