package javaStudy.ch16_network.ex.TcpIP;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PracticeTcpIpServer01 {
	
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
		ServerSocket serverSocket = null;
		
		try {
			// 서버소켓을 생성하여 7777번 포트와 결합(bind)시킨다.
			serverSocket = new ServerSocket(7777); 
			System.out.println(getTime()+" 서버가 준비되었습니다.");
			
			doFunction(serverSocket);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	private static void doFunction(ServerSocket serverSocket) {
		
		
		while (true) {
			try {
				System.out.println(getTime()+" 연결 요청 대기중");

				// 서버 소켓은 클라이언트의 연결 요청이 올 때까지 실행을 멈추고 계속 기다린다.
				// 클라이언트의 연결요청이 오면 클라이언트 소켓과 통신할 새로운 소켓을 생성한다.
				// putty로 /172.30.35.136, 7777접속해야 함
				Socket socket = serverSocket.accept();
				System.out.println(getTime()+", "+socket.getInetAddress() + "로부터 연결요청이 들어왔습니다.");
				
				// 소켓의 출력 스트림을 얻는다.
				OutputStream out = socket.getOutputStream();
				DataOutputStream dos = new DataOutputStream(out);
				
				// 원격 소켓(remote socket)에 데이터를 보낸다.
				dos.writeUTF("Notice: test MSG from server");
				System.out.println(getTime()+", 데이터를 전송했습니다.");
				
				// 스트림과 소켓을 닫아준다.
				dos.close();
				socket.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	private static String getTime() {
		SimpleDateFormat nowTime = new SimpleDateFormat("hh:mm:ss");
		return nowTime.format(new Date()); 
	}
	
	
}
