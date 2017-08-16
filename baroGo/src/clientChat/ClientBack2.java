package clientChat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientBack2 {

	private Socket socket;
	private DataOutputStream out;

	public void connet() {
		try 
		{
			socket = new Socket("127.0.0.1", 3939);
			System.out.println("¼­¹ö ¿¬°áµÊ.");
			out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String msg2) 
	{
		try 
		{
			out.writeUTF(msg2);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void socket_close()
	{
		try {
			socket.close();
		} catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
}