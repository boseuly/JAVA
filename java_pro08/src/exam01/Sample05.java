package exam01;

public class Sample05 {

	public static void main(String[] args) {
		/*
		 * Wrapper 클래스
		 * - 기본 자료형을 객체로 만들어서 객체로 다룰 수 있게 해주는 클래스(원래는 기본 자료형인데 객체로 감싸준다고 생각)
		 * - 문자열 객체를 Wrapper 클래스로 변환하여 사용할 수 있게 된다.
		 * 	 예) 사용자 입력을 문자열로 받고 그것을 기본자료형으로 변경할 수 있다.
		 * 
		 * Boolean, Byte, Character, Short, Integer, Long, Float, Double -> 모두 Wrapper 클래스에 해당
		 * 
		 */
		
		// 문자열을 기본자료형으로 변경하기(Wrapper 사용)
		boolean b1 = Boolean.parseBoolean("true");	
		byte b2 = Byte.parseByte("10");
		short s1 = Short.parseShort("20");
		char c1 = "문자열".charAt(0);
		int i1 = Integer.parseInt("30");		// parseInt : 문자열로 된 부분에서 정수만 추출해서 변환해주는 것
		long l1 = Long.parseLong("40");
		float f1 = Float.parseFloat("50.5");
		double d1 = Double.parseDouble("60.6");
		
		// 기본 자료형을 문자열 변경(기존 방식)
		String s2 = new String("" + 10);
		s2 = new String("" + true);
		
		// 기본 자료형을 문자열 변경(Wrapper 사용)
		String s3 = Integer.valueOf(10).toString();	// valueOf(10)을 Integer 형태로 변환해서 문자열로 만들어라	
		s3 = Boolean.valueOf(true).toString();
		
	}

}
