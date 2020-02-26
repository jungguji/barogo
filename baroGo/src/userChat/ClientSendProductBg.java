package userChat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 
 * @author 지중구
 * 		손님석에서 상품 주문할 때 사용되는 클래스
 *
 */
public class ClientSendProductBg {
	
	private Socket socket;
	private DataOutputStream out;
	
	public void connet(String strSendMsg) {
		try {
			socket = new Socket("127.0.0.1", 9292);
			System.out.println("서버 연결됨.");
			
			out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(strSendMsg);
			out.flush();
			socket.close();
		} catch (IOException e) {
			try {
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}