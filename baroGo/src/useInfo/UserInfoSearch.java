package useInfo;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import db.UserDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jgj.util.barogo.StringUtil;
import jgj.util.barogo.ViewerUtil;

public class UserInfoSearch implements Initializable {
    @FXML private TextField textSearch;
    @FXML private Button btnSearch;
    @FXML private TableView<SearchDTO> tables;
    @FXML private AnchorPane searchPane;
    
    private UserDAO dao = new UserDAO();
    private SearchDTO searchBean = new SearchDTO();
    
    String userName;
    
    public UserInfoSearch(String userName) {
        this.userName = userName;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ArrayList<UserVO> arUserVO = dao.userSearch(userName, searchBean);
            
            printUserInfoToTableView(arUserVO);
            
            // 테이블뷰의 행 클릭햇을때
            tables.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SearchDTO>() {
                @Override
                public void changed(ObservableValue<? extends SearchDTO> observable, SearchDTO oldValue, SearchDTO newValue) {
                    System.out.println("테이블뷰의 행 클릭");
                }
            });
            
            // 테이블컬럼에 값 저장
            TableColumn names = tables.getColumns().get(0);
            names.setCellValueFactory(new PropertyValueFactory("strName"));
            names.setStyle("-fx-alignment: CENTER;");
            
            TableColumn ids = tables.getColumns().get(1);
            ids.setCellValueFactory(new PropertyValueFactory("strID"));
            ids.setStyle("-fx-alignment: CENTER;");
            
            TableColumn births = tables.getColumns().get(2);
            births.setCellValueFactory(new PropertyValueFactory("birth"));
            births.setStyle("-fx-alignment: CENTER;");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handlebuttonAction(ActionEvent event) throws Exception {
        if (StringUtil.isEmpty(userName)) {
            TimeAddPeoplePopUp();
            
            return;
        }
        
        Button botton = (Button) event.getSource();
        int addTime = getAddTime(botton.getId());
        
        TimeAddPopUp(userName, addTime);
    }
    
    private int getAddTime(String buttonId) {
        int id = Integer.parseInt(buttonId);
        
        int result = 1;
        switch (id) {
            case 1:
                result = 1;
                break;
            case 2:
                result = 2;
                break;
            case 3:
                result = 3;
                break;
            case 4:
                result = 6;
                break;
            case 5:
                result = 9;
                break;
            case 6:
                result = 13;
                break;
        }
        
        return result;
    }
    
    // 서치버튼 클릭 핸들러
    // 클릭시 텍스트 필드의 값으로 검색된 내용이 tableview에 출력
    public void handleBtnUseSearchAction(ActionEvent event) {
        tables.getItems().clear();
        try {
            ArrayList<UserVO> userList = dao.userSearch(textSearch.getText(), searchBean);
            
            printUserInfoToTableView(userList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void printUserInfoToTableView(ArrayList<UserVO> UserVO) {
        StringBuilder birth = new StringBuilder();
        
        for (UserVO B : UserVO) {
            SearchDTO userData = new SearchDTO();
            
            birth.append("").append(B.getiBirth1()).append(".").append(B.getiBirth2()).append(".").append(B.getiBirth3());
            
            userData.setStrID(B.getStrID());
            userData.setStrName(B.getStrName());
            userData.setBirth(birth.toString());
            
            tables.getItems().add(userData);
        }
    }
    
    public void handleBtnHomeAction(ActionEvent event) throws Exception {
        
        ViewerUtil.showStage(this, "../useInfo/useInfoPopUP.fxml", null, new UserInfoPopUP());
        
        Stage primaryStage = (Stage)searchPane.getScene().getWindow();
        primaryStage.close();
    }
    
    public void TimeAddPeoplePopUp() throws Exception {
        ViewerUtil.showStage(this, "../useInfo/TimeAddPeople.fxml", null, new TimeAddPeople());
    }
    
    public void TimeAddPopUp(String userName, int addTime) {
        try {
            Object timeadd1 = new TimeAdd1(userName, addTime);
            ViewerUtil.showStage(this, "../useInfo/TimeChk.fxml", null, timeadd1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}