package adminSales;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import db.DBManager;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import jgj.util.barogo.ViewerUtil;

public class ReturnController implements Initializable {
    
    @FXML private TableView<SalesVO> returnTableView;
    
    @FXML private TextField         tfReceiptNo;
    @FXML private DatePicker         dpDate;
    @FXML private Button             btnSearch;
    @FXML private Button             btnReturn;
    
    DBManager db = new DBManager();
    
    public void handleBtnSearchAction(ActionEvent action) {
        returnTableView.getItems().clear();
        ArrayList<SalesVO> salesList = db.SearchReceipt(tfReceiptNo.getText(), dpDate.getValue());
        
        if (salesList.isEmpty()) {
            
            try {
                ViewerUtil.showStage(this, "ReturnCheck.fxml", "product.css", null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            returnTableView.getItems().addAll(salesList);
        }
    }
    
    public void handleBtnReturnAction(ActionEvent action) {
        AnchorPane root = new AnchorPane();
        ObservableList<Node> rootList = root.getChildren();

        root.setPrefWidth(320);
        root.setPrefHeight(137);
        
        Label ReturnText = new Label();

        ReturnText.setLayoutX(90);
        ReturnText.setLayoutY(34);
        ReturnText.setText("반품 하시겠습니까?");
        ReturnText.setFont(Font.font("MDotum",15));

        Button btnOK = new Button();
        Button btnCancel = new Button();
        
        btnOK.setLayoutX(73);
        btnOK.setLayoutY(91);
        btnOK.setMnemonicParsing(false);
        btnOK.setPrefHeight(26);
        btnOK.setPrefWidth(60);
        btnOK.setText("확인");
        btnOK.setFont(Font.font("Aral",13));
        
        btnCancel.setLayoutX(191);
        btnCancel.setLayoutY(91);
        btnCancel.setMnemonicParsing(false);
        btnCancel.setPrefHeight(26);
        btnCancel.setPrefWidth(60);
        btnCancel.setText("취소");
        btnCancel.setFont(Font.font("Aral",13));

        rootList.add(ReturnText);
        rootList.add(btnOK);
        rootList.add(btnCancel);
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("Style3.css").toExternalForm());
        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        
        btnOK.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                stage.close();
                
                db.Return_query(tfReceiptNo.getText(), dpDate.getValue());
                db.ReturnGoods(tfReceiptNo.getText(),dpDate.getValue());
                
                try {
                    ViewerUtil.showStage(this, "ReturnFinish.fxml", "../globalLogin/global.css", null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                returnTableView.getItems().clear();
                returnTableView.getItems().add(null);
            }
        });
        
        btnCancel.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event) {
                stage.close();
            }
        });
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn productName = (TableColumn) returnTableView.getColumns().get(0);
        productName.setCellValueFactory(new PropertyValueFactory("ProductName"));
        productName.setStyle("-fx-alignment: CENTER;");

        TableColumn price = (TableColumn) returnTableView.getColumns().get(1);
        price.setCellValueFactory(new PropertyValueFactory("Price"));
        price.setStyle("-fx-alignment: CENTER;");

        TableColumn count = (TableColumn) returnTableView.getColumns().get(2);
        count.setCellValueFactory(new PropertyValueFactory("Count"));
        count.setStyle("-fx-alignment: CENTER;");
        
        returnTableView.getItems().add(null);
    }

}
