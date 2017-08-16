package clientChat;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;
 
 
public class TcpIpClient {
    public static void main(String[] args) {
        try{
            String serverIp = "127.0.0.1";
 
            // 소켓을 생성하여 연결을 요청한다.
            System.out.println("서버에 연결중입니다. 서버IP : " + serverIp);
            Socket socket = new Socket(serverIp, 7777);
             
            // 소켓의 입력스트림을 얻는다.
            InputStream in = socket.getInputStream();
            DataInputStream dis = new DataInputStream(in);
             
            // 소켓으로부터 받은 데이터를 출력한다.
            System.out.println("서버로부터 받은 메세지 : " + dis.readUTF());
            System.out.println("연결을 종료합니다.");
             
            dis.close();
            socket.close();
            System.out.println("연결이 종료되었습니다.");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}