package game.db;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import game.player.UserPlayer;

public class Database {
	private UserPlayer upl = new UserPlayer();
	private File file;
	
	public Database() {
		this.file = new File("C:/Users/user1/gbo_game.record");	// 이걸 실행하면 여기에 이 파일이 생성된다.
		
	}
	
	public Database(File file) {	// 파일을 바꾸고 싶을 때 사용
		this.file = file;
	}
	
	// 분명 그냥 FileReader로 했을 때는 파일에서 문자가 제대로 저장됐는데 BufferedInputStream하니까 외계어임
	public int[] load() {		// 이전 정보를 가져와서 int배열로 정보를 전달함
		// 데이터 불러오기 (Input, Read)
		int[] nArr = new int[3];
		if(file.exists()) {		// 파일이 있는지 확인을 먼저 하고 파일을 불러온다. -> FileNotFoundException 에러를 막는법
			
//			try(BufferedReader br = new BufferedReader(new FileReader(this.file)))	{
//				String data = "";
//				while(br.ready()) {
//					data = br.readLine();
//				}
//				StringTokenizer st = new StringTokenizer(data, " ");
//				nArr = new int[st.countTokens()];
//				int i = 0;
//				while(st.hasMoreTokens()) {
//					String s = st.nextToken();
//					nArr[i++] = Integer.parseInt(s);		// parseInt -> 문자열의 인자값을 받으면 해당 값을 10진수 Integer형으로 바꿔준다.
//				}
			
			try(DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)))){
				int i = 0;
				while(dis.available() > 0) {	// 읽을 수 있는 바이트 수 반환
					nArr[i++] = dis.readInt();
				}
			
				
			} catch (FileNotFoundException e) {	// 파일이 없는 상태에서 불러오면 이 에러가 난다.
				System.out.println("FileReader 클래스로 읽을 파일을 찾지 못했습니다.");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("파일을 읽는 과정에 문제가 발생하였습니다.");
				e.printStackTrace();
			}
		}
		
		return nArr;
	}
	
	public void save(int[] record) {		// 전적 정보는 int형배열로 저장
		// 데이터 저장하기 (Output, Writer), 내보내기
		
		// 방법1) BufferedWriter 사용해서 파일 내보내기 한 거 
//		try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.file))){	// try catch 문법 중 try with resource구문에 해당 -> 알아서 close처리 -> 원래는 Sample02번처럼 close를 해줘야함 
//			for(int i = 0; i < record.length; i++) {
//				bw.write(Integer.valueOf(record[i]).toString() + " ");
//			}
//			bw.flush();
//			
//			
		// 방법2) 이건 DataOutputStream 이용해서 파일 내보내기 한 거 -> 내가 파일을 확인하지 않아도 된다고 하면 이거 사용하는 게 편함 
		try(DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))){
			
			for(int i = 0; i < record.length; i++) {
				dos.writeInt(record[i]);
			}
			
			
			
		} catch (FileNotFoundException e) {
			System.out.println("쓰기 작업을 위한 파일을 찾을 수 없습니다.");
			e.printStackTrace();
		}catch(IOException e) {				// 만약 읽는 과정에 문제가 발생한다면 처리할 구문
			System.out.println("쓰기 작업 과정에 문제가 발생하였습니다.");
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
}
