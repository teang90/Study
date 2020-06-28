package javaStudy.javaJeongSuk.ch16_network.ex.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UdpServer {
	
//	  서버로부터 서버시간을 전송바다아 출력하는 UDP 소켓 클라이언트와 서버 프로그램이다.
//	  클라이언트 datagrampacket을 생성해서 datagramsocket으로 서버에 전송하며, 서버는 전송받은 datagrampacket의
//	  getAddress(), getPort()를 호출해서 클라이언트의 정보를 얻어서 서버시간을 datagrampacket에 담아서 전송한다.
	
	public static void main(String[] args) {
		try {
			new UdpServer().start();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void start() {
		try {
			// 7777포트를 사용하는 소켓 생성
			int port = 7777;
			DatagramSocket dataSocket = new DatagramSocket(port);
			DatagramPacket inPacket, outPacket;
			
			byte[] inMsg  = new byte[10];
			byte[] outMsg ;
			
			while (true) {
				// 데이터를 수신하기 위한 패킷을 생성
				inPacket = new DatagramPacket(inMsg, inMsg.length);

				// 패킷을 통해 데이터를 수신(recv)한다.
				dataSocket.receive(inPacket);

				// 수신한 패킷으로부터 client의 ip주소와 port를 얻는다.
				InetAddress addr = inPacket.getAddress();
				int portOfRecvPack = inPacket.getPort();

				// 서버로부터 현재 시간을 시분초 형태로 반환한다.
				SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
				String time = f.format(new Date());
				outMsg = time.getBytes(); // time을 byte배열로 변환

				// 패킷을 생성해서 client에게 전송(send)한다.
				outPacket = new DatagramPacket(outMsg, outMsg.length, addr, portOfRecvPack);
				dataSocket.send(outPacket);
			}
			
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	} 
}
