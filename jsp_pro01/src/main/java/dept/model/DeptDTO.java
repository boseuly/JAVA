package dept.model;

// 오라클 db의 departments테이블 
public class DeptDTO {
		private int deptId;
		private String deptName;
		private int mngId;
		private int locationId;
		
		public int getDeptId() {
			return deptId;
		}
		public void setDeptId(int deptId) {
			this.deptId = deptId;
		}
		public String getDeptName() {
			return deptName;
		}
		public void setDeptName(String deptName) {
			this.deptName = deptName;
		}
		public int getMngId() {
			return mngId;
		}
		public void setMngId(int mngId) {
			this.mngId = mngId;
		}
		public int getLocationId() {
			return locationId;
		}
		public void setLocationId(int locationId) {
			this.locationId = locationId;
		}
		@Override
		public String toString() {
			return "DeptDTO [deptId=" + deptId + ", deptName=" + deptName + ", mngId=" + mngId + ", locationId="
					+ locationId + "]";
		}
}
