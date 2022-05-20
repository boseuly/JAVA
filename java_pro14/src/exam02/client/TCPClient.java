package exam02.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		
		try {
			/*
			 * 1. 클라이언트용 소켓 생성
			 */
			byte[] addr = new byte[] {(byte)192,(byte)168,(byte)219,107};
			InetAddress serverIP = InetAddress.getByAddress(addr);
			int serverPort = 51100;			// 클라이언트 포트는 랜덤이기 때문에 따로 만들어주지 않음(서버 포트는 지정해야 됨)
				
			Socket sock = new Socket(serverIP, serverPort);		// 여기에 서버 주소가 들어간다.
			
			/*
			 * 2. 통신용 입출력 스트림 생성 
			 */							// 버퍼(병목현상 ↓)		// ← 문자로 변환 			// ← 바이트
			BufferedReader sockIn = new BufferedReader(new InputStreamReader(sock.getInputStream()));	// Socket에 스트림이 존재한다.  
										// 성능향상보조스트림		// 문자변한보조스트림			// 바이트기반스트림
			BufferedWriter sockOut = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
										// 버퍼 →  			// 문자를 바이트로 변환 → 				// 바이트 전달
			
			while(true) {
				// 서버로부터 들어온 메시지가 있는지 확인 -> 있으면 반복 진행 (읽기 위한 반복문)
				while(sockIn.ready()) {	
					String line = sockIn.readLine();	// 줄단위로 읽음
					System.out.println(line);
				}
				
				
				// 서버에 메시지 전송
				System.out.print("cline : ");
				String msg = sc.nextLine();
				sockOut.write(msg);
				sockOut.newLine(); // 개행대신 써준 거 -> 이거 빠트리면 에러남(파일을 못 읽어서) Why? 위에 한 줄씩 읽는다고 했기 때문에
				sockOut.flush();
				
				
			}
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
}
