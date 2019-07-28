package javaStudy.ch16_network.ex.TcpIP;

import java.net.Socket;
import java.util.Properties;

public class PracticeTcpIpClient05 {
	
	public static void main(String[] args) {
		try {
			String serverIp = "127.0.0.1";
			int port = 7777;
			// 소켓을 생성하여 연결을 요청한다.
			System.out.println("서버에 연결 중입니다."+serverIp);
			Socket socket = new Socket(serverIp, port);
			
			//소켓의 입력 스트림을 얻는다.
			System.out.println("서버에 연결되었습니다.");
			Sender sender = new Sender(socket);
			Receiver recv = new Receiver(socket);
			
			sender.start();
			recv.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
