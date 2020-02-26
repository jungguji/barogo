package userChat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 지중구
 *		관리자에서 채팅을 걸 때 연결을 기다리는 bg 클래스
 */
public class userChatServerBackground implements Runnable {
	private ServerSocket serverSocket;
	private static Socket socket;
	private String msg;
	private static ClientChatGui gui;
	private DataOutputStream out;
	private DataInputStream in;
	

	public void setting() throws IOException {
			serverSocket = new ServerSocket(5959);
			while (true) {
				System.out.println("서버 대기중...");
				socket = serverSocket.accept(); // 먼저 서버가 할일은 계속 반복해서 사용자를 받는다.
				System.out.println(socket.getInetAddress() + "에서 접속했습니다.");
				gui = new ClientChatGui();
				try {
					gui.start(null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 여기서 새로운 사용자 쓰레드 클래스 생성해서 소켓정보 저장
				Receiver receiver = new Receiver(socket);
				receiver.start();
			}
	}

	public void addClient(String nick, DataOutputStream out) throws IOException {
		System.out.println("addClinet ");
		sendMessage(nick + "번님이 접속하셨습니다.\n");
	}

	public void removeClient(String nick) {
		sendMessage(nick + "번님이 나가셨습니다.");
	}

	// 메시지 내용 전파
	public void sendMessage(String msg) {
		try {
			out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(msg);
			out.flush();
			gui.appendMsg(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	// -----------------------------------------------------------------------------
	class Receiver extends Thread {
		private String nick;

		/** XXX 2. 리시버가 한일은 자기 혼자서 네트워크 처리 계속..듣기.. 처리해주는 것. */
		public Receiver(Socket socket) throws IOException {
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			nick = in.readUTF();
			addClient(nick, out);
		}

		public void run() {
			try {// 계속 대기
				while (in != null) 
				{
					msg = in.readUTF();
					sendMessage(msg);
				}
			} catch (IOException e) {
				removeClient(nick);
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