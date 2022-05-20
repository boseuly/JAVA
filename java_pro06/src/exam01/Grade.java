package exam01;

public class Grade {
	private String name;
	private double score;
	private char rank;
	private final static char[] RANK = new char[] {
			'F','F','F','F','E','E','D','C','B','A','A'
	};
	
	public Grade(String name) {
		this.name = name;
	}
	
	public Grade(String name, double score) {
		this.name = name;
//		this.score = score;
//		this._setRank();           //setScore에 이미 this._setRank()가 있기 때문에 중복돼서 
		this.setScore(score);      //그냥 setScore 한 번으로 끝낼 수 있음(이걸 재사용했다고 함)
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getScore() {
		return score;
	}
	
	public void setScore(double score) {
		if(score <= 100 && score >=0) {
			this.score = score;
		}
		this._setRank();    //원래는 여기에 그냥 등급 매기는 식을 써도 되는데 setRank로 분리해줌 
						   // -> setScore에 setRank를 호출해준다
	}
	
	public char getRank() {
		return rank;
	}
	
	private void _setRank() {
		/*
		 * 점수가 설정되면 여기에 등급을 분류하기 위한 코드를 작성(rank의 setter를 안 만드는 이유임)
		 * A~F까지 점수 매기기
		 * 
		 */
		//방법1) 
//		if(this.score <= 100 && this.score >= 90) {
//			this.rank = 'A';
//		}else if(this.score < 90 && this.score >= 80) {
//			this.rank = 'B';
//		}else if(this.score < 80 && this.score >= 70) {
//			this.rank = 'C';
//		}else if(this.score < 70 && this.score >= 60) {
//			this.rank = 'D';
//		}else if(this.score < 60 && this.score >= 40) {
//			this.rank = 'E';
//		}else {
//			this.rank = 'F';
//		}
	
	
	//방법 2)
//	아래부터 0,1,2,3,4,5,6,7,8,9,10 이라는 몫값을 인덱스 값으로 설정 -> 그 인덱스에 각 등급을 넣어주면 됨(위에 식처럼 사용할 수 있음)
//	switch((int)(this.score/10)){
//	case 10:     case 9:
//		this.rank = 'A';	break;
//	case 8:
//		this.rank = 'B';	break;
//	case 7:
//		this.rank = 'C';	break;
//	case 6:
//		this.rank = 'D';	break;
//	case 5:		case 4:
//		this.rank = 'E';	break;
//	case 3:		case 2:		case 1:		case 0:
//		this.rank = 'F';	break;

		
//		switch결과로 나온 식 
		this.rank = Grade.RANK[(int)(this.score/10)];
	}
	
	
}
