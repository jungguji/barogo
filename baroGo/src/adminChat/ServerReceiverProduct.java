package adminChat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import adminChat.ServerBackground.Receiver;
import adminSales.ReceiverProduct;

/**
 * 
 * @author 지중구
 * 		사용자가 상품을 주문할 때 까지 대기하고 있는 클래스
 *
 */
public class ServerReceiverProduct implements Runnable {
	private ServerSocket serverSocket;
	private static Socket socket;
	private DataInputStream in;
	private String strMsg = null;
	
	public void setting() throws IOException {
			serverSocket = new ServerSocket(9292);
			while (true) {
				System.out.println("서버 대기중...");
				socket = serverSocket.accept(); // 먼저 서버가 할일은 계속 반복해서 사용자를 받는다.
				in = new DataInputStream(socket.getInputStream());
				strMsg = in.readUTF();
				ReceiverProduct send = new ReceiverProduct(strMsg);
				try {
					send.start(null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Receiver receiver = new Receiver(socket);
				receiver.start();
			}
	}

	// -----------------------------------------------------------------------------
	class Receiver extends Thread {
		private String nick;

		/** XXX 2. 리시버가 한일은 자기 혼자서 네트워크 처리 계속..듣기.. 처리해주는 것. */
		public Receiver(Socket socket) throws IOException {
			in = new DataInputStream(socket.getInputStream());
		}

		public void run() {
			try {// 계속 대기
				while (in != null) 
				{
					strMsg = in.readUTF();
					ReceiverProduct send = new ReceiverProduct(strMsg);
					try {
						send.start(null);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
