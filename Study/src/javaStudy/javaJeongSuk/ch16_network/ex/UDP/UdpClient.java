package javaStudy.javaJeongSuk.ch16_network.ex.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient {
	
	public void start() throws Exception{
		DatagramSocket dataSocket = new DatagramSocket();
		InetAddress serverAddr = InetAddress.getByName("127.0.0.1");
		
		//데이터가 저장될 공간으로 byte배열을 생성한다.
		byte[] msg = new byte[100];
		int port = 7777;
		DatagramPacket outPacket = new DatagramPacket(msg, 1, serverAddr, port);
		DatagramPacket inPacket = new DatagramPacket(msg, msg.length);
		
		dataSocket.send(outPacket); // datagramPacket을 전송
		dataSocket.receive(inPacket); // datagramPacket을 수신
		
		System.out.println("현재 서버 타임 : "+new String(inPacket.getData()));
		
		dataSocket.close();
	}
	
	public static void main(String[] args) {
		try {
			new UdpClient().start();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

