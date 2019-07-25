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
	 * 		1.4.2 블로그 요약글 https://hyunssssss.tistory.com/244 [현's 블로그]
	 * 		- http,ftp,telnet 등의 서비스를 지원하는 웹 서버들의 위치를 표현하는 체계이다.
	 *		- 보통 다음의 형태를 가진다 - protocol://hostname[:port]/path (ex. http://naver.com)
	 *		
	 *		- URL 클래스 : 웹상의 주소를 나타내는 클래스, URL 객체 생성 시 잘못된 URL 형태를 줬을때 예외처리를 해줘야함
	 *		- URL 클래스 사용시 간단하게 호스트로부터 데이터를 읽어올 수 있다.
	 *		- ex1)
	 *		  -ex1

			  ->URL클래스로 호스트의 정보를 표시하고 페이지를 화면에 출력하는 예제
			
				1) URL 객체 생성
				
				  URL url = new URL("http://www.naver.com");
				
				2) URL객체로의 스트림 열기
				
				  InputStream in = url.openStream();
				
				3) 받는 스트림을 버퍼에 저장
				
				  BufferedReader bf = new BufferedReader( new InputStreamReader(in));
				
				4) 버퍼를 화면에 출력하고 스트림 닫기
				출처: https://hyunssssss.tistory.com/244 [현's 블로그]
	 *			
	 * 	
	 *	1.5 URLConnection
	 *		참고 https://goddaehee.tistory.com/161
	 *
	 *		- URLConnection은 어플리케이션과 URL간의 통신연결을 나타내는 최상위 클래스로 추상클래스이다.
	 *		- URLConnection을 상속받아 구현한 클래스(하위 클래스)는 HttpURLConnection, JarURLConnection이 있다.
	 *		  URL의 프로토콜이 HTTP이라면 openConnection()은 Http URLConnection을 반환한다.
	 *		- URLConnection을 사용하여 연결하고자하는 자원에 접근, 읽기, 쓰기를 할 수 있다.
	 *	
	 *
	 *		javaKing 블로그 부분 정보(http://blog.naver.com/javaking75/140188363267)
			 - URL url = new URL(strUrl); //URL객체를 생성
		       InputStream is = url.openStream(); //서버로부터 데이터를 주고 받을수있게 스트림을 연결.
		       ★★★///openStream()메소드를 이용하면 URL이 위치한곳과 자동으로 접속이 일어나고,★★★ 
		       //그 결과로 Inpusstream이 반환된다.
			   [출처] [Java] 네트워크 (Network) - 개요, InetAddress, URL, URLConnection 클래스|작성자 자바킹
	
		
	 *	   - URLConnection의 메소드(자바의 정석 954페이지)
	 *			1) void addRequestProperty(String key, String value)   
	 *													: 지정된 키와 값을 RequestProperty에 추가, 
	 *			   기존의 중복되는 키가 있는 경우 값을 덮어쓰지 않는다(그럼? 2개 이상부터 배열처리? 아님 동일키에 다른 값이 생김? check 필요).
	 *			   -> 블로그 요약글 참고 : [http://jo.centis1504.net/?p=1570] 참고: 키 중복시 배열, 복수 맵 생성이 아니고, 최초 데이터 이후의 중복된 데이터는 받지 않음
	 *			   							-> 따라서, 중복된 데이터를 넣어줄 경우 split으로 찢을걸 생각하고 
	 *			ex) httpUrl.setRequestProperty("duplicationEX", "A") 이후 httpUrl.setRequestProperty("duplicationEX", "B")가 아니라
	 *				httpUrl.setRequestProperty("duplicationEX", "A, B") 이런 식으로 중복이 필요한 데이터를 합쳐서 넣어줘야한다.
	 *				흠...
	 *			
	 *			2) void connect();						: URL에 지정된 자원에 대한 통신을 연결
	 *			3) boolean getAllowUserInteration()		: UserInteraction의 허용여부 반환
	 *			4) int getConnectTimeOut(), void setConnectTimeOut() 
	 *													: 연결 종료 시간을 천분의 일초로 반환, 연결 종료 시간 설정
	 *			5) Object getContent()					: content객체를 반환
	 *			6) Object getContent(Class[] classes)	: content객체를 반환
	 *			7) String getContentEncoding()			: content의 인코딩을 반환
	 *			8) int getContentLength()				: content의 크기를 반환
	 *			9) String getContentType()				: content의 타입 반환
	 *			10)long getDate()						: 헤더의 date필드의 값을 반환
	 *			11)boolean getDefaultAllowUserInteraction()
	 *													: defaultAllowUserInteraction의 값을 반환
	 *			12)String getDefaultRequestProperty(String key)
	 *													: RequestProperty에서 지정된 키의 디폴트 값을 얻는다.
	 *			13)boolean getDefaultUseCaches()		: useCache의 디폴트 값을 얻는다
	 *			14)boolean getDoInput(), void setDoInput(boolean doinput)
	 *													: doInput의 필드값을 얻는다. (doInput의 필드값?) / doInput 필드 값을 설정
	 *			15)boolean getDoOutput(), void setDoOutput(boolean dooutput)
	 *													: doOutput의 필드값을 얻는다.(doOutput의 필드값?) / doOutput 필드값을 설정
	 *			16)long getExpiration()					: 자원(URL)의 만료일자를 얻는다(천분의 일초단위)
	 *			17)FileNameMap getFileNameMap(), void setFileNameMap(FileNameMap map)
	 *													: 파일 네임 맵(mimetable)을 반환한다. / 파일 네임 맵을 설정
	 *		   ★18)String getHeaderField(int n)			: 헤더의 n번째 필드를 읽어온다.
	 *			19)String getHeaderField(String name)	: 헤더에서 지정된 이름의 필드를 읽어온다.
	 *			20)long getHeaderFieldDate(String name, long Default)
	 *													: 지정된 필드의 값을 날짜값으로 변환하여 반환한다.
	 *													: 필드 값이 유효하지 않을 경우 디폴트값을 반환
	 *			21)int getHeaderFieldDateInt(String name, int Default)
	 *													: 지정된 필드의 값을 정수값으로 변환하여 반환한다.
	 *													: 필드값이 유효하지 않은 경우 디폴트 값을 반환
	 *			22)String getHeaderFieldKey(int key)	: 헤더의 n번째 필드를 읽어온다.
	 *		   ★23)Map getHeaderFields()				: 헤더의 모든 필드와 값이 저장된 Map을 반환
	 *		   ?24)long getIfModifiedSince(), void setIfModifiedSince(long ifmodifiedsince)
	 *													: ifModifiedSince(변경여부) 필드의 값을반환 / modifiedSince 필드 값을 설정
	 *			25)long getLastModified()				: LastModified(최종변경일) 필드의 값을 반환
	 *		   ★26)InputStream getInputStream()			: URLConnection에서 InputStream을 반환
	 *		   ★27)OutputStream getOutputStream()		: URLConnection에서 OutputStream을 반환
	 *			28)Permission getPermission()			: Permission(허용권한)을 반환
	 *			29)int getReadTimeOut()					: 읽기 제한시간의 값을 반환(천분의 일초)
	 *			30)void setReadTimeOut()				: 읽기 제한시간의 값을 설정(천분의 일초)
	 *			31)Map getReqeustProperties()			: RequestProperties에 저장된(키, 값)을 Map으로 반환
	 *													  setRequestProperties(parameter)는 없는듯, 아마 setRequestProperty()로 넣고
	 *													  전체를 반환하는 역할만 하는듯
	 *			32)String getRequestProperty(String key): RequestProperty에서 지정된 키의 값을 반환
	 *			33)URL getUrl()							: URLConnection의 URL반환 
	 *			34)boolean getUseCaches(), void setUseCaches(boolean usecaches)				
	 *													: 캐시의 사용 여부를 반환 / 캐시의 사용 여부 설정
	 *			35)String guessContentTypeFromName(String fname)
	 *													: 지정된 파일(fname)의 content-type을 추측하여 반환
	 *			36)String guessContentTypeFromStream(InputString is)
	 *													: 지정된 입력 스트림(is)의 content-type을 추측하여 반환
	 *			37)void setAllowUserInteraction(boolean allowuserinteraction)
	 *													: UserInteraction의 허용여부를 설정
	 *			38)void setContentHandlerFactory(ContentHandlerFactory fac)
	 *													: contentHandlerFactory를 설정
	 *			39)void setDefaultAllowUserInteraction(boolean defaultallowuserinteraction)
	 *													: UserInteraction 허용 여부의 기본값 설정
	 *			40)void setDefaultRequestProperty(String key, String value)
	 *													: RequestProperty의 기본 키쌍을 설정
	 *			41)void setDefaultUseCaches(boolean defaultusecaches)
	 *													: 캐시 사용여부의 기본값 설정
	 *			42)void setFileNameMap(FileNameMap map)	: FileNameMap 필드값을 설정
	 *
	 *			- ex의 PracticeURLConnection01 예제 참고 (16-3)
	 *			- ex의 PracticeURLConnection02 예제 참고 (16-4)
	 *
	 *			URL에 연결하여 그 내용을 읽어오는 예제이다. 
	 *			만일 URL이 유효하지 않으면 Malformed - URLException이 발생
	 *			openStream()을 호출하여 URL의 InputStream을 얻은 이후로는 
	 *			파일로 부터 데이터를 읽는 것과 다르지 않다.
	 *			openStream()은 openConnection()을 호출해서 URLConnection을 얻은 다음
	 *			여기에 다시 getInputStream()을 호출한 것과 같다.
	 *			즉 URL에 연결해서 InputStream을 얻어 온다.
	 *
	 *			InputStream in = url.openStream() <---> URLConnection conn = url.openConnection()
	 *											  		InputStream in = conn.getInputStream();
	 *
	 *			이진 데이터를 읽어서 파일에 저장하는 예제(FileReader -> FileOuputStream 사용)
	 *			- ex의 PracticeURLConnection03 예제 참고 (16-5)
	 *
	 *	
	 *	2. 소켓 프로그래밍
	 *	소켓 프로그래밍은 소켓을 이용한 통신 프로그래밍을 뜻한다. 
	 *	소켓(socket)이란 프로세스간의 통신에 사용되는 양쪽 끝단(endPoint)를 의한다.
	 *	서로 멀리 떨어진 두 사람이 통신하기 위해서 전화기가 필요한 것처럼, 
	 *	프로세스간의 통신을 위해서는 그 무언가가 필요하고 그것이 소켓이다.
	 *
	 *	자바에서는 java.net 패키지를 통해 소켓 프로그래밍을 지원한다. 
	 *	소켓 통신에 사용되는 프로토콜에 따라 다른 종류의 소켓을 구현하여 제공한다.
	 *	여기선 TCP, UDP를 이용한 소켓 프로그래밍에 대해 학습할 것이다.
	 *	
	 *	  2.1 TCP, UDP
	 *		TCP/IP 프로토콜은 이기종 시스템간의 통신을 위한 표준 프로토콜로 프로토콜의 집합이다.
	 *		TCP와 UDP 모두 TCP/IP 프로토콜(TCP/IP protocol suites)에 포함되어 있으며, 
	 *		OSI 7계층의 전송계층(transport layer)에 해당하는 프로토콜이다.
	 *		TCP와 UDP는 전송방식이 다르며 각 방식에 장단점이 있다.
	 *		
	 *		_________________________________________________________________________________________________________________
	 *		|항	  목:							TCP											UDP
	 *		|----------------------------------------------------------------------------------------------------------------
	 *		|연결방식:			연결기반(connection-oriented)					비연결기반(connectionless-oriented)
	 *		|					- 연결 후 통신(전화기)							- 연결없이 통신(소포)
	 *		|					- 1:1 통신 방식									-  1:1, 1:n, n:n 통신 방식
	 *		|________________________________________________________________________________________________________________
	 *		|
	 *		|특    징:			데이터의 경계를 구분안함(byte-stream)			데이터의 경계를 구분함(datagram)
	 *		|					신뢰성 있는 데이터 전송							신뢰성 없는 데이터 전송
	 *		|					- 데이터의 전송 순서 보장됨						- 데이터의 전송 순서가 바뀔 수 있음
	 *		|					- 데이터의 수신여부를 확인함					- 데이터의 수신여부를 확인 안 함
	 *		|					  (데이터가 손실되면 재선송 됨)					  (데이터가 손실되어도 알 수 없음)
	 *		|					- 패킷을 관리할 필요가 없음						- 패킷을 관리해주어야 함
			|
	 *		|					UDP보다 전송속도가 느림							TCP보다 전송 속도가 빠름
	 *		|________________________________________________________________________________________________________________
	 *		|관련 클래스:		Socket, ServerSocket						DatagramSocket, DatagramPacket, MulticastSocket
	 *		-----------------------------------------------------------------------------------------------------------------
	 *		
	 *		TCP를 이용한 통신은 전화에, UDP를 이용한 통신은 소포에 비유된다. 
	 *		TCP는 데이터를 전송하기 전에 먼저 상대편과 연결을 한 후에 데이터를 전송하며 잘 전송되었는지 확인하고 
	 *		전송에 실패했다면 해당 데이터를 재전송하기 때문에 신뢰있는 데이터의 전송이 요구되는 통신에 적합하다.
	 *		예를 들어 파일을 주고 받는데 적합하다. 
	 *		
	 *		UDP는 상대편과 연결하지 않고 데이터를 전송하며, 데이터를 전송하지만 데이터가 
	 *		바르게 수신되었는지 확인하지 않기 때문에 데이터가 전송됐는지 확인할 길이 없다. 
	 *		또한, 데이터를 보낸 순서대로 수신한다는 보장이 없다.
	 *		대신 이러한 확인과정이 필요하지 않기 때문에 TCP에 비해 빠른 전송이 가능하다.
	 *		게임이나 동영상의 데이터를 전송하는 경우와 같이 데이터가 중간에 손실되어 조금 끊기더라도 
	 *		빠른 전송이 필요할 때 적합하다. 이때 전송 순서가 바뀌어 늦게 도착한 데이터는 무시하면 된다.
	 *		
	 *
	 *	  2.2 TCP 소켓 프로그래밍
	 *		TCP 소켓 프로그래밍은 클라이언트와 서버간의 1:1 통신이다.
	 *		먼저 서버 프로그램이 실행되어 클라이언트 프로그램의 연결요청을 기다리고 있어야 한다.
	 *		서버 프로그램과 클라이언트 프로그램간의 통신과정을 단계별로 보면 다음과 같다.
	 *
	 *		  --------------------------------------------------------------------		 
	 *		  1) 서버 프로그램에서는 서버소켓을 사용해서 서버 컴퓨터의 
	 *			 특정 포트에서 클라이언트의 연결요청을 처리할 준비를 한다.
	 *
	 *		  2) 클라이언트 프로그램은 접속할 서버의 IP주소와 
	 *			 포트 정보를 가지고 소켓을 생성해서 서버에 연결을 요청한다. 
	 *		  
	 *		  3) 서버소켓은 클라이언트의 연결요청을 받으면 서버에 
	 *		     새로운 소켓을 생성해서 클라이언트의 소켓과 연결되도록 한다.
	 * 
	 *		  4) 이제 클라이언트의 소켓과 새로 생성된 서버의
	 *			 소켓은 서버소켓과 관계없이 일대일 통신을 한다.
	 *		  -------------------------------------------------------------------- 
	 *		
	 *		서버 소켓은 포트와 결합(bind)되어 포트를 통해 원격 사용자의 연결요청을 기다리다가 
	 *		연결요청이 올 때마다 새로운 소켓을 생성하여 상대편 소켓과 통신할 수 있도록 연결한다.
	 *		여기까지가 서버 소켓의 역할이고, 실제적인 데이터 통신은 서버소켓과 관계없이 소켓과 소켓 간에 이루어진다.		
	 *		
	 *		이는 마치 전화 시스템과 유사해서 서버소켓은 전화 교환기에, 소켓은 전화기에 비유할 수 있다.
	 *		전화교환기(서버소켓)는 외부전화기(원격 소켓)로부터 걸려온 전화를 내부의 전화기(소켓)로 연결해주고 실제 통화는
	 *		전화기(소켓) 대 전화기(원격 소켓)로 이루어지게하기 때문이다.
	 *		
	 *		여러 소켓이 하나의 포트를 공유해서 사용할 수 있지만, 서버소켓은 다르다. 서버 소켓은 포트를 독점한다.
	 *		만일 한 포트를 둘 이상의 서버소켓과 연결하는 것이 가능하다면 클라이언트 
	 *		프로그램이 어떤 서버소켓과 연결괴어야하는지 알 수 없을 것이다.
	 *		
	 *		포트(port)는 호스트(컴퓨터)가 외부와 통신을 하기 위한 통로로 
	 *		하나의 호스트가 65536개의 포트를 가지고 있으며 포트는 번호로 구별된다.
	 *		포트의 번호는 0 ~ 65536의 범위에 속하는 값인데 보통 1023번 이하의 포트는 FTP, Telnet과 같은 
	 *		기존의 다른 통신 프로그램들에 의해서 사용되는 경우가 많기 때문에 1023번 이상의 번호 중에서 
	 *		사용하지 않는 포트를 골라 사용해야 한다.

	 *		(
	 *		참고 : 두 서버 소켓이 서로 다른 프로토콜을 사용하는 경우에는 같은 포트를 사용할 수 있다. 
	 *				포트는 같아도 클라이언트 프로그램이 사용하는 프로토콜로 어떤 서버소켓과 연결되어야하는지 
	 *				구별할 수 있기 때문이다.
	 *				그래도 가능하면 하나의 포트는 하나의 서버소켓만 사용하도록하는 것이 바람직하다.
	 *		)
	 *	
	 *		정리하자면, 서버소켓은 소켓간의 연결만 처리하고 실제 데이터는 소켓들끼리 서로 주고 받는다.
	 *		소켓들이 데이터를 주고받는 연결통로는 바로 입출력스트림이다.
	 *		소켓은 두 개의 스트림, 입력 스트림과 출력 스트림을 가지고 있으며, 
	 *		이 스트림들은 연결된 상대편 소켓의 스트림들과 교차연결된다.
	 *		한 소켓의 입력스트림은 상대편 소켓의 출력 스트림과 연결되고, 출력 스트림은 입력 스트림과 연결된다.
	 *		그래서 한 소켓에서 출력스트림으로 데이터를 보내면 상대편 소켓에서는 입력스트림으로 받게된다.
	 *		이것 역시 앞서 비유한 전화기(소켓)와 비슷해서 소켓이 두 개의 입출력 스트림을 갖는 것처럼 
	 *		전화기 역시 입력과 출력을 위한 두 개의 라인을 가지고 있다.
	 *	
	 *		프로세스1				프로세스2
	 *			소켓1					소켓2
	 *	 	    - 출력 스트림1	-------- 입력 스트림2
	 *		    - 입력 스트림1	-------- 출력 스트림2
	 *
	 *		자바에서는 TCP를 이용한 소켓 프로그래밍을 위해 Socket과 ServerSocket클래스를 제공한다.
	 *			- Socket : 프로세스간의 통신을 담당, InputStream, OutputStream을 가지고 있다.
	 *				  	   이 두 스트림을 통해 프로세스간의 통신(입출력)이 이루어진다.
	 *			
	 *			- ServerSocket : 포트와 연결(bind)되어 외부의 연결요청을 기다리다 연결요청이 들어오면,
	 *							 Socket을 생성해서 소켓과 소켓간의 통신이 이루어지도록 한다.
	 *							 한 포트에 하나의 ServerSocket만 연결할 수 있다.(프로토콜이 다르면 같은 포트 공유 가능)
	 *
	 *			단순 TCP/IP 서버 구현 예제 - PracticeTcpIpServer01.java 
	 *			- 이 예제는 간단한 TCP/IP 서버를 구현한 것이다. 이 예제를 실행하면
	 *			  서버 소켓이 7777번 포트에서 클라이언트 프로그램의 연결 요청을 기다린다.
	 *			  클라이언트의 요청이 올 떄까지 진행을 멈추고 계속 기다린다.
	 *			  그러다가 클라이언트 프로그램이 서버에 연결을 요청하면, 
	 *			  서버 소켓은 새로운 소켓을 생성하여 클라이언트 프로그램의 소켓(원격소켓)과 연결한다.
	 *			
	 *			  새로 생성된 소켓은 [Notice: test Message from server]라는 데이터를
	 *			  원격 소켓에 전송하고 연결을 종료한다. 그리고 서버 프로그램(PracticeTcpIpServer01.java)를
	 *			  실행시킨 후 클라이언트 프로그램 PriceticeTcpIpClient.java를 실행시키고 바로 Ctrl+C로 서버 프로그램을 종료시킨것이다.
	 *			
	 *			  while(true){
	 *				try{
	 *					...
	 *					Socket socket = serverSocket.accept();
	 *					...
	 *				}...
	 *			  }
	 *   		
	 *   		  클라이언트 프로그램의 요청을 지속적으로 처리하기 위해 무한 반복문을 사용했기 때문에
	 *   		  서버 프로그램을 종료 시키려면 Ctrl+C를 눌러서 강제종료 시켜야한다.
	 *   		  이 예제의 자세한 실행 과정은 다음 예제인 클라이언트 프로그램과 함께 설명...
	 * 			
	 * 			
	 * 			ex - PriceticeTcpIpClient01.java (PracticeTcpIpServer01.java와 통신하기 위한 예제) 
	 * 			- 이 예제는 이전 예제인 TCP/IP서버 PracticeTcpIpServer01.java와 통신하기 위한 클라이언트 프로그램이다.
	 * 			  연결하고자 하는 서버의 IP와 포트번호를 가지고 소켓을 생성하면 자동적으로 서버에 연결요청을 하게된다.
	 * 
	 * 				String serverIp = "127.0.0.1";
	 * 				Socket socket = new Socket(serverIp, 7777);
	 * 			    			
	 *				서버 프로그램이 실행되고 있지 않거나 서버의 전원이 꺼져있어서 서버와 연결을 실패하면 
	 *				connectionException이 발생한다.
	 *				서버와 연결되면 소켓의 입력스트림을 얻어서 서버가 전송한 데이터를 읽을 수 있다.
	 *
	 *				InputStream in = socket.getInputStream();
	 *				DataInputStream dis = new DataInputStream(in);
	 *				
	 *				//소켓으로부터 받은 데이터를 출력한다.
	 *				System.out.println(""서버로부터 받은 메시지"+dis.readUTF());
	 *				
					// 그리고 서버와의 작업이 끝나면 소켓과 스트림을 닫아야한다.
					 dis.close();
					 socket.close();
					 
					 지금까지 TCP/IP를 이용하는 아주 간단한 클라이언트/서버 프로그램을 
					 소개으며 중점적으로 서버와 클라이언트가 어떻게 통신하는지 이해하는것이 중요함
					 
					 위의 예제는 한대의 호스트에서 서버 프로그램과 클라이언트 프로그램을 테스트할 IP를 127.0.0.1로 설정했지만
					 원래는 서버가 실제로 사용하고 있는 IP를 지정해야한다.
					 다음 예시는 서버와 클라이언트의 연결과정을 소개한다. 
					 서버 IP : 182.168.10.100, 
					 클라이언트 IP : 192.169.10.101이라고 가정한다.
					 
					 1) 서버프로그램(PracticecTcpIpServer01)을 실행
					 	- 192.168.10.100 > PracticecTcpIpServer01.java
					 
					 2) 서버 소켓을 생성( 192.168.10.100 > PracticecTcpIpServer01.java)
					 	- serverSocket = new ServerSocket(7777);
					 	- make socket (ServerSocket, port 7777) on PracticecTcpIpServer01
					 	
					 3) 서버 소켓이 클라이언트 프로그램의 연결요청을 처리할 수 있도록 대기상태로 만든다.
					 	클라이언트 프로그램의 연결요청이 오면 새로운 소켓을 생성하여 클라이언트 프로그램의 소켓과 연결한다.
					 	- Socket socket = ServerSocket.accept(); (in PracticecTcpIpServer01)
					 	
					 4) 클라이언트 프로그램(PriceticeTcpIpClient01)에서 소켓을 생성하여 서버 소켓에 연결을 요청한다.
					 	- Socket socket = new Socket("192.168.10.100", 7777);
					 	- Socket of PriceticeTcpIpClient01.java of 192.168.10.101 -----> ServerSocket(7777) of PracticecTcpIpServer01.java of 192.168.10.100
					 	
					 5) 서버 소켓은 클라이언트 프로그램의 연결 요청을 받아 
					 	새로운 소켓을 생성하여 클라이언트 프로그램의 소켓과 연결한다.
					 	- Socket socketA = serverSocket.accept();

					 6) 서버 소켓은 클라이언트 프로그램의 연결요청을 받아 
					 	새로운 소켓을 생성하여 클라이언트 프로그램의 소켓과 연결한다.
					 	
					 		192.168.10.100														192.168.10.101
					 	PracticecTcpIpServer01												PriceticeTcpIpClient01
					 	 - serverSocket(7777)	<------------------------------------------------ socket
					 	 			↓														    ↗
					 	 - socketA (make new socket to give and take with clientSocket)--------↗
					 	 
					 	 
					 	 ex) PracticeTcpIpServer02.java
					 	 - Socket 클래스에 정의된 getPort()와 getLocalPort()를 사용해서 tcp/ip 통신에서 소켓이 사용하고 있는 포트를 알아낼 수 있다.
					 	   getPort()가 반환하는 값은 상대편 소켓(원격 소켓)이 사용하는 포트이고 
					 	   getLocalPort()가 반환하는 값은 소켓 자신이 사용하는 포트이다.
					 	   
					 	   결과에서 보면 연결을 요청한 클라이언트 프로그램의 소켓이 사용한 포트는 2839번이고(내 경우 63464) 서버 프로그램의 소켓은 7777
					 	   이를 통해서 서버소켓이 7777번 포트를 사용해도 서버 소켓이 아닌 소켓은 7777번 포트를 사용할 수 있다는 것이다.
					 	   
					 	 
					 	 ex) PracticeTcpIpServer03.java
					 	 - ServerSocket클래스의 setSoTimeout(int timeout)을 사용해서 서버소켓의 대기시간을 지정할 수 있다.
					 	   지정한 대기 시간 동안 연결 요청이 없을 경우 accept()에서 SocketTimeOutException이 발생하므로 catch를 적절히 사용해야한다.
					 	 
					 	  
					 	 ex) PracticeTcpIpServer04.java
					 	 - 여러개의 쓰레드를 생성하여 클라이언트의 요청을 동시에 처리하도록 하였다.
					 	   서버에 접속하는 클라이언트의 수가 많을 때는 쓰레드를 이용해서 클라이언트의 요청을 병렬적으로 처리하는 것이 좋다.
					 	   그렇지 않으면 서버가 접속을 요청한 순서대로 처리하기 때문에 늦게 접속을 요청한 클라이언트는 오랜 시간을 기다리게된다.
					 	   
					 	 
					 	 ex) PracticeTcpIpServer05.java
					 	 ex) PracticeTcpIpServer06.java(Multichat)
					 	 
					 	    
					 	
					 	
					 
					 
					 
					 
					 
					 
					 
					 
					 
					 
					 
					 
					 
	 *
	 * 
	 * 
	 * 
	 * 			    			
	 * 
	 *			
	 *
	 *			
	 *			
	 *
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
