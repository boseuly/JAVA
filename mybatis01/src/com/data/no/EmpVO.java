package com.data.no;

public class EmpVO {
		private  int employee_id; // db의 컬럼명과 똑같이 설정하기
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
