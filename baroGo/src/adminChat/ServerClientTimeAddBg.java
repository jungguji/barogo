package adminChat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 관리자가 손님에게 시간을 추가 해줄때 사용되는 클래스
 *  추가 해줄 시간이 파라미터로 넘어옴
 */
public class ServerClientTimeAddBg {
	
	private Socket socket;
	private DataOutputStream out;
	
	public void connet(int a_iAddTime) {
		try {
			socket = new Socket("127.0.0.1", 3553);
			System.out.println("서버 연결됨.");
			
			out = new DataOutputStream(socket.getOutputStream());
			
			out.writeUTF(String.valueOf(a_iAddTime));
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