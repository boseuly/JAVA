package exam03;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Sample02 {

	public static void main(String[] args) {
		String userHome = System.getProperty("user.home");
		
		Properties prop = new Properties();
		
		try {
			prop.load(new FileInputStream(new File(userHome + "/eclipse/jee-2021-12/eclipse/configuration/config.ini")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 	
		System.out.println(prop);
		System.out.println(prop.get("eclipse.product"));			// Properties.get()은 Object를 요구한다.
		System.out.println(prop.getProperty("eclipse.product"));	// Properties.getProperty()는 String을 요구한다.
		
		
		prop.put("x", "100"); 	// 키랑 값 넣어주기	// 얘는 문자열만 취급을 하기 때문에 문자열로만 써줘야 한다. Object를 받아서 그런 듯
		prop.put("Properties", "content");
		
		// 파일에 내용추가 하기	// (FileOutputStream)byte기반인데 한글로 작성해서 파일에서는 외계어임 -> FileWriter사용하면 됨
		String newPath = "/eclipse/jee-2021-12/eclipse/configuration/config.copy";
		try {
			prop.store(new FileWriter(new File(userHome + newPath)), "Comment Write");	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
