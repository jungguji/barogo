package serverChat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import adminView.UseInfoViewController;

public class ServerBack2 implements Runnable{

	private ServerSocket serverSocket;
	private Socket socket;
	private UseInfoViewController gui = new UseInfoViewController();
	private String msg;
	private DataInputStream in;
	
	public void setting() throws IOException 
	{
		serverSocket = new ServerSocket(3939);
		while (true) 
		{
			System.out.println("서버 대기중...");
			socket = serverSocket.accept();
			System.out.println(socket.getInetAddress() + "에서 접속했습니다.");
			
			// 여기서 새로운 사용자 쓰레드 클래스 생성해서 소켓정보 저장
			Receiver receiver = new Receiver(socket);
			receiver.start();
		}
	}

	// -----------------------------------------------------------------------------
	class Receiver extends Thread {

		/** XXX 2. 리시버가 한일은 자기 혼자서 네트워크 처리 계속..듣기.. 처리해주는 것. */
		public Receiver(Socket socket) throws IOException 
		{
			in = new DataInputStream(socket.getInputStream());
		}

		public void run() {
			try 
			{	// 계속 대기
				while (in != null) 
				{
					System.out.println("ServerBack");
					msg = in.readUTF();
					System.out.println("@@@@@@@ ServerBack2 run()에서 msg @@@@@@@ ");
					System.out.println(msg);
					gui.appendMsg(msg);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try
		{
			setting();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}