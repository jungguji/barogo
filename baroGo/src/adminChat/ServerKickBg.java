package adminChat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 
 * @author 지중구
 * 		관리자에서 손님을 강제 종료시킬 때 사용되는 클래스
 * 		파라미터로 사용중인 손님자리의 정보가 넘어감 (btnSeat.getText)
 *
 */
public class ServerKickBg {
	
	private Socket socket;
	private DataOutputStream out;
	
	public void connet(String a_strType) {
		try {
			socket = new Socket("127.0.0.1", 8426);
			System.out.println("서버 연결됨.");
			
			out = new DataOutputStream(socket.getOutputStream());
			
			out.writeUTF(a_strType);
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