package exam03;

import java.io.Serializable;

public class ObjSample implements Serializable {	
	// 읽고 쓰기 위해서는 Serializable(직렬화 기능)을 implements해줘야 한다.
	// -> 프로그램에서 만들어진 객체를 파일에 저장할 때(바이트 데이터로 만들 때) 직렬화 과정이 필요하다.
	private int num;
	private double point;
	private boolean ys;
	private String name;
	
	public ObjSample(int i, double d, boolean b, String s) {
		num = i;
		point = d;
		ys = b;
		name = s;
		
	}

	@Override
	public String toString() {
		return "ObjSample [num=" + num + ", point=" + point + ", ys=" + ys + ", name=" + name + "]";
	}
	
}
