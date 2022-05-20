package exam02.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	public static void main(String[] args) {
		
		try {
			/*
			 * 1. 서버용 소켓 생성
			 */
			
			ServerSocket sSock = new ServerSocket();
			
			/*
			 * 2. 생성한 소켓에 서버 IP주소, 서버 Port번호 바인딩(연결)
)			 */
			byte[] addr = new byte[] {(byte)192, (byte)168, (byte)219,107};
			InetAddress serverIP = InetAddress.getByAddress(addr);	// 서버 IP주소
			int serverPort = 51100;									// 서버 포트
			
			InetSocketAddress serverIpPort = new InetSocketAddress(serverIP, serverPort);
			sSock.bind(serverIpPort);			// 서버ip에 포트를 연결시킨 것
			
			/*
			 * 3. 클라이언트 연결 요청을 대기 후 요청이 오면 accept해서 클라이언트 소켓 생성 
			 */
			Socket cSock = sSock.accept();		// cSock : 클라이언트 소켓 , accept를 해야지만 만들어진다.
			
			InetAddress clientIP = cSock.getInetAddress();	// 클라이언트가 연결 요청을하면 accept해서 클라이언트 소켓이 랜덤으로 만들어지고 클라이언트의 주소를 저장
			int clientPort = cSock.getPort();				// 클라이언트 포트를 저장
			int connectionPort = cSock.getLocalPort();		// 자기자신에 대한 포트 저장
			
			System.out.printf("%s:%d <----> %s:%d\n", serverIP.getHostAddress(),
					connectionPort, clientIP.getHostAddress(), clientPort);
			
			/*
			 * 4. 통신용 입출력 스트림 생성 
			 */							// 버퍼(병목현상 ↓)		// ← 문자로 변환 			// ← 바이트
			BufferedReader sockIn = new BufferedReader(new InputStreamReader(cSock.getInputStream()));	// Socket에 스트림이 존재한다. 
										// 성능향상보조스트림		// 문자변한보조스트림			// 바이트기반스트림
			BufferedWriter sockOut = new BufferedWriter(new OutputStreamWriter(cSock.getOutputStream()));	
			
			/*
			 * 5. 지속적인 통신을 위한 반복문
			 */
			boolean disconnect = false;
			while(true) {
				// 클라이언트로부터 수신한 메시지가 있으면 반복 진행
				while(sockIn.ready()) {
					String line = sockIn.readLine();
					
					if(line.contains("exit")) {		// 원래는 클라이언트에서 서버를 끝내면 안 된다. 그냥 해본 거임.
						disconnect = true;
						break;
					}
					System.out.println(line);
				}
				if(disconnect) {
					break;
				}
			}
			
			/*
			 * 6. 통신 종료 후에는 모든 자원 반납 (지금 우리는 pc가 하나라서 굳이 close 안해줘도 되지만 원래는 해야하는 단계임)
			 */
			sockIn.close();
			sockOut.close();
			cSock.close();
			sSock.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
