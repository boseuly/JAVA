package exam01;

public class Run {

	public static void main(String[] args) {
		Lotto l = new Lotto();
		l.generate();
		System.out.println("-----수정후-----");
		l.setMin(4);
		l.setMax(67);
		l.setCount(8);
		l.generate();
	}

}
