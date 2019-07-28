package javaStudy.ch16_network.ex.TcpIP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class PracticeTcpIpClient06 {
	
	public static void main(String[] args) {
		
		if (args.length != 1) {
			System.out.println("USAGE : JAVA TcpIP EX");
//			System.exit(0);
		}
		
		try {
			// 소켓을 생성하여 연결을 요청한다.
			String serverIp = "127.0.0.1";
			int port = 7777;
			Socket socket = new Socket(serverIp, port);
			System.out.println("서버에 연결 중입니다."+serverIp+" "+args[0]);
//			Thread sender = new Thread(new ClientSender(socket, "teang"));
			Thread sender = new Thread(new ClientSender(socket, args[0]));
			Thread recv = new Thread(new ClientRecv(socket));
			
			sender.start();
			recv.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


class ClientSender extends Thread {
	Socket socket = null;
	DataOutputStream dos = null;
	String name = "";
	
	public ClientSender(Socket socket, String name) {
		this.socket = socket;
		try {
			dos = new DataOutputStream(socket.getOutputStream());
			this.name = name;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		System.out.println("start에 의한 시작");
		Scanner scan = new Scanner(System.in);
		try {
			if (dos != null) {
				dos.writeUTF(name);
			}
			
			while (dos != null) {
				dos.writeUTF("["+name+"] :"+scan.nextLine());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class ClientRecv extends Thread {
	Socket socket;
	DataInputStream dis ;
	
	public ClientRecv(Socket socket) {
		this.socket = socket;
		try {
			dis = new DataInputStream(socket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (dis != null) {
			try {
				System.out.println(dis.readUTF().toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
