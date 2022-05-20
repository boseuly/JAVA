package exam01.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class UDPClient {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		try {
//			byte[] ipAddr = new byte[] {(byte)192, (byte)168, (byte)219, 107};	// 특정 주소를 직접 지정해주는 방법
//			InetAddress iNet = InetAddress.getByName("localhost");				// 얘는 pc내부용에서 사용하기 위한 주소
//			InetAddress iNet = InetAddress.getByAddress(ipAddr);		
			
			/*
			 * 1. 네트워크 통신을 위한 IP 주소 정보 설정
			 */
			InetAddress iNet = InetAddress.getLocalHost();	// pc A랑 pc B가 연결되어 통신하기 위해서는 getLocalHost()를 사용해야 됨. 얘는 외부용
			InetAddress serverINet = iNet;					// 서버 주소(같은 pc라서 주소가 같을 수밖에 없음)

			/*
			 * 2. UDP 통신용 소켓 생성
			 */
			DatagramSocket dSock = new DatagramSocket(5010, iNet);		// 5010은 클라이언트 포트
			
			/*
			 * ** 3번 4번 순서가 바뀌면 클라이언트가 된다.	 클라이언트 측이 먼저 메시지를 보내고 서버가 보낸다.
			 * 3. 사용자가 입력한 메시지를 데이터그램 패킷으로 만들어서 전송
			 */
			while(true) {
				System.out.print("Client : ");
				String msg = sc.nextLine();		// 문자열로 입력한 것을 바이트로 변환 하고 , 길이 만큼의 값을 넣고
				
				DatagramPacket dPack = new DatagramPacket(msg.getBytes(), msg.getBytes().length, serverINet,5110);	// 5110 서버 포트
				dSock.send(dPack);
				
				/*
				 * 4. 서버로부터 데이터 수신확인 응답을 받기 위한 부분(꼭 들어가야 하는 건 아니지만 주고 받는 걸 보기 위해 써둔 거임-원래 udp는 소통 안 함)
				 */
				
				byte[] buff = new byte[1024];	// 1024 바이트 배열을 만든다.
				DatagramPacket resPack = new DatagramPacket(buff, buff.length);	// 데이터그램 패킷을 미리 만들어 놓고 클라이언트가 보낸 데이터를 받는다.
				dSock.receive(resPack);
				
				InetAddress clientIP = dPack.getAddress();		//송신측 IP
				int clientPort = dPack.getPort();				// 송신축 Port
				
				String rev = new String(resPack.getData(), 0, resPack.getData().length);
				System.out.println("Server Message -> " + rev);
				
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
}
