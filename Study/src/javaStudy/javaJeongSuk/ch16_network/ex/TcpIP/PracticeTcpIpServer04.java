package javaStudy.javaJeongSuk.ch16_network.ex.TcpIP;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PracticeTcpIpServer04 implements Runnable {

//	- 여러개의 쓰레드를 생성하여 클라이언트의 요청을 동시에 처리하도록 하였다.
//	   서버에 접속하는 클라이언트의 수가 많을 때는 쓰레드를 이용해서 클라이언트의 요청을 병렬적으로 처리하는 것이 좋다.
//	   그렇지 않으면 서버가 접속을 요청한 순서대로 처리하기 때문에 늦게 접속을 요청한 클라이언트는 오랜 시간을 기다리게된다.
	
	
	ServerSocket serverSocket = null;
	Thread[] threads = null;
	public PracticeTcpIpServer04() {}
	
	public PracticeTcpIpServer04(int i) {
		// 서버를 생성하여 7777번 포트와 결합시킨다.
		try {
			serverSocket = new ServerSocket(7777);
			System.out.println(getTime()+" 서버 준비 완료");
			
			threads = new Thread[i];
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	public static void main(String[] args) {
		// 5개의 스레드를 생성하는 서버를 생성.
		PracticeTcpIpServer04 server = new PracticeTcpIpServer04(5);
		server.start();
	}


	private void start() {
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(this);
			threads[i].start();
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println(getTime()+" 연결 요청" );
				
				Socket socket = serverSocket.accept();
				System.out.println(getTime()+", "+socket.getInetAddress()+" & "+socket.getPort()+" 요청 접수");
				
				//소켓의 출력 스트림을 얻는다.
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				dos.writeUTF("[notice] this msg is from server");
				System.out.println(getTime()+" 데이터 전송완료");
				
				dos.close();
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public static String getTime() {
		SimpleDateFormat f = new SimpleDateFormat("hh:mm:ss");
		return f.format(new Date());
	}
}
