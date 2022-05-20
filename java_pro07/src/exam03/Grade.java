package exam03;

import java.util.Objects;

public class Grade extends Score {
	// 점수와 등급 정보를 가지는 객체
	private char level;
	
	public Grade(double point) {	// 다른 곳에서 사용하기 위해 만든 생성자 
		setPoint(point);	// point는 Score 멤버변수이기 때문에 근데 private라서 setter로 접근
	}
	
	//등급 구하는 메소드
	private void _setLevel() {
		char[] rank = new char[] {
				'F','F','F','F','E','E','D','C','B','A','A'
		};
		this.level = rank[(int)(this.getPoint() / 10)];
	}
	
	@Override
	public void setPoint(double point) {	//부모한테 점수 설정하게 하고 자식에서는 등급이 매기기
		super.setPoint(point);
		this._setLevel();		// 위에 있는 메소드 호출
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(level);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))		// 부모 클래스의 equals메소드에 obj를 넣은 것
			return false;
		if (getClass() != obj.getClass())		// 얘넨 둘이 같은 클래스를 참조하고 있는지 확인해주기
			return false;
		Grade other = (Grade) obj;
		return level == other.level;
	}

	@Override
	public String toString() {
		return "Grade [Point()=" + getPoint() + ", level=" + level + "]";
	}

	public char getLevel() {
		return level;
	}
	

	public void setLevel(char level) {
		this.level = level;
	}
	
	
	
}
