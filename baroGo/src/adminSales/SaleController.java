package adminSales;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import jgj.util.barogo.StringUtil;
import jgj.util.barogo.ViewerUtil;

public class SaleController implements Initializable {
    @FXML private TableView<SalesVO> productTableView;

    @FXML private Button snack;
    @FXML private Button drink;
    @FXML private Button noodle;

    @FXML private List<Button> productButtonList;
    
    @FXML private Button btnSales;
    @FXML private Button btnReturn;
    @FXML private Button btnInit;
    
    @FXML private TextField tfSalesPrice = new TextField();

    private DBManager db = new DBManager();
    
    ArrayList<SalesVO> saleList = new ArrayList<SalesVO>();
    ArrayList<String> productNameList = new ArrayList<String>();
    ArrayList<Integer> priceList = new ArrayList<Integer>();
    ArrayList<Integer> countList = new ArrayList<Integer>();
    ArrayList<Integer> productTypeList = new ArrayList<Integer>();
    
//    int saleCount = 1;
//    int productType;    //0 - snack, 1 - drink, 2 - noodle
    
    String date, time;
    
    int snackcount = 0,
        drinkcount = 0,
        noodlecount = 0;
    
    public void handleCategoryButtonAction(ActionEvent action) {
        Button catetoryButton = (Button) action.getSource();
        
        String category = catetoryButton.getId();
        ArrayList<SalesVO> saleList = db.findNameAndPriceByCategory(category);

        int productType = getProductType(category);
        for (int i = 0; i < saleList.size(); i++) {
            SalesVO sale = saleList.get(i);
            Button productButton = productButtonList.get(i);
            
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", i);
            map.put("productType", productType);
            map.put("name", sale.getProductName());
            map.put("price", sale.getPrice());
            
            productButton.setUserData(map);
            productButton.setText(sale.getProductName() + "(" + sale.getPrice() + ")");
            
        }
    }
    
    private int getProductType(String category) {
        int productType = 0;
        if ("snack".equals(category)) {
            productType = 0;
        } else if ("drink".equals(category)) {
            productType = 1;
        } else if ("noodle".equals(category)) {
            productType = 2;
        }
        
        return productType;
    }

    @SuppressWarnings("unchecked")
    public void handleProductButtonAction(ActionEvent action) {
        Button button = (Button) action.getSource();
        
        Map<String, Object> map = (Map<String, Object>) button.getUserData();
        
        SalesVO sale = new SalesVO();
        sale.setProductName((String) map.get("name"));
        sale.setProductType((int) map.get("productType"));
        sale.setPrice((int) map.get("price"));
        sale.setCount(1);
        
        saleList.add(sale);

        productTableView.getItems().add(sale);

        int totalPrice = 0;
        String total = tfSalesPrice.getText();
        if (StringUtil.isNotEmpty(total)) {
            totalPrice = Integer.parseInt(total);
        }
        
        totalPrice = totalPrice + sale.getPrice();
        tfSalesPrice.setText(String.valueOf(totalPrice));
    }
    
    public void handleBtnSalesAction(ActionEvent action)
    {
        int price = 0;
        
        Calendar calendar = Calendar.getInstance();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date = dateFormat.format(calendar.getTime());
        
        dateFormat = new SimpleDateFormat("HH:mm");
        time = dateFormat.format(calendar.getTime());
        
        for (int i = 0; i < saleList.size(); i++) {
            SalesVO sales = saleList.get(i);
            productNameList.add(sales.getProductName());
            productTypeList.add(sales.getProductType());
            priceList.add(sales.getPrice());
            countList.add(sales.getCount());
            
            switch(productTypeList.get(i)) {
                case 0:
                    snackcount++;
                    break;
                case 1:
                    drinkcount++;
                    break;
                case 2:
                    noodlecount++;
                    break;
            }
            
            price += priceList.get(i);
        }
        
        if(!saleList.isEmpty()) {
            showProductCheckView(price);
        } else {
            emptyProduct();
        }
        
        
    }
    
    public void handleBtnReturnAction(ActionEvent action) {
        try {
            ViewerUtil.showStage(this, "Return.fxml", "../guestLogin/global.css", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void handleBtnInitAction(ActionEvent action) {
        productTableView.getItems().clear();
        saleList.clear();
        
        tfSalesPrice.setText("");
    }
    
    public void emptyProduct() {
        AnchorPane root = new AnchorPane();
        ObservableList<Node> rootList = root.getChildren();

        root.setPrefWidth(350);
        root.setPrefHeight(130);
        
        Label lblchk = new Label();
        lblchk.setLayoutX(75);
        lblchk.setLayoutY(32);
        lblchk.setText("구매하실 상품을 선택해주세요.");
        lblchk.setTextAlignment(TextAlignment.CENTER);
        lblchk.setFont(Font.font("MDotum",15));

        Button btnOK = new Button();
        btnOK.setLayoutX(150);
        btnOK.setLayoutY(80);
        btnOK.setMnemonicParsing(false);
        btnOK.setPrefHeight(26);
        btnOK.setPrefWidth(60);
        btnOK.setText("확인");
        btnOK.setFont(Font.font("Aral",13));

        rootList.add(lblchk);
        rootList.add(btnOK);
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../guestLogin/global.css").toExternalForm());
        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        
        btnOK.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                stage.close();
            }
        });
        
    }
    
    public void showProductCheckView(int SalesPrice)
    {
        AnchorPane root = new AnchorPane();
        ObservableList<Node> rootList = root.getChildren();

        root.setPrefWidth(350);
        root.setPrefHeight(150);
        
        Label lblSalesPrice = new Label();
        Label lblSalesText1 = new Label();
        Label lblSalesText2 = new Label();
        Label lblSalesText3 = new Label();

        lblSalesPrice.setLayoutX(180);
        lblSalesPrice.setLayoutY(28);
        String SalesPriceText = ""+SalesPrice;
        lblSalesPrice.setText(SalesPriceText);
        lblSalesPrice.setFont(Font.font("MDotum",18));

        lblSalesText1.setLayoutX(52);
        lblSalesText1.setLayoutY(32);
        lblSalesText1.setText("총 주문 금액은                 원  이며");
        lblSalesText1.setTextAlignment(TextAlignment.CENTER);
        lblSalesText1.setFont(Font.font("MDotum",15));

        lblSalesText2.setLayoutX(88);
        lblSalesText2.setLayoutY(53);
        lblSalesText2.setText("지불 방법은 선불입니다.");
        lblSalesText2.setTextAlignment(TextAlignment.CENTER);
        lblSalesText2.setFont(Font.font("MDotum",15));
        
        lblSalesText3.setLayoutX(112);
        lblSalesText3.setLayoutY(74);
        lblSalesText3.setText("주문하시겠습니까?");
        lblSalesText3.setTextAlignment(TextAlignment.CENTER);
        lblSalesText3.setFont(Font.font("MDotum",15));

        Button btnOK = new Button();
        btnOK.setLayoutX(100);
        btnOK.setLayoutY(107);
        btnOK.setMnemonicParsing(false);
        btnOK.setPrefHeight(26);
        btnOK.setPrefWidth(60);
        btnOK.setText("확인");
        btnOK.setFont(Font.font("Aral",13));
        
        Button btnCancel = new Button();
        btnCancel.setLayoutX(190);
        btnCancel.setLayoutY(107);
        btnCancel.setMnemonicParsing(false);
        btnCancel.setPrefHeight(26);
        btnCancel.setPrefWidth(60);
        btnCancel.setText("취소");
        btnCancel.setFont(Font.font("Aral",13));

        rootList.add(lblSalesPrice);
        rootList.add(lblSalesText1);
        rootList.add(lblSalesText2);
        rootList.add(lblSalesText3);
        rootList.add(btnOK);
        rootList.add(btnCancel);
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../guestLogin/global.css").toExternalForm());
        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        
        btnOK.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                int receiptNo = db.selectReceiptNo() + 1;
                
                for (int i = 0; i < saleList.size(); i++) {
                    db.uploadGoods(receiptNo, productNameList.get(i), countList.get(i), priceList.get(i), date, time);
                }
                
                db.insertReceipt(receiptNo, date, time, 0, snackcount, noodlecount, drinkcount, Integer.parseInt(tfSalesPrice.getText()));

                allClear(stage);
                
                try {
                    ViewerUtil.showStage(this, "OrderFinish.fxml", "../guestLogin/global.css", null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        btnCancel.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event) {
                allClear(stage);
            }
        });
    }
    
    private void allClear(Stage stage) {
        productTableView.getItems().clear();
        
        saleList.clear();
        productNameList.clear();
        productTypeList.clear();
        countList.clear();
        priceList.clear();
        tfSalesPrice.setText("");
        
        snackcount = 0;
        noodlecount = 0;
        drinkcount = 0;
        
        stage.close();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        TableColumn ProductName = (TableColumn) productTableView.getColumns().get(0);
        ProductName.setCellValueFactory(new PropertyValueFactory("ProductName"));
        ProductName.setStyle("-fx-alignment: CENTER;");

        TableColumn Price = (TableColumn) productTableView.getColumns().get(1);
        Price.setCellValueFactory(new PropertyValueFactory("Price"));
        Price.setStyle("-fx-alignment: CENTER;");

        TableColumn Count = (TableColumn) productTableView.getColumns().get(2);
        Count.setCellValueFactory(new PropertyValueFactory("Count"));
        Count.setStyle("-fx-alignment: CENTER;");
        
    }
}
