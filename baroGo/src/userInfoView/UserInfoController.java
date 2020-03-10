package userInfoView;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.ResourceBundle;

import org.apache.ibatis.session.SqlSession;

import barogo.user.repository.UserMapper;
import clientChat.ClientBack2;
import db.DBManager;
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
import javafx.stage.StageStyle;
import useInfo.UserVO;
import userChat.ClientChatGui;
import userChat.ClientChatServerBackground;
import userChat.ClientKickBg;
import userChat.ClientServerTimeAddBg;

/**
 * 
 * @author 정기성
 *     게스트 게스트 Action 컨트롤러
 *     게스트의 정보 확인창
 */
public class UserInfoController implements Initializable{

    @FXML private Button btnproduct;
    @FXML private Button btntalk;
    @FXML private Button btnSeat;
    @FXML private Button btnExit;
    @FXML private Label salesType;
    @FXML private Label lblid;
    @FXML private Label lblusetime;
    @FXML private Label lblremaintime;
    @FXML private Label lblfirstmoney;
    @FXML private Label lblLatermoney;
    @FXML private Text tfPCNum;
    
    private String userId;
    
    SqlSession sqlSession;
    UserMapper mapper;
    
    public UserInfoController() {}
    
    public UserInfoController(String userId, SqlSession sqlSession) {
        this.userId = userId;
        this.sqlSession = sqlSession;
        this.mapper = sqlSession.getMapper(UserMapper.class);
    }
    
    private DBManager db = new DBManager();
    private ClientBack2 client = new ClientBack2();
    
    private int startMoney = 1000;
    private int laterMoney = 0;
    
    private int hour = 0;
    private int minute = 0;
    private static int iAddHour = 0;

    public void handleBtnproduct(ActionEvent  action){
    try{
        FXMLLoader another = new FXMLLoader( getClass().getResource( "../userInfoView/Order.fxml" ));
        try {
           AnchorPane anotherPage = (AnchorPane) another.load();
           // 다른창 띄우는 작업 .... 2
           Scene anotherScene = new Scene(anotherPage);
           anotherScene.getStylesheets().add(
                    getClass().getResource("../userInfoView/Order.css").toString()); // CSS Style 적용
            System.out.println("상품을 주문하시겠습니까?");
           Stage stage = new  Stage();
           stage.initStyle(StageStyle.UTILITY);    // 테두리 제거
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
    public void handleBtnTalk(ActionEvent action) throws Exception {
        ClientChatGui gui = new ClientChatGui(tfPCNum.getText());
        gui.start(null);
    }
    
    public void handleBtnSeat(ActionEvent action) {
        UserVO user = mapper.findByUserId(userId);
        if (user.isPrepayment()) {
            String remainTime = lblremaintime.getText();
            
            String[] hourAndMinute = remainTime.split(":");
            LocalTime time = LocalTime.of(Integer.parseInt(hourAndMinute[0]), Integer.parseInt(hourAndMinute[1]));
            
            mapper.updateRemianTimeByUserId(time, userId);
            sqlSession.commit();
        } else {
            String useTime = lblusetime .getText();
            db.updateSeatByuserId(userId, useTime);
        }
        
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../guestLogin/Glogin.fxml"));
            Parent mainView = loader.load();

            System.out.println("로그인창으로 돌아갑니다.");
            Scene scene = new Scene(mainView);

            scene.getStylesheets().add(getClass().getResource("../guestLogin/Glogin.css").toExternalForm());
            Stage primaryStage = (Stage) btnSeat.getScene().getWindow();
            primaryStage.setX(0);
            primaryStage.setY(0);
            primaryStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void handleBtnExitAction(ActionEvent action) throws InterruptedException 
    {
        String strMsg = salesType.getText()+"\n"+ lblid.getText() + "\n" + lblremaintime.getText() + "\n" + "temp"+ "\n" + tfPCNum.getText();
        
        pc_exit(strMsg);
        
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
    
    public void pc_exit(String type) throws InterruptedException {
        String[] subString = type.split("\n");
        
        if("선불".equals(subString[0])) {
            db.user_data_save(subString[1], subString[2]);
            db.user_pcnum_reset(subString[1]);
            
            sendMessage(subString[4]);
        } else {
            db.user_pcnum_reset(subString[1]);
            
            sendMessage(subString[3]);
        }
        
        System.out.println("컴퓨터를 종료합니다.");
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec("C:\\WINDOWS\\system32\\cmd.exe");
            OutputStream os = process.getOutputStream();
            os.write("shutdown -s -f -c -t \n\r".getBytes()); // 5초 이내로 컴터 꺼짐
            os.close();
            process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void sendMessage(String message) {
        client.connet();
        client.sendMessage(message);
        client.socket_close();
    }
    
    public void actioninit() {
        UserVO user = mapper.findByUserId(userId);
        
        int pcNumber = user.getPcNumber();
        
        System.out.println("pC번호 : " + pcNumber);
        tfPCNum.setText(String.valueOf(pcNumber));
        
        lblid.setText(user.getUserId());
        if (user.isPrepayment()) {
            salesType.setText("선불");
            
            lblfirstmoney.setText(user.getCumulativeAmount()+" 원");
            
            String strInitRemainTime = user.getRemainTime().toString();
            
            String[] strTime = strInitRemainTime.split(":");
            
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
                                
                                hour += iAddHour;
                                String time = hour + ":" + minute;
                                
                                iAddHour = 0;
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
            
        } else {
            salesType.setText("후불");
            lblLatermoney.setText(startMoney + "원");
            
            Task<Void> task = new Task<Void>() {
                @Override
                public Void call() throws Exception {
                    while (true) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                
                                hour += iAddHour;
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
        }
    }

    // 시간추가 한 시간
    public void add_hour_time(String a_strTime) {
        iAddHour = Integer.parseInt(a_strTime); 
    }
    
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        
        actioninit();
        
        // 관리자에서 채팅 올 경우 대기
        // 추방당할 경우
        // 시간 추가 당할 경우
        
        Thread th = new Thread(new ClientChatServerBackground(tfPCNum.getText()));
        Thread th2 = new Thread(new ClientKickBg());
        Thread th3 = new Thread(new ClientServerTimeAddBg());
        
        th.setDaemon(true);
        th2.setDaemon(true);
        th3.setDaemon(true);
        
        th.start();
        th2.start();
        th3.start();
    }
}
