 package exam01;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Sample01 {

	public static void main(String[] args) {
		/*
		 * 입출력(IO, Input/ Output)
		 * 	- 컴퓨터 내부/ 외부 장치와 데이터를 주고 받는 것
		 * 	- 프로그램을 기준으로 외부 장치에서 데이터가 현재 동작하는 프로그램으로 들어오면 입력
		 * 	 외부 장치로 데이터를 보내면 출력
		 * 
		 * 스트림(Stream) : 데이터를 계속 보내주는 것
		 * 	- 입력스트림, 출력스트림으로 나누어서 불린다. 
		 * 	- 두 장치간에 데이터를 지속적으로 입/출력하기 위해 생성된 연결 통로
		 * 	- 단방향 통신을 지원하기 때문에 입력/출력을 동시에 할 수 없다.
		 * 	  동시에 하기 위해서 입력 스트림, 출력 스트림을 개별적으로 만든다.
		 * 	- 스트림을 통해 데이터를 주고 받을 때 문자기반 스트림과 바이트 기반 스트림으로 
		 * 	  분리된다.
		 * 	- 문자기반스트림의 경우 일반 텍스트 문서를 전송하기 위해 사용한다. (한글, 워드는 일반 텍스트 문서 X)
		 *	  바이트기반 스트림, 문자기반 스트림 어떤 걸 사용해야할지 모르겠다면 아래 구별법 사용하기
		 *	  txt확장자를 사용 -> 어떤 프로그램을 연결프로그램 
		 *	  -> 메모장으로 열었는데 도대체 뭐가 있는 모르겠다 싶으면(외계어) 바이트 스트림으로 해야 함
		 * 	  (네트워크 통신 할 때 -> 바이트 기반 , 우리가 만들 때 -> 문자 기반)
		 */
		
		/*
		 * File 클래스
		 * - 파일의 크기, 이름, 정보 등을 알아내기 위한 클래스로 사용
		 * - 파일 생성, 삭제 및 폴더 생성, 삭제 기능을 제공(File 객체를 이용하면 디렉터리나 기본 파일 형태를 지원받을 수 있다.)
		 * 
		 */
		
		File f = new File("C:/Users/user1/eclipse/jee-2021-12/eclipse/eclipse.ini");		// 내가 확인하고자 하는 파일의 경로를 알 수 있는 문자열
		System.out.println("파일 크기 : " + f.length() + "byte");
		System.out.printf("파일 크기 : %.2f KByte", f.length()/ 1024.0);
		
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy년 MM월 dd일 a hh시 mm분 ss초");
		String modifiedDate = sFormat.format(new Date(f.lastModified()));
		System.out.printf("수정 날짜 : %d\n", f.lastModified());
		
		System.out.printf("실행 파일 : %s\n", f.canExecute());	// 실행 권한이 있는지
		System.out.printf("읽기 파일 : %s\n", f.canRead());	// 읽기 권한이 있는지
		System.out.printf("쓰기 파일 : %s\n", f.canWrite());	// 쓰기 권한이 있는지 확인
		
		System.out.printf("파일 : %s\n", f.isFile());
		System.out.printf("폴더 : %s\n", f.isDirectory());
		System.out.printf("숨김 : %s\n", f.isHidden());
		
		System.out.printf("파일/폴더명 : %s\n", f.getName());
		System.out.printf("상위 폴더명 : %s\n", f.getParent());
		System.out.printf("전체 경로명 : %s\n", f.getPath());
		
		System.out.printf("실제 파일/폴더 존재 유무 : %s\n", f.exists());
		
		
		// 파일 생성
		File f2 = new File("C:/Users/user1/eclipse/jee-2021-12/eclipse/my_file.txt");
		try {
			f2.createNewFile();		// 파일 만들 때 사용 되는 메소드
		} catch (IOException e) {
			System.out.println("f2.createNewFile()로 파일 생성 중 에러 발생!");
			e.printStackTrace();
		}
		
		
		File f3 = new File("C:/Users/user1/eclipse/jee-2021-12/eclipse/my_forder");
		f3.mkdir();				
		
		
		// 파일 삭제
		File f4 = new File("C:/Users/user1/eclipse/jee-2021-12/eclipse/my_file.txt");
		f4.delete();
		
		
		File f5 = new File("C:/Users/user1/eclipse/jee-2021-12/eclipse/");
		// 문자열 배열에 담은 경우	(타입이 다름)	-> 얘는 파일명 출력
		String[] fileList1 = f5.list();
		System.out.println(Arrays.toString(fileList1));
		
		// 파일 배열에 담은 경우 (타입이 다름) -> 얘는 디렉터리 경로까지 출력해줌	
		// 파일 객체 파일들을 가지고 일괄 작업을 할 수 있다.
		File[] fileList2 = f5.listFiles();
		System.out.println(Arrays.toString(fileList2));
		
		for(int i = 0; i < fileList2.length; i++) {
			String name = fileList2[i].getName();
			if(name.startsWith("my_")) {		// 해당 문자열로 시작하는 것을 찾는 것
				fileList2[i].delete();
			}
		}
		
		
		
		
		
		
		
		

	}

}
