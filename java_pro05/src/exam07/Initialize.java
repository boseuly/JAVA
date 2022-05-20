package exam07;

public class Initialize {
	public int num1 = 0;
	public static String name1;
	
	public int num2 = 0;
	public static String name2 ="java";
	
	public int num3 = 0;
	public static String name3 = "programming";
	
	// 평소에는 초기화 블럭 사용할 필요 없음(자주 안 쓰임)
	{	num3 = num3 + 10; }
	
	static { name3 = name2 + " " + name3; }
	
}
