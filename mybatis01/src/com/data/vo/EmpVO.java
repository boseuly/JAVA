package com.data.vo;

public class EmpVO {
	// vo 만들 때 멤버 변수명을 db 컬럼명과 동일하게 지어주기!!!! 안 그럼 매치를 못 시켜서 null 만 나온다.
	// mybatis가 컬럼명에 맞는 변수명을 찾아서 정보를 가지고 오고, 전달해주는데 이름이 다르면 매칭이 안 됨. 
	// 단, select 할 때 변수명으로 별칭을 지어주면 되긴 함.
	// 자바에서 사용하는 멤버변수를 기준으로 만들 거면 변수명을 기준으로 하고, db의 컬럼명을 기준으로 만들거면 컬럼명을 기준으로 한다.
		private  int employee_id; 
		private String first_name;
		private String last_name;
		
		public int getEmployee_id() {
			return employee_id;
		}
		public void setEmployee_id(int employee_id) {
			this.employee_id = employee_id;
		}
		public String getFirst_name() {
			return first_name;
		}
		public void setFirst_name(String first_name) {
			this.first_name = first_name;
		}
		public String getLast_name() {
			return last_name;
		}
		public void setLast_name(String last_name) {
			this.last_name = last_name;
		}
}
