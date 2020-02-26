package userChat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import userInfoView.UserInfoController;

/**
 * @author 지중구
 *		관리자에서 손님석 pc를 강제로 종료 시키기 위한 연결을 대기하는 bg 클래스
 */
public class ClientKickBg implements Runnable {
	private ServerSocket serverSocket;
	private static Socket socket;
	private DataInputStream in;
	private String strType;
	
	public void setting() throws IOException {
			serverSocket = new ServerSocket(8426);
			while (true) {
				System.out.println("서버 대기중...");
				socket = serverSocket.accept(); // 먼저 서버가 할일은 계속 반복해서 사용자를 받는다.
				in = new DataInputStream(socket.getInputStream());
				strType = in.readUTF();
				
				UserInfoController useinfo = new UserInfoController();
				try {
					useinfo.pc_exit(strType);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}

	// -----------------------------------------------------------------------------

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			setting();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
