package adminSales;

import java.util.ArrayList;
import java.util.StringTokenizer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ReceiverProduct extends Application{

	private AnchorPane root = new AnchorPane();
	private ObservableList<Node> list = root.getChildren();
	TableView<SendProductDTO> tvSendProduct = new TableView<SendProductDTO>();
	
	String msg;
	
	public ReceiverProduct(String a_strMsg) {
		root.setPrefHeight(500);
		root.setPrefWidth(280);
		
		tvSendProduct.setLayoutX(13);
		tvSendProduct.setLayoutY(57);
		tvSendProduct.prefHeight(334);
		tvSendProduct.prefWidth(254);
		
		TableColumn productName = new TableColumn("��ǰ��");
		TableColumn productCount = new TableColumn("����");
		TableColumn etc = new TableColumn("���");
		
		tvSendProduct.getColumns().addAll(productName, productCount,etc);
		
		TableColumn names = tvSendProduct.getColumns().get(0);
		names.setCellValueFactory(new PropertyValueFactory("strProductName"));
		names.setStyle("-fx-alignment: CENTER;");
		
		TableColumn ids = tvSendProduct.getColumns().get(1);
		ids.setCellValueFactory(new PropertyValueFactory("strProductCount"));
		ids.setStyle("-fx-alignment: CENTER;");
		
		Label lbl = new Label();
		
		lbl.setLayoutX(74);
		lbl.setLayoutY(14);
		lbl.setPrefHeight(34);
		lbl.setPrefWidth(132);
		lbl.setText("�ֹ� ��û");
		lbl.setFont(Font.font(28));
		
		Label lblPCNum = new Label();
		
		lblPCNum.setLayoutX(100);
		lblPCNum.setLayoutY(460);
		lblPCNum.setFont(Font.font(25));
		
		list.add(tvSendProduct);
		list.add(lbl);
		list.add(lblPCNum);
		
		StringTokenizer strToken = new StringTokenizer(a_strMsg, ":");
		String[] temp = new String[255];
		
		
		ArrayList<SendProductDTO> arProBean = new ArrayList<SendProductDTO>();
		int i = 0;
		
		
		while(strToken.hasMoreElements())
		{
			temp[i]  = strToken.nextToken();
			StringTokenizer strToken2 = new StringTokenizer(temp[i], ",");
			String temp2[]	= new String[255];
			int j = 0;
			while(strToken2.hasMoreElements())
			{
				temp2[j] = strToken2.nextToken();
				j++;
			}
			SendProductDTO haha = new SendProductDTO();
			haha.setStrProductName(temp2[0]);
			haha.setStrProductCount(temp2[1]);
			
			arProBean.add(haha);
			i++;
		}
		
		for (SendProductDTO B : arProBean) {
			SendProductDTO proData = new SendProductDTO();
			
			proData.setStrProductName(B.getStrProductName());
			proData.setStrProductCount(B.getStrProductCount());
			System.out.println("proData.getiProductCount() : " +proData.getStrProductCount());
			System.out.println("proData.getStrProductName() : " +proData.getStrProductName());
			
			tvSendProduct.getItems().add(proData);
		}
		
	}


	public static void main(String[] args)
	{
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Platform.runLater(()->{
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		});
		
	}
}
