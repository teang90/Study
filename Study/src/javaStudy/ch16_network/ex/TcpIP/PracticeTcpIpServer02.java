package javaStudy.ch16_network.ex.TcpIP;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PracticeTcpIpServer02 {
	
//	 - Socket 클래스에 정의된 getPort()와 getLocalPort()를 사용해서 tcp/ip 통신에서 소켓이 사용하고 있는 포트를 알아낼 수 있다.
//	   getPort()가 반환하는 값은 상대편 소켓(원격 소켓)이 사용하는 포트이고 
//	   getLocalPort()가 반환하는 값은 소켓 자신이 사용하는 포트이다.
//	   
//	   결과에서 보면 연결을 요청한 클라이언트 프로그램의 소켓이 사용한 포트는 2839번이고(내 경우 63464) 서버 프로그램의 소켓은 7777
//	   이를 통해서 서버소켓이 7777번 포트를 사용해도 서버 소켓이 아닌 소켓은 7777번 포트를 사용할 수 있다는 것이다.
	
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket  =  null;
		try {
			
			//서버 소켓을 생성하여 7777번 포트와 결합(bind)시킨다
			serverSocket = new ServerSocket(7777);
			System.out.println(getTime()+", 서버가 준비되었습니다.");
			funcMethod(serverSocket);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void funcMethod(ServerSocket serverSocket) throws IOException {
		
		while (true) {
			System.out.println(getTime() + ", 연결 요청을 대기중");
			Socket socket = serverSocket.accept();
			System.out.println(getTime()+", "+socket.getInetAddress()+" 연결 요청이 들어왔습니다.");
			System.out.println("port : "+socket.getPort());
			System.out.println("local port : "+ socket.getLocalPort());
			
			// 소켓의 출력 스트림을 얻는다.
			OutputStream out = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(out);
			
			//원격 소켓(remote socket)에 데이터를 보낸다.
			dos.writeUTF("[Notice] test msg from server");
			System.out.println(getTime()+", 데이터를 전송했습니다. by server");
			
			//스트림과 소켓을 닫는다
			socket.close();
			dos.close();
		}
	}
	
	public static String getTime() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy:MM:dd hh:mm:ss");
		return f.format(new Date());
		
		
	}
	
}
