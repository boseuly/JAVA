package exception;

public class GradeException extends RuntimeException {	// Excpetion은 강제 한는 성격이 큼
	public GradeException() {
		super();
	}
	public GradeException(String message) {
		super(message);
	}

}
