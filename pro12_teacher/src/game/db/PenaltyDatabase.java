package game.db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PenaltyDatabase {
	// 여기서부터가 파일 입출력 , 파일 열어주고 거기에 사용자가 입력한 내용을 저장해주고, 사용자에게 내보내는 것
	private String userHome;
	private File file;
	private String[] penaltys;	// penaltys는 어차피 다른 메소드들에서도 다 사용이 되니까 멤버변수로 빼버림	// 벌칙 내용
	// 입출력이 너무 잦음 -> 수정해주기
	
	
	// 생성자
	public PenaltyDatabase() {	
		userHome = System.getProperty("user.home");	// 알아서 주소 찾아주는 메소드(지금은 홈주소 찾는 거임)
		this.file = new File(userHome + "/up_down.penalty");		// 파일 생성 
		this._parser(this._load());					// 애초에 생성자를 만들 때 불러오는 작업
	}
	
	// 파일에 벌칙 추가
	public void add(String data) {
		this.penaltys = Arrays.copyOf(this.penaltys, this.penaltys.length + 1);	// 동적배열 -> 길이 늘려서 벌칙 추가하기
		
		try(FileWriter fw = new FileWriter(file, true);) {		// file이 존재한다면
			this.penaltys[this.penaltys.length - 1] = data;		// 추가할 문자열을 마지막 인덱스에 저장을 하는 것
			fw.write(data + "\n");		// 직접 파일에 쓰는 것뿐만 아니라 배열에도 같이 추가를 해줘야 제대로 추가 됨
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//getList는 그냥 벌칙 문자열 배열만 전달해준다.
	public String[] getList() {		// List를 가져와야 하니까 문자열 배열 반환타입 필요
		// 출력이 아닌 입력이 필요함 -> 외부장치에서 우리 프로그램으로 들어와야 함
		return this.penaltys.clone();		// 그냥 this.penaltys 라고 하면 SettingMenu에서 접근해서 penaltys 값을 변경하면 값이 값이 바뀌는데 
											// this.penalty.clone() 이라고 하면 복사된 주소값을 전달하는 거라서 SettingMenu에서 접근해도 penaltys 의 값에는 영향을 안 미친다.
											// 외부에서 penaltys의 값을 맘대로 바꾸면 안 되기 때문에 복사를 해서 안전하게 전달을 하는 거다. 
	}

	public void modify(int index, String penalty) {
		this.penaltys[index] = penalty;		// 해당 인덱스의 문자열을 수정 해주면 된다.
		
		this._save();  						// 수정하고 나서 저장을해라 -> 즉, 쓰기 작업(출력)
		
	}
	
	public void remove(int index) {
		
		String[] temp = new String[this.penaltys.length - 1];	// 한 개를 지워야 하기 때문에 기존의 길이보다 -1인 String[] 변수 temp생성
		// 여기 반복문 사용하면 더 쉽게 할 수 있음(깊은 복사 부분)
		System.arraycopy(this.penaltys, 0, temp, 0, index);		// temp에 penaltys를 깊은 복사해준다.
		System.arraycopy(this.penaltys, index + 1, temp, index, penaltys.length - (index + 1));	// 
		this.penaltys = temp;
		
		this._save();
		
	}
	
//	 내가 원하는 것으로 래핑 작업을 해주는 것
	private void _parser(String data) {		// data : 벌칙
		StringTokenizer st = new StringTokenizer(data, "\n");		// 줄바꿈을 기준으로 문자열을 쪼갬	// 이전에 new String(data, "\n") -> 이건 문자배열(char[])을 쪼개서 문자열에 넣기 위함이었음
		String[] res = new String[st.countTokens()];				// StringTokenizer은 문자열만 받음(문자배열 X)
		int idx = 0;
		while(st.hasMoreTokens()) {
			res[idx++] = st.nextToken();
		}
		this.penaltys = res.clone();		// 이 결과를 penaltys에 (clone으로)복사
	}
	
	
	
	
	// 불러 오는 작업을 메소드로 만듦(input)
	private String _load() {	// 바이트 기반 스트림 + 문자 변환 보조 스트림 + 성능 향상 보조 스트림 
		
		String res = null;
//		try(DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)))){		// 파일을 읽고
//			int i = 0;
//			byte[] bArr = null;	// 문자열 배열을 만들어서 넣어줘야 한다. 반환형이 문자열이니까
//			
//			while(dis.available() > 0) {	// inputstream에 읽을 수 있는 게 있다면 
//				bArr = dis.readAllBytes();	// 모든 바이트를 가져와라 -> 바이트 배열에 저장
//				res = new String(bArr);		// 정보를 가지고 있는 바이트 배열을 다시 String에 넣기
//			}
		
		// BufferedReader 사용한 경우
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			String data = "";
			while(br.ready()) {
				data = br.readLine();	// 한줄씩 읽어서 data에 저장
			}
			
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	
	
	// modify랑 remove에 공통적으로 들어가는 로직 빼둠
	private void _save() {	
		// 그냥 FileWriter만 사용한 경우
//		try (FileWriter fw = new FileWriter(file)){
//			for(int i = 0; i < this.penaltys.length; i++) {
//				fw.write(this.penaltys[i] + "\n");
//			}
//			fw.flush();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		// DataOutputStream으로 사용한 경우 
//		try(DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))){
//			for(int i = 0; i < penaltys.length; i++) {
//				dos.writeBytes(this.penaltys[i]);	// 벌칙 배열의 i번째를 바이트로 읽어서 저장해라
//			}
		
		// BufferedWriter을 사용한 경우
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
			for(int i = 0; i < this.penaltys.length ; i++) {	// 패널티 길이만큼 반복
				bw.write(penaltys[i]);	// 패널티 i번째를 읽어서 저장
			}
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
		
	
	
	
}
