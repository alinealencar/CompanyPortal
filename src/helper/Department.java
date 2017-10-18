package helper;

public class Department {

	private String deptName;
	private String deptLoc;
	
	private Department (String deptName, String deptLoc){
		this.deptName = deptName;
		this.deptLoc = deptLoc;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptLoc() {
		return deptLoc;
	}

	public void setDeptLoc(String deptLoc) {
		this.deptLoc = deptLoc;
	}
	
	
	
}
