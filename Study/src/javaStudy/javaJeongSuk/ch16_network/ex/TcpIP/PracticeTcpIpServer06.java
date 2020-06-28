package javaStudy.javaJeongSuk.ch16_network.ex.TcpIP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class PracticeTcpIpServer06 {
	private HashMap<Object, Object> cliMap;
	
	public HashMap<Object, Object> getCliMap() {
		return cliMap;
	}

	public void setCliMap(String name, Object contents) {
		if (this.cliMap == null) {
			cliMap = new HashMap<Object, Object>();
		}
		cliMap.put(name, contents);
	}
	public static void main(String[] args) {
		start();
	}
	public PracticeTcpIpServer06() {
		cliMap = new HashMap<Object, Object>();
		Collections.synchronizedMap(cliMap);
	}
	
	public static void start() {
		ServerSocket sevSocket = null;
		Socket socket = null;
		
		try {
			// 서버 소켓을 새로 생성
			int port = 7777;
			sevSocket = new ServerSocket(port);
			System.out.println("서버가 시작되었습니다.");
			
			while (true) {
				socket = sevSocket.accept();
				System.out.println("[" 	  +  socket.getInetAddress()+
									" : " +  socket.getPort()+", 에서 접속했습니다.");
				
				ServerReceiver sevRecv = new ServerReceiver(socket);
				sevRecv.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendToAll(String msg) {
		
		Iterator<Object> iter = cliMap.keySet().iterator();
		
		while (iter.hasNext()) {
			try {
				
				DataOutputStream dos = (DataOutputStream)cliMap.get(iter.next());
				dos.writeUTF(msg);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}

class ServerReceiver extends Thread {
	Socket socket;
	DataInputStream  dis = null;
	DataOutputStream dos = null;
	
	public ServerReceiver(Socket socket) {
		this.socket = socket;
	
		try {
			this.dis = new DataInputStream(socket.getInputStream());
			this.dos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public void run() {
		PracticeTcpIpServer06 prct06 = new PracticeTcpIpServer06();
		String name = "";
		try {
			name = dis.readUTF();
			prct06.sendToAll("#"+name+"님이 입장하셨습니다.");
			
			prct06.setCliMap(name, dos);
			System.out.println("현재 서버의 접속자 수는"+prct06.getCliMap().size()+"명");
			
			while (dis != null) {
				prct06.sendToAll(dis.readUTF());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			prct06.sendToAll("#"+name+"님이 퇴장하였습니다.");
			prct06.getCliMap().remove(name);
			System.out.println("["+socket.getInetAddress()+":"+socket.getPort()+"]에서 접속을 종료했습니다.");
			System.out.println("현재 서버의 접속자 수는 "+prct06.getCliMap().size()+"명 입니다.");
		}
		
	}

	
	
	
	
	
}