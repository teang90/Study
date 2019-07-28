package javaStudy.ch16_network.ex.TcpIP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class PracticeTcpIpServer05 {

	/*
	 * 소켓으로 데이터를 송신하는 작업과 수신하는 작업을 별도의
	 * 쓰레드 Sender와 Receiver가 처리하도록 하여 송신과 수신이 동시에 이루어지도록 했다.
	 * 
	 * 서버 프로그램(PracticeTcpIpServer05)과 클라이언트(PracticeTcpIpClient05)의 화면에
	 * 입력한 데이터가 상대방의 화면에 출력되므로 1:1 채팅이 가능하다.
	 */
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket ;
		
		try {
			//서버 소켓을 생성하여 7777번 포트와 결합
			serverSocket = new ServerSocket(7777);
			System.out.println("서버가 준비되었습니다.");
			
			socket = serverSocket.accept();
			if (socket == null) return;
			
//			Thread th1	  = new Thread(send); // send대신에 socket
// 			run 메소드에 socket 넣어서 바로 돌리고 싶음(Sender 생성자 안만들고)
			Sender sender = new Sender(socket);
			Receiver recv = new Receiver(socket);
			
			sender.start();
			recv.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

class Sender extends Thread {
	Socket socket = null;
	DataOutputStream dos ;
	String name = "";
	
	public Sender() {}
	
	public Sender(Socket socket) {
		this.socket = socket;
		try {
			dos = new DataOutputStream(socket.getOutputStream());
			name = "["+socket.getInetAddress()+":"+socket.getPort()+"]";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		Scanner scan = new Scanner(System.in);
		
		while (dos != null) {
			try {
//				System.out.println("dos's IP hashCode : "+socket.getInetAddress().hashCode()); //dos's IP hashCode : 2130706433
//				dos.writeUTF(socket.getInetAddress().toString());
				dos.writeUTF("["+name+"] : "+scan.nextLine());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}

class Receiver extends Thread {
	Socket socket = null ;
	DataInputStream dis = null ;
	
	public Receiver(Socket socket) {
		this.socket = socket;
		
		try {
			dis = new DataInputStream(socket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while (dis != null) {
			try {
				System.out.println(dis.readUTF());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}	
	
}
