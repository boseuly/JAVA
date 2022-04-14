package exam03;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;

public class Sample01 {

	public static void main(String[] args) {
		/*
		 * 보조 스트림
		 * - 입출력에 사용하는 기반 스트림을 보조하는 역할을 수행한다.
		 * - 기반 스트림의 기능을 높이거나 추가 기능을 부여하기 위해 사용
		 * - 보조 스트림은 반드시 기반 스트림을 사용해야 쓸 수 있다.(보조하는 역할이기 때문에 단독으로 사용 불가)
		 * 
		 * - 문자 변환 보조 스트림(InputStreamReader, OutStreamWriter)
		 * 		(FileOutputStream, FileInputStream과 같은)바이트 기반 스트림 + 문자 변환 보조 스트림 사용 : 바이트 기반 스트림을 문자로 변환할 수 있도록 도와주는 스트림 
		 * 		-> 네트워크 통신은 바이트기반으로 이뤄지는데 사용자가 보기 위해서는 문자 변환 보조 스트림을 사용해야 함
		 * 
		 * - 성능 향상 보조 스트림(BufferedInputStrream, BufferedOutputStream, BufferedReader, BufferedWriter)
		 * 		바이트 기반 스트림 + 성능 향상 보조 스트림(BufferedInputStream, BufferedOutputStream)
		 * 		문자 기반 스트림 + 성능 향상 보조 스트림(BufferedReader, BufferedWriter)
		 * 
		 * - 기본 데이터 타입 보조 스트림(DataInputStream, DataOutputStream)
		 * 		바이트 기반 스트림 + 기본 데이터 타입(정수, 실수, boolean형 등) 보조 스트림 : 기본 데이터 타입을 입력/출력할 수 있도록 도와주는 스트림
		 * 		굳이 파일을 열어서 사용자가 확인할 필요가 없다면 편하게 dataInputStream을 사용하는 게 현함
		 * 
		 * - 객체 보조 스트림(ObjectInputSteam, ObjectOutputStream)
		 * 		바이트 기반 스트림 + 객체 보조 스트림
		 * 
		 */
		
		Sample01 smp = new Sample01();
//		smp.ex01();
//		smp.ex02();
//		smp.ex03();
//		smp.ex04();
		smp.ex05();
		

	}
	
	public void ex05() {	// 객체 자체를 사용하려고 할 때 
		/* 객체 보조(바이트 기반 스트림과 사용 됨)
		 * 주의 : 객체를 바이트 데이터로 변환 했을 때의 객체 구조가 바이트 데이터를 객체로 변환할 때의
		 * 		객체 구조와 동일 해야 한다. (객체 구조가 바뀌면 변환에 실패한다.) 갑자기 객체가 변하거나 수정되면 안 된다.
		 */
		
		// 쓰기(내보내기_output)	// 문자를 바이트 기반 스트림으로 내보낸다. 그래서 파일을 보면 외계어임. 
		String userHome = System.getProperty("user.home");
		File f = new File(userHome + "/객체보조스트림.테스트");
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))){
			ObjSample os = new ObjSample(123, 12.34, false, "객체를 파일로 저장");	// 객체가 이 구조와 같아야 한다. 클래스 멤버변수의 이름이나 위치 등이 변하면 안 됨
			oos.writeObject(os);			// 객체를 읽어서 파일에 저장하는 것	// 이 과정에서 직렬화가 일어남
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		// 읽기(가져오기_input)	// 바이트 기반 파일을 문자로 가져온다.
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))){	
			Object obj = ois.readObject();
			ObjSample os = (ObjSample)obj;	// 객체 자체를 파일로 쓰고 파일을 불러오고 할 수 있다.
			System.out.println(os);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
			
	}
	
	
	
	public void ex04() {
		// 기본 데이터 타입 보조		// 얘는 바이트 기반 스트림이랑 쓰임
		String userHome = System.getProperty("user.home");
		File f = new File(userHome + "/기본데이터타입보조스트림.테스트");
		
		try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(f))){
			dos.writeBoolean(false);
			dos.writeInt(100);
			dos.writeDouble(12.34);

			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		
		// 읽어오기
		try(DataInputStream dis = new DataInputStream(new FileInputStream(f))){
			boolean b1 = dis.readBoolean();
			int i1 = dis.readInt();
			double d1 = dis.readDouble();
			System.out.println(b1);
			System.out.println(i1);
			System.out.println(d1);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	
	public void ex03() {
		// 성능 향상 보조 스트림	// 쓰기(내보내기_output)
		String userHome = System.getProperty("user.home");
		File f = new File(userHome + "/성능향상보조스트림.테스트");
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
			bw.write("문자 기반 스트림 + 성능 향상 보조 스트림");		// 문자 기반 스트림이기 때문에 바이트로 굳이 바꿀 필요가 없음	
			bw.newLine();	// buffereWriter에서 지원하는 개행 메소드
			
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e	) {
			e.printStackTrace();
		}
		
		// 읽어오기(불러오기_input)
		try(BufferedReader br = new BufferedReader(new FileReader(f))){
			
			while(br.ready()) {		// 만약 읽을 파일이 존재한다면
				System.out.println(br.readLine());	// BufferedReader에서 readLine() 사용 가능 -> 한 줄씩 읽겠다.	
			}
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e	) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public void ex02() {
		// 성능 향상 보조 스트림	// 쓰기(내보내기_output)
		String userHome = System.getProperty("user.home");
		File f = new File(userHome + "/성능향상보조스트림.테스트");
		
		try(BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f))) {
			bos.write("바이트 기반 스트림 + 성능 향상 보조 스트림".getBytes());		// byte배열을 가져와라
			
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e	) {
			e.printStackTrace();
		}
		
		// 읽어오기(불러오기_input)
		try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f))){
			StringBuilder sb = new StringBuilder();
			
			while(true) {
				// BufferedInputStream에서 지원하는 available()을 사용하기 위해 readAllBytes()가 아닌 이 방법을 이용함
				int size = bis.available();	// buffer의 읽을 수 있는 양 만큼
				if(size == 0) {				// 버퍼에 채워져있는 데이터가 0이다 -> break;
					break;
				}
				byte[] bytes = new byte[size];
				bis.read(bytes,0,size);		// bytes를 size만큼 읽어라
				sb.append(new String(bytes));
			}
			System.out.println(sb);
			
			
		// 얘는 FileInputStream에서도 제공해주는 거라서 일부러 위에 방법으로 함 (다양한 방법을 써보기 위해)
//			byte[] bytes = bis.readAllBytes();		// 모든 바이트 읽어서 바이트 배열에 저장하고
//			String data = new String(bytes);		// 바이트를 문자열로 저장한 다음에 data에 저장
//			System.out.println(data);
			
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e	) {
			e.printStackTrace();
		}
		
		
		// 바이트 기반 스트림 + 성능 향상 보조 스트림 + 문자 변환 보조 스트림
		
		// OutputStreamWriter의 생성자는 OutputStream을 받는다. BufferedOutputStream은 outputStream계열이기 때문에 생성자 안에 넣을 수 있음
		try(OutputStreamWriter osw = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(f)))) {	// 이거 API에서 생성자의 매개변수, 상속관계 잘 따져보기
			osw.write("바이트 기반 스트림 + 성능 향상 보조 스트림 + 문자 변환 보조 스트림");		
			
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e	) {
			e.printStackTrace();
		}
		
		// 읽어오기(불러오기_input)
		try(InputStreamReader isr = new InputStreamReader(new BufferedInputStream(new FileInputStream(f)))){
			char[] buff = new char[1024];
			StringBuilder sb = new StringBuilder();
			
			while(isr.ready()) {
				int size = isr.read(buff);
				sb.append(buff,0,size);
			}
			System.out.println(sb.toString());
			
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e	) {
			e.printStackTrace();
		}
	}
	
	

	public void ex01() {
		// 문자 변환 보조 스트림	// 문자를 바이트로 변환하고 바이트를 문자로 변환하는 복잡한 과정을 생략할 수 있다.
		String userHome = System.getProperty("user.home");
		File f = new File(userHome + "/문자보조스트림.테스트");
		
		try(OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(f))){	// try 리드 리소스를 통해서 close를 시키기 때문에 따로 close 쓸 필요 없음
			osw.write("바이트 기반 스트림 + 문자 보조 스트림");	
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e	) {
			e.printStackTrace();
		}
		
		try(InputStreamReader isr = new InputStreamReader(new FileInputStream(f))){
			char[] buff = new char[1024];
			StringBuilder sb = new StringBuilder();
			while(isr.ready()) {	// 읽을 준비가 됐는지 확인
				int size = isr.read(buff);
				sb.append(buff,0,size);
			}
			System.out.println(sb.toString());
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e	) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
}
