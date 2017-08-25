package useInfo;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import db.DBManager;
import db.UserDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jgj.util.barogo.ViewerUtil;

public class UseInfoSearch implements Initializable {
    @FXML private TextField         textSearch;
    @FXML private Button            btnSearch;
    @FXML private TableView<SearchBean>     tables;
    @FXML private AnchorPane        searchPane;
    
    private SearchBean                  searchBean  = new SearchBean();
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        String strSearchName = null;
        ArrayList<UseBean>  arUseBean;
        
        try {
            DBManager db = new DBManager();
            strSearchName = db.search_temp_print();
            arUseBean = UserDAO.user_search(strSearchName, searchBean);
            
            printUserInfoToTableView(arUseBean);
            
            // 테이블뷰의 행 클릭햇을때
            tables.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SearchBean>() {
                @Override
                public void changed(ObservableValue<? extends SearchBean> observable, SearchBean oldValue, SearchBean newValue) {
                    // TODO Auto-generated method stub
                    UserDAO.select_user(newValue.getStrID());
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

    public void handleBtn1Action(ActionEvent event)
    {
        String strID = DBManager.temp_id_print();
        if(strID == null)
        {
            TimeAddPeoplePopUp();
        } else {
            TimeAddPopUp(1);
        }
    }
    
    public void handleBtn2Action(ActionEvent event)
    {
        String strID = DBManager.temp_id_print();
        if(strID == null)
        {
            TimeAddPeoplePopUp();
        } else {
            TimeAddPopUp(2);
        }
    }
    
    public void handleBtn3Action(ActionEvent event)
    {
        String strID = DBManager.temp_id_print();
        if(strID == null)
        {
            TimeAddPeoplePopUp();
        } else {
            TimeAddPopUp(3);
        }
    }
    
    public void handleBtn6Action(ActionEvent event)
    {
        String strID = DBManager.temp_id_print();
        if(strID == null)
        {
            TimeAddPeoplePopUp();
        } else {
            TimeAddPopUp(6);
        }
    }
    
    public void handleBtn9Action(ActionEvent event)
    {
        String strID = DBManager.temp_id_print();
        if(strID == null)
        {
            TimeAddPeoplePopUp();
        } else {
            TimeAddPopUp(9);
        }
    }
    
    public void handleBtn13Action(ActionEvent event)
    {
        String strID = DBManager.temp_id_print();
        if(strID == null)
        {
            TimeAddPeoplePopUp();
        } else {
            TimeAddPopUp(13);
        }
    }
    
    // 서치버튼 클릭 핸들러
    // 클릭시 텍스트 필드의 값으로 검색된 내용이 tableview에 출력?
    public void handleBtnUseSearchAction(ActionEvent event) {
        ArrayList<UseBean>  arUseBean;
        
        tables.getItems().clear();
        try {
            arUseBean = UserDAO.user_search(textSearch.getText(), searchBean);
            
            printUserInfoToTableView(arUseBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void printUserInfoToTableView(ArrayList<UseBean> useBean) {
        StringBuilder birth = new StringBuilder();
        
        for (UseBean B : useBean) {
            SearchBean userData = new SearchBean();
            
            birth.append("").append(B.getiBirth1()).append(".").append(B.getiBirth2()).append(".").append(B.getiBirth3());
            
            userData.setStrID(B.getStrID());
            userData.setStrName(B.getStrName());
            userData.setBirth(birth.toString());
            
            tables.getItems().add(userData);
        }
    }
    
    public void handleBtnHomeAction(ActionEvent event)
    {
        try{
            FXMLLoader another = new FXMLLoader( getClass().getResource( "../useInfo/useInfoPopUP.fxml" ));
            try {
               AnchorPane anotherPage = (AnchorPane) another.load();
               // 다른창 띄우는 작업 .... 2
               Scene anotherScene = new Scene(anotherPage);
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
        Stage primaryStage = (Stage)searchPane.getScene().getWindow();
        primaryStage.close();
    }
    
    public void TimeAddPeoplePopUp()
    {
        try{
            FXMLLoader another = new FXMLLoader( getClass().getResource( "../useInfo/TimeAddPeople.fxml" ));
            try {
               AnchorPane anotherPage = (AnchorPane) another.load();
               // 다른창 띄우는 작업 .... 2
               Scene anotherScene = new Scene(anotherPage);
               Stage stage = new  Stage();
               stage.setTitle("경고");
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
    
    public void TimeAddPopUp(int a_iAddTime) {
        try {
            DBManager db = new DBManager();
            db.time_temp_insert(a_iAddTime);
            
            ViewerUtil.showStageNotCss(this, "../useInfo/TimeChk.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}