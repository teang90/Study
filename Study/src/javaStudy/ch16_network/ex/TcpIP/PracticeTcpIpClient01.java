package javaStudy.ch16_network.ex.TcpIP;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

public class PracticeTcpIpClient01 {
	
//	이 예제는 간단한 TCP/IP 서버를 구현한 것이다. 이 예제를 실행하면
//	 *			서버 소켓이 7777번 포트에서 클라이언트 프로그램의 연결 요청을 기다린다.
//	 *			클라이언트의 요청이 올 떄까지 진행을 멈추고 계속 기다린다.
//	 *			그러다가 클라이언트 프로그램이 서버에 연결을 요청하면, 
//	 *			서버 소켓은 새로운 소켓을 생성하여 클라이언트 프로그램의 소켓(원격소켓)과 연결한다.
//	 *			
//	 *			새로 생성된 소켓은 [Notice: test Message from server]라는 데이터를
//	 *			원격 소켓에 전송하고 연결을 종료한다. 그리고 서버 프로그램(PracticeTcpIpServer01.java)를
//	 *			실행시킨 후 클라이언트 프로그램 PriceticeTcpIpClient.java를 실행시키고 바로 Ctrl+C로 서버 프로그램을 종료시킨것이다.
//	 *			
//	 *			while(true){
//	 *				try{
//	 *					...
//	 *					Socket socket = serverSocket.accept();
//	 *					...
//	 *				}...
//	 *			}
//	 *   		
//	 *   		클라이언트 프로그램의 요청을 지속적으로 처리하기 위해 무한 반복문을 사용했기 때문에
//	 *   		서버 프로그램을 종료 시키려면 Ctrl+C를 눌러서 강제종료 시켜야한다.
//	 *   		이 예제의 자세한 실행 과정은 다음 예제인 클라이언트 프로그램과 함께 설명...
//	 * 			
	
	public static void main(String[] args) {
		try {
			String serverIp = "127.0.0.1";
			
			System.out.println("서버에 연결 중입니다."+serverIp);
			
			// 소켓을 생성하여 연결을 요청한다.
			Socket socket = new Socket(serverIp, 7777);
			
			//소켓의 입력 스트림을 얻는다.
			InputStream in = socket.getInputStream();
			DataInputStream dis = new DataInputStream(in);
			
			//소켓으로부터 받은 데이터 출력
			System.out.println("서버로부터 받은 메시지 : "+dis.readUTF());
			System.out.println("연결 종료");
			
			// 스트림과 소켓 종료
			dis.close();
			socket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
