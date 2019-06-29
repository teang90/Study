package javaStudy.ch16_network;

public class Network01 {
	/**
	 * 참고
	 * https://zeroit.tistory.com/entry/java-Network-URL-URI
	 * 
	 * 네트워킹
	 * 	 	- 두 대 이상의 컴퓨터를 케이블로 연결하여 네트를 구성하는 것을 말한다.
	 * 		- 인터넷 : 수 많은 컴퓨터가 이루는 하나의 거대한 네트워크
	 * 	 
	 * 
	 * 	1.1 클라이언트/서버
	 * 		- 서버		 : 서비스를 제공하는 컴퓨터(service provider), 즉 서비스를 제공하는 소프트웨어가 실행되는 컴퓨터를 말한다.
	 *		- 클라이언트 : 서비스를 사용하는 컴퓨터(service user)	
	 * 		- 서비스	 : 서버가 클라이언트로부터 요청받은 작업을 처리하여 그 결과를 제공하는 것을 말한다. 
	 * 				  	 (ex 파일서버(클라이언트가 요청한 파일을 제공하는 서비스를 수행), 메일서버, 웹서버, 어플리케이션 서버)
	 * 
	 * 		- 서버가 서비스를 제공하기 위해서는 서버프로그램이 있어야하고 클라이언트가 서비스를 제공받기 위해서는 서버프로그램과 연결할 수 있는
	 * 		  클라이언트 프로그램이 있어야한다.
	 * 		  (ex - 웹 서버에 접속하여 정보를 얻기 위해서는 웹 브라우저라는 클라이언트 프로그램이 있어야한다.
	 * 			  - FTP 서버에 접속하여 파일을 전송받기 위해서는 알FTP와 같은 FTP 클라이언트 프로그램이 필요하다.)	
	 * 		
	 * 		- 일반 PC의 경우 주로 서버에 접속하는 클라이언트 역할을 수행하지만, 
	 * 		  FTP Serv-U와 같은 FTP서버 프로그램이나 Tomcat과 같은 웹 서버 프로그램을 설치하면 서버역할도 수행 가능하다.
	 * 		
	 * 		- 프루나, 소리바다와 같은 프로그램의 경우 클라이언트와 서버 프로그램을 하나로 합친 것으로서 
	 * 		  다른 컴퓨터로부터 파일을 가져오는 동시에 또 다른 컴퓨터에게 파일을 제공할 수 있다.
	 * 
	 * 		- 서버 기반 모델(server-based model): 네트워크 구성시 전용서버를 두는 것
	 * 		- P2P 모델(peer to peer)			: 별도의 전용 서버 없이 클라이언트가 서버 역할을 동시에 수행하는 것
	 * 
	 * 
	 * 	1.2 IP주소(IP Address)
	 * 		- IP주소는 컴퓨터(host)를 구별하는데 사용되는 고유한 값으로, 인터넷에 연결된 모든 컴퓨터는 IP주소를 갖는다.
	 * 		- 구성 1) 물리적 구성: IP주소는 4byte(32bit)로서 총 4개의 정수로 구성, 각 4개의 정수는 부호 없는 1byte값, 즉 0~255사이의 정수이다.
	 * 		- 구성 2) 의미적 구성: IP주소는 네트워크 주소와 호스트 주소로 나뉘며, 네트워크 주소와 호스트 주소가 각각 몇 bit를 차지하는 지는 
	 * 							   네트워크를 어떻게 구성했는지에 따라 다르다. 또한, 서로 다른 두 호스트의 IP주소의 네트워크 주소가 
	 * 							   같다는 것은 두 호스트가 같은 네트워크에 포함되어 있다는 것을 의미한다
	 * 							   (동일 네트워크라면 앞의 a.b.c.d에서 두 호스트가 a,b,c는 같고, d는 서로 다르다는 의미).
	 * 		- 특징: IP 주소와 서브넷 마스크를 '&'연산하면 네트워크 주소를 얻어낼 수 있어서 서로 다른 두 호스트의 IP주소를 서브넷 마스크로 
	 * 				'&' 연산을 수행해서 비교하면 두 호스트가 같은 네트워크 상에 존재하는지 확인 가능
	 * 	
	 * 	
	 * 	1.3 InetAddress
	 * 		- 자바에서는 IP주소를 다루기 위해 InetAddress 클래스를 제공한다.
	 * 		- InetAddress의 메소드 
	 * 			1) byte[] getAddress() 								: IP주소를 byte 배열로 반환한다.
	 *			2) static InetAddress[] getAllByName(String host) 	: 도메인명(host)에 지정된 모든 호스트의 IP주소를 배열에 담아 반환한다.
	 *			3) static InetAddress getByAddress(byte[] addr)		: byte배열을 통해 IP 주소를 얻는다.
	 *			4) static InetAddress getByName(String host)		: 도메인명(host)를 통해서 IP주소를 얻는다.
	 *			5) String getCanonicalHostName()					: FQDN(Fully Qualified Domain Name)을 반환한다.
	 *			6) String getHostAddress()							: 호스트의 IP주소를 반환한다.
	 *			7) String getHostName()								: 호스트의 이름을 반환한다.
	 *			8) static InetAddress getLocalHost()				: 지역 호스트의 IP 주소를 반환한다.
	 *			9) boolean isMulticastAddress()						: IP 주소가 멀티캐스트 주소인지 알려준다.
	 *			10)boolean isLoopbackAddress()						: IP 주소가 loopback 주소(127.0.0.1)인지 알려준다.   
	 * 
	 *		- 예제: PracticeInetAddress01 class 참고 
	 *
	 *
	 *	1.4 URL(Uniform Resource Location) - 동일한 자원 위치?
	 *		ty's) 웹 하나의 사이트를 자원이라고 생각하고 사용자에게 동일한 그곳의 위치를 가리키는 명시적 주소가 URL이라고 이해
	 * 		
	 * 		- URL은 인터넷에 존재하는 여러 서버들이 제공하는 자원에 접근할 수 있는 주소를 표현하기 위한 것
	 * 		- 형식 : '프로토콜://호스트명:포트번호/경로명/파일명?쿼리스트링#참조'
	 * 				http://teang90.cafe24.com/oneBoard.do?board_pk=141&cPage=1	
	 * 				http://www.codechobo.com:80/sample/hello.html?referer=codechobo#index1	
	 * 				- 프로토콜  : 자원에 접근하기 위해 서버와 통신하는데 사용되는 통신규약(http)
	 * 				- 호스트명  : 자원을 제공하는 서버의 이름(www.teang90.cafe24.com, www.codechobo.com)
	 * 				- 포트번호  : 통신에 사용되는 서버의 포트번호(80) (HTTP프로토콜에서 80번 포트를 기본적으로 사용하기 때문에 URL에서 생략하는 경우 디폴트로 80포트 간주)
	 * 				- 경로명    : 접근하려는 자원이 저장된 서버상의 위치(/, /sample/)
	 * 				- 파일명    : 접근하려는 자원의 이름(oneBoard.do(이 요청 url에 해당하는 jsp페이지), hello.html)
	 * 				- 쿼리		: URL에서 ?이후의 부분(board_pk&cPage, referer=codechobo)
	 * 				- 참조		: URL애서 #이후의 부분(index)
	 * 		
	 * 		- 자바에서 제공하는 URL클래스의 메소드
	 * 			1)  URL(String)										: 지정된 문자열 정보의 URL객체 생성
	 * 			2)  URL(String protocol, String host, String file)	: 지정된 값으로 구성된 URL객체 생성

	 * 			3)  URL(String protocol, String host, int port, String file) 
	 * 																: 지정된 값으로 구성된 URL객체 생성
	 * 
	 * 			4)  String getAuthority()							: 호스트 명과 포트를 문자열로 반환		
	 * 			5)  Object getContent								: URL의 Content객체를 반환
	 * 			6)  Object getContent(Class[] classes)				: URL의 Content객체를 반환
	 * 			7)  int getDefaultPort()							: URL의 기본 포트를 반환(HTTP는 80)
	 * 			8)  String getFile()								: 파일명을 반환
	 * 			9)  String getHost()								: 호스트명을 반환
	 * 			10) String getPath()								: 경로명을 반환
	 * 			11) int getPort()									: 포트를 반환
	 * 			12) String getProtocol()							: 프로토콜을 반환
	 * 			13)	String getQuery()								: 쿼리를 반환
	 * 			14)	String getRef()									: 참조를 반환
	 * 		★★★	15)	String getUserInfo()							: 사용자 정보를 반환 ★★★(어떤 정보를 가져오는지?)
	 * 			16)	URLConnection openConnection()					: URL과 연결된 URLConnection을 얻는다.
	 * 			17)	URLConnection openConnection(Proxy proxy)		: URL과 연결된 URLConnection을 얻는다.
	 * 			18) InputStream openStream()						: URL과 연결된 URLConnection의 InputStream을 얻는다.
	 * 			19)	boolean sameFile(URL other)						: 두 URL이 서로 같은 것인지 알려준다.
	 * 
	 * 			20)	void set(String protocol, String host, int port,
	 * 						 String file, String ref) 				: URL객체의 속성을 지정된 값으로 설정한다.
	 * 
	 * 			21) void set(String protocol, String host, int port,
	 * 						 String authority, String userInfo, 
	 * 						 String path, String query, String ref) : URL객체의 속성을 지정된 값으로 설정한다.
	 *  
	 * 			22) String toExternalForm()							: URL을 문자열로 변환하여 반환
	 * 			23)	URL toURL()										: URL을 URL로 변환하여 반환...?
	 * 
	 * 		- URL객체를 생성하는 방법
	 * 			URL url = new URL("www.naver.com");
	 * 		
	 * 		- ex의 PracticeURL01 예제 참고 (16-2)
	 * 	
	 *	1.5 URLConnection
	 *		 참고 https://goddaehee.tistory.com/161
	 *
	 *		- URLConnection은 어플리케이션과 URL간의 통신연결을 나타내는 최상위 클래스로 추상클래스이다.
	 *		- URLConnection을 상속받아 구현한 클래스(하위 클래스)는 HttpURLConnection, JarURLConnection이 있다.
	 *		  URL의 프로토콜이 HTTP이라면 openConnection()은 Http URLConnection을 반환한다.
	 *		- URLConnection을 사용하여 연결하고자하는 자원에 접근, 읽기, 쓰기를 할 수 있다.
	 *		- URLConnection의 메소드(자바의 정석 954페이지)
	 *		
	 * 
	 * 
	 *	
	 *	
	 *	
	 *
	 *
	 * 
	 */
	
	
	
	
	
	
}
