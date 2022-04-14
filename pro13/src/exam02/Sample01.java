package exam02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public class Sample01 {

	public static void main(String[] args) {
		/*
		 * 프로그램에서 사용한 데이터를 파일로 쓰거나 읽기 
		 */
		
		// 정수 배열에 저장된 1 ~ 100까지의 임의의 정수를 파일로 저장 
		Random rand = new Random();
		
		int[] iArr = new int[6];
		for(int i = 0; i < iArr.length; i++) {
			iArr[i] = rand.nextInt(100) + 1;
		}
		System.out.println(Arrays.toString(iArr));
		
		// 문자기반으로 쓰기 작업해보기(FileWriter 사용)
		File f = new File("C:/Users/user1/eclipse/jee-2021-12/eclipse/read_test.txt"); 
		
		try (FileWriter fw = new FileWriter(f)){
			
//			fw.write(Arrays.toString(iArr));			// 방법1) iArr배열을 String으로 바꿔서 내보내기 -> 문제: [1, 2, 3 ..]이렇게 배열형식으로 저장됨
			for(int i = 0; i < iArr.length; i++) {		// 방법2) for문을 이용해서 숫자를 
//				fw.write(iArr[i]); -> 이렇게 정수로 그냥 출력하면 문자가 깨진다.
				fw.write(Integer.valueOf(iArr[i]).toString() + " "); 	// Wrapper 클래스로 래핑 한 뒤 -> 이렇게 문자로 변환을 시켜줘야 한다.
			}
			fw.flush();
			
		}catch(FileNotFoundException e) {	
			System.out.println("쓰기 작업을 위한 파일을 찾을 수 없습니다.");
		} catch (IOException e) {
			System.out.println("파일을 읽는 도중 오류가 발생하였습니다.");
			e.printStackTrace();
		}
		
		// 읽어오기
		StringBuilder sb = new StringBuilder();
		try (FileReader fr = new FileReader(f)){
			char[] buffer = new char[4];	 // 버퍼 크기만큼 읽는다.
			char[] readChars = new char[0];	 // 읽고 나서 모아둘 배열이 또 필요함
			
			while(true) {
				int i = fr.read(buffer);		//  byte 배열이 제공이 된다. -> 이를 모아서 문자열로 만드는 것 // 버퍼 크기만큼 byte를 읽어서 i에 저장(4개를 읽으면 i = 4, 2개를 읽으면 i = 2)
				
				if(i == -1) {					// 더이상 읽을 게 없으면 -1
					break;
				}
				
				int endIndex = readChars.length;							
				readChars = Arrays.copyOf(readChars, readChars.length + i);	// 원래 0바이트인데 읽을 때마다 4바이트씩 늘리는 중	// i가 아니라 buffer.length 라고 하면 불필요한 문자가 추가적으로 출력되기 때문에 i(읽어진 byte)라고 해줘야 정확해진다.
				System.arraycopy(buffer, 0, readChars, endIndex, i);		// buffer에 저장된 값을 4byte씩 읽어서 readBytes에 저장		// System.arraycopy는 길이를 늘리진 못하나봄
			}																// endIndex ->  
			sb.append(new String(readChars));		// 콘솔창에서 우리가 확인을 하기위해 쓴 거지 필요 없음
			
			StringTokenizer st = new StringTokenizer(sb.toString(), " ");
			int nArr[] = new int[st.countTokens()];
			int i = 0;
			while(st.hasMoreTokens()) {
				String s = st.nextToken();
				nArr[i++] = Integer.parseInt(s);
			}
			System.out.println(Arrays.toString(nArr));
			fr.close();							// 파일 입출력을 할 때는 반드시 close() 해주기  -> 우리가 창을 열면 닫아줘야 하듯 이것도 닫아줘야 한다.
		} catch (FileNotFoundException e) {			// 파일을 찾지 못 했을 경우 어떻게 해결할지	
			System.out.println("FileInputStream 클래스로 읽을 파일을 찾지 못했습니다.");
			e.printStackTrace();
		} catch (IOException e) {					// 이건 fis.read()에서 나온 에러
			System.out.println("FileInputStream 클래스로 읽을 파일을 찾지 못했습니다.");
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
