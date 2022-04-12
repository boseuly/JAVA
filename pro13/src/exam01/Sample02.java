package exam01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Sample02 {

	public static void main(String[] args) {
		/* 파일을 읽는 작업
		   FileInputStream 클래스
		   - 바이트 기반 스트림으로 바이트 단위로 파일을 읽는다.
		   - 미디어, 이미지, 텍스트 파일 등 모든 종류의 파일 읽기 가능
		*/
		File f = new File("C:/Users/user1/eclipse/jee-2021-12/eclipse/read_test.txt");
		StringBuilder sb = new StringBuilder();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(f);
			/* 영문으로 읽을 때에는 아래의 방식으로도 충분 (하지만 별로 안 좋은 방식)
			while(true) {
				int i = fis.read();		// -1이 나오면 더이상 읽을 게 없어서 중단 된다. (byte기반으로 읽고 있음)
				if(i == -1) {			// 따라서 i가 -1이 되면 break;
					break;
				}
				sb.append((char)i);	// byte 단위로 읽은 것을 char형으로 형변환하여 문자열 추가	// 1byte를 일고, 1byte char로 변환하는 거기 때문에 영문자만 가능
			}
			*/
			byte[] buffer = new byte[4];	 // 버퍼 크기만큼 읽는다.
			byte[] readBytes = new byte[0];	 // 읽고 나서 모아둘 배열이 또 필요함
			
			while(true) {
				int i = fis.read(buffer);		//  byte 배열이 제공이 된다. -> 이를 모아서 문자열로 만드는 것 // 버퍼 크기만큼 byte를 읽어서 i에 저장(4개를 읽으면 i = 4, 2개를 읽으면 i = 2)
				if(i == -1) {					// 더이상 읽을 게 없으면 -1
					break;
				}
				
				int endIndex = readBytes.length;							
				readBytes = Arrays.copyOf(readBytes, readBytes.length + i);	// 원래 0바이트인데 읽을 때마다 4바이트씩 늘리는 중	// i가 아니라 buffer.length 라고 하면 불필요한 문자가 추가적으로 출력되기 때문에 i(읽어진 byte)라고 해줘야 정확해진다.
				System.arraycopy(buffer, 0, readBytes, endIndex, i);		// buffer에 저장된 값을 4byte씩 읽어서 readBytes에 저장		// System.arraycopy는 길이를 늘리진 못하나봄
			}																// endIndex ->  
			sb.append(new String(readBytes));		// 콘솔창에서 우리가 확인을 하기위해 쓴 거지 필요 없음
			System.out.println(sb.toString());
			
			fis.close();							// 파일 입출력을 할 때는 반드시 close() 해주기  -> 우리가 창을 열면 닫아줘야 하듯 이것도 닫아줘야 한다.
		} catch (FileNotFoundException e) {			// 파일을 찾지 못 했을 경우 어떻게 해결할지	
			System.out.println("FileInputStream 클래스로 읽을 파일을 찾지 못했습니다.");
			e.printStackTrace();
		} catch (IOException e) {					// 이건 fis.read()에서 나온 에러
			System.out.println("FileInputStream 클래스로 읽을 파일을 찾지 못했습니다.");
			e.printStackTrace();
		}finally {
			try {
				fis.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}

}
