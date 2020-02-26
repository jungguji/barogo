package adminChat;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * 
 * @author 지중구
 *		관리자에서 손님에게 채팅을 걸 때 사용되는 GUI 클래스
 */
public class ServerChatClientGui extends Application{

	ServerChatClientBackground client;
	private TextArea taView = new TextArea();
	
	public ServerChatClientGui() {
		// TODO Auto-generated method stub
		client = new ServerChatClientBackground();
		
		AnchorPane root = new AnchorPane();
		ObservableList<Node> list = root.getChildren();
		
		TextField tfText = new TextField();
		
		root.setPrefWidth(300);
		root.setPrefHeight(400);
		
		Line line = new Line();
		
		line.setEndX(160);
		line.setStartX(-130);
		line.setLayoutX(135);
		line.setLayoutY(50);
		
		Label lbl = new Label();
		
		lbl.setLayoutX(14);
		lbl.setLayoutY(8);
		lbl.prefHeight(36);
		lbl.prefWidth(80);
		lbl.setText("TALK");
		lbl.setFont(Font.font("Arial Bold",30));
		
		taView.setDisable(true);
		taView.setLayoutX(5);
		taView.setLayoutY(51);
		taView.setPrefHeight(319);
		taView.setPrefWidth(291);
		
		Button btnSend = new Button();
		
		btnSend.setLayoutX(246);
		btnSend.setLayoutY(372);
		btnSend.setMnemonicParsing(false);
		btnSend.setPrefHeight(25);
		btnSend.setPrefWidth(49);
		btnSend.setText("전송");
		
		btnSend.setOnAction(event->{
			String msg = "관리자 : " + tfText.getText()+"\n";
			Platform.runLater(()->{
				client.sendMessage(msg);
				tfText.setText("");				
			});
		});
		
		tfText.setLayoutX(5);
		tfText.setLayoutY(372);
		tfText.setPrefHeight(25);
		tfText.setPrefWidth(236);
		
		
		list.add(line);
		list.add(lbl);
		list.add(taView);
		list.add(btnSend);
		list.add(tfText);
		
		client.setNickname("관리자");
		client.setGui(this);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("../userInfoView/talk.css").toExternalForm());
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		client.connet();
	}
	
	public void appendMsg(String msg) {
		taView.appendText(msg);
	}
	
}
