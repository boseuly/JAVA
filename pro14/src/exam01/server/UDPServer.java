package exam01.server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UDPServer {
	public static void main(String[] args) {
		try {
			/*
			 * 1. 네트워크 통신을 위한 IP 주소 정보 설정
			 */
			
			byte[] ipAddr = new byte[] {(byte)192, (byte)168, (byte) 219, 107};		// 서버측은 내가 사용할 주소를 지정해서 정해주는 게 낫다.
			InetAddress iNet = InetAddress.getByAddress(ipAddr);
			
			/*
			 * 2. UDP 통신용 소켓 생성
			 */
			DatagramSocket dSock = new DatagramSocket(5110, iNet);	// 5110 서버 포트(만약 실행 오류 나면 클라이언트에 적어놓은 서버 포트와 다르거나, 이미 누가 5110번 포트를 실행하고 있거나)
			
			while(true) {	// 통신을 끊기 전까지는 계속 연결할 거니까 while문 사용 
				
				/*
				 * 3. 클라이언트가 보낸 메시지를 수신 대기함 		  
				 */
				byte[] buff = new byte[1024];		// 1024 바이트 배열을 만든다.
				DatagramPacket dPack = new DatagramPacket(buff, buff.length);	// 데이터그램 패킷을 미리 만들어 놓고 클라이언트가 보낸 데이터를 받는다.
				dSock.receive(dPack);				// 메시지를 받는다.	-> dPack안에 메시지가 들어감
				
				SimpleDateFormat sFormat = new SimpleDateFormat("[yyyy.MM.dd a hh:mm:ss.sss]");
				String time = sFormat.format(new Date());
				
				// 언제 누가 접속했는지 알려주기
				InetAddress clientIP = dPack.getAddress();
				int clientPort = dPack.getPort();
				System.out.printf("%s - %s:%d 에서 접속했습니다.\n", time, clientIP, clientPort);
				
				try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File(System.getProperty("user.home") + "/connection.log"), true))){
					bw.write(time + "/n");
					bw.flush();
				}catch(IOException e	) {
					e.printStackTrace();
				}
				
				/*
				 * 4. 수신한 메시지(바이트)를 문자열로 변환
				 */
				String rev = new String(dPack.getData(), 0, dPack.getData().length);
				System.out.println("Client 메시지를 수신하였습니다.");
				System.out.println("Client Message -> " + rev);
				
				/*
				 * 5. 응답을 위한 데이터 그램 생성
				 */
				
				String msg = "수신함";					// dPack은 클라이언트에서 들어온 메시지 그곳에 클라이언트 정보도 있음
				clientIP = dPack.getAddress();			// 누가보냈는지,
				clientPort = dPack.getPort();			// 어떤 포트로 보냈는지 정보 저장
				DatagramPacket respPack = new DatagramPacket(msg.getBytes(), msg.getBytes().length, clientIP, clientPort);	// 그리고 그 주소와 포트에 데이터 전송
				dSock.send(respPack);
				
				// 위에 거랑 이거랑 무슨 차이가 있는지 분석하기
//				InetAddress clientIP = dPack.getAddress();	// 그곳에 있는 클라이언트의 정보를 가져오는 것, 데이터 패킷에 들어있는 정보주소를 가져온다.
//				int clientPort = dPack.getPort();
//				DatagramPacket respPack = new DatagramPacket(msg.getBytes(), msg.getBytes().length, clientIP, clientPort);
//				DatagramPacket respPack = new DatagramPacket(msg.getBytes(), msg.getBytes().length, serverINet, 5110);	// 5110 서버 포트
//				dSock.send(respPack);
				
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
