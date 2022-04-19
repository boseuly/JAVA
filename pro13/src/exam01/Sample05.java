package exam01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Sample05 {

	public static void main(String[] args) {
		/*
		 * FileWriter
		 * - 문자 단위로 파일에 데이터를 쓰기 위해 사용
		 * - 보통 텍스트 파일에 데이터를 쓰기 위해 사용
		 * - byte랑 비슷함. 차이는 문자로만 바꿔주면 됨
		 */
		
		// 파일 쓰기 작업 
		File f = new File("C:/Users/user1/eclipse/jee-2021-12/eclipse/read_test.txt");
		
		try (FileWriter fos = new FileWriter(f, true)){	// try catch 문법 중 try with resource구문에 해당 -> 알아서 close처리 -> 원래는 Sample02번처럼 close를 해줘야함 
			fos.write(65);		// 아스키코드로 파일에 저장된다. -> A가 저장됨		// true를 안 써주면 덮어쓰기가 된다. 이어쓰기를 원하면 true를 꼭 써주기
			
			char[] cArr = new char[] {66, 67, 68, 69};		// 방법 1) byte배열을 만들어서 출력할 수도 있음
			fos.write(cArr);
			
			fos.write("\nFileOutputStream\n");	// 방법 2) 좀더 쉽게 ""안에 내용을 파일에 저장할 수도 있음
			fos.write("한글로도 쓸 수 있음");	
			
			fos.flush();					// flush() : 파일을 입출력할 때는 buffer라는 공간을 지나는데 여기서 빠짐없이 다 내보내라는 의미
		} catch (FileNotFoundException e) {
			System.out.println("쓰기 작업을 위한 파일을 찾을 수 없습니다.");
			e.printStackTrace();
		}catch(IOException e) {				// 만약 읽는 과정에 문제가 발생한다면 처리할 구문
			System.out.println("파일을 읽는 과정에 문제가 발생하였습니다.");
			e.printStackTrace();
		}

	}

}
