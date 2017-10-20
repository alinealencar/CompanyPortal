package dataModel;

public class Department {

	private String deptName;
	private String deptLoc;
	private int id;
	
	private Department (String deptName, String deptLoc, int id){
		this.deptName = deptName;
		this.deptLoc = deptLoc;
		this.id = id;
	}
	
	private Department (String deptName, int id){
		this.deptName = deptName;
		this.deptLoc = deptLoc;
		this.id = id;
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
	
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	
}
