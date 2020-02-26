package adminChat;

import java.io.IOException;

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
 *		손님이 채팅을 걸 때 사용하는 GUI를 그려주는 클래스
 *
 */
public class ServerChatGui  extends Application {
	
	private AnchorPane root = new AnchorPane();
	private ObservableList<Node> list = root.getChildren();
	private ServerBackground server;
	private TextField tfText = new TextField();
	private TextArea taView = new TextArea();
	
	public ServerChatGui() throws IOException {
		// TODO Auto-generated method stub
		
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
		
		server = new ServerBackground();
		btnSend.setOnAction(event->{
			String msg = "관리자  : " + tfText.getText()+"\n";
			Platform.runLater(()->{
				System.out.println("서버 gui - " +msg);
				server.sendMessage(msg);
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
		
	}

	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Platform.runLater(()-> {
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../userInfoView/talk.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		});
		
	}
	public void appendMsg(String msg) {
			taView.appendText(msg);			
	}
	
}
