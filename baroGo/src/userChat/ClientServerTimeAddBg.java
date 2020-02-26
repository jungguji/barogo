package userChat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import userInfoView.UserInfoController;

/**
 * @author 지중구
 *		관리자에서 손님에게 시간을 추가할 때 연결을 대기하는 bg 클래스
 */
public class ClientServerTimeAddBg implements Runnable {
	private ServerSocket serverSocket;
	private static Socket socket;
	private DataInputStream in;
	private String strTime;
	UserInfoController user = new UserInfoController();
	
	public void setting() throws IOException {
			serverSocket = new ServerSocket(3553);
			while (true) {
				System.out.println("서버 대기중...");
				socket = serverSocket.accept(); // 먼저 서버가 할일은 계속 반복해서 사용자를 받는다.
				in = new DataInputStream(socket.getInputStream());
				strTime = in.readUTF();
				System.out.println(strTime);
				user.add_hour_time(strTime);
				// 여기서 새로운 사용자 쓰레드 클래스 생성해서 소켓정보 저장
				Receiver receiver = new Receiver(socket);
				receiver.start();
			}
	}

	// -----------------------------------------------------------------------------
	class Receiver extends Thread {

		/** XXX 2. 리시버가 한일은 자기 혼자서 네트워크 처리 계속..듣기.. 처리해주는 것. */
		public Receiver(Socket socket) throws IOException {
			in = new DataInputStream(socket.getInputStream());
		}

		public void run() {
			try {// 계속 대기
				while (in != null) 
				{
					strTime = in.readUTF();
					user.add_hour_time(strTime);
				}
			} catch (IOException e) {
			}
		}
	}
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
