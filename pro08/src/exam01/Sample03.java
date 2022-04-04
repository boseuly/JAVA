package exam01;

public class Sample03 {

	public static void main(String[] args) {
		/*
		 * StringBuilder / StringBuffer
		 * - String은 불변의 특징을 가진다.
		 * 	 불변 : 문자열의 문자값을 변경할 수 없다. -> 새로운 문자열로 만들어서 변경된 것처럼 썼을 뿐이다.
		 * - StringBuilder, StringBuffer는 가변(Mutable)의 특징을 가진다.
		 * - StringBulder와 StringBuffer의 기능은 동일하다. 단, 멀티쓰레드 관련 기능 중 Thread Safe 기능에 대해
		 * 	 StringBuffer만 제공을 한다. (StringBulder가 성능이 좋은데 Thread Safe를 제공하지 않는다는 단점이 있음)
		 * - Thread Safe 기능은 멀티쓰레드에서 발생할 수 있는 문제 중 공유자원에 대한 접근을 하나의 쓰레드만 접근하여
		 * 	 사용하도록 락을 걸어 다른 쓰레드가 작업을 하지 못 하게 막아주는 기능이다.
		 * 
		 */
		/*
		 * Concat과 +연산자는 같은 문자열을 합치는 기능을 합니다. 
		 * 하지만 동작하는 방식이 다른데요. Concat은 합친 문자열을 String으로 생성해줍니다. 
		 * 하지만 + 연산자는 문자열을 먼저 StringBuilder로 변환시킨 뒤, Append로 문자열을 더하고
		 *  다시 toString함수로 문자열로 반환해주는 방식입니다.
		 */
		
		StringBuilder sb1 = new StringBuilder();
//		StringBuffer sb1 = new StringBuffer();

		// .append(String) 항상 마지막 위치에 추가 된다.
		sb1.append("문자열");
		sb1.append(" 추가");
		System.out.println(sb1);
		
		// .insert(offset, string)는 원하는 위치에 추가해줄 수 있다.
		sb1.insert(0, "StringBuilder ");
		System.out.println(sb1);
		sb1.insert(14, "클래스 : ");
		System.out.println(sb1);
		
		// .indexOf(string) 인덱스 위치 알려주는 것
		int idx = sb1.indexOf("Builder");
		System.out.println("Builder 문자열 위치 -> " + idx);
		
		// .charAt(index)
		for(int i = 0; i < sb1.length(); i++) {
			System.out.println("StringBuilder에서" + i + "번째 문자만 추출 -> " + sb1.charAt(i));
		}
		
		// .replace(start, end, string)
		System.out.println(sb1);
		sb1.replace(14, 17, "Class");	// 범위를 지정해서 수정
		System.out.println(sb1);
		
		// .deleteCharAt(index) 또는 .delete(start, end)
		sb1.deleteCharAt(20);
		System.out.println(sb1);
		
		sb1.delete(19, 24);
		System.out.println(sb1);
		
		// .setCharAt(index, char)
		sb1.setCharAt(14, 'c');
		System.out.println(sb1);
		
		
	}

}
