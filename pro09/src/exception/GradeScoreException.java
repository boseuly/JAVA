package exception;

public class GradeScoreException extends RuntimeException {	// unchecked는 처음에 빨간줄로 에러가 뜨지 않음 -> 동작하는 중간에 그냥 오류가 남
	public GradeScoreException() {							// 런타임 오류는 실행을 하다가 오류가 나도록 하는 것(처음부터 오류 나는 게 아님)
		super();
		
	}
	public GradeScoreException(String message) {
		super(message);
	}
}
