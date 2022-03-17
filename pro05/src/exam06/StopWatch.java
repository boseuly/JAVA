package exam06;

public class StopWatch {
/*	
	+------------------------+	
	|	exam06.StopWatch     |		
	+------------------------+	
	| - seconds : int 		 |
	| - minutes : int 		 |
	| - hours	: int  		 |
	+------------------------+	
	| + start()	: void		 |	
	| + pause()	: void		 |
	| + stop() 	: void		 |
    | + reset()	: void		 |
	+------------------------+	
	
		
*/		
	private int hour;
	private int minute;
	private int second;
	
	public void setHour(int h) {		//setter
		hour = h;
	}
	public int getHour() {				//getter
		return hour;
	}
	public void start() {}
	public void stop() {}
	public void pause() {}
	public void reset() {}
		

}
