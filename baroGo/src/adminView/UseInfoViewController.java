package adminView;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import adminCalculate.CalculateView;
import adminChat.ServerBackground;
import adminChat.ServerChatClientGui;
import adminChat.ServerKickBg;
import adminSales.SaleController;
import adminStats.MonthStatsController;
import barogo.user.repository.UserMapper;
import db.MyBatisConnectionFactory;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jgj.util.barogo.CommonUtil;
import jgj.util.barogo.StringUtil;
import jgj.util.barogo.ViewerUtil;
import serverChat.ServerBack2;
import useInfo.UserInfoPopUP;
import useInfo.UserVO;
/**
 * 
 * @author 지중구
 *        관리자 메인뷰를 나타내는 클래스
 */
public class UseInfoViewController implements Initializable {
    @FXML private MenuItem menuItemUseInfo;
    @FXML private Button btnReset ;
    @FXML private Text txtPcNum, txtName, txtPaymentPlan, txtStartTime, txtUseTime, txtMoney;
    @FXML private Label  lblRemainSeat;
    @FXML private Button btnOK;
    @FXML private List<Button> bottonList;
    
    private static String[] astrReceive = new String[81];
    private static String[] astrStartTime = new String[81];
    private static int[] aiFlag = new int[81];
    private static boolean[] aisFlag = new boolean[81];
    private static int iRemainSeat = 80;
    
    SqlSessionFactory sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
    
    UserMapper mapper;
    SqlSession sqlSession;
    
    public UseInfoViewController() {}
    
    public UseInfoViewController(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        this.mapper = sqlSession.getMapper(UserMapper.class);
    }
    
    public void handleBtnUseInfoViewAction(ActionEvent action) throws Exception {
        Object obj = new UserInfoPopUP(sqlSessionFactory.openSession());
        ViewerUtil.showStage(this, "../useInfo/useInfoPopUP.fxml", "../useInfo/useInfo.css", obj);
    }

    public void handleBtnCalculateAction(ActionEvent action) throws Exception {
        ViewerUtil.showStage(this, "../adminCalculate/Calculate.fxml", "../adminCalculate/adminCalculate.css", new CalculateView());
    }
    
    public void handleBtnSalesAction(ActionEvent action) throws Exception {
        ViewerUtil.showStage(this, "../adminSales/product.fxml", "../adminSales/product.css", new SaleController());
    }
    
    public void handleBtnStatsAction(ActionEvent action) throws IOException {
        FXMLLoader fxml = ViewerUtil.getFXMLLoader(this, "../adminStats/MonthStats.fxml", new MonthStatsController());
        TabPane tabPane = (TabPane) fxml.load();
        
        Scene scene = new Scene(tabPane);
        scene.getStylesheets().add(this.getClass().getResource("../adminStats/stats.css").toString());
        
        ViewerUtil.showStage(scene);
    }
    
    public void handleBtnInfoAction(ActionEvent action) throws Exception {
        ViewerUtil.showStage(this, "../adminView/Infomation.fxml", "../adminView/Information.css", null);
    }
    
    public void handleBtnInfo(ActionEvent action) {
        Stage primaryStage = (Stage)btnOK.getScene().getWindow();
        primaryStage.close();
    }
    
    public void handleBtnResetAction(ActionEvent action) {
        btnReset.setOnAction(event-> {
            for (Button botton : bottonList) {
                botton.setText("");
            }
            txtPcNum.setText("");
            resetButtonText();
        });
    }
    
    public void handleBtnExitAction(ActionEvent action) {
        Platform.exit();
    }
    
    public void handleChatAction(ActionEvent action) throws Exception {
        ServerChatClientGui gui = new ServerChatClientGui();
        gui.start(null);
    }
    
    public void handleKickAction(ActionEvent action) {
        Button button = (Button) action.getSource();
        String msg = button.getText() + "\n" + button.getId();
        
        ServerKickBg kick = new ServerKickBg();
        kick.connet(msg);
    }
    
    public void handleMenuTimeAddAction(ActionEvent action) {
        MenuItem menu = (MenuItem) action.getSource();
        String menuId = menu.getId();
        int pcNumber = Integer.parseInt(menuId.substring(menuId.length() - 1, menuId.length()));
        
        AnchorPane root = new AnchorPane();
        root.prefHeight(186);
        root.prefWidth(600);

        Button btn1 = new Button();
        
        btn1.setLayoutX(28);
        btn1.setLayoutY(26);
        btn1.prefHeight(60);
        btn1.prefWidth(90);
        btn1.setText("1000원 \n (1시간)");
        btn1.setOnAction(event->{
            handleItemTimeAdd(event, pcNumber, 1);
        });
        
        Button btn2 = new Button();
        
        btn2.setLayoutX(134);
        btn2.setLayoutY(26);
        btn2.prefHeight(60);
        btn2.prefWidth(90);
        btn2.setText("2000원 \n(2시간)");
        btn2.setOnAction(event->{
            handleItemTimeAdd(event, pcNumber, 2);
        });
        
        Button btn3 = new Button();
        
        btn3.setLayoutX(234);
        btn3.setLayoutY(26);
        btn3.prefHeight(60);
        btn3.prefWidth(90);
        btn3.setText("3000원 \n(3시간)");
        btn3.setOnAction(event->{
            handleItemTimeAdd(event, pcNumber, 3);
        });
        
        Button btn4 = new Button();
        
        btn4.setLayoutX(28);
        btn4.setLayoutY(102);
        btn4.prefHeight(60);
        btn4.prefWidth(90);
        btn4.setText("5000원 \n(5시간)");
        btn4.setOnAction(event->{
            handleItemTimeAdd(event, pcNumber, 5);
        });
        
        Button btn5 = new Button();
        
        btn5.setLayoutX(134);
        btn5.setLayoutY(102);
        btn5.prefHeight(60);
        btn5.prefWidth(90);
        btn5.setText("7000원 \n(9시간)");
        btn5.setOnAction(event->{
            handleItemTimeAdd(event, pcNumber, 9);
        });
        
        Button btn6 = new Button();
        
        btn6.setLayoutX(234);
        btn6.setLayoutY(102);
        btn6.prefHeight(60);
        btn6.prefWidth(90);
        btn6.setText("10000원 \n(13시간)");
        btn6.setOnAction(event->{
            handleItemTimeAdd(event, pcNumber, 13);
        });
        
        Label lblView = new Label();
        
        lblView.setLayoutX(404);
        lblView.setLayoutY(26);
        lblView.setPrefWidth(118);
        lblView.setPrefHeight(36);
        lblView.setText("시간 추가");
        
        Button btnExit = new Button();
        
        btnExit.setLayoutX(468);
        btnExit.setLayoutY(115);
        btnExit.setPrefWidth(109);
        btnExit.setPrefHeight(37);
        btnExit.setText("돌아가기");
        btnExit.setOnAction(event->{
            Stage primaryStage = (Stage)root.getScene().getWindow();
            primaryStage.close();
        });
        
        ObservableList<Node> list = root.getChildren();
        list.add(btn1);
        list.add(btn2);
        list.add(btn3);
        list.add(btn4);
        list.add(btn5);
        list.add(btn6);
        list.add(lblView);
        list.add(btnExit);
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../guestLogin/global.css").toExternalForm());
        
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UTILITY);    // 테두리 제거
        stage.setScene(scene);
        stage.show();
    }
    
    public void handleItemTimeAdd(ActionEvent aciotn, int pcNumber, int addTime) {
        
        UserVO user = mapper.findByPcNumber(pcNumber);
        Platform.runLater(()->{
            AnchorPane timeChk = new AnchorPane();
            ObservableList<Node> list = timeChk.getChildren();
            
            timeChk.setPrefHeight(147);
            timeChk.setPrefWidth(552);
            
            Button btnOK = new Button();
            
            btnOK.setLayoutX(105);
            btnOK.setLayoutY(90);
            btnOK.setPrefHeight(23);
            btnOK.setPrefWidth(78);
            btnOK.setText("예");
            btnOK.setOnAction(event->{
                addAction(user, addTime);
                
                Stage primaryStage = (Stage)timeChk.getScene().getWindow();
                primaryStage.close();
            });
            
            Button btnNo = new Button();
            
            btnNo.setLayoutX(383);
            btnNo.setLayoutY(90);
            btnNo.setPrefHeight(23);
            btnNo.setPrefWidth(78);
            btnNo.setText("아니오");
            btnNo.setOnAction(event->{
                Stage primaryStage = (Stage)timeChk.getScene().getWindow();
                primaryStage.close();
            });
            
            Label lblAlert = new Label();
            
            lblAlert.setLayoutX(105);
            lblAlert.setLayoutY(14);
            lblAlert.setPrefWidth(335);
            lblAlert.setPrefHeight(37);
            lblAlert.setText("'"+ user.getUserId() + "' 님에게 "+ addTime +"시간을 추가하시겠습니까?");
            
            list.add(btnOK);
            list.add(btnNo);
            list.add(lblAlert);
            
            Scene scene = new Scene(timeChk);
            scene.getStylesheets().add(getClass().getResource("../guestLogin/global.css").toExternalForm());
            
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UTILITY);    // 테두리 제거
            stage.setScene(scene);
            stage.show();
        });
    }
    
    private void addAction(UserVO user, int addTime) {
        LocalTime totalTime = getTimePuls(user.getRemainTime(), addTime);
        mapper.updateRemianTimeByUserId(totalTime, user.getUserId());
        
        sqlSession.commit();
//        ServerClientTimeAddBg timeAdd = new ServerClientTimeAddBg();
//        timeAdd.connet(addTime);
        
    }
    
    private LocalTime getTimePuls(LocalTime remainTime, long addTime) {
        LocalTime totalTime = null;
        
        if(CommonUtil.isEmpty(remainTime)) {
            totalTime = LocalTime.of((int) addTime, 0);
        } else {
            
            totalTime = remainTime.plusHours(addTime);
        }
        
        return totalTime;
    }

    public void handleBtnSeatAction(ActionEvent action) {
        Button target = (Button) action.getSource();
        int idx = Integer.parseInt(target.getId()) - 1;
        
        if(StringUtil.isEmpty(astrReceive[1])) {
            txtPcNum.setText(bottonList.get(idx).getId());
            resetButtonText();
        } else {
            String[] receiveMsg = astrReceive[1].split("\n");
            setButtonText(receiveMsg, 1);
        }
    }
    
    public void resetButtonText() {
        txtName.setText("");
        txtPaymentPlan.setText("");
        txtUseTime.setText("");
        txtStartTime.setText("");
        txtMoney.setText("");
    }
    
    private void setButtonText(String[] receiveMsg, int pcNumber) {
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < 6; i++) {
            if (i != 2) {
                sb.append(" \n");
            }
            sb.append(receiveMsg[i]);
        }
        
        String userInfo = sb.toString();
        
        setLabel(pcNumber, userInfo, receiveMsg[1]);
        
        txtName.setText(receiveMsg[3]);
        txtPaymentPlan.setText(receiveMsg[2]);
        txtUseTime.setText(receiveMsg[4]);
        txtMoney.setText(receiveMsg[5]);
    }
    
    private void setLabel(int pcNumber, String userInfo, String startTime) {
        Button botton = bottonList.get((pcNumber-1));
        botton.setText(userInfo);
        txtPcNum.setText(botton.getId());
        
        // 처음 접속했는지 체크
        if (StringUtil.isEmpty(userInfo)) {
            aiFlag[pcNumber] = 0;
            
            if (aisFlag[pcNumber])  {
                addRemainSeat();
                aisFlag[pcNumber] = false;
                astrStartTime[pcNumber] = null;
            }
        }
        // 맞으면 시작시간 저장
        if (aiFlag[pcNumber] == 1)  {
            
            if (!aisFlag[pcNumber])  {
                removeRemainSeat();
                aisFlag[pcNumber] = true;
            }
            
            astrStartTime[1] = startTime;
        }
        
        txtStartTime.setText(astrStartTime[1]);
    }

    public void appendMsg(String msg) {
        StringTokenizer strToken = new StringTokenizer(msg, "\n");
        String strSubMsg = null;
        
        if(strToken.hasMoreElements()) {
            strSubMsg = strToken.nextToken();
        }
        
        switch (Integer.parseInt(strSubMsg)) {
            case 01: astrReceive[1] = msg;      aiFlag[1] += 1; break;
            case 02: astrReceive[2] = msg;      aiFlag[2] += 1; break;
            case 03: astrReceive[3] = msg;      aiFlag[3] += 1; break;
            case 04: astrReceive[4] = msg;   aiFlag[4] += 1; break;
            case 05: astrReceive[5] = msg;      aiFlag[5] += 1; break;
            case 06: astrReceive[6] = msg;      aiFlag[6] += 1; break;
            case 07: astrReceive[7] = msg;      aiFlag[7] += 1; break;
            case 0x08: astrReceive[8] = msg; aiFlag[8] += 1; break;
            case 0x09: astrReceive[9] = msg; aiFlag[9] += 1; break;
            case 10: astrReceive[10] = msg;     aiFlag[10] += 1; break;
        }
    }
    
    public void removeRemainSeat() {
        iRemainSeat--;
        
        Platform.runLater(()-> {
            System.out.println(" remainSeat M : " + iRemainSeat);
            lblRemainSeat.setText(String.valueOf(iRemainSeat));
        });
    }
    
    public void addRemainSeat() {
        iRemainSeat++;
        
        Platform.runLater(()-> {
            System.out.println(" remainSeat P : " + iRemainSeat);
            lblRemainSeat.setText(String.valueOf(iRemainSeat));
        });
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
        // 통신을 위해 서버를 데몬스레드로 대기 시키고 요청이 올때만 활성화?
        // ServerBack2클래스는 Runnable 인터페이스를 상속
        // ServerBackgorund는 채팅용 ServerBack2는 데이터 받는 용
        // ServerReceiverProduct는 상품 주문 올꺼 받는 용
        for(int i = 0; i < aiFlag.length; i++) {
            aiFlag[i] = 0;
            aisFlag[i] = false;
        }
        
//        Thread th = new Thread(new ServerBack2());
//        Thread th2 = new Thread(new ServerBackground());
//        
//        th.setDaemon(true);
//        th2.setDaemon(true);
//        
//        th.start();
//        th2.start();
    }
}