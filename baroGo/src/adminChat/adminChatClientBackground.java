package adminChat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class adminChatClientBackground {
	
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private ServerChatGui gui;
	private String msg;
	private String strPCNum;
	
	public final void setGui(ServerChatGui a_gui)
	{
		this.gui = a_gui;
	}
	
	public void connet() {
		try {
			socket = new Socket("127.0.0.1", 9146);
			System.out.println("서버 연결됨.");
			
			out = new DataOutputStream(socket.getOutputStream());
			
			out.writeUTF(strPCNum);
			out.flush();
			
			// 스레드 안하니 생성하는거랑 얘랑 계속 교착상태? 나서 스레드로 만듬
			Thread th = new Thread(new ClientReceiver(socket));
			th.setDaemon(true);
			th.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setNickname(String a_strPCNum) {
		this.strPCNum = a_strPCNum;
	}
	
	public void sendMessage(String msg2) {
		try {
			out.writeUTF(msg2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	class ClientReceiver implements Runnable
	{
		private Socket socket;
		private DataInputStream in;
		
		ClientReceiver(Socket a_socket)
		{
			this.socket = a_socket;
			try{
				in = new DataInputStream(socket.getInputStream());				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() 
		{
			// TODO Auto-generated method stub
			while(in!=null)
			{
				try 
				{
					msg = in.readUTF();
					gui.appendMsg(msg);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
