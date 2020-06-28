package javaStudy.javaJeongSuk.ch16_network.ex.TcpIP;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PracticeTcpIpServer03 {
	
//	 - ServerSocket클래스의 setSoTimeout(int timeout)을 사용해서 서버소켓의 대기시간을 지정할 수 있다.
//	   지정한 대기 시간 동안 연결 요청이 없을 경우 accept()에서 SocketTimeOutException이 발생하므로 catch를 적절히 사용해야한다.
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		
		try {
			// 서버 소켓을 생성하여 7777번 포트와 결합시킨다.
			serverSocket = new ServerSocket(7777);
			System.out.println(getTime()+"서버 준비 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		while(true) {
			System.out.println(getTime()+"연결 요청 대기 중");
			
			// 요청 대기시간 5초 설정
			// 5초 동안 접속 요청이 없을 경우 SocketTimeOutExcpetion 발생함
			try {
				serverSocket.setSoTimeout(3*1000);
			
				Socket socket = serverSocket.accept();
				if (socket != null) {
					System.out.println(getTime()+", "+socket.getInetAddress()+"로부터 연결 요청 접수 완료");
				}
				
				//소켓의 출력 스트림을 얻는다.
				// OutputStream out = socket.getOutputStream();
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				dos.writeUTF("[Notict] test msg from server");
				
				dos.close();
				socket.close();
			} catch (SocketException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	static String getTime() {
		SimpleDateFormat f = new SimpleDateFormat("hh:mm:ss");
		return f.format(new Date());
	}
}
