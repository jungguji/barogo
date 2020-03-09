/**
 * 
 */
package adminCalculate;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.ibatis.session.SqlSession;

import barogo.user.repository.UserMapper;
import db.DBManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jgj.util.barogo.StringUtil;
import jgj.util.barogo.ViewerUtil;

/**
 * @author 지중구
 *      정산하는 창이 보여지는 클래스
 *      정산하기 전에 정산할 날짜를 정하고 관리자인지 체크하고
 *      카운터의 현금과 순이익을 맞춰서 맞으면 db에 저장된다.
 */
public class CalculateViewController implements Initializable {
    @FXML private List<TextField> countTextFieldList;
    @FXML private List<TextField> priceTextFieldList;
    @FXML private TextField         tfTotalCount, tfTotalPrice;
    @FXML private TextField         tfAdminID, tfAdminPW;
    @FXML private Button             btnSelect, btnReset, btnLogin, btnCalc;
    @FXML private Label             lblAdmin;
    @FXML private TableView<SalesDTO>     tvTotalSales;
    @FXML private DatePicker        pickDate;
    @FXML private Label                lblTotalMoney;
    @FXML private AnchorPane        paneCalc;
    
    private final static int PAYS_50000 = 50000;
    private final static int PAYS_10000 = 10000;
    private final static int PAYS_5000 = 5000;
    private final static int PAYS_1000 = 1000;
    private final static int PAYS_500 = 500;
    private final static int PAYS_100 = 100;
    private final static int PAYS_50 = 50;
    private final static int PAYS_10 = 10;
    
    private CalculateVO    calcBean    = new CalculateVO();
    private DBManager db = new DBManager();
    
    UserMapper mapper;
    SqlSession sqlSession;
    
    public CalculateViewController(SqlSession sqlSession, UserMapper mapper) {
        this.sqlSession = sqlSession;
        this.mapper = mapper;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (TextField field : countTextFieldList) {
            field.setOnKeyReleased(event-> {   
                int[] count = initCountField(); 
                setPays(count); 
            });
        }
        
        LocalDate nowDate = LocalDate.now();
        
        pickDate.setPromptText(String.valueOf(nowDate));
        
        ObservableList<SalesDTO> salesList = FXCollections.observableArrayList(
                new SalesDTO("","", "")
        );
        
        // ���̺��÷��� �� ����
        TableColumn name = tvTotalSales.getColumns().get(0);
        name.setCellValueFactory(new PropertyValueFactory("name"));
        name.setStyle("-fx-alignment: CENTER;");
        
        TableColumn count = tvTotalSales.getColumns().get(1);
        count.setCellValueFactory(new PropertyValueFactory("count"));
        count.setStyle("-fx-alignment: CENTER;");
        
        TableColumn price = tvTotalSales.getColumns().get(2);
        price.setCellValueFactory(new PropertyValueFactory("price"));
        price.setStyle("-fx-alignment: CENTER;");
        
        tvTotalSales.setItems(salesList);
    }
    
    private int[] initCountField() {
        int[] count = new int[8];
        
        for (int i = 0; i < countTextFieldList.size(); i++) {
            TextField field = countTextFieldList.get(i);
            if (StringUtil.isEmpty(field.getText())) {
                count[i] = 0;
            } else {
                count[i] =  Integer.parseInt(field.getText());
            }
        }
        
        return count;
    }
    
    private void setPays(int[] count) {
        int[] price = new int[8];
        
        int[] paysArray = initPays();
        
        for (int i = 0; i < paysArray.length; i++) {
            price[i] = count[i] * paysArray[i];
        }
        
        int totalCount = 0;
        int totalPrice = 0;
        for (int i = 0; i < count.length; i++) {
            totalCount += count[i];
            totalPrice += price[i];
        }
        
        for (int i = 0; i < priceTextFieldList.size(); i++) {
            TextField field = priceTextFieldList.get(i);
            field.setText(String.valueOf(price[i]));
        }
        
        tfTotalCount.setText(String.valueOf(totalCount));
        tfTotalPrice.setText(String.valueOf(totalPrice));
    }
    
    private int[] initPays() {
        int[] pays = new int[8];
        
        pays[0] = PAYS_50000;
        pays[1] = PAYS_10000;
        pays[2] = PAYS_5000;
        pays[3] = PAYS_1000;
        pays[4] = PAYS_500;
        pays[5] = PAYS_100;
        pays[6] = PAYS_50;
        pays[7] = PAYS_10;
        
        return pays;
    }

    public void handleBtnLoginAction(ActionEvent action) {
        String adminId = tfAdminID.getText();
        String adminPassword = tfAdminPW.getText();

        String result = mapper.findAdmin(adminId, adminPassword);
        
        if (StringUtil.isEmpty(result)) {
            tfAdminID.setText("");
            tfAdminPW.setText("");
        } else {
            tfAdminID.setDisable(true);
            tfAdminPW.setDisable(true);

            for (TextField field : countTextFieldList) {
                field.setDisable(false);
            }

            lblAdmin.setText(result);
        }
    }
    
    public void handleBtnResetAction(ActionEvent action)
    {
        ObservableList<SalesDTO> salesList = FXCollections.observableArrayList(
                new SalesDTO("","", "")
        );
        
        tvTotalSales.setItems(salesList);

        tfTotalCount.setText("");
        tfTotalPrice.setText("");
        
        tfAdminID.setDisable(false);
        tfAdminPW.setDisable(false);

        tfAdminID.setText("");
        tfAdminPW.setText("");
        lblAdmin.setText("");
        lblTotalMoney.setText("");
        
        setTextFieldEmptyAndDisabled(countTextFieldList);
        setTextFieldEmptyAndDisabled(priceTextFieldList);
    }
    
    private void setTextFieldEmptyAndDisabled(List<TextField> list) {
        for (int i = 0; i < list.size(); i++) {
            TextField field = list.get(i);
            field.setText("");
            field.setDisable(true);
        }
    }

    public void handleBtnSelectAction(ActionEvent action) {
        calcBean = db.salse_total(pickDate.getValue());
        
        int iSalesCount     = calcBean.getiCount();
        int iSalesSum        = calcBean.getiSales();
        int iReturnCount     = calcBean.getiReturnCount();
        int iReturnSum        = calcBean.getiReturnSales();
        
        int resultCount = iSalesCount - iReturnCount;
        int resultsum = iSalesSum - iReturnSum;

        lblTotalMoney.setText(String.valueOf(resultsum));
        
        ObservableList<SalesDTO> salesList = FXCollections.observableArrayList(
                new SalesDTO("총 매출액", String.valueOf(iSalesCount), String.valueOf(iSalesSum)),
                new SalesDTO("총 반품액", String.valueOf(iReturnCount) , String.valueOf(iReturnSum)),
                new SalesDTO("순 매출액", String.valueOf(resultCount), String.valueOf(resultsum))
        );
        tvTotalSales.setItems(salesList);
    }

    public void handleBtnCalcAction(ActionEvent acton) throws Exception {
        String strTotalPrice = tfTotalPrice.getText();
        String strTotalMomey = lblTotalMoney.getText();
        
        if(strTotalMomey.equals("")) {
            ViewerUtil.showStage(this, "../adminCalculate/CalcNoSelect.fxml", null, new CalcPopup());
            return;
        } 

        if(strTotalPrice.equals(strTotalMomey)) {
            if(db.stats_overlap_chk(pickDate.getValue())) {
                ViewerUtil.showStage(this, "../adminCalculate/CalcOverlap.fxml", null, new CalcPopup());
            } else {
                db.insert_stats_data(pickDate.getValue(), calcBean.getiSales(), Integer.parseInt(lblTotalMoney.getText()));
                ViewerUtil.showStage(this, "../adminCalculate/CalcOk.fxml", null, new CalcPopup());
                
                Stage primaryStage = (Stage)paneCalc.getScene().getWindow();
                primaryStage.close();
            }
        } else {
            ViewerUtil.showStage(this, "../adminCalculate/CalcPopUp.fxml", null, new CalcPopup());
        }
    }
}